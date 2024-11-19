public class InvertedIndexBST {
    
    BST<Word> inverted_index;
    
    public InvertedIndexBST(){
        
        inverted_index = new BST<Word>();
    }
    
    public void add_from_inverted_list(Inverted inverted){
        
        if (inverted.inverted_index.isEmpty()){
            return;
         }
        
        inverted.inverted_index.findFirst();
        while (!inverted.inverted_index.last()){
            
            inverted_index.insert(inverted.inverted_index.retrieve().text , inverted.inverted_index.retreive());
            inverted.inverted_index.findNext();
        }
            inverted_index.insert(inverted.inverted_index.retrieve().text , inverted.inverted_index.retreive());  
}
    
    public void add(String text , int id){
        
        if (!search_word_in_inverted(text)){
            
            Word w = new Word(text);
            w.doc_IDS.insert(id);
            inverted_index.insert(text, w);
        } else{
            
            Word existing_word = inverted_index.retrieve();
            existing_word.add_Id(id);
        }
    }
    
    public boolean search_word_in_inverted(String w){
        
        return inverted_index.findKey(w);
    }
    
    public void display_inverted_index(){
        
        if (inverted_index == null){
            
            System.out.println("null inverted_index");
            return;
        }
        else if (inverted_index.isEmpty()){
            
            System.out.println("empty inverted_index");
            return;
        }
        inverted_index.inOrder();
    }
}
