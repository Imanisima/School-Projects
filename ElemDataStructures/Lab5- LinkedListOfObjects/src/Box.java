/**
 * Used with Runner.java, LinkedList.java
 *
 * Write no more than two constructors
 * public static getVolume()
 * public static isCube()
 * NO MAIN METHOD
 */


public class Box {
    private double width, height, length;
    Box next;

    Box(){

    }

    public Box (double width, double height, double length){
        this.width = width;
        this.height = height;
        this.length = length;
    }

    public Box (double width, double height, double length, Box nextBox){
        this.width = width;
        this.height = height;
        this.length = length;
        next = nextBox;
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

    public boolean isCube(){
        if ((this.width == this.height) && (this.width == this.length)){
            return true;
        }
        return false;
    }
}
