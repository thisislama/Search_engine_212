import java.util.Scanner;
import java.io.*;

public class ReadDataset {

    public static void main(String [] args){

        Load("\\csc212\\dataset.csv");

    }

    public static void Load(String filename){

        String line = null;
        try{

            File f = new File(filename);
            Scanner sc = new Scanner(f);

            sc.nextLine();
            while (sc.hasNextLine()){

                line = sc.nextLine();
                if (line.trim().length()< 3){
                    break;
                }
                System.out.println(line);
                String id = line.substring(0, line.indexOf(','));
                int idd = Integer.parseInt(id.trim());
                String sentence = line.substring(line.indexOf(',') + 1).trim();

            }



        } catch (Exception e) {
            System.out.println("End Of File");
        }

    }
}
