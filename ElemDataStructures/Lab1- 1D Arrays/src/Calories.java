/**
 * This program will track the calorie intake of a person by reading
 * the file given and calculating the amount consumed.
 *
 * Note:
 * There must be three columns: Breakfast, Lunch, and Dinner
 * There must be seven rows: Monday-Sunday
 */


import java.io.*;
import java.text.DecimalFormat;
import java.util.*;


public class Calories {
    public static void main(String[] args) {
        /* My Arrays */
        List<String> daysOfWeek = new ArrayList<String>();
        //Add Monday-Sunday to the daysOfWeek list:
        daysOfWeek.add("Monday");
        daysOfWeek.add("Tuesday");
        daysOfWeek.add("Wednesday");
        daysOfWeek.add("Thursday");
        daysOfWeek.add("Friday");
        daysOfWeek.add("Saturday");
        daysOfWeek.add("Sunday");

        List<Double> breakfast = new ArrayList<Double>();
        List<Double> lunch = new ArrayList<Double>();
        List<Double> dinner = new ArrayList<Double>();

        /* FileReader */
        Scanner scnr = null;
        try {
            scnr = new Scanner(new File("input.txt"));
            while (scnr.hasNext()) {
                breakfast.add(scnr.nextDouble());
                lunch.add(scnr.nextDouble());
                dinner.add(scnr.nextDouble());
            }
        } catch (Exception e) {
            System.out.println();

            System.out.println("FATAL ERROR FOUND :(");
            System.out.println("There is a number or numbers missing from a column.");
            System.out.println("Program will now terminate.");
            System.exit(0);

        }

        //close Scanner
        scnr.close();

        /* Call Method to Print Lists */
        printList(breakfast, lunch, dinner, daysOfWeek);

        System.out.println();

        /* Call Method for Calorie Over Consumption */
        OverCal(breakfast, lunch, dinner, daysOfWeek);

        System.out.println();

        /* Call Method for Averaging Each of the Three Meals */
        avgCal(breakfast, lunch, dinner);
    }

    /* PrintList Method prints all the calories from each column */
    static void printList(List<Double> breakfast, List<Double> lunch, List<Double> dinner, List<String> daysOfWeek) {

        try {
            //title of the "table"
            System.out.println("Calories For The Day");

            //Column One: Breakfast Calories
            System.out.println("Breakfast:");
            for (int i = 0; i < daysOfWeek.size(); i++) {
                for (int k = 0; k < 1; k++) { //allocates each day to the given calorie amount
                    System.out.println(" " + daysOfWeek.get(i) + ": " + breakfast.get(i));
                }
            }

            System.out.println();

            //Column Two: Lunch Calories
            System.out.println("Lunch:");
            for (int i = 0; i < daysOfWeek.size(); i++) {
                for (int k = 0; k < 1; k++) {
                    System.out.println(" " + daysOfWeek.get(i) + ": " + lunch.get(i));
                }
            }

            System.out.println();

            //Column Three: Dinner Calories
            System.out.println("Dinner:");
            for (int i = 0; i < daysOfWeek.size(); i++) {
                for (int k = 0; k < 1; k++) {
                    System.out.println(" " + daysOfWeek.get(i) + ": " + dinner.get(i));
                }
            }
        }catch(IndexOutOfBoundsException ie){
            System.out.println();

            System.out.println("FATAL ERROR FOUND :(");
            System.out.println("There is a line or lines missing.");
            System.out.println("Program will now terminate.");
            System.exit(0);
        }
    }

    /* Method for Over Consumption: Tells which days are over 2250 calories */
    static void OverCal(List<Double> Morning, List<Double> Afternoon, List<Double> Evening, List<String> daysOfWeek) {
        double balancedDiet = 2250;
        double totalCal = 0;

        System.out.println("Days In Which Calorie Consumption is Over 2,250");

        //Add each meal together and attach it to the given day
        for (int i = 0; i < Morning.size(); i++) {
            totalCal = Morning.get(i) + Afternoon.get(i) + Evening.get(i);

            //Prints the days of which the user has consumed over 2,250 calories
            if (totalCal > balancedDiet) {
                System.out.println(daysOfWeek.get(i) + ": " + totalCal);
            }
        }

    }

    /* This method averages the amount of calories consumed for each meal */
    static void avgCal(List<Double> Breakfast, List<Double> Lunch, List<Double> Dinner){
        double sum = 0;
        double avg;
        DecimalFormat df = new DecimalFormat("#0.00");

        System.out.println("Average Calories Consumed During Each Meal");

        //Breakfast
        for (int i = 0; i < Breakfast.size(); i++){
            sum = sum + Breakfast.get(i);
        }
        avg = sum / Breakfast.size();
        System.out.println("Breakfast: " + df.format(avg));

        //Lunch
        for (int i = 0; i < Lunch.size(); i++){
            sum = sum + Lunch.get(i);
        }
        avg = sum / Lunch.size();
        System.out.println("Lunch: " + df.format(avg));

        //Dinner
        for (int i = 0; i < Dinner.size(); i++){
            sum = sum + Dinner.get(i);
        }
        avg = sum / Dinner.size();
        System.out.println("Dinner: " + df.format(avg));
    }
}
