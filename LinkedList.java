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

    public LinkedList() {
        head = current = null;
    }

    public boolean empty() {
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

    public void insert(T val) {
        Node<T> newNode = new Node<>(val);
        newNode.next = head;
        head = newNode;
        current = head;
    }
}
