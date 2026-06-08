package generateOdai;

import java.util.Random;
import java.util.Scanner;

public class Odai {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String[] chosenOdai = new String[4];

        while (true) {
        	
            showMenu();
            int next = scanner.nextInt();

            if (next == 1) {
            	
            	while (true) {
            		
            		chooseOdai(random, chosenOdai);
            		showOdai(scanner, random, chosenOdai);
            		
            		int action = askNext(scanner);
            		
            		if (action == 1) {
            			continue;
            		} else if (action == 2) {
            			System.out.print("\n");
            			break;
            		} else if (action == 3) {
            			System.out.println("\n終了します！");
            			System.exit(0);
            		} else {
                        System.out.println("それはメニュー番号じゃないよ！");
                    }
            		
            	}	

            } else if (next == 2) {
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
        System.out.println("2. 終了");
        System.out.print("メニュー番号を入力してください！ : ");

    }

    public static void chooseOdai(Random random, String[] chosenOdai) {
        String[] gender = {"男性", "女性"};
        String[] color = {"赤", "橙", "黄", "緑", "水", "青", "紫", "白", "黒"};
        String[] zoom = {"アップ", "ヒキ", "バストアップ"};
        String[] angle = {"正面", "フカン", "アオリ"};

        int rge = random.nextInt(gender.length);
        chosenOdai[0] = gender[rge];

        int rco = random.nextInt(color.length);
        chosenOdai[1] = color[rco];

        int rzo = random.nextInt(zoom.length);
        chosenOdai[2] = zoom[rzo];

        int rpa = random.nextInt(angle.length);
        chosenOdai[3] = angle[rpa];

    }

    public static void showOdai(Scanner scanner, Random random, String[] chosenOdai) {

        System.out.print("\n");         // 改行
        System.out.println("今日は、" + chosenOdai[1] + "色をテーマに、");
        System.out.println(chosenOdai[2] + "・" + chosenOdai[3] + "の構図で、");
        System.out.println(chosenOdai[0] + "を描いてみよう！");
        System.out.print("\n");         // 改行

    }

    public static int askNext(Scanner scanner) {

    	System.out.println("1. お題を再生成");
        System.out.println("2. メニューへ");
        System.out.println("3. 終了");
        System.out.print("次はどうしますか？: ");

        return scanner.nextInt();
        
    }
}
