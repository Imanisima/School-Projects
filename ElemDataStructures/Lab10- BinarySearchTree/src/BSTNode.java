/**
 * Used with: BST.java, Runner.java
 *
 * Purpose: This class is used as the node of a binary tree
 */


public class BTNode {

    String data; //main node
    BTNode left; //left child
    BTNode right; //right child

    /* Constructors */
    BTNode(){}

    public BTNode(String obj){ //construct leaf node
        data = obj;
        left = null;
        right = null;
    }
}
