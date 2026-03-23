import java.util.Scanner;

public class TextUI {

    static Scanner scan = new Scanner(System.in);

    public static int promptNumber(String msg) {
        System.out.println(msg);
        int input = 0;
        input = Integer.parseInt(scan.nextLine());
        // scan.nextLine();//scanner bug
        return input;
    }
    public static String promptText(String msg){
        System.out.println(msg);
        String input = scan.nextLine();
        return input;
    }




}
