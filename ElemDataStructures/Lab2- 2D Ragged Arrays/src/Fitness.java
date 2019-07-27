/**
 *
 * This program will track calorie intake using 2-D Ragged Arrays
 * Three columns: Snacks/Meals
 * Seven rows: Monday-Sunday
 */

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.text.DecimalFormat;

public class Fitness {

    public static void main(String[] args) throws Exception {
        try {

            /* FileReader */

            //This array will read the entire file and determine the length of all lines
            List<String> readFile = Files.readAllLines(Paths.get("input.txt"));

            //readFile.size() -> amount of rows read from above file
            double[][] calIntake = new double[readFile.size()][];

            //figure out each column by iterating through each row
            for (int i = 0; i < readFile.size(); i++) {

                //Once the end of a line is reached, it is split
                String[] split = readFile.get(i).split("\\s");

                //use split to determine the number of columns in each row
                calIntake[i] = new double[split.length];

                for (int j = 0; j < split.length; j++) {
                    //convert each element to double
                    calIntake[i][j] = Double.parseDouble(split[j]);

                }

            }


            totalCal(calIntake);
            System.out.println();

            overConsump(calIntake);
            System.out.println();
            avgCal(calIntake);

        } catch (Exception e){
            System.out.println("FATAL ERROR FOUND:");
            System.out.println("THERE MUST BE EXACTLY 7 LINES WITHIN THE FILE.\nPROGRAM TERMINATED.");
        }
    }

    /* Method for printing total calories consumed each day */
    static void totalCal(double[][] calIntake){

        //Days of the week
        String[] daysOfWeek = {
                "Monday", "Tuesday", "Wednesday",
                "Thursday", "Friday", "Saturday", "Sunday"
        };

        System.out.println("Total Calories Consumed Each Day:");
        double sum = 0;
        for (int i = 0; i < calIntake.length; i++){
            System.out.print(daysOfWeek[i] + ": ");
            for (int j = 0; j < calIntake[i].length; j++){
                sum = sum + calIntake[i][j];
            }
            System.out.println(sum);
            sum = 0;
        }

    }

    /* Days in which over consumption occurs */
    static void overConsump(double[][] calIntake){
        System.out.println("Days In Which There Is An Over Consumption Of Calories:");
        double balance = 2250.0;
        double sum = 0;

        String[] daysOfWeek = {
                "Monday", "Tuesday", "Wednesday",
                "Thursday", "Friday", "Saturday", "Sunday"
        };

        for (int i = 0; i < calIntake.length; i++) {
            for (int j = 0; j < calIntake[i].length; j++) {
                sum = sum + calIntake[i][j];
            }
            if (sum > balance) {
                System.out.println(daysOfWeek[i] + ": " + sum);
            }
            sum = 0;
        }
    }

    /* Finds the length of the longest row for average*/
    public static int lengthOfLongest(double[][] calIntake) {
        int longest = calIntake[0].length;
        for (int i = 1; i < calIntake.length; i++){
            if (calIntake[i].length > longest){
                longest = calIntake[i].length;
            }
        }
        return longest;
    }

    /* Average calories consumed each column */
    static void avgCal(double[][] calIntake){
        System.out.println("Average Calories Consumed For Each Meal");
        DecimalFormat df = new DecimalFormat("#0.00");

        //Determine the length of the longest row
        int len = lengthOfLongest(calIntake);

        double[] colSums = new double[len]; //sum of each column
        double[] elemPerCol = new double[len]; //element per column

        for (int i = 0; i < calIntake.length; i++){
            for (int j = 0; j < calIntake[i].length; j++){
                colSums[j] = colSums[j] + calIntake[i][j];
                elemPerCol[j]++;
            }
        }

        for (int col = 0; col < colSums.length; col++) {
            System.out.println("Meal/Snack " + (col + 1) + ": " + df.format(colSums[col] / elemPerCol[col]));
        }
    }
}
