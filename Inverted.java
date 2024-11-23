
//linked list of linked list
//linked list of words --> linked list of doc ids
//retreive InvertList gives Word can use .text .doc_IDS , any method in Word
public class Inverted {
    LinkedList<Word> InvertList;

    public Inverted() {
        InvertList = new LinkedList<Word>();
    }

    //creates inverted index: 
    //if it doesn't exist in InverList
    //creates Word obj (w) and inserts the id to its (doc_IDS)
    //inserts (w) the new word to InvertList
    public void add(String text , int Id) {
        if(!Search_InvertedList(text)){ //if the text is not on the list
            Word w = new Word(text);
            w.doc_IDS.insert(Id);
            InvertList.insert(w);
        }
        else {// if the word already on the list
            Word exisit_word = InvertList.retrieve();// return Word
            exisit_word.addIdInWord(Id);// adds id to the retrieved word doc_IDS, add_Id?
        }
    }//method add end



// search word(text) in inverted index that returns Word obj when retreived
    public boolean Search_InvertedList(String w ) {
        if(InvertList == null || InvertList.isEmpty())
          return false;

        InvertList.findFirst();
         while(!InvertList.last()){
           if(InvertList.retrieve().text.equals(w)){
               return true;
          }
           InvertList.findNext();
        }//while loop ends
        if(InvertList.retrieve().equals(w))//
            return true;
        //
        return false;
    }

    public void diaplay_InvertedList(){
        if(InvertList == null){
            System.out.println("Inverted list is Null");
            return;
        }
        else if(InvertList.isEmpty()){
            System.out.println("Inverted list is empty");
        return;
        }
        InvertList.findFirst();
        while(!InvertList.last()){
            InvertList.retrieve().display();//display in Word class
            InvertList.findNext();
        }//loop while ends

       InvertList.retrieve().display();
//
    }
}
