
public class Index {

    LinkedList<Document> allDocs;

    public Index(){

        allDocs = new LinkedList<Document>();
    }

    public void addDoc(Document doc){

        allDocs.insert(doc);
    }

    public void displayDocs(){

        if (allDocs.isEmpty())
            return;

        allDocs.findFirst();
        while (! allDocs.last()){
            Document doc = allDocs.retrieve();
            System.out.println("ID: "+ doc.id);
            doc.words.display();//*******************************
            allDocs.findNext();

        }
        //for last one
        Document doc = allDocs.retrieve();
        System.out.println("ID: "+ doc.id);
        doc.words.display();//*******************************
    }
}
