public class BST_main {
    public static void main(String[] args) {
        BST<Double>bt = new BST<Double>();
        System.out.println("is empty?"+bt.empty());
        System.out.println("______________________");
        System.out.println("inserting 11 nodes");
        bt.insert("A",35.0);//1
        bt.insert("B",14.0);//2
        bt.insert("B",5.0);//3

        bt.insert("D",33.0);//4
        bt.insert("E",53.0);//5
        bt.insert("C",50.0);//6

        bt.insert("F",44.0);//7
        bt.insert("G",40.0);//8
        bt.insert("H",58.0);//9

        bt.insert("I",55.0);//10
        bt.insert("I",56.0);//11

        System.out.println("All nodes are ");
        bt.inOrder();














    }
}
