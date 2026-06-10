package generateOdai;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Odai {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        List<String[]> odaiList = new ArrayList<String[]>();
        viewOdai(odaiList);
        
        String[] chosenOdai = new String[odaiList.size()];
        String[] savedOdai = new String[3];

        while (true) {
        	
            showMenu();
            String next = scanner.nextLine();

            if (next.equals("1")) {
            	
            	while (true) {
            		
            		generateOdai(scanner, random, chosenOdai, savedOdai, odaiList);
            		
            		int action = askNext(scanner, savedOdai);
            		
            		if (action == 1) {
            			continue;
            		} else if (action == 2) {
            			System.out.print("\n");
            			break;
            		} else if (action == 3) {
            			System.out.println("\n終了します！");
            			System.exit(0);
            		}
            		
            	}
            	
            } else if (next.equals("2")) {
            
            	showHistory(savedOdai);
            	
            	while (true) {
            		
            		int action = askNext(scanner, savedOdai);
        		
            		if (action == 1) {
            			generateOdai(scanner, random, chosenOdai, savedOdai, odaiList);
            		} else if (action == 2) {
            			System.out.print("\n");
            			break;
            		} else if (action == 3) {
            			System.out.println("\n終了します！");
            			System.exit(0);
            		}
            		
            	}	

            } else if (next.equals("3")) {
            	System.out.println("\n終了します！");
                System.exit(0);

            } else {
                System.out.println("それはメニュー番号じゃないよ！");
            }
            
        }

    }
    
    

    public static void showMenu() {

        System.out.println("今日のお題を作ります！");
        System.out.println("1. お題生成");
        System.out.println("2. 履歴確認");
        System.out.println("3. 終了");
        System.out.print("メニュー番号を入力してください！ : ");

    }
    
    public static void generateOdai(Scanner scanner, Random random, String[] chosenOdai, String[] savedOdai, List<String[]> odaiList) {
    	
    	int d = difficultyChoice(scanner);
		chooseOdai(d, random, chosenOdai, odaiList);
		showOdai(d, chosenOdai);
		saveOdai(chosenOdai, savedOdai);
    	
    }
    
    public static int difficultyChoice(Scanner scanner) {
    	
    	while ( true ) {
    		
    		System.out.println("\n難易度はどうしますか？");
    		System.out.println("1. むずかしい");
    		System.out.println("2. ふつう");
    		System.out.println("3. かんたん");
    		System.out.print("選んでください！：");
    	
    		String dif = scanner.nextLine();
    		if ( dif.matches("[1-3]") ) {
    			return Integer.parseInt( dif );
    		} else {
    			System.out.println("選択肢の番号を入力してください！");
    		}
    		
    	}	
		
	}
    
    public static void viewOdai(List<String[]> odaiList) throws IOException {
    	
    	String fileName = "./data/odai.csv";
        File odaiFile = new File(fileName);
        
        if (odaiFile.exists()) {
        	
        	BufferedReader br = new BufferedReader(new FileReader(odaiFile));
        	
        	String odai;
        	while ((odai = br.readLine()) != null) {
        		
        		if (odai.contains(",")) {
        			String[] odais = odai.split(",");
        			odaiList.add(odais);
        		}	
        		
        	}
        	
        	br.close();
        	
        }
    	
    }

    public static void chooseOdai(int dif, Random random, String[] chosenOdai, List<String[]> odaiList) {
    	
        String[] gender = odaiList.get(0);
        String[] color = odaiList.get(1);
        String[] zoom = odaiList.get(2);
        String[] angle = odaiList.get(3);
        
        for (int i = 0; i < odaiList.size(); i++) {
            chosenOdai[i] = null;
        }

        int rge = random.nextInt(gender.length);
        chosenOdai[0] = gender[rge];

        int rco = random.nextInt(color.length);
        chosenOdai[1] = color[rco];

        if ( dif == 1 || dif == 2) {
        	int rzo = random.nextInt(zoom.length);
        	chosenOdai[2] = zoom[rzo];
        }	
        
        if ( dif == 1 ) {
        	int rpa = random.nextInt(angle.length);
        	chosenOdai[3] = angle[rpa];
        }	

    }

    public static void showOdai(int dif, String[] chosenOdai) {

        System.out.print("\n");         // 改行
        System.out.println("今日は、" + chosenOdai[1] + "色をテーマに、");
        
        if ( dif == 2 ) {
        	System.out.println(chosenOdai[2] + "の距離感で、");
        }
        
        if ( dif == 1 ) {
        	System.out.println(chosenOdai[2] + "・" + chosenOdai[3] + "の構図で、");
        }
        
        System.out.println(chosenOdai[0] + "を描いてみよう！");
        System.out.print("\n");         // 改行

    }
    
    public static String makeOdaiText(String[] chosenOdai) {
    	
    	String s = "";
    	
    	for (int i = 0; i < chosenOdai.length; i ++ ) {
			if ( chosenOdai[i] != null ) {
				s += chosenOdai[i] + "/";
			} else {
				break;
			}
		}
		s = s.substring(0, s.length()-1 );
		return s;
    	
    }
    
    public static void saveOdai(String[] chosenOdai, String[] savedOdai) {
    	
    	String s = makeOdaiText(chosenOdai);
    	int count = 0;
    	
    	for (int i = 0; i < savedOdai.length; i ++ ) {
    		
    		if ( savedOdai[i] == null ) {
    			savedOdai[i] = s;
    			break;
    		} else if ( savedOdai[i] != null ) {
    			count ++;
    		}
    		
    	}	
    			
    	if ( count == savedOdai.length ) {
    		savedOdai[0] = savedOdai[1];
    		savedOdai[1] = savedOdai[2];
    		savedOdai[2] = s;
    		
    	}
    	
    }
    
    public static void showHistory(String[] savedOdai) {
    	
    	System.out.println("\n最新3件の履歴を表示します！");
    	
    	for ( int i = 0; i < savedOdai.length; i ++ ) {
    		if ( savedOdai[i] != null ) {
    			System.out.println( savedOdai[i] );
    		} else if ( savedOdai[0] == null ) {
    			System.out.println("まだないよ");
    			break;
    		}
    	}
    	
    	System.out.println("履歴は以上です！\n");
    	
    }

    public static int askNext(Scanner scanner, String[] savedOdai) {

    	while ( true ) {
    	
    		if (savedOdai[0] == null) {
    			System.out.println("1. お題を生成");
    		} else {
    			System.out.println("1. お題を再生成");
    		}
    		System.out.println("2. メニューへ");
    		System.out.println("3. 終了");
    		System.out.print("次はどうしますか？: ");

    		String next = scanner.nextLine();
    		if (next.matches("[1-3]")) {
    			return Integer.parseInt(next);
    		} else {
    			System.out.println("メニュー番号を入力してください！");
    		}
    		
    	}	
        
    }
    
}
