import java.util.Scanner;

/**
 
 * Purpose: Create, Implement and Draw a heap
 *
 * Used with: heaps.java
 *
 */

public class heapSort {
    public static void main(String[] args) {
        /** Drawing **/
        int xMax = 100;
        int yMax = 100;

        StdDraw.setXscale(0, xMax);
        StdDraw.setYscale(0, xMax);
        StdDraw.setPenColor(StdDraw.BLACK);

        heaps H = new heaps(13);
        int[] S = {1, 5, 12, 22, 4, 28, 37, 8, 18, 81, 57, 2};

        /** Insert **/
        for(int i = 0; i < S.length; i++){
            H.insert(S[i]);
        }
        System.out.println("Inserting Elements: ");
        H.print();
        draw_tree(H, 0, xMax, yMax - 5, (yMax - 10.0)/H.H.length, 1);

        pause();

        /** Extract **/
        System.out.println("Extracting smallest element...");
        H.extractMin();
        draw_tree(H, 0, xMax, yMax - 5, (yMax - 10.0)/H.H.length, 1);
        H.print();

        pause();

        System.out.println("Overall Results of Heap");
        for (int i = 0; i < S.length; i++){
            System.out.print(S[i] + " ");
        }
        draw_tree(H, 0, xMax, yMax - 5, (yMax - 10.0)/H.H.length, 1);

        pause();
        System.out.println("Terminating Program.");
        System.exit(0);
    }

    public static void draw_tree(heaps H, double x0, double x1, double y, double y_inc, int index){
    	/* Index cannot be greater than the length of the heap */
        if(index > H.H[0]) return;

        double xm = (x0 + x1)/2;
        double yn = y-y_inc;

		/* Recursively draws the left side of the tree */
        if (H.H[0] >= index * 2){
            StdDraw.line(xm, y, (x0 + xm)/2, yn);
            draw_tree(H, x0, xm, yn, y_inc, index * 2);
        }

		/* Recursively  draws the right side of the tree */
        if (H.H[0] >= index * 2 + 1){
            StdDraw.line(xm, y, (x1 + xm)/2, yn);
            draw_tree(H, xm, x1, yn, y_inc, index * 2 + 1);
        }

        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledCircle(xm, y, 3);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.circle(xm, y, 3);
        StdDraw.text(xm, y, Integer.toString(H.H[index])); //print the number contained
    }

    /* Controls when the tree is to be displayed */
    public static void pause(){
        Scanner scnr = new Scanner(System.in);
        System.out.println("\nPlease Press [Enter]");
        scnr.nextLine();
        StdDraw.clear();
    }

}
