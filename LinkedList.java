
class Node<T> {
    public T data;
    public Node<T> next;

    public Node(T val) {
        data = val;
        next = null;
    }
}

public class LinkedList<T> {
    private Node<T> head;
    private Node<T> current;
    int n =0;

    public LinkedList() {
        head = current = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public boolean last() {
        return current == null || current.next == null;
    }

    public void findFirst() {
        current = head;
    }

    public void findNext() {
        if (current != null) {
            current = current.next;
        }
    }

    public T retrieve() {
        return current != null ? current.data : null;
    }
    
    public void update (T val){
        current.data = val;
    }

    public void insert(T val) {
        n++ ;
        Node<T> tmp; 
        if (isEmpty())
            current = head = new Node<T>(val);
        else{
           tmp = current.next;
           current.next = new Node<T>(val);
           current = current.next;
           current.next = tmp;
        }
       
    }
    
    
    public void display() {
        if (this == null){
            System.out.println("The list is null.");
            return;
        }
        if (head == null) {
            System.out.println("The list is empty.");
            return;
        }

        Node<T> p = head;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println("");
    }
    
    public boolean exist(T x){
        
        Node <T> p = head;
        while (p != null){
            if (p.data.equals(x))
                return true;
            p = p.next;
            
        }
        return false;
    }
    
   
}
