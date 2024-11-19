
public class Inverted {
    LinkedList<Word> InvertList;

    public Inverted() {
        InvertList = new LinkedList<Word>();
    }

    public void add(String text , int Id) {
        if(!Search_InvertedList(text)){ //if the text is not on the list
            Word w = new Word(text);
            w.doc_IDS.insert(Id);
            InvertList.insert(w);
        }
        else {// if the text already on the list
            Word exisit_word = InvertList.retrieve();// return word
            exisit_word.doc_IDS.insert(Id);
        }
    }//method add end




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
            InvertList.retrieve().display();
            InvertList.findNext();
        }//loop while ends

       InvertList.retrieve().display();
//
    }
}





