
public class InvertedIndexBST {
    
    BST<Word> inverted_indexBST;
    
    public InvertedIndexBST(){
        
        inverted_indexBST = new BST<Word>();
    }
    
    
    public void add(String text , int id){
        
        if (!search_word_in_inverted(text)){
            
            Word w = new Word(text);
            w.doc_IDS.insert(id);
            inverted_indexBST.insert(text, w);
        } else{
            
            Word existing_word = inverted_indexBST.retrieve();
            existing_word.addIdInWord(id);
        }
    }
    
    public boolean search_word_in_inverted(String w){
        return inverted_indexBST.findKey(w);
    }
    
    
    public void display_inverted_index(){
        
        if (inverted_indexBST == null){
            
            System.out.println("null inverted_indexBST");
            return;
        }
        else if (inverted_indexBST.isEmpty()){
            
            System.out.println("empty inverted_indexBST");
            return;
        }
        inverted_indexBST.inOrder();
    }
}
