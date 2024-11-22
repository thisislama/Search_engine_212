
import java.io.File;
import java.io.IOException;
import java.util.*;

public class driver {
  
  LinkedList<String> stopWords;
  static Index index1;
  Inverted inverted;//invert index
  InvertedIndexBST invertedBST;
  int num_token = 0;
  LinkedList<String> uniqueWords = new LinkedList<>();
  
  public driver(){
    
  stopWords= new LinkedList<>();
  index1= new Index();
  inverted= new Inverted();
  invertedBST = new InvertedIndexBST();
  }
  
  public void LoadStopWords(String fileName){
    //reads stop words and add them to the String LinkedList
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
  
  
  //reads documents from file
    public void LoadAllDoc(String fileName)
    {
      String line=null;
      try {
        File f=new File(fileName);
        Scanner s=new Scanner(f);
        //skip first line
        s.nextLine();
        while(s.hasNextLine()){
          line=s.nextLine();
          
          if(line.trim().length()<3)
          {
            System.out.println("Empty line"+line);
            break;
          }
          
          String x=line.substring(0,line.indexOf(','));
          int id=Integer.parseInt(x.trim());
          System.out.println("line: "+line);
          String content=line.substring(line.indexOf(',')+1).trim();
          System.out.println("content: "+content);
          //stores every word in a linked list of strings 
          LinkedList<String> wordsInDoc=MakeLinkedListOfWordsInDocIndexInvertedIndex(content,id);
          //creates new Document object, adds it to Index adds to allDocs
          index1.addDoc(new Document(id,wordsInDoc, content));
        }
        
      }catch(IOException e){
        System.out.println("end of file");
    }
    }

  //linked list of words in Document, calls MakeIndexAndInvertedIndex
  public LinkedList<String> MakeLinkedListOfWordsInDocIndexInvertedIndex(String content,int id)
  {
   LinkedList<String>wordsInDoc=new LinkedList<String>();
    MakeIndexAndInvertedIndex(content,wordsInDoc,id);
    return wordsInDoc;
  }

  //cleans content and adds to array, inverted  , invertedBST 
  public void MakeIndexAndInvertedIndex(String content,LinkedList<String>wordsInDoc,int id)
  {
    content=content.toLowerCase().replaceAll("\'"," ");
    content=content.toLowerCase().replaceAll("-"," ");
    content=content.toLowerCase().replaceAll("[^a-zA-Z0-9 ]","");
    String[] tokens=content.split("\\s+");
    num_token += tokens.length;
    
    for(String w:tokens){
      if(!uniqueWords.exist(w)){
      uniqueWords.insert(w);
      }
      if (!existIn_stop_words(w)){ //if it doesn't exist in stop words --> add to words array , inverted index , inverted index BST
          wordsInDoc.insert(w);
          inverted.add(w,id);
          invertedBST.add(w , id);
      }
    }
  }

  //search in stop words
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


public void displayDocWithGivenIDS(LinkedList<Integer> IDs){
      
      if (IDs.isEmpty()){
          System.out.println("no documents exist displayDocWithGivenIDS");
          return;  
      }
      IDs.findFirst();
      while(!IDs.last()){
          
          Document d = index1.getDoc(IDs.retrieve());
          if (d != null)
               System.out.println("Document "+d.id+" : "+d.content);
          IDs.findNext();
      }
      Document d = index1.getDoc(IDs.retrieve());
      if (d != null)
               System.out.println("Document "+d.id+" : "+d.content);
       System.out.println("");
  }
  
  
  public void LoadAllFiles(String stopFile,String DocsFile)
  {
    LoadStopWords(stopFile);
    LoadAllDoc(DocsFile);
  }
  
  
  public static void main(String[]args)
  {
    driver d= new driver();
        
  
    d.LoadAllFiles("C:\\Users\\96650\\Downloads\\stop.txt","C:\\Users\\96650\\Downloads\\dataset.csv");
        Scanner s = new Scanner(System.in);
int ch=0;
    do{
  display_menu();
  ch=s.nextInt();
        switch(ch) {
          case 1:
            System.out.println("enter a term to retrieve");
            String term = s.next();
            term = term.toLowerCase().trim();
            System.out.println(": using indix with lists");
            LinkedList<Integer> res = driver.index1.getDocsGivenTerm(term);
            System.out.print("word:" + term + "[");
            res.display();
            System.out.print("}");
            System.out.print("-------------------------");
            System.out.print("- inverted index with lists");
            boolean found = d.inverted.Search_InvertedList(term);
            if (found)
              d.inverted.InvertList.retrieve().display();
            else
              System.out.println("not found in inverted index with lists");
            System.out.println("- inverted index with BST");
            boolean found2 = d.invertedBST.search_word_in_inverted(term);
            if (found2)
              d.inverted.InvertList.retrieve().display();
            else
              System.out.println("not found in inverted index with lists");
            break;

          case 2:
            s.nextLine();
            int x;
            do{
            System.out.println("enter a query to retrieve:");
            String query = s.nextLine();
            query = query.toLowerCase();
            query = query.replaceAll("and","AND");
            query = query.replaceAll("or","OR");
            System.out.println("\nwhich method do you want to retrieve:\n"
                    + "1- Index\n"
                    + "2- Inverted Index\n"
                    + "3- BST\n");
             x = s.nextInt();
            s.nextLine();
            //do{

              if (x == 1){
                QueryProssing_from_index q1 = new QueryProssing_from_index(driver.index1);
                System.out.println("====="+ query+"======");
                LinkedList res1 = q1.BooleanQuery(query);
                d.displayDocWithGivenIDS(res1);
              }
              else if (x == 2){
                QueryProcessing q2 = new QueryProcessing(d.inverted);
                System.out.println("====="+ query+"======");
                LinkedList res2 = q2.BooleanQuery(query);
                d.displayDocWithGivenIDS(res2);
              }
              else if (x == 3){
                QueryProcessing_BST q3 = new QueryProcessing_BST(d.invertedBST);
                System.out.println("====="+ query+"======");
                LinkedList res3 = q3.BooleanQuery(query);
                d.displayDocWithGivenIDS(res3);
              }
              else if (x > 3){
                  System.out.println("wrong choice");
                break;
              }
              else
                System.out.println("Wrong Query");

            }while(x!= 4);
            break;

            
            case 3:
              s.nextLine();
              System.out.println("Enter a query to Rank");
              String query2 = s.nextLine();
              query2 = query2.toLowerCase();
              Ranking R5= new Ranking(d.invertedBST, driver.index1,query2);
              R5.insert_sorted_in_list();
              R5.displayRanking();
              break;
              case 4:
                d.index1.displayDocs();
                System.out.println("---------------------");
                break;
                case 5:
                  System.out.println("Number of documents="+driver.index1.allDocs.n);
                  System.out.println("---------------------");
                  break;
                  case 6:
                    System.out.println("Number of unique words without stop words="+d.inverted.InvertList.n);
                    System.out.println("---------------------");
                 break;
          case 7:
            d.inverted.diaplay_InvertedList();
            break;
          case 8:
            d.invertedBST.display_inverted_index();
            break;
          case 9:
            System.out.println("num of tokens="+d.num_token);
            System.out.println("num of unique words including stop words=="+d.uniqueWords.n);
            break;
          case 10:
            System.out.println("goodbye");
            break;
          default:
          System.out.println("error input try again");
          break;
        }
    }while(ch!=10);
        }

  public void displaystopWords(){
    stopWords.display();
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

}

