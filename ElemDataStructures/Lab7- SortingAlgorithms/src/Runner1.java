import java.util.Random;

/**
 *
 * Used with Runner2.java, TestSortPlot.pdf
 *
 * The purpose of this lab is to implement two versions of two sorting
 algorithm and sort an array of strings in lexicographic order.
 *
 * The purpose of the Runner1 class is to hold all the methods needed:
 *  Generate a random array of strings of a given length
 *  Print the new array
 *  Copy the array
 *  Implement copied arrays into the bubble and selection sort methods
 */

public class Runner1 {


    /* This method will generate an array of random strings */
    /* Takes a number representing size of list, and return an array of that many strings */
    public static String[] randomGen(int lengthOfList){

        String chooseChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random r = new Random();

        /* Used to create strings from characters */
        String[] ranStr = new String[lengthOfList];
        for (int i = 0; i < lengthOfList; i++){
            /* generate a random length between 3 and 10 */
            int ranLength = r.nextInt(8) + 3;

            /* Temp variable to hold characters for the string */
            String temp = "";


            for (int j = 0; j < ranLength; j++){
                /* append characters to the string ; once that is finish append to a new string */
                temp += Character.toString(chooseChar.charAt(r.nextInt(chooseChar.length())));
            }

            /* Add strings into its respective index of whatever size the array is */
            ranStr[i] = temp;
        }

        /* return the newly generated random string array */
        return ranStr;
    }

    /* Print the array of strings, one line each string */
    public static void printStrings(String[] randomStrList){

        for (int i = 0; i < randomStrList.length; i++){
            System.out.println(randomStrList[i]);
        }

    }

    /* Create a new array of strings and copy the given strings */
    public static String[] copyArray(String[] randomStr){

        /* Use the .clone method to copy the values of original string into another */
        String[] copyStr = randomStr.clone();

        /* return the copy of the array */
        return copyStr;
    }

    /* Bubble Sort Method sorts in lexiographic order */
    public static void bubbleSort(String[] copiedA){
        System.out.println("\nBubble Sort\nBefore Sorting:");
        //String[] copiedA = copyArray(sortA);
        printStrings(copiedA);

        for (int i = 0; i < copiedA.length-1; i++){ //take the first element of the given array
            for (int j = 0; j < copiedA.length-i-1; j++){ //and also the one adjacent to it
                if(copiedA[j].compareToIgnoreCase(copiedA[j + 1]) > 0){ //compare. If greater
                    String temp = copiedA[j]; //swap elements so that they are ordered
                    copiedA[j] = copiedA[j + 1];
                    copiedA[j + 1] = temp;
                } //and then move on to the next pair of elements
            }
        }

        /* Print new list */
        System.out.println("\nAfter Sorting:");
        printStrings(copiedA); //print newly ordered list

    }

    /* Selection Sort Method sorts in lexiographic order */
    public static void selectionSort(String[] copiedB){
        System.out.println("\nSelection Sort\nBefore Sorting:");
        /* Print strings before sorting */
        printStrings(copiedB);

        /* temp variable to keep track of the minimum value found in the array */
        String minValue;

       for (int i = 0; i < copiedB.length-1; i++){ //take the first element
           for (int j = i+1; j < copiedB.length; j++){ //and the one adjacent to it
               if(copiedB[i].compareTo(copiedB[j]) > 0){ //compare them. If smaller
                   minValue = copiedB[j]; //swap
                   copiedB[j] = copiedB[i];
                   copiedB[i] = minValue;
               } //continue until end of list is reached
           }
       }

        /* print new list */
        System.out.println("\nAfter Sorting:");
        printStrings(copiedB);
    }

}
