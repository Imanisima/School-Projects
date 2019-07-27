import java.io.*;

/**
 * The purpose of this lab is to compute postfix expressions.
*
 *
 */


public class Evaluator {


    public static void main(String[] args) throws Exception {
        GenericStack stack = new GenericStack();

        /* File Reader */
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        String line;
        int counter = 0;

            /* While end of file is not reached, count number of lines*/
            try{
        while ((line = br.readLine()) != null) {
            String[] numOfEquations = line.split("//s");

                for (int i = 0; i < numOfEquations.length; i++) {

                    if (!numOfEquations[i].equals("+") || !numOfEquations[i].equals("-") || !numOfEquations[i].equals("*") || !numOfEquations[i].equals("/")) {
                        int temp = Integer.parseInt(numOfEquations[i]);
                        stack.push(temp);
                    } else {
                        /* Keeps track of numbers within expression from the file */
                        int operandA, operandB;

                        operandA = (int) stack.pop();
                        operandB = (int) stack.pop();

                            switch (numOfEquations[i]) {
                                case "+":
                                    stack.push(operandA + operandB);
                                    break;

                                case "-":
                                    stack.push(operandB - operandA);
                                    break;

                                case "*":
                                    stack.push(operandA * operandB);

                                case "/":
                                    stack.push(operandB / operandA);
                                    break;
                            }
                    }
                }

            counter++;
        }

        boolean isNumber = checkIfNumber(counter, stack);
        if (isNumber){
            Object temp = stack.pop();
            System.out.println(temp);
        }

    } catch (Exception e) {
                System.out.print("Error.");
            }
    }

    /* Check if the character in the expression is an operator */
    private static boolean checkIfNumber(int counter, GenericStack stack){
        Object temp = stack.pop();

        if (stack.isEmpty()){
            for (int i = 0; i < counter; i++){
                stack.push(temp);
            }
            return true;
        }
        else{
            return false;
        }

    }

}
