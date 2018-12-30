import java.util.Scanner;

/**
 *
 * Used with Runner1.java, TestSortPlot.pdf
 *
 * The purpose of Runner2 is to test the time complexities of both Bubble Sort and Selection Sort (in nano time).
 * In this class I have also created a Runner 1 object so that I can call the respective methods without the need
 *  for the use of two main methods. I also prompt the user with a scanner in order to take in the length of the list.
 * In the end, Runner2 lets the user know which method of sorting was more efficient.
 *
 */


public class Runner2 {
    public static void main(String[] args) {

        /* Prompt user for the number of strings desired within the array */
        Scanner scnr = new Scanner(System.in);
        System.out.println("Input your desired length of list: ");
        int lengthOfList = scnr.nextInt(); //user input

        /* Create an object of first Runner class in order to call the methods */
        Runner1 firstRun = new Runner1();


        /* Call method to generate random string of given length */
        System.out.println("\nGenerated Strings" );
        String[] generatedString = firstRun.randomGen(lengthOfList);
        firstRun.printStrings(generatedString); //print strings as proof

        /* Create a copy of the generated strings for the sorting methods */
        String[] copyBubble = firstRun.copyArray(generatedString); //bubble sort
        String[] copySelec = firstRun.copyArray(generatedString); //selection sort

        /* Use nanoTime in order to keep track of the amount of time it takes to finish sorting */
        long bubbleTime, endBubble, startBubble; //variables used to keep track of total time used for bubble sort
        long selecTime, endSelec, startSelec; //variables used to keep track of total time used for selection sort

        /* Timer for Bubble Sort */
        startBubble = System.nanoTime(); //Begin timer
        firstRun.bubbleSort(copyBubble);
        endBubble = System.nanoTime(); //End timer

        /* Timer for Selection Sort */
        startSelec = System.nanoTime(); // Begin timer
        firstRun.selectionSort(copySelec);
        endSelec = System.nanoTime(); // End timer

        System.out.println("\nTotal Amount of Time for Sorting");
        bubbleTime = endBubble - startBubble; //total amount of time for bubble sort
        selecTime = endSelec - startSelec; //total amount of time for selection sort

        System.out.println("Bubble Sort: " + bubbleTime + " n/s"); //print the bubble sort's time
        System.out.println("Selection Sort: " + selecTime + " n/s"); //print the selection sort's time

        if (bubbleTime > selecTime)
            System.out.println("In this case, Selection Sort is more optimal than Bubble Sort when n is " + lengthOfList + ".");
        else if (bubbleTime < selecTime)
            System.out.println("In this case, Bubble Sort is more optimal than Selection Sort when n is " + lengthOfList + ".");
        else
            System.out.println("In this case, they take about the same time to complete when n is " + lengthOfList + ".");

    }

}
