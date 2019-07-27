import java.io.File;
import java.text.DecimalFormat;

/**
 * Used with Box.java, Runner.java
 *
 */


public class LinkedList {

    private Box head;
    private Box iterator; // temp variable

    LinkedList(){}

    /* Add b as the last node of LL */
    void addNode(Box b){
        if (head == null){
            head = b;
            return;
        }

        initiateIterator();

        while (iterator.next != null){
            iterator = iterator.next;
        }
        iterator.next = b;
    }

    /* This method inserts a box in the indicated position */
    boolean insertNode(Box b, int pos){
        System.out.println("\nAdding position: #" + pos);

        int currentPos = 0;
        initiateIterator();
        if (pos == 0){
            b.next = iterator;
            head = b;
            return true;
        }

        while (iterator != null){
            if (currentPos != (pos-1)){
                iterator = iterator.next;
                currentPos++;
            }
            else{
                b.next = iterator.next;
                iterator.next = b;
                return true;
            }
        }
        return false;
    }

    /* This method prints the dimensions + volume of each box */
    void printAllBoxes(){

        initiateIterator();
        while(iterator != null){
            System.out.print("\nWidth: " + iterator.getWidth() + "\nHeight: " + iterator.getHeight() + "\nLength: " + iterator.getLength() + "\nVolume: " + iterator.getVolume());
            iterator = iterator.next;
            System.out.println();
        }
    }

    /* This method removes a box from the inventory */
    boolean removeNode(int pos){
        System.out.println("\nRemoving position: #" + pos);

        if (pos == 0){
            head = head.next;
            return true;
        }

        initiateIterator();
        int currentPos = 0;
        while(iterator != null ){
            if (currentPos == pos-1){
                iterator = iterator.next;
                currentPos++;
            }
            if(iterator.next != null){
                iterator.next = iterator.next.next;
                return true;
            }
        }
        return false;
    }

    /* This method returns the position of the box */
    Box getBox(int pos) {
        initiateIterator();

        int index = 0;
        Box temp = iterator;
        if (iterator == null){
            return null;
        }

        while (iterator != null){
            if (index == pos){
                temp = iterator;
            }

            iterator = iterator.next;
            index++;
        }

        return temp;
    }

    /* This method prints the inventory in reverse order */
    void printReverse(){
        initiateIterator();

        /* Check if list is empty */
        if (iterator == null)
            return;

        /* Box used to print the boxes */
        Box reverse = null;

        /* If reverse is null, and current node (iterator) is not empty, then move forward */
        for (; iterator != null; iterator = iterator.next) {

            /* Pass the dimensions of each node into reverse, and call using recursion */
            reverse = new Box(iterator.getWidth(), iterator.getHeight(), iterator.getLength(), reverse);
        }

        while(reverse != null) {
            System.out.println("\nWidth: " + reverse.getWidth() + "\nHeight: " + reverse.getHeight() + "\nLength: " + reverse.getLength());
            reverse = reverse.next;
        }
    }

    /* Initiates the iterator variable */
    void initiateIterator(){
        iterator = head;
    }

    /* This method returns the box in current iterator position */
    Box getNextBox(){
        if (iterator == null){ //check if end of iterator has been reached
            return null;
        }

        Box temp = iterator; //iterator goes into temp variable
        iterator = iterator.next; // move next iterator into iterator
        return temp;
    }
}
