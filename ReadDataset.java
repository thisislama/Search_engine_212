
import java.util.Scanner;
import java.io.*;

public class ReadDataset {

    public static void main(String [] args){
        //changed
        Load("C:data\\dataset.csv");
        Load("C:data\\stop.txt");
    }

    public static void Load(String filename){

        String line = null;
        try{

            File f = new File(filename);
            Scanner sc = new Scanner(f);
            //skip first line
            sc.nextLine();
            while (sc.hasNextLine()){

                line = sc.nextLine(); 
                if (line.trim().length()< 3){
                    System.out.println("Empty line, skipped");
                    break;
                }
                System.out.println(line);
                
                //splitting id and content
                String id = line.substring(0, line.indexOf(','));
                int idd = Integer.parseInt(id.trim());//convert to int from string
                String content = line.substring(line.indexOf(',') + 1).trim();

            }



        } catch (Exception e) {
            System.out.println("End Of File");
        }
        //read stop words?
    }
}
