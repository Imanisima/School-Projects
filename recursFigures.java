/**
 * Name: Imani Martin
 * ID: 80576125
 * Class: CS2302
 * Professor: Dr. Olac Fuentes
 * TA: Zakia Al Kadri
 * Lab #: 1
 * Due: Wednesday, Jan. 31, 18
 * Used with: StdDraw.java
 *
 * Purpose: The purpose of this lab is to draw figures/fractals recursively using the StdDraw Class. Geometry and basic algebra
 *  are necessary in order to create some of the shapes. A slideshow has been implemented into the main method. If at any
 *  reason there are "zero" number of levels, the slideshow will skip over that figure and go to the next one.
 */


public class recursFigures {

    public static void main(String[] args) {

        double radius = 50; // overall size of the original shape

        /* Scale of the window for the graphic to appear */
        StdDraw.setXscale(0, 100);
        StdDraw.setYscale(0, 100);
        StdDraw.setPenColor(StdDraw.BLACK);

        /* Spiral circle */
        int numberOfSpirals = 12;
        drawSpiral(numberOfSpirals, radius, 50, 50);

        /* Clears the windows so that the next figure can appear */
        StdDraw.clear();

        /* Side Spiral of a circle*/
        int numberOfSideSpirals = 30;
        drawSideSpiral(numberOfSideSpirals, 50, 50, radius);

        StdDraw.clear();

        /* Coordinates of the four points of the square*/
        double[][] coordinatesForSquareSpiral = {{0,0},{0,100},{100,100},{100,0}};

        /* Spiral Square*/
        int numOfSquares = 25;
        drawSquareSpiral(numOfSquares, coordinatesForSquareSpiral);

        StdDraw.clear();

        /* Binary Tree -> Make sure to make the numOfLevels and the divideSpace equal to one anoter*/
        int numOfLevels = 3;
        /* divide space allows the branches to spread equally across the screen*/
        int divideSpace = 3;
        drawBinaryTrees(numOfLevels, 50, 100, 0, divideSpace);

        StdDraw.clear();

        /* Circles with inner circles in the shape of a cross*/
        int numOfCircles = 4;
        drawCircCross(numOfCircles, 50, 50, radius);

        StdDraw.clear();

        /* Once the slideshow ends the program will automatically terminate */
        System.exit(0);

    }

    /* 1) Recursively draws a circle within another */
    static void drawSpiral(int n, double radius, double x, double y){
        if (n == 0) return;

        /* Creates original circle */
        StdDraw.circle(x,y,radius);

        if (n > 0) {
            /* Multiply radius by a percentage in order to obtain inner circles */
            drawSpiral(n - 1, radius*0.85, x, y);
        }

        /* Figure stays on screen for approximately 4 seconds before going to the next one */
        StdDraw.pause(300);
    }

    /* 2) Recursively draws a side spiral within another; pattern looks like a clam*/
    static void drawSideSpiral(int n, double x, double y, double radius){
        if (n == 0) return;

        /* Create original circle */
        StdDraw.circle(x, y, radius);

        if (n > 0) {
            /* Radius is multiplied by a percentage in order to get inner circles
             * Radius is subtracted from x (horizontal coordinate) to move center of circle to edge */
            drawSideSpiral(n - 1, x - (radius * 0.1), y, radius - (radius * 0.1));

        }

        /* Figure stays on screen for approximately 4 seconds before going to the next one */
        StdDraw.pause(300);
    }

    /* 3) Recursively draws a square spiral */
    static void drawSquareSpiral(int n, double[][] coordinates){
        if (n == 0) return;

        if(n > 0) {

            /* Draw lines at each coordinate to create a square
            * coordinates[(i + 1)%4][0] is used to get the remaining coordinates of the square
            * */
            for (int i = 0; i < 4; i++) {
                StdDraw.line(coordinates[i][0], coordinates[i][1], coordinates[(i + 1) % 4][0], coordinates[(i + 1) % 4][1]);
            }

            /* Call getAngles() method to get the average points for the inner squares */
            coordinates = getAngles(coordinates);
            drawSquareSpiral(n - 1, coordinates);

        }

        /* Figure stays on screen for approximately 4 seconds before going to the next one */
        StdDraw.pause(200);
    }

    /* 3a) This method takes coordinates and obtains the average. This average is then weighted, creating the square-spiral design */
    static double[][] getAngles(double[][] coordinates){
        /* New array is created to hold angles */
        double[][] innerAngles = new double[4][2];

        for(int i = 0; i < 4; i++) {
            /* distance between one angle to another */
            int dist = (i + 1) % 4;
            /* Each coordinate or point is averaged and then weighted so that the angle is closer to that particular point,
             * creating the spiral-like design */
            innerAngles[i][0] = (((coordinates[i][0])*0.75) + (coordinates[dist][0])*0.25);
            innerAngles[i][1] = (((coordinates[i][1])*0.75) + (coordinates[dist][1])*0.25);
        }
        return innerAngles;
    }

    /* 4) Recursively draws tree branches (diagram shaped)
     * "Tracker" is used to keep up with the number of branches or n
     * "divideSpace" is used to evenly divide space in of the window, so that no branch is larger than another
     *
     * */
    static void drawBinaryTrees(int n, double x, double y, int tracker, int divideSpace){
        if (n == 0) return;

        /* Coordinate x for the top branch */
        double delta = 50;

        /* For each x coordinate, divide by 2 */
        for(int i = 0; i <= tracker; i++){
            /* change in x = change in x divided by two */
           delta = delta/2;
        }

        if (n > 0){
            /* Draw each line further and further out */
            StdDraw.line(x,y,x - delta, y - (100/divideSpace)); //left branch
            StdDraw.line(x,y,x + delta, y - (100/divideSpace)); //right branch

            drawBinaryTrees(n-1, x - delta, y - (100/divideSpace), tracker + 1, divideSpace);//left branch
            drawBinaryTrees(n-1, x + delta, y - (100/divideSpace), tracker + 1, divideSpace);//right branch

        }

        /* Figure stays on screen for approximately 4 seconds before going to the next one */
        StdDraw.pause(300);

    }

    /* 5) Recursively draws circles with a cross design inside composed of circles */
      static void drawCircCross(int n, double x, double y, double radius) {
        if (n == 0) return;

        if (n > 0) {
        /* Draws the original circle */
            StdDraw.circle(x, y, radius);

        /* Here, radius is broken into thirds as there are three circles horizontally and vertically.
         * Algorithm: Subtract or Add the 2/3 the center in order to get desired position */
            drawCircCross(n - 1, x, y + (radius * 2 / 3), radius / 3); //top
            drawCircCross(n - 1, x, y - (radius * 2 / 3), radius / 3); //bottom
            drawCircCross(n - 1, x, y, radius / 3); //center
            drawCircCross(n - 1, x - (radius * 2 / 3), y, radius / 3); //left
            drawCircCross(n - 1, x + (radius * 2 / 3), y, radius / 3); //right
        }

        /* Figure stays on screen for approximately 4 seconds*/
        StdDraw.pause(200);
    }

}