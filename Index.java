
public class Index {

    LinkedList<Document> allDocs;

    public Index(){

        allDocs = new LinkedList<Document>();
    }

    public void addDoc(Document doc){

        allDocs.insert(doc);
    }

    public Document getDoc(int i){
        if(allDocs.isEmpty()){
            System.out.println("No document exist!");
            return null;
        }
        allDocs.findFirst();
        while (!allDocs.last()){
            if(allDocs.retrieve().id==i)
                return allDocs.retrieve();
            allDocs.findNext();
        }
        if(allDocs.retrieve().id==i)
            return allDocs.retrieve();
        return null;
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
