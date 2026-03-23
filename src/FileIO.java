import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {


    public static ArrayList<String> readData(String path){

        ArrayList<String> data = new ArrayList<>();


        File file = new File(path);
        try {
            Scanner scan = new Scanner(file);
           // header = scan.nextLine();
            //parse data
            while(scan.hasNextLine()) {
                String line = scan.nextLine();// line = "1, Main Stage, 5000"

                data.add(line);
                        //udpakning

            }

        }catch (FileNotFoundException e){
            System.out.println("den sti var vist forkert");

        }

        return data;
    }

    public void saveData(){


    }
}





