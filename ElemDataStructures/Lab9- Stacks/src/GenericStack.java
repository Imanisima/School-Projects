/**
 *
 * The purpose of this lab is to compute post-fix expressions. This classes utilizes linked lists.
 * It also contains the methods relating to stacks
 */


public class GenericStack {

    private Node top;

    public GenericStack(){
        top = null;
    }

    /* Check if stack is empty */
    public boolean isEmpty(){
        return (top == null);
    }

    /* Add element to the top */
    public void push(Object newItem){
        top = new Node (newItem, top);
    }

    /* Prints topmost value and then removes it */
    public Object pop(){
        if (isEmpty()){
            System.out.println("List is empty.");
            return null;
        }

        else{
            Node temp = top; //temp variable to hole top item of list
            top = top.next; // top becomes the next element of the list
            return temp.info; //return value of the element
        }
    }

    /* Empty the list */
    public void popAll(){
        top = null;
    }

    /* Returns the value of topmost element but does not remove it */
    public Object peek(){
        if (isEmpty()){
            System.out.println("List is empty.");
            return null;
        }
        else{
            return top.info;
        }
    }

    /* Class for Linked List */
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

}
