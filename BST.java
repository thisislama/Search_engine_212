class BSTNode<T>{
    T data;
    BSTNode<T> left, right;

}


public class BST<T> {
    private BSTNode<T> root, current;


    public BST(){
        current =root = null;
    }

    public boolean empty(){
        return root==null;
    }

    public boolean full(){
        return false;
    }

    public T retrieve(){
        return current.data;
    }
}
