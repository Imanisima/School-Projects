/**
 * Used with Runner.java file
 *
 * All status variables are private
 * No more than two constructors
 * public getVolume()
 * public isCube() -> Truth
 */


public class Package {
    private double width, height, length;

    /* Constructor */
    Package(double new_width, double new_height, double new_length){
        width = new_width;
        height = new_height;
        length = new_length;
    }

    /* Modify private variables so that it's accessible */
    void setDim(double new_width, double new_height, double new_length){
        width = new_width;
        height = new_height;
        length = new_length;
    }

    /* Method for calculating cubic numbers. If cubic, return true. If not, false */
    boolean isCube(){

        if ((length == width) && (width == height)) {
            return true;
        }

        else {
            return false;
        }
    }

    /* Method for calculating volume (w * h * l) */
    double getVolume(){
        return width * height * length;
    }

    /* Method for calculating largest package */


    /* Method for gathering dimensions for printing */
    double[] packDim(){
        double[] cubicDim = {width, height, length};
        return cubicDim;
    }

}
