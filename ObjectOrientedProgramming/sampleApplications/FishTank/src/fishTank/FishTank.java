package fishTank;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.Random;
import javax.imageio.*;
import javax.swing.JComponent;
import javax.swing.Timer;


public class FishTank extends JComponent implements ActionListener{
    private BufferedImage fishTank;
    private Fish[] seaHorse;
    private Timer timer;

    private static final Random random = new Random();

    public FishTank(){
        BufferedImage fishImage;

        try{
            fishTank = ImageIO.read(new File("images/fishTank.png"));
            fishImage = ImageIO.read(new File("images/seahorse.png"));
        }catch(IOException e){
            fishTank = null;
            fishImage = null;
        }

        seaHorse = new Fish[3];
        for (int i = 0; i < seaHorse.length; i++){
            seaHorse[i] = new Fish(fishImage, 640, 480, random.nextInt(3)+1);
        }

        timer = new Timer(10, this);
        timer.start();
    }

    public void actionPerformed(ActionEvent event){
        repaint();
    }

    public void paint(Graphics g){
        Graphics2D g2;

        g2 = (Graphics2D)g;
        if (fishTank != null)
            g2.drawImage(fishTank, 0, 0, null);
        for (int i = 0; i < seaHorse.length; i++)
            seaHorse[i].paint(g);
    }

//    private static void createUI(){
//        JFrame.setDefaultLookAndFeelDecorated(true);
//        JFrame frame = new JFrame("Fish Tank Animation");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        FishTank tank = new FishTank();
//        frame.getContentPane().add(tank);
//
//        JLabel label = new JLabel("I can see this.");
//        frame.getContentPane().add(label);
//
//        frame.pack();
//        frame.setVisible(true);
//    }
//
//    public static void run(){
//        createUI();
//    }

    public static void main(String[] args) {



    }

}
