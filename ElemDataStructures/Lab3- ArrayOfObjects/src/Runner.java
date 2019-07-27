import java.text.DecimalFormat;
import java.io.*;

/**
 * Used with Package.java file
 *
 * This file will read the given text file and create an array
 * of objects called Package.
 * This method will also:
 * Find the largest package in the array and report index, dimensions, and volume
 * Indicate how many cubic, non-cubic packages are in the array
 * Report indices and dimensions of the CUBIC packages
 * Report average volume of CUBIC packages ONLY
 */


public class Runner {

    public static void main(String[] args) throws IOException {
        try {
            DecimalFormat df = new DecimalFormat("#0.00");


        /* File Reader */
            BufferedReader readFile = new BufferedReader(new FileReader("input.txt"));
            int numOfRows = 0; //counter for counting number of rows

            while (readFile.ready()) { //starts when file is not null and counts number of lines
                readFile.readLine();
                numOfRows++;
            }
            readFile.close();


        /* Package is created */
            Package[] inventory = new Package[numOfRows];
            //readFile = new BufferedReader(new FileReader("input.txt"));

            System.out.println("Summarization of El Paso Packaging and Supply Co. Inventory\nThere are currently a total of [" + numOfRows + "] packages!");
            System.out.println("------------------------------------------------------------");
            System.out.print("\t  Package# \t\t   Cubic  \t\t   Volume");


        /* This loop creates several packages */
            for (int i = 0; i < numOfRows; i++) {
                String splitRow[] = readFile.readLine().split(" "); //splits at the end of each line

                //divides the row: width, height, length
                inventory[i] = new Package(Double.parseDouble(splitRow[0]), Double.parseDouble(splitRow[1]), Double.parseDouble(splitRow[2]));
                inventory[i].setDim(Double.parseDouble(splitRow[0]), Double.parseDouble(splitRow[1]), Double.parseDouble(splitRow[2]));

                System.out.print("\n\tPackage# " + (i + 1) + ": ");
                System.out.print("\t\tCubic? " + inventory[i].isCube());
                System.out.print("\t\tVolume: " + df.format(inventory[i].getVolume()));
            }

            System.out.println("\n");

        /* Largest Package */
            System.out.println("The Largest Package");
            System.out.println("-----------------------------------------");
            largestPackage(inventory);

        /* Information Regarding the Cubic Packages */
            System.out.println("\n\nCubic Packages\n\t\t w    h     l     v(avg)");
            System.out.println("-------------------------------------------------------");
            System.out.println("Index#");

        /* Counters for calculating the number of cubic and non-cubic packages */
            int notCubic = 0;
            int isCubic = 0;

            //Loop for determining if cubic/non-cubic packages
            for (Package num : inventory) {

            /* If not Cubic */
                if (!num.isCube()) {
                    notCubic++; //increase total of non-cubics
                }

            /* If Cubic */
                else {
                    isCubic++; //increase the total of cubics
                    cubicDim(inventory);

                }
            }

        /* Prints the number of cubic and non-cubic packages */
            System.out.println("\nTotal # of Cubic and Non-Cubic Packages");
            System.out.println("----------------------------------------");
            System.out.println("Cubic\t Non-Cubic");
            System.out.println(isCubic + "\t\t " + notCubic);
            System.out.println();

        } catch (Exception e){
            System.out.println("\n\nFATAL ERROR FOUND: NEED AT LEAST THREE DIMENSIONS!");
        }
    }

    /* This method calculates the largest package */
    public static void largestPackage(Package[] inventory) {
        double currentPackage = inventory[0].getVolume();
        int tmp = 0;
        for (int i = 1; i < inventory.length; i++) {
            if ((inventory[i].getVolume()) > currentPackage) {
                currentPackage = inventory[i].getVolume();
                tmp = i;
            }
        }

        /* Prints Dimensions of Largest Package */
        double[] largestDim = inventory[tmp].packDim();
        System.out.println("Index#:\t" + tmp);
        System.out.println("Volume:\t" + currentPackage);
        System.out.print("Dimensions (w x h x l):\t");
        for (double dim : largestDim) {
            System.out.print(dim + " ");
        }
    }

    /* This method prints the indices and dimensions of the cubic packages */
    public static void cubicDim(Package[] inventory){
        DecimalFormat df = new DecimalFormat("#0.00");

        for(int i = 0;i < inventory.length; i++){
            if((inventory[i].isCube())){
                System.out.print(i + "\t\t");

                /* Print Dimensions of Cubic Package */
                double[] cubicDim = inventory[i].packDim();
                for (double dim : cubicDim){
                    System.out.print(dim + "  ");
                }

                /* Print Average Volume */
                double product = inventory[i].getVolume();
                double avgVol = product/3;
                System.out.println(df.format(avgVol));
            }

        }
    }

}
