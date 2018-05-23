import java.util.*;

/**
 *
 * Purpose: The purpose of this lab is to implement a basic binary search tree. Note that the code has been manipulated:
 *  Inorder traversal using stacks
 *  Iterative search operations
 *  Balancing a BST when given a sorted array
 *  Extracting elements in a BST into a sorted array
 *  Printing the elements in order of depth
 */


public class binarySearchTrees {

    /* Node class */
    static class bstNode {
        private int item;
        private bstNode left;
        private bstNode right;

        private bstNode(int i) {
            item = i;
            left = null;
            right = null;
        }

        public bstNode(int i, bstNode l, bstNode r) {
            item = i;
            left = l;
            right = r;
        }
    }

    public static void main(String[] args){

        /** Canvas Dimensions for Drawing Trees **/
        int xMax = 100;
        int yMax = 100;

        StdDraw.setXscale(0, xMax);
        StdDraw.setYscale(0, yMax);
        StdDraw.setPenColor(StdDraw.BLACK);

        /* Original array */
        int [] A = {19, 15, 1, 2, 4, 18, 13, 9, 20, 6, 3, 16, 11, 7, 17, 14, 8, 5, 10, 12};

        /** Insert elements **/
        System.out.println("Now Showing Original Tree");

        bstNode B = null;
        for (int i = 0; i < A.length; i++){
            B = insert(B, A[i]);
        }

        /* Display Original Tree */
        draw_tree(B, 0, xMax, yMax - 5, (yMax - 10.0)/height(B));

        /** Inorder Traversal **/
        inOrder(B);

        /** Search for specified element **/
        Scanner scnr = new Scanner(System.in);

        /* Search for specified element */
        System.out.println("\n\nPlease enter an integer to search for: ");
        int element = scnr.nextInt();
        System.out.println("Is element " + element +  " present in the tree?\n" + (search(B, element)));


        /** Create balanced binary tree **/
        /* Given a sorted array build a balanced BST */
        System.out.println("\nCreating a Balanced Binary Search Tree using Array");
        System.out.println("What is the length of your desired array?");
        int length = scnr.nextInt();

        int[] givenArray = new int[length];
        System.out.println("Input desired numbers. Press [enter] after each number: ");
        for (int i = 0; i < givenArray.length; i++){
            givenArray[i] = scnr.nextInt();
        }

        /* Ensure array is sorted */
        Arrays.sort(givenArray);
        StdDraw.clear(); //clears canvas for next illustration

        /* Create balanced tree */
        System.out.println("Now Showing A Balanced Binary Tree");
        bstNode balancedTree = balanceTree(givenArray, 0, givenArray.length - 1);

        /* Display Balanced BST */
        draw_tree(balancedTree, 0, xMax, yMax - 5, (yMax - 10.0)/height(balancedTree));

        /** From Binary Search Tree to Sorted Array **/
        int size = sizeOfTree(balancedTree);
        int[] nowASortedArray = new int[size];
        System.out.println("Converting Binary Search Tree to a Sorted Array");
        BST_To_SortedArray(balancedTree, nowASortedArray, 0);

        /* Print contents */
        for (int i = 0; i < size; i++) {
            System.out.print(nowASortedArray[i] + " ");
        }

        /** Print the elements according to depth **/
        System.out.println("\n\nPrinting Elements According to Depth of Binary Tree");
        printLevel(balancedTree);

        /* Terminate Program */
        pause();
        System.out.print("Program Terminated.");
        System.exit(0);

    }


    /** Operations Needed for Binary Search Tree Traversal**/
    /* Insert Elements into tree */
    private static bstNode insert(bstNode T, int i) {
        /* Check that root is empty */
        if (T == null)
            /* Root becomes first number of array */
            T = new bstNode(i);

            /* Insert all numbers less than root */
        else if (i < T.item)
            T.left = insert(T.left, i);

        /* Insert all numbers greater than root */
        else
            T.right = insert(T.right, i);

        /* newly formed node */
        return T;


    }

    /* Prints elements in order using stacks */
    private static void inOrder(bstNode T) {
        System.out.println("Binary Tree In Order Using Stacks:");

        Stack<bstNode> stack = new Stack<>();

        /** Left side of tree **/
        /* Ensure tree (root) is not empty */
        while (T != null) {
            /* Push (enqueue) first element of the tree beginning with leftmost side */
            stack.push(T);
            T = T.left;
        }

        /* Check if stack isn't empty */
        while (!stack.isEmpty()) {
            /* Dequeue first item of the stack */
            T = stack.pop();

            /* Print each item */
            System.out.print(T.item + " ");

            /** Right side of the tree **/
            /* Check if node has a right child */
            if (T.right != null) {
                T = T.right;

                while (T != null) {
                    /* Enqueue the rightmost element */
                    stack.push(T);
                    /* if rightmost element has a left child, enqueue that element */
                    T = T.left;
                }
            }
        }


    }


    /* Search for specific element using iteration */
    private static boolean search(bstNode T, int i) {

        while (T != null){
            /* Check left of root */
            if (i < T.item)
                T = T.left;

            /* Check right of root */
            else if (i > T.item)
                T = T.right;

            else
                return true; //item found
        }

        return false; //item not found
    }

    /* Build a balanced binary search tree from a sorted array */
    private static bstNode balanceTree(int[] givenArray, int first, int last) {
        if (first > last)
            return null;

        /* Find middle of the array and then make it root */
        int middle = (first + last) / 2;
        bstNode T = new bstNode(givenArray[middle]);

        /** Left Side of Tree **/
        /* Recursively go through subarray until middle is found, making it left child */
        T.left = balanceTree(givenArray, first, middle - 1);

        /** Right Side of Tree **/
        /* Recursively go through subarray until middle is found, creating right child */
        T.right = balanceTree(givenArray, middle + 1, last);


        return T;
    }

    /* Find the size of the tree (number of nodes) in order to determine size of the array */
    private static int sizeOfTree(bstNode T) {
        if (T == null)
            return 0;

        return 1 + sizeOfTree(T.left) + sizeOfTree(T.right);
    }

    /* Recursively traverses through the tree, adding each node into the array */
    private static int BST_To_SortedArray(bstNode T, int[] convertedBST, int index) {
        if (T == null) return index;

        /* Recursively finds leftmost element and saves it to index variable */
        index = BST_To_SortedArray(T.left, convertedBST, index);
        /* Position of the array and the value associated with the node is stored */
        convertedBST[index] = T.item;

        /* Recursively finds the rightmost element and saves it to the index variable */
        index = BST_To_SortedArray(T.right, convertedBST, index + 1);

        /* Returns that particular element */
        return index;

    }

    /* Locate the number of depth (levels) within the tree using height method */
    private static void printLevel(bstNode T){
        int height = height(T);

        for (int i = 0; i <= height; i++){
            System.out.println("Keys at depth " + i + ":");
            DetermineElementsOfDepth(T, i);
            System.out.println();
        }

    }


    /* Find height of the tree */
    private static int height(bstNode T) {
        if (T == null) return -1;

        int heightOfLeft = height(T.left);
        int heightOfRight = height(T.right);

        if (heightOfLeft > heightOfRight)
            return 1 + heightOfLeft;

        return 1 + heightOfRight;
    }

    /* Recursively print the elements coinciding with each level  */
    private static void DetermineElementsOfDepth(bstNode T, int numberOfLevels){
        if (T == null) return;

        if (numberOfLevels == 0)
            System.out.print(T.item + " ");

        else if (numberOfLevels >= 1){
            DetermineElementsOfDepth(T.left, numberOfLevels - 1);
            DetermineElementsOfDepth(T.right, numberOfLevels - 1);
        }
    }

    /** The Canvas **/
    /* Necessary for displaying the binary search trees in a seperarate window */
    public static void draw_tree(bstNode T, double x0, double x1, double y, double y_inc){
        if (T == null)
            return;

        double xm = (x0 + x1)/2;
        double yn = y-y_inc;

        if (T.left != null){
            StdDraw.line(xm, y, (x0 + xm)/2, yn);
            draw_tree(T.left, x0, xm, yn, y_inc);
        }

        if (T.right != null){
            StdDraw.line(xm, y, (x1 + xm)/2, yn);
            draw_tree(T.right, xm, x1, yn, y_inc);
        }

        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledCircle(xm, y, 3);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.circle(xm, y, 3);
        StdDraw.text(xm, y, Integer.toString(T.item));
    }

    /* Controls when the tree is to be displayed */
    public static void pause(){
        Scanner scnr = new Scanner(System.in);
        System.out.println("\nPlease Press [Enter] To End Program");
        scnr.nextLine();
        StdDraw.clear();
    }

}

