import java.util.Scanner;

/**
 
 * Purpose: The purpose of this lab is to implement various operations of B-Trees, and depth manipulation.
 * Raw input is used in order to manipulate program at user will.
 * Try catch statements are used just in case an invalid value is inputted.
 *
 **/

public class BTreeTest {

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        /** Original Array **/
        //int[] S = {8, 9, 11, 4, 7,  12, 13,  17, 24, 15, 27, 28, 30, 33, 34, 37, 40, 42, 50, 16};
        int[] S = {13,4,7,17,24,1,3,5,6,8,10,11,14,16,18,20,25,30,31,35};
       

        BTree B = new BTree(3);

        /** Building B-tree from given array **/
        for (int i = 0; i < S.length; i++) {
            B.insert(S[i]);
        }

        System.out.println("New Tree: ");
        B.printNodes();
        System.out.println("*********************");
        

        BTreeNode T = B.root;

        /** Extracting items in the tree into a sorted array */
        int[] sortArray = new int[S.length];

        System.out.println("\nConverting Tree into Sorted Array");
        sortArray = B_Tree_To_SortedArray(T, sortArray, 0);

        for (int elem : sortArray){
            System.out.print(elem + " ");
        }

        /** Find smallest element in the tree */

        try {
            System.out.println("\nSearch for Smallest Element in Given Depth\nPlease enter desired depth:");
            int depth = scnr.nextInt();
            System.out.println(minElement(T, depth));

            /** Find largest element in tree */
            System.out.println("\nSearch for Largest Element in Given Depth");
            System.out.println(maxElement(T, depth));

            /** Number of nodes in the tree given the depth */

            System.out.println("\nNumber of Nodes Present in Desired Level");
            System.out.println(numOfNodesAtDepth(T, depth, 0));

            /** Print items within given depth */
            System.out.println("\nPrinting Elements Of Desired Level");
            printItems(T, depth);
        }catch (NullPointerException e){
            System.out.println("The level entered does not exist.");
        }

        /** Number of full nodes in the tree */
        System.out.println("\nNumber of Full Nodes in Tree");
        System.out.println(numOfNodes_Full(T));

        /** Number of full leaves in the tree */
        System.out.println("\nNumber of Full Leaves in Tree");
        System.out.println(numOfLeaves_Full(T));

        System.out.println();

        /** Return the depth the key is found */

        try {
            System.out.println("Locating the Depth in Which the Key is Found\nPlease enter the number you want to search for:");
            int key = scnr.nextInt();
            System.out.println(key + " is present in depth " + returnDepthOfKey(T, key));

             /** Print all elements in the same node as the key */
            System.out.println("\nAll Elements In Same Node as Key");
            printAllInNode(T, key);

        }catch (Exception e){
            System.out.println("NOTE: The entered element CANNOT be located. The node associated with it DOES NOT exist!");
        }



        /** Canvas for Drawing **/
        int xMax = 100;
        int yMax = 100;

        StdDraw.setXscale(0, xMax);
        StdDraw.setYscale(0, yMax);
        StdDraw.setPenColor(StdDraw.BLACK);
        draw_tree(T, xMax, yMax-15, 50);
        StdDraw.text(50, 50, "Tree attempted. Still no good. :(");
    }

    /* Drawing the tree */
    public static void draw_tree(BTreeNode T, double x, double y, double size){
        if (T == null)
            return;

        double xm = x/2;
        double yn = y-size/2;

        for (int i = 0; i < T.n; i++) {
            /* Check children of root */
            if (T.c[i] != null) {
                draw_tree(T.c[i], x, yn-20, yn/6);
            }

            /* Move to next node */
            draw_tree(T.c[T.n], x/2, (yn-30 * 0.8), yn/6);

            /* If not leaf continue */
            if (!T.isLeaf){
                draw_tree(T.c[i], x, yn-20, yn/6);
            }

            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.filledCircle(xm, y, 3);
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.circle(xm, y, 3);
            StdDraw.text(xm, y, Integer.toString(T.item[i]));
        }
    }

    /* Extracting items in B-tree into a sorted array */
    public static int[] B_Tree_To_SortedArray(BTreeNode T, int[] sortArray, int index){
        /* Check if root is a leaf */
        if (T.isLeaf) {
            for (int i = 0; i < T.n; i++) {
                /* Copy element into the array position, and then go to next position */
                sortArray[index] = T.item[i];
                index = index+1;
            }
            return sortArray;

        }
        else {
            /* If not a leaf */
            for (int i = 0; i < T.n; i++) {
                /* Recursively traverse through tree until smallest element is found */
                sortArray = B_Tree_To_SortedArray(T.c[i], sortArray, index);
                index += T.c[i].n;
                /* If no there are no more leaves, or smallest has been found */
                if(!T.c[i].isLeaf){
                    /* Traverse through the rest of the node */
                    for(int j = 0; j < T.c[i].n+1; j++){
                        index += T.c[i].c[j].n;
                    }
                }
                sortArray[index] = T.item[i];
                index = index+1;
            }

            sortArray = B_Tree_To_SortedArray(T.c[T.n], sortArray, index);

        }

        return sortArray;
    }


    /* Return minimum element in the tree at a given depth */
    public static int minElement(BTreeNode T, int d){

        if (d == 0)
            return T.item[0];

        return minElement(T.c[0], d-1);
    }

    /* Return maximum element in the tree at a given depth */
    public static int maxElement(BTreeNode T, int d){
        if (d == 0)
            return T.item[T.n-1]; //last item within the depth

        return maxElement(T.c[T.n], d-1);
    }


    /* Return number of nodes in the tree at a given depth */
    public static int numOfNodesAtDepth(BTreeNode T, int d, int tracker){
        int sum = 0;

        if (tracker == d) return 1;

        if(T.isLeaf) return 0;

        /* After depth is found, iterate through the associated nodes within, and add each sum to the element */
        for (int i = 0; i < T.n+1; i++){
            if(T.c[i] != null){
                sum += numOfNodesAtDepth(T.c[i], d, tracker + 1);
            }
        }

        return sum;
    }

    /* Print all items in the tree at given depth */
    public static void printItems(BTreeNode T, int d){
        if (d == 0){
            /* Just print root */
            for (int i = 0; i < T.n; i++){
                System.out.print(T.item[i] + " ");
            }
        }

        else{
            if (!T.isLeaf){
                /* i < T.n will not print the last element within the depth */
                for (int i = 0; i <= T.n; i++){
                    printItems(T.c[i], d-1);
                }
            }
        }
    }

    /* Return number of Nodes in the tree that are full */
    public static int numOfNodes_Full(BTreeNode T){
        int sum = 0;

        /* Check if root is a leaf */
        if (T.isLeaf) return sum;


        /* Check if the node is full */
        if (T.n == T.c.length-1)
            sum += 1;

        /* Traverse the children, take the sum of nodes associated with c[i] */
        for (int i = 0; i < T.n; i++){
            sum += numOfNodes_Full(T.c[i]);
        }

        /* Switch nodes */
        sum += numOfNodes_Full(T.c[T.n]);
        return sum;
    }

    /* Return number of leaves in a tree that are full */
    public static int numOfLeaves_Full(BTreeNode T){
        int sum = 0;

        if (T.isLeaf) {

            /* check if full */
            if (T.n == T.c.length-1)
                return 1;

            else{
                return 0;
            }
        }

        /* Traverse through children */
        for (int i = 0; i < T.n; i++){
            sum += numOfLeaves_Full(T.c[i]);
        }

        sum += numOfLeaves_Full(T.c[T.n]);
        return sum;

    }

    /* Given a key, return depth that the key is found, otherwise -1 */
    public static int returnDepthOfKey(BTreeNode T, int key){
        int i = 0;

        /* Searching for desired item */
        while ((i < T.n) && (key > T.item[i])){
            i++;
        }

        /* If the key is not in the current node */
        if ((i == T.n) || (key < T.item[i])){
            /* Or the key is not present in the tree */
            if (T.isLeaf)
                return -1;

            else{
                /* Check the children of the root */
                int depth = returnDepthOfKey(T.c[i], key);

                /* If not present in the subtree */
                if (depth == -1)
                    return -1;
                else
                    /* continue traversing through each level */
                    return depth + 1;
            }
        }

        /* Key is present in current node */
        return 0;
    }


    /* Given a key, print all keys in the same node as the key */
    public static void printAllInNode(BTreeNode T, int key){
        boolean isFound = false;


        /* Traverse through items in search of key */
        for(int i = 0; i < T.n; i++){
            if(T.item[i] == key){
                isFound = true;
            }
        }

        if(isFound){
            /* Print out all keys within same node as found key */
            for(int j = 0; j < T.n; j++){
                System.out.print(T.item[j]+ " ");
            }
            return;
        }


        /* If key is less than root, traverse through child[0] */
        if(key < T.item[0]){
            printAllInNode(T.c[0], key);
            return;
        }

        /* If key is greater than root */
        for(int k = 0; k < T.n-1; k++){
            if(key > T.item[k] && key < T.item[k+1]){
                /* traverse through children */
                printAllInNode(T.c[k+1], key);
                return;
            }

        }

        printAllInNode(T.c[T.n],key);

    }

}
