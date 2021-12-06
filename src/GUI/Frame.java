package GUI;

import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Frame {

    public static void main(String[] args) {

        // JFrame = a GUI window to add components to
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        JFrame frame = new JFrame(); //creates a frame
        frame.setTitle("OOP Recitation 7 "); //sets title of frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit from application
        frame.setResizable(false); //prevent frame from being resized
        frame.setSize(screenSize); //sets the x-dimension, and y-dimension of frame
        frame.setVisible(true); //make frame visible

        ImageIcon image = new ImageIcon("./resources/sp.png"); //create an ImageIcon
        frame.setIconImage(image.getImage()); //change icon of frame
        frame.getContentPane().setBackground(new Color(21, 208, 178)); //change color of background

    }
}