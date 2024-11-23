
//mainly for inverted
public class Word {
    String text;//word
    LinkedList<Integer> doc_IDS;//ids that have that word

    public Word(String w) {
        text = w;
        doc_IDS = new LinkedList<Integer>();
    }
    //only adds if not exist
    public void addIdInWord(int id) {
        if (!existsInDocIDS(id))
            doc_IDS.insert(id);
    }

    public boolean existsInDocIDS(Integer id) {
        if (doc_IDS.isEmpty())
            return false;
        doc_IDS.findFirst();
        while (!doc_IDS.last()) {
            if (doc_IDS.retrieve().equals(id)) {
                return true;
            }
            doc_IDS.findNext();
        }
        return doc_IDS.retrieve().equals(id);
    }

    public void display() {
        System.out.println("");
        System.out.print("Word: "+text);
        System.out.print(" : ");
        doc_IDS.display();
        
    }
}
