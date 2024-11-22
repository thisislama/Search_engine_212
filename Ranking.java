

class Doc_Rank{
int id;
int rank;
public Doc_Rank (int i, int r){
id=i;
rank=r;
}
public void display (){
System.out.printf ("%-8d%-8d\n", id, rank) ;
}
}
public class Ranking{
static String Query;
static InvertedIndexBST invertedBST;
static Index indexl;
static LinkedList<Integer> all_doc_in_query;//doc ids
static LinkedList<Doc_Rank>all_doc_ranked;//list of ranked documents

public Ranking (InvertedIndexBST inverted, Index indexl, String Query) {
this.invertedBST=inverted;
this.indexl=indexl;
this.Query=Query;
all_doc_in_query=new LinkedList<Integer>() ;
all_doc_ranked=new LinkedList<Doc_Rank> () ;
}


public static void displayRanking() {
if (all_doc_ranked.isEmpty() ) {
System.out.println("empty all_doc_ranked") ;
return;
}
System.out.printf("%-8s%-8s\n", "DoCID", "Score") ;
all_doc_ranked. findFirst ();
while (!all_doc_ranked. last ()){
all_doc_ranked.retrieve().display();
all_doc_ranked.findNext();
}
all_doc_ranked.retrieve().display();
}


 public static Document get_doc_given_id(int id) {
    return indexl.getDoc(id);
 }
 
 //counts how many times the word appeared in the document
public static int term_frequency_in_doc (Document d, String word){
int freq=0;
LinkedList<String>words=d.words;
if (words.isEmpty()) return 0;
 words.findFirst ();
while (!words.last()){
if (words.retrieve ().equalsIgnoreCase(word))
freq++;
words.findNext() ;}
if (words.retrieve().equalsIgnoreCase(word) )
freq++;
return freq;

}

//counts frequency for ALL words in query , actual ranking
public static int get_doc_rank_score (Document d, String Query){
if (Query.length()==0)
return 0;
String terms[] = Query.split(" ");
int sum_freq=0;
for (int i=0;i<terms.length;i++){
sum_freq+=term_frequency_in_doc(d, terms[i].trim().toLowerCase ()) ;
}
return sum_freq;
}

//get linkedlist of each term in the query (Inverted) and add them to A,,then all_doc_in_query
public static void RankQuery (String Query) {
LinkedList<Integer> A =new LinkedList<Integer>();
if (Query.length()==0) return ;
String terms []=Query.split ("\\s+"); 
boolean found=false;
for (int i=0;i<terms.length;i++){

found=invertedBST.search_word_in_inverted (terms [i].trim().toLowerCase()) ;
if (found)
  A=invertedBST.inverted_indexBST.retrieve().doc_IDS;////////////////
  Adding_in_1_List_sorted(A);
}
}

//takes sorted ids linked lists
public static void Adding_in_1_List_sorted(LinkedList<Integer>A)
{
if (A.isEmpty())
return ;
A.findFirst();
while(!A.isEmpty()) {
boolean found=existsIn_result(all_doc_in_query, A.retrieve()) ;
if(!found) {
insert_sorted_Id_list(A.retrieve()) ;
}
if (!A.last () )
A.findNext () ;
else 
    break;}
}

public static boolean existsIn_result (LinkedList<Integer>result, Integer id){
if (result.isEmpty()) return false;
result.findFirst ();
while (!result.last ()) {
if (result.retrieve().equals(id) ) {
return true;
}
result.findNext () ;}
if (result.retrieve().equals(id) ) {
return true;}
return false;}


//insert sorted ids (all_doc_in_query)
public static void insert_sorted_Id_list (Integer id){
     if (all_doc_in_query.isEmpty()) {
         all_doc_in_query.insert(id);
            return;}
all_doc_in_query.findFirst () ;
while (!all_doc_in_query.last())
{
if (id<all_doc_in_query.retrieve ()){
Integer id1=all_doc_in_query.retrieve() ;
all_doc_in_query.update(id) ;
all_doc_in_query.insert(id1) ;
return;
}
else
all_doc_in_query.findNext() ;
}
if (id<all_doc_in_query.retrieve ()){
Integer id1=all_doc_in_query.retrieve() ;
all_doc_in_query.update(id) ;
all_doc_in_query.insert(id1) ;
return;}
 else
 all_doc_in_query.insert(id) ;
}

//ranked sorted
public static void insert_sorted_in_list(){
RankQuery(Query);
if(all_doc_in_query.isEmpty ()) {
System.out.println("empty query in Ranking insert_sorted_in_list") ;
return;}

all_doc_in_query.findFirst();
while (!all_doc_in_query.last ())
{
    Document d = get_doc_given_id(all_doc_in_query.retrieve());
    int Rank=get_doc_rank_score (d, Query) ;
    insert_sorted_list(new Doc_Rank(all_doc_in_query.retrieve(), Rank));
    all_doc_in_query.findNext() ;
}
    Document d = get_doc_given_id(all_doc_in_query.retrieve());
    int Rank=get_doc_rank_score (d, Query) ;
    insert_sorted_list(new Doc_Rank(all_doc_in_query.retrieve(), Rank));
}

//sort ranks descending
public static void insert_sorted_list(Doc_Rank dr){
    
if(all_doc_ranked.isEmpty ()) {
all_doc_ranked.insert(dr) ;
return;
}
all_doc_ranked.findFirst();
while (!all_doc_ranked.last()){

    if (dr.rank > all_doc_ranked.retrieve().rank) {
Doc_Rank dr1=all_doc_ranked.retrieve () ;
all_doc_ranked.update(dr) ;
all_doc_ranked.insert(dr1) ;
return;
}
   else
all_doc_ranked.findNext() ;
   }
 if (dr.rank > all_doc_ranked.retrieve().rank) {
Doc_Rank dr1=all_doc_ranked.retrieve () ;
all_doc_ranked.update(dr) ;
all_doc_ranked.insert(dr1) ;
return;
}
 else
 all_doc_ranked.findNext() ;
}
}
