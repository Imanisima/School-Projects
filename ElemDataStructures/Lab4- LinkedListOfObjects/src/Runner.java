import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

/**
 *
 * Used with file: Box.java
 *
 * The purpose of the program is to use linked lists instead of arrays in order to read a file
 * and display the respective information WITHOUT reading the given file twice.
 * An array cannot be used to store Box objects nor the LinkedList method [ :( ]
 * However, stringTokenizer may be used!
 */


public class Runner {
    private Node head = null;

    public static void main(String[] args) throws NullPointerException{
        DecimalFormat df = new DecimalFormat("#0.00");

        try {
            /* Dimensions */
            double width, height, length;
            int numOfBoxes = 0;

            /* Temporary Package */
            Runner inventory = new Runner();
            Box tempBox;

            System.out.println("Welcome to El Paso Packaging and Supply Company!\n");
            System.out.println("Please upload a file so that it can be read.");
            System.out.println("...\n...\n...\nFile uploaded.");
            /* File Reader */
            BufferedReader readFile = new BufferedReader(new FileReader("input.txt"));
            String line;
            while((line = readFile.readLine()) != null){
                /* Use String Tokenizer to efficiently split the lines into their respective sections (width, height, length) */
                StringTokenizer readToken = new StringTokenizer(line, " ");

                while(readToken.hasMoreElements()){
                    /* Parse and feed them into their respective sections */
                    width = Double.parseDouble(readToken.nextElement().toString());
                    height = Double.parseDouble(readToken.nextElement().toString());
                    length = Double.parseDouble(readToken.nextElement().toString());

                    /* Temporary box that adds elements to the inventory package */
                    tempBox = new Box(width, height, length);
                    inventory.insert(tempBox);
                    numOfBoxes++;

                    }

                }
                if (numOfBoxes == 0) {
                    System.out.println("FATAL ERROR FOUND!\nThere are no boxes.\nThere's nothing to display.\nTerminating.");
                    System.exit(0);
                }


            readFile.close();

            /* Now to print the results!! */
            System.out.println("\nNumber of Boxes: " + numOfBoxes);
            System.out.println("Average Volume of Boxes: " + df.format(inventory.avgVolume()));

            System.out.println("\nNumber of Cubic Boxes: " + inventory.numOfCubic());
            System.out.println("Average Volume of Cubic Boxes: " + df.format(inventory.avgCubicVolume()));

            /* Smallest Box */
            Node smallestBox = inventory.smallestBox();
            System.out.println("\nSmallest Box\nIndex: " + smallestBox.positionOfBox);
            System.out.println("Width: " + smallestBox.getData().getWidth());
            System.out.println("Height: " + smallestBox.getData().getHeight());
            System.out.println("Length: " + smallestBox.getData().getLength());
            System.out.println("Volume: " + df.format(smallestBox.volume));

            /* Largest Box */
            Node largestBox = inventory.largestBox();
            System.out.println("\nLargest Box\nIndex: " + largestBox.positionOfBox);
            System.out.println("Width: " + largestBox.getData().getWidth());
            System.out.println("Height: " + largestBox.getData().getHeight());
            System.out.println("Length: " + largestBox.getData().getLength());
            System.out.println("Volume: " + df.format(largestBox.volume));

            /* Largest Cubic Box */
            Node largestCubic = inventory.largestCubic();
            System.out.println("\nLargest Cubic Box\nIndex: " + largestCubic.positionOfBox);
            System.out.println("Width: " + largestCubic.getData().getWidth());
            System.out.println("Height: " + largestCubic.getData().getHeight());
            System.out.println("Length: " + largestCubic.getData().getLength());
            System.out.println("Volume: " + df.format(largestCubic.volume));

            /* Smallest Cubic Box */
            Node smallestCubic = inventory.smallestCubic();
            System.out.println("\nSmallest Cubic Box\nIndex: " + smallestCubic.positionOfBox);
            System.out.println("Width: " + smallestCubic.getData().getWidth());
            System.out.println("Height: " + smallestCubic.getData().getHeight());
            System.out.println("Length: " + smallestCubic.getData().getLength());
            System.out.println("Volume: " + df.format(smallestCubic.volume));

            if (smallestCubic == largestCubic){
                System.out.println("\nNote: There is only one cubic box, so the smallest and largest cubics are the same.");
            }

        }catch (Exception e){
            System.out.println("Fatal Error Found!");
        }
    }

    /* What's done behind the scenes? */

    /* Insert an element at the end of the list, therefore creating the list */
    private void insert (Box element) {
        Node node = new Node(element);
        node.setNext(head);
        head = node;
    }

    /* This method keeps track of the number of cubic boxes */
    private int numOfCubic() {
        Node temp = head;
        int totalCubics = 0; //keeps track of the total number of cubics within package
         /* Checks if package is empty */
        if (head == null) {
            return 0;
        }

        /* Tracking cubics */
        while (temp != null) {
            if (temp.isCubic) {
                totalCubics++;
            }
            /* Continue to the end of the list */
            temp = temp.getNext();
        }
        /* Return total number of cubics */
        return totalCubics;
    }

    /* This method locates the smallest box of all the boxes */
    private Node smallestBox() {
        int counter = 1;
        int index = 1;

        /* Checks if box is empty */
        Node smallest = head;
        Node temp = head.getNext();
        double currentVol = head.volume;

        while (temp != null) {
            /* Compare temporary box volume to the previous volume saved*/
            if (temp.volume < currentVol) {
                currentVol = temp.volume;
                smallest = temp;
                index = counter;
            }
            /* Traverse until the end of list is reached */
            temp = temp.getNext();
        }
        smallest.positionOfBox = index;
        return smallest;
    }

    /* This method looks for the largest box */
    private Node largestBox() {
        int counter = 1;
        int index = 1;

            /* Again, check if empty */
        if (head == null) {
            return null;
        }

        Node largest = head;
        Node temp = head.getNext();
        double current = head.volume;

        while (temp != null) {
            counter++;
            /* Compare the volumes together */
            if (temp.volume > current) {
                current = temp.volume;
                largest = temp;
                index = counter;
            }
            temp = temp.getNext();
        }
        // Position found
        largest.positionOfBox = index;
        return largest;
    }

    /* Searches for the cubics */
    private Node smallestCubic() {
        /* Note that smallestCubic is set to null so that a box can be created and compared */
        Node smallestCubic = null;
        Node temp = head;
        double currentVol = 0;
        int counter = 0;
        int index = 0;

        while (temp != null) {
            counter++;

            /* currentVol must be zero or an error occurs*/
            if (temp.isCubic && (temp.volume < currentVol || currentVol == 0)) {
                currentVol = temp.volume;
                smallestCubic = temp;
                index = counter;
            }
            temp = temp.getNext();
        }
        smallestCubic.positionOfBox = index;

        return smallestCubic;
    }

    /* Searches for the largest cubic */
    private Node largestCubic() {
        Node largestCubic = null;
        Node temp = head;
        double currentVol = 0;
        int counter = 0;
        int index = 0;

        while (temp != null) {
            counter++;
            if (temp.isCubic && (temp.volume > currentVol)) {
                largestCubic = temp;
                index = counter;
            }
            temp = temp.getNext();
        }

        largestCubic.positionOfBox = index;

        return largestCubic;
    }

    /* This method returns the volume of all the boxes present */
    private double avgVolume() {
        double sum = 0;
        int totalBoxes = 0;
        Node temp = head;

        while (temp != null) {
            sum = sum + temp.volume;
            totalBoxes++;
            temp = temp.getNext();
        }
        double average = sum / totalBoxes;
        return average;
    }

    /* This method only takes the average volume of cubics*/
    private double avgCubicVolume() {
        double sum = 0;
        int totalCubics = 0;
        Node temp = head;

        while (temp != null) {
            if (temp.isCubic) {
                sum = sum + temp.volume;
                totalCubics++;
            }
            temp = temp.getNext();
        }
        double averageVol = sum / totalCubics;
        return averageVol;
    }

    /* Node Class with the use of Generics */
    private class Node {

        private Box link;
        private Node next;
        private boolean isCubic;
        private double volume;
        private int positionOfBox;

        private Node(Box node) {
            this.link = node;
            this.isCubic = node.isCubic();
            this.volume = node.getVolume();
        }

        /* Data Retrieval */
        private Box getData() {
            return this.link;
        }

        /* Can't access a private variable without a setter */
        private void setNext(Node link) {
            this.next = link;
        }

        /* Retrieves the next link */
        private Node getNext() {
            return this.next;
        }
    }
}
