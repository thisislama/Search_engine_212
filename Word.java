
public class Word {
    String text;
    LinkedList<Integer> doc_IDS;

    public Word(String w) {
        text = w;
        doc_IDS = new LinkedList<Integer>();
    }

    public void add_Id(int id) {
        if (!existsIn_doc_IDS(id))
            doc_IDS.insert(id);
    }
// can use exists in LinkedList instead
    public boolean existsIn_doc_IDS(Integer id) {
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
        System.out.println("\n------------");
        System.out.println("Word: "+text+"\n[");
        doc_IDS.display();
        System.out.println("]");
    }
}
