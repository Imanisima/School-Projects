package fishTank;

import java.awt.*;
import java.awt.image.*;
import java.util.*;

/**
 *
 * A tank of fish that swims randomly
 *
 */

public class Fish{
    protected BufferedImage[] images;
    protected int initialSpeed, x_max, y_max, speed, x, y;
    protected int state, stateChange;
    protected int ticks, ticksInState;

    private static final int INITIAL_LOCATION = -320;
    private static final Random random = new Random();

    public Fish(BufferedImage fishImage, int width, int height, int speed){
        int imageHeight, imageWidth;

        if (fishImage != null){
            imageHeight = fishImage.getHeight(null);
            imageWidth = fishImage.getWidth(null)/3;

            images = new BufferedImage[3];
            for (int i = 0; i < 3; i++){
                images[i] = fishImage.getSubimage(i * imageWidth, 0, imageWidth, imageHeight);
            }
        }

        x_max = width;
        y_max = height;

        x = random.nextInt(x_max);
        y = random.nextInt(y_max);

        this.speed = speed;
        this.state = 0;
        this.stateChange = 1;
        this.ticksInState = 20 - 2 * speed;
    }

    public void paint(Graphics g){
        ticks += 1;
        if (ticks > ticksInState){
            ticks = 0;
            state += stateChange;
            if (state == 2)
                stateChange = -1;
            else if (state == 0)
                stateChange = 1;
        }

        x += speed;

        if (x > x_max){
            x = INITIAL_LOCATION;
            y = random.nextInt(y_max);
        }

        if (images[state] != null)
            g.drawImage(images[state], x, y, null);
    }
}
