/**
 * Used with: BST.java, BTNode.java
 *
 * Purpose: This class tests the binary search tree
 */


public class Runner {

    public static void main(String[] args) {
        BST bst = new BST();

        /* Words to insert within tree */
        bst.insert("Monkey");
        bst.insert("Jaguar");
        bst.insert("Rabbit");
        bst.insert("Platypus");
        bst.insert("Giraffe");
        bst.insert("Klipspringer");
        bst.insert("Vicuna");
        bst.insert("Quokka");
        System.out.println("---------------------------");

        /* Print the tree */
        System.out.println("Printing BST:");
        bst.printBT();
        System.out.println("---------------------------");

        /* Print total number of elements within tree */
        System.out.print("Total number of nodes: ");
        System.out.println(bst.size());
        System.out.println("---------------------------");

        /* Print elements in ascending order */
        System.out.println("Printing BST in ascending order:");
        bst.printAscending();
        System.out.println("---------------------------");

        /* Print elements in descending order */
        System.out.println("Printing BST in descending order:");
        bst.printDescending();
        System.out.println("---------------------------");

        /* Prints the longest string of the tree */
        System.out.print("The longest string is: ");
        System.out.println(bst.getLongestString());

    }
}
