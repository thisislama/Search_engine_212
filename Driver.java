//import java.util.LinkedList;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Driver {
  LinkedList<String> stopWords;
  Index ind1;
  Inverted inverted;
  public Driver()
  {
  stopWords= new LinkedList<>();
  ind1= new Index();
  inverted= new Inverted();
  }
  public void LoadStopWords(String fileName)
  {
  try {
    File f=new File(fileName);
      Scanner s=new Scanner(f);
      while(s.hasNextLine() )
        {
          String line=s.nextLine();
          stopWords.insert(line);
  }
  }
    catch(IOException e)
    {
      e.printStackTrace();
    }}
  
    public void LoadAllDoc(String fileName)
    {
      String line=null;
      try {
        File f=new File(fileName);
        Scanner s=new Scanner(f);
        s.nextLine();
        while(s.hasNextLine()){
          line=s.nextLine();
          if(line.trim().length()<3)
          {
            System.out.println("Empty line");
            break;
          }
          String x=line.substring(0,line.indexOf(','));
          int id=Integer.parseInt(x.trim());
          String content=line.substring(line.indexOf(',')+1).trim();
          LinkedList<String> wordsInDoc=MakeLinkedListOfWordsInDocIndexInvertedIndex(content,id);
          ind1.addDoc(new Document(id,wordsInDoc));
        }
        
      }catch(IOException e){
        System.out.println("Empty of file");
    }
    }
  public LinkedList<String> MakeLinkedListOfWordsInDocIndexInvertedIndex(String content,int id)
  {
   LinkedList<String>wordsInDoc=new LinkedList<String>();
    MakeIndexAndInvertedIndex(content,wordsInDoc,id);
    return wordsInDoc;
  }
  public void MakeIndexAndInvertedIndex(String content,LinkedList<String>wordsInDoc,int id)
  {
    content=content.toLowerCase().replaceAll("[^a-zA-Z0-9 ]","");
    String[] tokens=content.split("\\s+");
    for(String w:tokens){
      if(!existIn_stop_words(w)){
      wordsInDoc.insert(w);
        inverted.add(w,id);
      }
    }
  }
  public boolean existIn_stop_words(String word)
  {
    if(stopWords==null || stopWords.isEmpty())
      return false;
    stopWords.findFirst();
    while(!stopWords.last()){
      if(stopWords.retrieve().equals(word)){
        return true;
      }
      stopWords.findNext();
    }
     if(stopWords.retrieve().equals(word)){
       return true;
     }
      return false;
  }
  public void LoadAllFiles(String stopFile,String DocsFile)
  {
    LoadStopWords(stopFile);
    LoadAllDoc(DocsFile);
  }
  public static void main(String[]args)
  {
    Driver d= new Driver();
    d.LoadAllFiles("stop.txt","dataset.csv");
    d.ind1.displayDocs();
    System.out.println("\n----------------------------");
    d.inverted.diaplay_InvertedList();
  }
  public void displaystopWords(){
    stopWords.display();
  }
}

public static void display_menu(){
  System.out.println("1- Retrieve a term (there are choices"
  +":using index with lists"
  +"-inverted index with lists"
  +"-inverted index with BST.");
  System.out.println("2- Boolean Retrieval.");
  System.out.println("3- Ranking Retrieval.");
  System.out.println("4- Indexed Documents: print all document.");
  System.out.println("5- Number of Documents in the index.");
  System.out.println("6- Number of unique words in indexed.");
  System.out.println("7- Show inverted index with list of lists.");
  System.out.println("8- Show inverted index with BST.");
  System.out.println("9- Indexed tokens: to show number of vocabulary and tokens in the index.");
}
