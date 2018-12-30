import java.util.Stack;

/**
 * Used with: BTNode.java, Runner.java
 *
 * Purpose: This class contains the methods necessary to be called fro the trees
 */


public class BST {

    private BTNode root;

    BST(){}

    BST(String str){
        root = new BTNode(str);
    }

    /* Return number of elements in binary search tree */
    public int size(){
        return countElements(root);
    }

    /* Method that calculates the number of elements present */
    private int countElements(BTNode root){
        if (root == null){
            return 0;
        }
        else{
            return (1 + countElements(root.left) + countElements(root.right));
        }
    }

    /* Insert the string in the parameter into the BST */
    public boolean insert(String str) {

        /* Check if the tree is empty */
        if (root == null){
            root = new BTNode(str);
            return true;
        }

        /* Temp variable used to traverse */
        BTNode temp = root;

        while (true){
            /* Traverse to left */
            if (str.compareTo(temp.data) < 0){
                /* If left side isn't empty, make temp equal the left most */
                if (temp.left != null)
                    temp = temp.left;

                else{
                    /* Add string to the leftmost branch */
                    temp.left = new BTNode(str);
                    break;
                }
            }

            /* If right side is not empty, traverse */
            else if (str.compareTo(temp.data) > 0){
                if (temp.right != null)
                    temp = temp.right;
                else{
                    /* Add string to right most branch */
                    temp.right = new BTNode(str);
                    break;
                }
            }
            else
                break; //string found, do not insert
        }
        return false;

    }

    /* Print the BST */
    public void printBT(){
        if (root == null)
            return;

        /* Hold strings in a stack */
        Stack<BTNode> s = new Stack<BTNode>();
        BTNode current = root;

        /* Traverse starting from the left */
        while (current != null){
            s.push(current);
            current = current.left;
        }

        while (s.size() > 0){
            current = s.pop();
            System.out.println(current.data + " ");
            if (current.right != null){
                current = current.right;

                while (current != null){
                    s.push(current);
                    current = current.left;
                }
            }
        }
    }

    /* Print the elements in ascending order */
    public void printAscending(){
        printAscending(root);

    }

    private void printAscending(BTNode root){
        if (root != null){
            printAscending(root.left);
            System.out.println(root.data);
            printAscending(root.right);
        }
    }

    /* Print elements in descending order */
    public void printDescending(){
        printDescending(root);

    }

    private void printDescending(BTNode root){
        if (root != null){
            printDescending(root.right);
            System.out.println(root.data);
            printDescending(root.left);
        }
    }

    /* Return longest string of BST */
    public String getLongestString(){
        getLongestString(root);
        return root.data;
    }

    private BTNode getLongestString(BTNode root){

        /* Temp variable used to traverse */
        BTNode temp = root;

        while (true) {

            /* Compare strings */
            if (temp.data.compareTo(temp.right.data) < 0)
                /* while the rightmost branch is yet to be empty */
                while (temp.right != null) {
                    /* temp becomes the rightmost value */
                    temp = temp.right;
                }
            return temp;
        }
    }
}
