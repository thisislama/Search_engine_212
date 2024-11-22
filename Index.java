

//linked list of linked lists
public class Index {

    LinkedList<Document> allDocs;
    //linked list of linked list documents: has linked list of strings (words) and ids
    //retreive gives data object of type Document can use .id , .words , .content , methods in Document
    public Index(){

        allDocs = new LinkedList<Document>();
    }

    public void addDoc(Document doc){

        allDocs.insert(doc);
    }
//gets document given id
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

        if (allDocs == null){
            System.out.println("null docs");
            return;
        }
        
        if (allDocs.isEmpty()){
            System.out.println("empty docs");
            return;
        }

        allDocs.findFirst();
        while (! allDocs.last()){
            Document doc = allDocs.retrieve();
            System.out.println("ID: "+ doc.id);
            doc.words.display();
            allDocs.findNext();

        }
        //for last one
        Document doc = allDocs.retrieve();
        System.out.println("ID: "+ doc.id);
        doc.words.display();
    }
    
    public LinkedList<Integer> getDocsGivenTerm(String term){
        
        LinkedList<Integer> res = new LinkedList<>();
        
        if (allDocs.isEmpty()){
            System.out.println("no documents exist in Index getDocsGivenTerm ");
            return null;          
        }
        allDocs.findFirst();
        while(!allDocs.last()){
            
            if (allDocs.retrieve().words.exist(term.toLowerCase().trim()))
                res.insert(allDocs.retrieve().id);
            allDocs.findNext();
        }
        if (allDocs.retrieve().words.exist(term.toLowerCase().trim()))
                res.insert(allDocs.retrieve().id);
        return res;
        
}}
