import java.util.*;

/*
* Finite Automata Program
*
* */

public class finiteAutomata {

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        System.out.println("Please enter the number of states:");
        int n = scnr.nextInt();

        System.out.println("Please enter the number of symbols:");
        int m = scnr.nextInt();

        int[][] state = new int[n][m];
        int[] character = new int[m]; // User enters words for automata
        boolean[] finalState = {false, true, false};

        System.out.println("Please input your desired word. After each char, press enter: ");
        for (int i = 0; i < m; i++){
            character[i] = scnr.nextInt();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if ((i == 0 || i == 1) && (j == 0 || j == 1)) {
                    state[i][j] = 1;
                } else {
                    state[i][j] = 2;
                }
            }
        }

        printState(state);
        System.out.println();
        finiteAuto(state, finalState, character);
    }

    public static void printState(int[][] state){
        for (int i = 0; i < state.length; i++){
            for (int j = 0; j < state[i].length; j++){
                System.out.print(state[i][j]);
            }
            System.out.println();
        }
    }

    public static void finiteAuto(int[][] state, boolean[] finalState, int[] word){

        int q = 0; // initial state is 0
        for (int i = 0; i < word.length; i++){
            System.out.println("Current State: " + q); // current state
            System.out.println("Final State: " + finalState[q]); // final state
            q = state[q][word[i]];
        }
        System.out.println("Current State: " + q);
        System.out.println("Final State: " + finalState[q]);
    }
}
