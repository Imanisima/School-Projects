import java.util.Random;

/**
 *
 * The purpose of this lab is to write methods using recursion and linked lists, without using loops within methods.
 * In this lab I also generate a random string to be passed through.
 *
 * Used with: StringNode.java
 *
 */


public class Operations {

    public static void main(String[] args) throws NullPointerException{

        StringNode L = new StringNode("0" + getRandString(2 + (int)(Math.random()*5)));
        StringNode temp = L;
        for (int i = 1; i <= 9; i++){
            temp.next = new StringNode(i + getRandString(2 + (int)(Math.random() * 5)));
            temp = temp.next;
        }

        /* Print the Given Strings */
        System.out.println("\nAll Strings in the List");
        printList(L);
        System.out.println();

        /* Check if list of strings are sorted */
        boolean sortList = isListOrdered(L);
        System.out.println("Is List Ordered? " + sortList);
        System.out.println();

        /* Prints the length of strings */
        System.out.println("Strings of 'K' Length");
        System.out.println("k\t Number of Strings with 'k' length: ");
        System.out.println("---------------------------------------");
        for (int k = 0; k < 7; k++){
            System.out.println(k + "\t" + countKLengthStrings(L, k));

        }

        /* Prints Longest String of the List */
        System.out.println("\nLongest String in List\nString:\t\tLength:\n" + longestStringOfList(L) + "\t\t" + lengthOfString(L));

        /* Reverses Original String */
        System.out.println("\nGiven String in Reverse: ");
        L = reverseList(L);
        printList(L);
        System.out.println();

        /* Remove the String Indicated */
        System.out.println("Remove Given String");
        StringNode LL = removeString(L, L.next.next.head);

        /* Prints results after removing string */
        System.out.println("Strings in New List");
        printList(LL);
        System.out.println();

        /* prints original list (reversed) */
        System.out.println("Strings in Previous List");
        printList(L);
        System.out.println();

        /* Insert String into Given Position within list and prints the revised version */
        System.out.println("Insert String into New List");
        LL = insertString(LL, "Hello World!", 3);
        printList(LL);
        System.out.println();

        /* Check if list is in order */
        sortList = isListOrdered(L);
        System.out.println("Is List Ordered? " + sortList);
        System.out.println();

        /* Insert String into indicated position */
        LL = insertString(LL, "ABBA", 2);
        LL = insertString(LL, "DoGeeseSeeGod", 3);

        /* Count number of palindromes present */
        int amountOfPalin = countPalindromes(LL);
        System.out.println("There are " + amountOfPalin + " palindromes present.");

    }

    /* -------------------------------------------------------------------------------- */


    /* Returns the string that only contains 26 capital english letters */
    private static String getRandString(int length){
        /* Create an array of strings with desired length */
        //char[] strLen = new char[length];
        /* Choose what characters are desired to be part of the random selection */
        String chooseChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        Random r = new Random();

        /* Use string builder to append characters into string, literally created strings from characters */
        StringBuilder ranStr = new StringBuilder();
        for (int i = 0; i < length; i++){
            int ranChar = r.nextInt(chooseChar.length());
            ranStr.append(chooseChar.charAt(ranChar));
        }

       /* return actual string instead of address */
        return ranStr.toString();
    }

    /* Prints all strings present in the list. Only the head is passed */
    private static void printList(StringNode m){
        /* Checks if list is empty, and return empty */
        if (m == null)
            return;

        /* Print the first String of the list */
        System.out.println(m.head);

        /* Send the rest through to be printed */
        printList(m.next);
    }

    /* Returns the number of Strings with given length within linked list */
    private static int countKLengthStrings(StringNode m, int k){
        /* Check if list is empty */
        if (m == null)
            return 0;

        /* recursively returns strings in the list that are equal in length*/
        else if (m.head.length() == k) {
            /* Continues to iterate through until the end is reached */
            return 1 + countKLengthStrings(m.next, k);
        }

        /* Return final count */
        return countKLengthStrings(m.next, k);
    }

    /* Returns the longest String in the list */
    private static String longestStringOfList(StringNode m){
        /* Check if list is empty */
        if (m != null){
            /* String in next position after current is saved for temporary value */
            String longestStr = longestStringOfList(m.next);

            /* Compare values of the string, if current is greater than the temp */
            if (m.head.length() > longestStr.length())
                /* return current string */
                return m.head;

            /* Else return the temp */
            return longestStr;
        }

        /* else return nothing */
        return "Empty";
    }

    /* Returns the length of the given linked list */
    private static int lengthOfString(StringNode m){
        /* Check if list is empty, otherwise length of string does not exist */
        if (m == null)
            return 0;

        /* Takes all the lengths [staircase principle] and adds the total at the end */
        return 1 + lengthOfString(m.next);
    }

    /* Reverses the list and returns the head of the now reverse list */
    private static StringNode reverseList(StringNode m){

        /* If head is null, return the head */
        if ((m == null) || (m.next == null))
            return m;

        /* Creates a sublist so that the list is reversed */
        StringNode newList = reverseList(m.next);
        /* Move head to end, while next element points to the end of list */
        m.next.next = m;
        /* Last node points to null, indicating end of list is reached */
        m.next = null;

        /* return new head of the list */
        return newList;
    }

    /* Remove the first occurrence of the target string from the list */
    private static StringNode removeString(StringNode m, String removee){
        if (m == null) {
            System.out.println("There is nothing to remove.");
            return null;
        }

        if (m.head.equals(removee))
            return m.next;

        return new StringNode(m.head, removeString(m.next, removee));
    }

    /* Inserts a string in a specific position of the linked list */
    private static StringNode insertString(StringNode m, String insertee, int position){
        /* Check if position is the first of the list */
        if (position == 0) {
            /* Create a sublist that includes the string desired */
            StringNode newList = new StringNode(insertee);
            /* Set the string/node after the inserted node to the previous head (putting it into position 1) */
            newList.next = m;
            /* Return new head */
            return newList;
        }

        /* As long as m (head) is not empty */
        if (m != null){
            /* Pass in the rest of the list */
            m.next = insertString(m.next, insertee, position-1);

            /* Return the head */
            return m;
        }

        /* If the insertee falls in none of these conditions, return nothing6 */
        return null;
    }

    /* Checks if the list is in order */
    private static boolean isListOrdered(StringNode m) {

        /* If list is empty, it is technically already ordered */
        if (m == null || m.next == null)
            return true; //base case

        /* if head is less than the next one, then it is ordered */
        else if ((m.head.compareToIgnoreCase(m.next.head) < 0))
                isListOrdered(m.next); //base case

        /* Otherwise it is not ordered */
        else {
            return false;
            }

        /* When end is reached, return true */
        return true;

    }

    /* Checks if string is palindrome */
    private static boolean isPalindrome(String s){
        /* Check is not case sensitive! */
        if ((s.length() == 0) || (s.length() == 1))
            return true; //base case

        /* Check if first character is equal to last */
        if (s.charAt(0) == s.charAt(s.length()-1))
            return isPalindrome(s.substring(1, s.length() - 1));

        return false;
    }

    /* Counts how many strings are palindromes */
    private static int countPalindromes(StringNode m){
        int counter = 0;

        if (m == null)
            return counter;

        if (m != null){
            if (isPalindrome(m.head))
                return 1 + countPalindromes(m.next);
           else
               return countPalindromes(m.next);
        }

        return counter;
    }

}
