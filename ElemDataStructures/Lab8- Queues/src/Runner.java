import sun.misc.*;

import java.util.*;

/**
 *
 * The purpose of this lab is to test the Queue class
 *
 * Used with: Queue.java
 */
public class Runner {

    /* Testing purposes */
    public static void main(String[] args){
        /* Create a Queue object to hold the numbers desired */
        Queue myQ = new Queue();

        /* Add items to the queue */
        myQ.enqueue(10);
        myQ.enqueue(20);
        myQ.enqueue(30);
        myQ.enqueue(40);
        myQ.enqueue(50);

        /* Print Current Queue */
        System.out.println("Current Queue: ");
        printQueue(myQ);

        /* Remove an item and then print */
        System.out.println("\n\nNow I will dequeue one element: ");
        myQ.dequeue(); //remove first element
        printQueue(myQ);

        /* Reverse the queue after removing one */
        System.out.println("\n\nNow I will reverse it: ");
        reverseQueue(myQ);

        /* Prompt user for an item to add */
        System.out.println("\nNow to enqueue a number.\nWhat number would you like to add?");
        Scanner scnr = new Scanner(System.in);
        int chooseEnqueue = scnr.nextInt();

        /* Print the queue with the added number */
        System.out.println("\nThe updated queue is as follows: ");
        myQ.enqueue(chooseEnqueue);
        printQueue(myQ);

        /* Add another number */
        System.out.println("\n\nAdd another number: ");
        chooseEnqueue = scnr.nextInt();
        myQ.enqueue(chooseEnqueue);
        System.out.println();

        /* Print the updated Queue */
        System.out.println("\nQueue updated ");
        printQueue(myQ);
        System.out.println();

        /* Reverse the updated queue from above */
        System.out.println("\nReversing: ");
        reverseQueue(myQ);
    }

    /* The purpose of this method is to print all the elements of the queue */
    public static void printQueue(Queue q){
        int lengthOfQueue = q.size(); //save the length of the given queue
        for (int i = 0; i < lengthOfQueue; i++) {
            int temp = (int)q.dequeue(); //cast the item within q as an integer so that it can be read and save into temp
            q.enqueue(temp); //then add the item back into the queue
            System.out.print(temp + " "); //print
        }

    }

    /* This method prints the elements in reverse */
    public static void reverseQueue(Queue q){
        Stack<Object> reversalQ = new Stack<Object>();

            /* Check if original queue is empty */
            while (!q.isEmpty()) //while it is not
                /* take the first element of the queue, read it, delete it, and insert into stack */
                reversalQ.push(q.dequeue()); //Note that when elements are added to stacks, it is the bottom-most element, not the first


            /* Check if stack (containing queue) is empty */
            while (!reversalQ.isEmpty()) //if not
                /* return the first element of the stack, remove it, and add to original q */
                q.enqueue(reversalQ.pop()); //Note that now the elements have been reversed

            /* Print results */
            printQueue(q);

    }


}
