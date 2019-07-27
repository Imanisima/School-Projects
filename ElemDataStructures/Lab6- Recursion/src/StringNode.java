/**
 *
 * The purpose of this lab is to write methods using recursion and linked lists, without using loops within methods.
 * In this lab I also generate a random string to be passed through.
 *
 * Used with: Operations.java
 */


public class StringNode {

    public String head;
    public StringNode next;

    /* Constructors */
    StringNode(){}

    StringNode (String s){

        head = s;
    }

    StringNode(String s, StringNode tail){
        head = s;
        next = tail;
    }
}
