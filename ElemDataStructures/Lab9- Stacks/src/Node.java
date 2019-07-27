/**
 * Used with: GenericStack.java, Evaluator.java
 
 Class for Linked List
 */
 
public class Node{
    Object info;
    Node next;

    public Node(){}

    /* Constructor */
    Node (Object data, Node nextNode){
        info = data;
        next = nextNode;
    }

    public Node(Object data){
        info = data;
    }
}
