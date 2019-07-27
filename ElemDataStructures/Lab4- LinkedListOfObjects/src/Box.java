/**
 *
 * Used with: Runner.java
 * Keep only one public variable, rest must be private
 * Write no more than two constructors
 * public static getVolume() -> return volume of box
 * public static isCube() -> return true if cubic
 * NO MAIN METHOD
 */


public class Box {
    private double width, height, length;

    public Box (double width, double height, double length){
        this.width = width;
        this.height = height;
        this.length = length;
    }

    public double getWidth(){
        return this.width;
    }

    public double getHeight(){
        return this.height;
    }

    public double getLength(){
        return this.length;
    }

    public double getVolume(){
        return this.width * this.height * this.length;
    }

    public boolean isCubic(){
        if (this.width == this.height && this.width == this.length)
            return true;
        else{
            return false;
        }
    }
}
