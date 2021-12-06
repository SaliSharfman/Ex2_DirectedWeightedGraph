package GUI;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import implementation.Graph;
import implementation.GraphAlgorithms;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.LinkedList;

public class myGraphWindow extends JFrame {

    public myGraphWindow() throws HeadlessException {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit from application
        this.setSize(screenSize); //sets the x-dimension, and y-dimension of frame
        this.add(new MyPanel());
        this.setVisible(true);

    }

    public class MyPanel extends JPanel{

        DirectedWeightedGraph graph = new Graph();
        DirectedWeightedGraphAlgorithms graphAlgorithms = new GraphAlgorithms();
        LinkedList<Point2D> points = new LinkedList<>();

        public MyPanel(DirectedWeightedGraphAlgorithms graphAlgorithm){

            this.graphAlgorithms.init(graphAlgorithm.copy());


            this.points.add(new Point2D.Double(10, 100));
            this.points.add(new Point2D.Double(100, 100));
            this.points.add(new Point2D.Double(200, 100));
            this.points.add(new Point2D.Double(300, 100));
            this.points.add(new Point2D.Double(400, 100));
            this.points.add(new Point2D.Double(500, 100));


        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            Point2D prev = null;
            for(Point2D p:points){
                g.setColor(Color.BLACK);
                g.fillOval((int)p.getX()-5, (int)p.getY()-5, 10, 10);
                if (prev!=null){
                    g.setColor(Color.RED);
                    g.drawLine((int)p.getX(), (int)p.getY(), (int)prev.getX(), (int)prev.getY());
                }
                prev = p;
            }
        }
    }

    public static void main(String[] args) {
        new myGraphWindow();
    }
}

