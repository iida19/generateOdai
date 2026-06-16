package generateOdai;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Odai {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        List<String[]> odaiList = new ArrayList<String[]>();
        viewOdai(odaiList);
        
        String[] chosenOdai = new String[odaiList.size()];
        
        int savingSize = 3;
        int favoriteSize = 10;
        String savingPlace = "./data/history.csv";
        String favoritePlace = "./data/favorite.csv";
        List<String> savedOdai = readDocument( savingPlace, savingSize );
        List<String> favoriteOdai = readDocument( favoritePlace, favoriteSize );
        
        int count = 0;

        while (true) {
        	
            showMenu();
            String next = scanner.nextLine();

            if (next.equals("1")) {
            	
            	while (true) {
            		
            		generateOdai(scanner, random, chosenOdai, savedOdai, odaiList, savingSize);
            		count ++;
            		
            		String f = askFavorite(scanner);
            		if (f.equals("y")) {
            			favoriteOdai(scanner, chosenOdai, favoriteOdai, favoriteSize);
            		}
            		
            		int action = askNext(scanner, count);
            		
            		if (action == 1) {
            			continue;
            		} else if (action == 2) {
            			System.out.print("\n");
            			break;
            		} else if (action == 3) {
            			exitOdai(savingPlace, favoritePlace, savedOdai, favoriteOdai);
            		}
            		
            	}
            	
            } else if (next.equals("2")) {
            	
            	System.out.println("\n最新3件の履歴を表示します！");
            	showDocument(savedOdai);
            	System.out.println("履歴は以上です！\n");
            	
            	while (true) {
            		
            		int action = askNext(scanner, count);
        		
            		if (action == 1) {
            			generateOdai(scanner, random, chosenOdai, savedOdai, odaiList, savingSize);
            			count ++;
            			
            			String f = askFavorite(scanner);
                		if (f.equals("y")) {
                			favoriteOdai(scanner, chosenOdai, favoriteOdai, favoriteSize);
                		}
                		
            		} else if (action == 2) {
            			System.out.print("\n");
            			break;
            			
            		} else if (action == 3) {
            			exitOdai(savingPlace, favoritePlace, savedOdai, favoriteOdai);
            		}
            		
            	}
            	
            } else if (next.equals("3")) {
            	System.out.println("\nお気に入り登録したお題一覧を表示します！");
            	showDocument(favoriteOdai);
            	System.out.println("お気に入りは以上です！\n");
            	
            	while (true) {
            		
            		int action = askNext(scanner, count);
        		
            		if (action == 1) {
            			generateOdai(scanner, random, chosenOdai, savedOdai, odaiList, savingSize);
            			
            			String f = askFavorite(scanner);
                		if (f.equals("y")) {
                			favoriteOdai(scanner, chosenOdai, favoriteOdai, favoriteSize);
                		}
                		
            		} else if (action == 2) {
            			System.out.print("\n");
            			break;
            			
            		} else if (action == 3) {
            			exitOdai(savingPlace, favoritePlace, savedOdai, favoriteOdai);
            		}
            		
            	}

            } else if (next.equals("4")) {
            	exitOdai(savingPlace, favoritePlace, savedOdai, favoriteOdai);

            } else {
                System.out.println("番号で入力してください！\n");
            }
            
        }

    }
    
    
    
    public static List<String> readDocument( String fileName, int size ) {
    	
    	File fn = new File( fileName );
    	List<String> lists = new LinkedList<String>();
    	BufferedReader br = null;
    	
    	if ( fn.exists() ) {
    		
    		try {
    			
    			br = new BufferedReader( new FileReader( fn ) );
    			
    			String line;
    			int i = 0;
    			while ( ( line = br.readLine() ) != null && i < size ) {
    				lists.add(line);
    				i ++;
    			}
    			
    		} catch ( IOException e ) {
    			e.printStackTrace();
    			
    		} finally {
    			
    			if ( br != null ) {
    				
    				try {
    					br.close();
    				} catch ( IOException e ) {
    					e.printStackTrace();
    				}
    				
    			}
    			
    		}
    		
    	}
    	
    	return lists;
    	
    }

    public static void showMenu() {

        System.out.println("今日のお題を作ります！");
        System.out.println("1. お題生成");
        System.out.println("2. 履歴確認");
        System.out.println("3. お気に入り確認");
        System.out.println("4. 終了");
        System.out.print("メニュー番号を入力してください！ : ");

    }
    
    public static void generateOdai(Scanner scanner, Random random, String[] chosenOdai, List<String> savedOdai, List<String[]> odaiList, int size) {
    	
    	int d = difficultyChoice(scanner);
		chooseOdai(d, random, chosenOdai, odaiList);
		showOdai(d, chosenOdai);
		saveOdai(chosenOdai, savedOdai, size);
    	
    }
    
    public static int difficultyChoice(Scanner scanner) {
    	
    	while ( true ) {
    		
    		System.out.println("\n難易度はどうしますか？");
    		System.out.println("1. かんたん");
    		System.out.println("2. ふつう");
    		System.out.println("3. むずかしい");
    		System.out.println("4. とてもむずかしい");
    		System.out.print("選んでください！：");
    	
    		String dif = scanner.nextLine();
    		if ( dif.matches("[1-4]") ) {
    			return Integer.parseInt( dif );
    		} else {
    			System.out.println("選択肢の番号を入力してください！");
    		}
    		
    	}	
		
	}
    
    public static void viewOdai(List<String[]> odaiList) {
    	
    	String fileName = "./data/odai.csv";
        File odaiFile = new File(fileName);
        BufferedReader br = null;
        
        if (odaiFile.exists()) {
        	
        	try {
        		
        		br = new BufferedReader(new FileReader(odaiFile));
        	
        		String odai;
        		while ((odai = br.readLine()) != null) {
        		
        			if (odai.contains(",")) {
        				String[] odais = odai.split(",");
        				odaiList.add(odais);
        			}	
        		
        		}
        		
        	} catch ( IOException e ) {
        		
        		e.printStackTrace();
        		
        	} finally {
        		
        		if ( br != null ) {
        			
        			try {
        				br.close();
        			} catch ( IOException e ) {
        				e.printStackTrace();
        			}
        			
        		}
        		
        	}	
        	
        }
    	
    }

    public static void chooseOdai(int dif, Random random, String[] chosenOdai, List<String[]> odaiList) {
        
        for ( int i = 0; i < odaiList.size(); i ++ ) {
        	chosenOdai[i] = null;
        }
        
        int level = 0;
        for ( int i = 0; i < odaiList.size(); i ++ ) {
        	
        	int r = random.nextInt( ( odaiList.get( i ) ).length );
        	chosenOdai[i] = ( odaiList.get( i ) )[ r ];
        	level ++;
        	
        	if ( dif == 1 && level >= 2 ) {
        		break;
        	} else if ( dif == 2 && level >= 4 ) {
        		break;
        	} else if ( dif == 3 && level >= 5 ) {
        		break;
        	}
        	
        }

    }

    public static void showOdai(int dif, String[] chosenOdai) {

        System.out.print("\n");         // 改行
        System.out.println("今日は、" + chosenOdai[1] + "色をテーマに、");
        
        if ( dif == 2 ) {
        	System.out.println(chosenOdai[2] + "の距離感で、" + chosenOdai[3] + "表情の" );
        }
        
        if ( dif == 3 || dif == 4 ) {
        	System.out.println(chosenOdai[2] + "・" + chosenOdai[4] + "の構図で、" + chosenOdai[3] + "表情の" );
        }
        
        System.out.println(chosenOdai[0] + "を描いてみよう！");
        
        if ( dif == 4 ) {
        	
        	System.out.print("エクストラお題：");
        	String extra = "";
        	for ( int i = 5; i < chosenOdai.length; i ++ ) {
        		extra += chosenOdai[i] + "/";
        	}
        	extra = extra.substring(0, extra.length()-1 );
        	System.out.println(extra);
        	
        }	
        
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
    
    public static void saveOdai(String[] chosenOdai, List<String> documents, int size) {
    	
    	String s = makeOdaiText(chosenOdai);
    	
    	if (documents.size() < size) {
    		documents.add(s);
    	} else if (documents.size() >= size) {
    		documents.remove(0);
    		documents.add(s);
    	}
    	
    }
    
    public static String askFavorite(Scanner scanner) {
    	
    	while (true) {
    		
    		System.out.print("このお題をお気に入りに登録しますか？(y/n)：");
    		String ans = scanner.nextLine();
    		System.out.print("\n");
    	
    		if (ans.equals("y") || ans.equals("n")) {
    			return ans;
    		} else {
    			System.out.println("yかnで入力してください！");
    		}
    		
    	}	
    	
    }
    
    public static void favoriteOdai(Scanner scanner, String[] chosenOdai, List<String> documents, int size) {
    	
    	String s = makeOdaiText(chosenOdai);
    	
    	if (documents.size() < size) {
    		documents.add(s);
    		System.out.println("お気に入りに登録しました！");
    		
    	} else if (documents.size() >= size) {
    		
    		System.out.println("お気に入りの枠がいっぱいです！消す項目の番号を入力してください！");
    		for (int i = 0; i < documents.size(); i ++) {
    			System.out.println( ( i + 1 ) + ". " + documents.get(i) );
    		}
    		
    		String doc = "";
    		int d;
    		
    		while (true) {
    		
    			System.out.print("消す項目の番号(今出たお題のお気に入り登録をキャンセルするなら0)：");
    			doc = scanner.nextLine();
    			
    			if ( doc.matches("\\d+")) {
    				d = Integer.parseInt(doc) - 1;
    				
    				if ( d >= 0 && d < documents.size() ) {
        				documents.remove( d );
        				documents.add(s);
        				System.out.println(doc + "番を消去して、このお題をお気に入りに登録しました！\n");
        				break;
        			
        			} else if ( d == ( -1 ) ) {
        				System.out.println("登録をキャンセルしました！\n");
        				break;
        			} else {
        				System.out.println("一覧の番号の中から選んでください！\n");
        			}
    				
    			} else {
    				System.out.println("番号の数字を入力してください！\n");
    			}
    			
    		}	
    		
    	}
    	
    }
    
    public static void keepDocument( List<String> documents, String fileName ) {
    	
    	PrintWriter pw = null;
    	
    	try {
    		
    		pw = new PrintWriter( new FileWriter( fileName ) );
    		
    		if ( documents.get(0) != null ) {
    			
    			for ( String s : documents ) {
    				
    				if ( s != null ) {
    					pw.println( s );
    				}
    				
    			}
    			
    		}	
    		
    	} catch ( IOException e ) {
    		
    		e.printStackTrace();
    		
    	} finally {
    		
    		if ( pw != null ) {
    			pw.close();
    		}
    		
    	}
    	
    }
    
    public static void showDocument(List<String> documents) {
    	
    	if ( documents.get(0) == null ) {
    			System.out.println("まだないよ");
    	} else {
    		for ( String s : documents ) {
    			System.out.println(s);
    		}
    	}
    	
    }

    public static int askNext(Scanner scanner, int count) {

    	while ( true ) {
    	
    		if (count == 0) {
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
    
    public static void exitOdai( String savingPlace, String favoritePlace, List<String> savedOdai, List<String> favoriteOdai ) {
    	
    	keepDocument( savedOdai, savingPlace );
		keepDocument( favoriteOdai, favoritePlace );
		System.out.println("\n終了します！");
		System.exit(0);
    	
    }
    
}
