import java.io.*;
import java.util.*;


/**
 * Used with Box.java, LinkedList.java
 *
 */

public class Runner {

    public static void main(String[] args) throws NullPointerException {

        try {
            /* Dimensions */
            double width, height, length;
            int numOfBoxes = 0;

            /* Temporary Package */
            LinkedList inventory = new LinkedList();
            Box tempBox;

            /* File Reader */
            BufferedReader readFile = new BufferedReader(new FileReader("input.txt"));
            String line;
            while ((line = readFile.readLine()) != null) {
                /* Use String Tokenizer to efficiently split the lines into their respective sections (width, height, length) */
                StringTokenizer readToken = new StringTokenizer(line, " ");

                while (readToken.hasMoreElements()) {
                    /* Parse and feed them into their respective sections */
                    width = Double.parseDouble(readToken.nextElement().toString());
                    height = Double.parseDouble(readToken.nextElement().toString());
                    length = Double.parseDouble(readToken.nextElement().toString());

                    /* Temporary box that adds elements to the inventory package */
                    tempBox = new Box(width, height, length);
                    inventory.addNode(tempBox);
                    numOfBoxes++;
                }

            }

            if (numOfBoxes == 0) {
                System.out.println("FATAL ERROR FOUND!\nThere are no boxes.\nThere's nothing to display.\nTerminating.");
                System.exit(0);
            }

            readFile.close();

            /* Print all boxes */
            System.out.println("Printing All Boxes: ");
            inventory.printAllBoxes();

            /* Prints the largest box */
            System.out.println("\nLargest Box: ");
            largestBox(inventory);

            /* Prints the smallest box */
            System.out.println("\nSmallest Box: ");
            smallestBox(inventory);

            /* Prints the reverse of the given inventory */
            System.out.println("\nReverse: ");
            inventory.printReverse();

            /* Prints the number of cubics available within the inventory */
            System.out.println("\nNumber of Cubics: " + numOfCubic(inventory));

            /* Remove the indicated box and then print the results */
            inventory.removeNode(1);
            inventory.printAllBoxes();

            /* Adds a box in the indicated position */
            Box addBox = new Box (14, 15, 12);
            inventory.insertNode(addBox, 5);
            inventory.printAllBoxes();


        } catch (Exception e) {
            System.out.println("Fatal Error Found!");
        }
    }


    /* This method counts the number of cubic boxes present in the package */
    static int numOfCubic(LinkedList inventory) {
        int counter = 0;

        /* Finds the head of the list */
        inventory.initiateIterator();
        /* iterate through the list */
        Box cubicBox = inventory.getNextBox();

        /* If cubic, add to counter */
        while (cubicBox != null){
            if (cubicBox.isCube()) {
                counter++;
            }
            /* continue to iterate through until list is no longer */
            cubicBox = inventory.getNextBox();
        }
        /* Return the number of cubic boxes present */
        return counter;

    }

    /* This method prints the largest box of inventory */
    private static void largestBox(LinkedList inventory){
        int index = 0;

        inventory.initiateIterator();

        Box largest = inventory.getNextBox();
        double smallestVolume = largest.getVolume();

        while ((largest = inventory.getNextBox()) != null){

            if (largest.getVolume() > smallestVolume){
                smallestVolume = largest.getVolume();
                index++;
            }
        }

        System.out.println("Index #: " + index);
        System.out.println("Width: " + inventory.getBox(index).getWidth() + "\nHeight: " + inventory.getBox(index).getHeight() + "\nLength: " + inventory.getBox(index).getLength());
    }

    /* This method returns the smallest box of the inventory */
    private static void smallestBox(LinkedList inventory){
        int index = 0;

        inventory.initiateIterator();

        /* Holds the boxes in order to compare */
        Box smallest = inventory.getNextBox();
        /* Saves the volume of the current box into volume */
        double largestVolume = smallest.getVolume();

        /* Compare volumes */
        while ((smallest = inventory.getNextBox()) != null){
            /* If the smallest box is less than  */
            if (smallest.getVolume() < largestVolume){
                /* save the smallest volume */
                largestVolume = smallest.getVolume();
                index++;
            }
        }

        System.out.println("Index #: " + index);
        System.out.println("Width: " + inventory.getBox(index).getWidth() + "\nHeight: " + inventory.getBox(index).getHeight() + "\nLength: " + inventory.getBox(index).getLength());
    }
}
