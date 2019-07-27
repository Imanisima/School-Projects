/**
  *
 * Purpose: To solve the partition problem using backtracking,
 *  create a randomized algorithm to check various trigonometric expressions.
 *
 */


public class AlgoDesignTechniques {

    public static void main(String[] args) {
        /** Partition Problem **/
        System.out.println("Partition Problem");
        int[] set = {2, 1, 3, 8, 9};
        System.out.print("Given set: ");
        for (int i = 0; i < set.length; i++)
            System.out.print(set[i] + " ");

        System.out.println("Is there a partition? " + partitionProblem(set, set.length));


        /** Randomized Algo **/
        System.out.println("\nRandomization Algorithm");
        System.out.println("Tangent\ntan(x) = sin(x)/cos(x): " + checkTan());
        System.out.println("sin(x)/cos(x) = 1/cos(x): " + checkTan2());

        System.out.println("\nPythagorean Identities");
        System.out.println("sin^2(x) + cos^2(x) = 1: " + pythag());
        System.out.println("tan^2(x) + 1 = csc^2(x): " + pythag2());

        System.out.println("\nCofunction Formulas");
        System.out.println("sin(pi/2 - x) = cos(x): " + coFunc());
        System.out.println("tan(pi/2 - x) = cos(x): " + coFunc2());
    }

    /** Partition Problem **/
    /* Solving the partition problem using backtracking */
    public static boolean partitionProblem(int[] set, int setLength){
        int sum = 0;
        /* Add all elements within the array */
        for(int i = 0; i < setLength; i++)
            sum += set[i];

        System.out.println("\nGoal: " + sum);

        /* Check if sum is odd. If so, the set cannot be partitioned into two */
        if((sum % 2) != 0) return false;

        /* Otherwise check if there is a subset */
        return subsetSum(set, setLength, sum/2);
    }

    public static boolean subsetSum(int[] set, int length, int sum){
        if (sum == 0) return true;
        if (length < 0 || sum < 0) return false;

        /* Check if last element is larger than total */
        if(set[length - 1] > sum)
            return subsetSum(set, length-1, sum);

        /* Include last element or ignore last element */
        return subsetSum(set, length - 1, sum) || subsetSum(set, length-1, sum - set[length - 1]);
    }

    /** Randomization Algorithm **/
    /** Tan **/
    public static boolean checkTan(){
        double theta;
        for (int i = 0; i < 1000; i++){
            theta = (Math.random()-0.5)*2000;
            if(Math.abs(f(theta) - g(theta)) > 0.0001){
                return false;
            }
        }
        return true;
    }

    public static double f(double theta){
        return Math.sin(theta)/Math.cos(theta);
    }

    public static double g(double theta){
        return Math.tan(theta);
    }

    /* Test 2 */
    public static boolean checkTan2(){
        double theta;
        for (int i = 0; i < 1000; i++){
            theta = (Math.random()-0.5)*2000;
            if(Math.abs(f2(theta) - g2(theta)) > 0.0001){
                return false;
            }
        }
        return true;
    }

    public static double f2(double theta){
        return Math.sin(theta)/Math.cos(theta);
    }

    public static double g2(double theta){
        return (1/Math.cos(theta));
    }

    /** Pythagorean Identities **/
    public static boolean pythag(){
        double theta;
        for (int i = 0; i < 1000; i++){
            theta = (Math.random()-0.5)*2000;
            if(Math.abs(f3(theta) - g3(theta)) > 0.0001){
                return false;
            }
        }
        return true;
    }

    public static double f3(double theta){
        return (Math.sin(theta)*Math.sin(theta)) + (Math.cos(theta)*Math.cos(theta));
    }

    public static double g3(double theta){
        return 1;
    }

    /* Test 2 */
    public static boolean pythag2(){
        double theta;
        for (int i = 0; i < 1000; i++){
            theta = (Math.random()-0.5)*2000;
            if(Math.abs(f4(theta) - g4(theta)) > 0.0001){
                return false;
            }
        }
        return true;
    }

    public static double f4(double theta){
        return (Math.tan(theta)*Math.tan(theta)) + 1;
    }

    public static double g4(double theta){
        return ((1/Math.sin(theta))*(1/Math.sin(theta)));
    }

    /** Cofunction Formulas **/
    public static boolean coFunc(){
        double theta;
        for (int i = 0; i < 1000; i++){
            theta = (Math.random()-0.5)*2000;
            if(Math.abs(f5(theta) - g5(theta)) > 0.0001){
                return false;
            }
        }
        return true;
    }

    public static double f5(double theta){
        return Math.sin((Math.PI/2) - theta);
    }

    public static double g5(double theta){
        return Math.cos(theta);
    }

    /* Test 2 */
    public static boolean coFunc2(){
        double theta;
        for (int i = 0; i < 1000; i++){
            theta = (Math.random()-0.5)*2000;
            if(Math.abs(f6(theta) - g6(theta)) > 0.0001){
                return false;
            }
        }
        return true;
    }

    public static double f6(double theta){
        return (Math.tan(Math.PI/2) - theta);
    }

    public static double g6(double theta){
        return Math.cos(theta);
    }
}
