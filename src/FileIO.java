import javax.imageio.IIOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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

    public static void saveData(ArrayList<String> data, String path, String header){
        //genneløb data
        try {
            FileWriter writer = new FileWriter(path);
            writer.write(header);

            for (String s : data) {
                writer.write(s+"\n");
            }
            writer.close();
        }catch (IOException e ){
            System.out.println("data blev ikke gemt");
        }
        //for hver linje skal vi write to file (path)



    }
}





