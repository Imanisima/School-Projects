package fishTank;

import javax.swing.*;
import java.awt.*;

public class Fish {
    protected int size;
    protected Color color;
    protected int x, y;

    public Fish(int size){
        this.size = size;
        color = Color.yellow;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public void setXoordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int size(){
        return size;
    }

    public void drawFish(Graphics g, int x, int y, Color color){
        g.setColor(color);
        g.fillOval(x + size/2, y + size/2, size *4, size);

        Polygon polygon = new Polygon();
        polygon.addPoint(x,y);
        polygon.addPoint(x + size, y + size);
        polygon.addPoint(x, y + 2 * size);
        polygon.addPoint(x + size/3, y + size);
        g.fillPolygon(polygon);

        g.setColor(Color.green); // eyes
        g.fillOval(x + size * 7/2, y + size * 4/5, size / 3, size / 3);
    }

    public void paint(Graphics g, int x, int y){
        drawFish(g, x, y, color);
    }

    public void remove(Graphics g, int x, int y, Color tankColor){
        drawFish(g, x, y, tankColor);
    }
}

class NewFish extends Fish {
    public NewFish(int size){
        super(size);
    }
}

class Tank extends JFrame {
    private static final int tank_width = 500;
    private static final int tank_height = 400;
    private static final Color tank_color = Color.CYAN;

    public Tank(){
        super("Fish Tank");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(tank_color);
        setSize(tank_width, tank_height);
        setVisible(true);
    }
    public Graphics getGraphicsContext(){
        return getContentPane().getGraphics();
    }
}

class RunAnimation {
    Fish redFish = new Fish(20);
    NewFish whiteFish = new NewFish(30);
    NewFish blackFish = new NewFish(20);

    boolean direction;
    int x_initial, y_initial;

    public void RunAnimation(){

        direction = true;
        x_initial = 200;
        y_initial = 200;
    }

    public void move(){
        if(direction){ // backward
            x_initial++;
            if(x_initial >= 350){
                x_initial -= 2;
                direction = false;
            }
        }
        else{ // forward
            x_initial--;
            if(x_initial <= 0){
                x_initial += 2;
                direction = true;
            }
        }
    }

    public void moveUp(){
        if(direction){
            y_initial++;
            if(y_initial >= 300){
                y_initial -= 2;
                direction = false;
            }
        }
        else{
            y_initial--;
            // turn around?
            if(y_initial <= 250){
                y_initial += 2;
                direction = true;
            }
        }
    }

    public void repaint(){
        int speed = 50;

        redFish.setColor(Color.red);
        whiteFish.setColor(Color.gray);
        blackFish.setColor(Color.black);

        Tank tank = new Tank();
        boolean animationComplete = false;

        Thread loop = new Thread();
        while (!animationComplete){
            paint(tank.getGraphicsContext());
            try{
                loop.sleep(speed);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void paint(Graphics g){
        redFish.remove(g, x_initial, y_initial, Color.cyan);
        whiteFish.remove(g, x_initial + 20, y_initial + 40, Color.cyan);
        blackFish.remove(g, x_initial, y_initial + 60, Color.cyan);

        move();

        redFish.paint(g, x_initial, y_initial);
        whiteFish.paint(g, x_initial + 20, y_initial + 40);
        blackFish.paint(g, x_initial, y_initial + 60);
    }

    public static void main(String[] args) {
        RunAnimation run = new RunAnimation();
        run.repaint();
    }
}
