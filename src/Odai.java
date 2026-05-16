import java.util.ArrayList;

public class Odai {

    public static void main(String[] args) {

        String[] chosenOdai = new String[4];

        while (true) {
            showMenu();
            System.out.print("メニュー番号を入力してください！ : ");
            int next = new java.util.Scanner(System.in).nextInt();

            if (next == 1) {
                chooseOdai(chosenOdai);
                showOdai(chosenOdai);
                System.exit(0);

            } else if (next == 2) {
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

    }

    public static void chooseOdai(String[] chosenOdai) {
        String[] gender = {"男性", "女性"};
        String[] color = {"赤", "橙", "黄", "緑", "水", "青", "紫", "白", "黒"};
        String[] zoom = {"アップ", "ヒキ", "バストアップ"};
        String[] parse = {"正面", "フカン", "アオリ"};

        int rge = new java.util.Random().nextInt(2);
        chosenOdai[0] = gender[rge];

        int rco = new java.util.Random().nextInt(9);
        chosenOdai[1] = color[rco];

        int rzo = new java.util.Random().nextInt(3);
        chosenOdai[2] = zoom[rzo];

        int rpa = new java.util.Random().nextInt(3);
        chosenOdai[3] = parse[rpa];

    }

    public static void showOdai(String[] chosenOdai) {

        System.out.println("今日は、" + chosenOdai[1] + "色をテーマに、");
        System.out.println(chosenOdai[2] + "・" + chosenOdai[3] + "の構図で、");
        System.out.println(chosenOdai[0] + "を描いてみよう！");

    }
}
