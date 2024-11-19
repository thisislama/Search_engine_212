//import java.util.LinkedList;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Driver {
  
  LinkedList<String> stopWords;
  Index index1;
  Inverted inverted;
  InvertedIndexBST invertedBST;
  int num_token = 0;
  LinkedList<String> uniqueWords = new LinkedList<>();
  
  public Driver(){
    
  stopWords= new LinkedList<>();
  index1= new Index();
  inverted= new Inverted();
  invertedBST = new InvertedIndexBST();
  }
  
  public void LoadStopWords(String fileName){
    
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
            System.out.println("Empty line"+line);
            break;
          }
          
          String x=line.substring(0,line.indexOf(','));
          int id=Integer.parseInt(x.trim());
          System.out.println("line: "+line);
          String content=line.substring(line.indexOf(',')+1).trim();
          System.out.println("content: "+content);
          
          LinkedList<String> wordsInDoc=MakeLinkedListOfWordsInDocIndexInvertedIndex(content,id);
          index1.addDoc(new Document(id,wordsInDoc, content));
        }
        
      }catch(IOException e){
        System.out.println("end of file");
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
    content=content.toLowerCase().replaceAll("\'"," ");
    content=content.toLowerCase().replaceAll("-"," ");
    content=content.toLowerCase().replaceAll("[^a-zA-Z0-9 ]","");
    String[] tokens=content.split("\\s+");
    
    for(String w:tokens){
      if(!uniqueWords.exist(w)){
      uniqueWords.insert(w);
      }
      if (!existIn_stop_words(w)){
          wordsInDoc.insert(w);
          inverted.add(w,id);
          invertedBST.add(w , id);
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


public void displayDocWithGivenIDS(LinkedList<Integer> IDs){
      
      if (IDs.isEmpty()){
          System.out.println("no documents exist");
          return;  
      }
      
      IDs.findFirst();
      while(!IDs.last()){
          
          Document d = index1.get_document_given_id(IDs.retrieve());
          if (d != null)
               System.out.println("Document "+d.id+" : "+d.content);
          IDs.findNext();
      }
      Document d = index1.get_document_given_id(IDs.retrieve());
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

public static void TeastwithMenu(){
  Driver d=new Driver();
    d.LoadAllFiles("stop.txt","dataset.csv");
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
            LinkedList<Integer> res = Driver.ind1.get_all_documents_given_term(term);
            System.out.print("word:" + term + "[");
            res.display();
            System.out.print("}");
            System.out.print("-------------------------");
            System.out.print("- inverted index with lists");
            boolean found = d.inverted.search_word_in_inverted(term);
            if (found)
              d.inverted.search_inverted_index.retrieve().display();
            else
              System.out.println("not found in inverted index with lists");
            System.out.println("- inverted index with BST");
            boolean found2 = d.invertedBST.search_word_in_inverted(term);
            if (found2)
              d.inverted.search_inverted_index.retrieve().display();
            else
              System.out.println("not found in inverted index with lists");
            break;

          case 2:
            s.nextLine();
            System.out.println("enter a query to retrieve:");
            String query = s.nextLine();
            query = query.toLowerCase();
            query = query.replaceAll("and","AND");
            query = query.replaceAll("or","OR");
            System.out.println("\nwhich method do you want to retrieve:\n"
                    + "1- Index\n"
                    + "2- Inverted Index\n"
                    + "3- BST\n");
            int x = s.nextInt();
            do{

              if (x == 1){
                QueryProcessing_from_index q = new QueryProcessing_from_index(Driver.index1);
                System.out.println("====="+ query+"======");
                LinkedList res1 = QueryProcessing_from_index.MixedQuery(query);
                d.displayDocWithGivenIDS(res1);
              }
              else if (x == 2){
                QueryProcessing q = new QueryProcessing(d.inverted)
                System.out.println("====="+ query+"======");
                LinkedList res1 = QueryProcessing.MixedQuery(query);
                d.displayDocWithGivenIDS(res1);
              }
              else if (x == 3){
                QueryProcessing_BST q = new QueryProcessing_BST(d.invertedBST);
                System.out.println("====="+ query+"======");
                LinkedList res1 = QueryProcessing_BST.MixedQuery(query);
                d.displayDocWithGivenIDS(res1);
              }
              else if (x == 4){
                break;
              }
              else
                System.out.println("Wrong Query");

              s.nextLine();
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
            }while(x!= 4);


            case 3:
              s.nextLine();
              System.out.println("Enter a query to Rank");
              String query2 = s.nextLine();
              query2 = query2.toLowerCase();
              Ranking R5= new Ranking(d.invertedBST,index1,query2);
              R5.insert_sorted_in_list();
              R5.display_all_doc_with_score_usingList();
              break;
              case 4:
                d.index1.diaplay_InvertedList();
                System.out.println("---------------------");
                break;
                case 5:
                  System.out.println("Number of documents="+Driver.index1.allDocs);
                  System.out.println("---------------------");
                  break;
                  case 6:
                    System.out.println("Number of unique words without stop words="+d.inverted.inverted);
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






    }
