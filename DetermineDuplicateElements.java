import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

/**
 * 
 * Purpose: The purpose of this lab is to use several methods to determine if an array of integers
 *  in a certain range contains (0 to m-1) duplicate elements.
 *      This program uses the following methods for finding duplicates:
 *
 *  HashSets
 *  SelectionSort
 *  QuickSort
 *  Boolean Array
 *
 *  Note: All methods outside of main are private because there is no reason to see the inner workings, and the methods
 *      are only being accessed from the DetermineDuplicateElements.class.
 */


public class DetermineDuplicateElements {

    public static void main(String[] args) {
    
		/** Prompt **/    	
        Scanner scnr = new Scanner(System.in);

        /* Prompt user for the desired length of array */
        System.out.println("Please enter your desired length of the array:");
        int arrayLength = scnr.nextInt();
        int[] randomArrayOfInt = new int[arrayLength];

        /* Prompt user for the range of numbers to be generated: 0 to m-1 */
        System.out.println("Please enter the highest possible value that can be shown in the array:");
        int highestValueInArray = scnr.nextInt();


        /** Create an array of randomized elements **/
        /* Create a random array of integers in the range specified*/
        System.out.println("Array generated from random values: ");
        Random randomInt = new Random();
        for (int i = 0; i < randomArrayOfInt.length; i++){
            /* Generates a value 0 to m-1, and input each value into the array position */
            randomArrayOfInt[i] = randomInt.nextInt(highestValueInArray);
        }

        /** Print Contents **/
        printArray(randomArrayOfInt);


        /** Check for duplicates **/
        /* Check for duplicates in unsorted array */
        long dupStartTime, dupEndTime;

        /* Timing Optimized Duplicate method in nanoseconds */
        dupStartTime = System.nanoTime();
        boolean hasDuplicate = optimizedDuplicates(randomArrayOfInt);
        dupEndTime = System.nanoTime();
        System.out.println(hasDuplicate);

        /** Selection Sort **/
        /* Copy original randomly generated array for selection sort */
        int[] selectionSortArr = Arrays.copyOf(randomArrayOfInt, randomArrayOfInt.length);
        
        /* Timing Selection Sort in nanoseconds */
        long selecStartTime, selecEndTime;

        selecStartTime = System.nanoTime();
        selectionSort(selectionSortArr);
        selecEndTime = System.nanoTime();

        /** Quick Sort **/
        /* Copy original randomly generated array to use quick sort */
        int[] quickSortArr = Arrays.copyOf(randomArrayOfInt, randomArrayOfInt.length);
        System.out.println("\nQuick Sort\nBefore Sorting");
        printArray(quickSortArr);

        int low = 0; //first element
        int high = quickSortArr.length-1; //last element

        /* Timer for Quick Sort in nanoseconds */
        long quickStartTime, quickEndTime;
        quickStartTime = System.nanoTime();
        quickSortArr = quickSort(quickSortArr, low, high);
        quickEndTime = System.nanoTime();

        /* Print results */
        System.out.println("\nArray after sorting:");
        printArray(quickSortArr);
        System.out.println(checkSortedDuplicates(quickSortArr));

        /** Find Duplicates using Boolean Array **/
        /* Create a boolean array that can be used to detect duplicates in unsorted array */
        int[] checkArrayElement = Arrays.copyOf(randomArrayOfInt, randomArrayOfInt.length);

        long boolStartTime, boolEndTime;
        boolStartTime = System.nanoTime();
        System.out.println(detectDuplicates(checkArrayElement));
        boolEndTime = System.nanoTime();

        /** Print nanoTime of sort methods, the optimized duplicate method, and finding duplicate for  **/
        System.out.println("\nTotal Amount of Time (n/s) ");
        System.out.println("Optimized Duplicate: " + (dupEndTime - dupStartTime));
        System.out.println("Selection Sort: " + (selecEndTime - selecStartTime));
        System.out.println("Quick Sort: " + (quickEndTime - quickStartTime));
        System.out.println("Boolean array: " + (boolEndTime - boolStartTime));

    }


    /* This method uses a HashSet in order to determine if non-unique integers are present. */
    private static boolean optimizedDuplicates(int[] randomElement) throws ArrayIndexOutOfBoundsException{
        System.out.println("\nCheck for duplicates:");

        HashSet<Integer> uniqueSet = new HashSet<>();
        /* Add each unique element into HashSet */
        for (int element : randomElement) {
            /* If the element is no longer unique */
            if (!uniqueSet.add(element)){
                /* Then duplicate has been found */
                return true;
            }
        }
        return false;
    }


    /* Prints all the elements within the given array */
    private static void printArray(int[] givenArray){
        for (int i = 0; i < givenArray.length; i++){
            System.out.print(givenArray[i] + " ");
        }
    }


    /* Selection sort method checks each element of the array against the adjacent elements until the
       smallest possible number in the array is found, and then it starts with the next number */
    private static void selectionSort(int[] sortArray){
        System.out.println("\nSelection Sort\nBefore Sorting");
        printArray(sortArray);

        /** Begin sorting **/
        int lowestIndex; //temp variable to hold smallest element

        for (int i = 0; i < sortArray.length-1; i++){

            /* Compare one element to the element before it to find the minimum */
            for (int j = i+1; j < sortArray.length; j++){
                /* if the prev element is greater than the element adjacent to it */
                if (sortArray[j] < sortArray[i]) {
                    /* swap elements to get the smallest element */
                    lowestIndex = sortArray[j];
                    /* element at index i is now the minimal element */
                    sortArray[j] = sortArray[i];
                    sortArray[i] = lowestIndex;

                }
            }

        }

        System.out.println("\nArray after sorting:");
        printArray(sortArray);

        /** Check for duplicates in now sorted array **/
        System.out.println(checkSortedDuplicates(sortArray));
    }


    /* Checks if a sorted array has duplicates */
    private static boolean checkSortedDuplicates(int[] sortedArray){
        System.out.println("\n\nAre there duplicates?");

        for (int i = 0; i < sortedArray.length-1; i++){
            if (sortedArray[i] == sortedArray[i+1])
                return true;
        }
        return false;
    }


    /* Finds a pivot point and separates the array into two lists. Once lower section and higher section are sorted
        then the array itself has been sorted */
    private static int[] quickSort(int[] sortArray, int lowerSubArray, int higherSubArray){

        /** Base Cases **/
        if (sortArray.length == 0) return sortArray;
        if (lowerSubArray >= higherSubArray) return sortArray;

        /** Find the middle or pivot point **/
        int pivot = sortArray[lowerSubArray + (higherSubArray - lowerSubArray)/2];

        /* Variables needed for dividing the array in half: lower than pivot vs higher than pivot */
        int i = lowerSubArray;
        int j = higherSubArray;

        while (i <= j){
            /** Separate into "sub" arrays **/
            /* all elements on the left side are lower than the pivot */
            while(sortArray[i] < pivot){
                i++;
            }

            /* all elements on the right side are higher than pivot */
            while(sortArray[j] > pivot){
                j--;
            }

            /* If a value is not in this respective side, swap values */
            if (i <= j){
                /** Swap **/
                int temp = sortArray[i];
                sortArray[i] = sortArray[j];
                sortArray[j] = temp;
                i++; //starting from the leftmost index, move pointer to the right
                j--; //starting from the rightmost index, move pointer inward
            }

            /** Use recursion to finish sorting **/
            if (lowerSubArray < j){
                quickSort(sortArray, lowerSubArray, j);
            }

            if (higherSubArray > i){
                quickSort(sortArray, i, higherSubArray);
            }
        }

        return sortArray;
    }


    /* Uses boolean array in order to indicate if an element has been seen before. Then determine duplicates */
    private static boolean detectDuplicates(int[] unsortedArray){
        System.out.println("\nBoolean Array\nGiven Array:");
        printArray(unsortedArray);

        System.out.println("\nThe array as a boolean:");
        boolean[] checkElement = new boolean[unsortedArray.length];

		/** Search if there are duplicates **/
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < unsortedArray.length; i++){
            /* If duplicate is found, then it is not added to the table */
            if (!set.add(unsortedArray[i])){
                checkElement[i] = true;
            }
            else{
                checkElement[i] = false;
            }
        }


        /** Print contents of boolean array **/
        for (boolean check : checkElement){
            System.out.print(check + " ");
        }


        /** Check Duplicates **/
        System.out.println("\nDuplicates present? ");
        for (int i = 0; i < checkElement.length; i++){
            if (checkElement[i]) {
                return true;
            }

        }

        return false;

    }

}
