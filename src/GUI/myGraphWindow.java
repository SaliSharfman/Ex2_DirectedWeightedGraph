package GUI;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;
import implementation.Graph;
import implementation.GraphAlgorithms;
import implementation.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Iterator;
import java.util.LinkedList;

public class myGraphWindow extends JFrame {

    DirectedWeightedGraphAlgorithms graphAlgorithms = new GraphAlgorithms();

    public myGraphWindow(DirectedWeightedGraphAlgorithms graphAlgorithm) throws HeadlessException {

        this.graphAlgorithms.init(graphAlgorithm.copy());

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit from application
        this.setSize(screenSize); //sets the x-dimension, and y-dimension of frame
        this.add(new MyPanel(graphAlgorithms));
        this.setVisible(true);

    }

    public class MyPanel extends JPanel{

        LinkedList<Point2D> points = new LinkedList<>();

        public MyPanel(DirectedWeightedGraphAlgorithms graphAlgorithm){

            DirectedWeightedGraph graph = graphAlgorithm.getGraph();

            Iterator<NodeData> nit = graph.nodeIter();
            while (nit.hasNext()) {
                NodeData n = nit.next();
                points.add(new Point2D.Double(n.getLocation().x(), n.getLocation().y()));
                System.out.println("point " + n.getLocation().x() + n.getLocation().y() + " was added ");
            }




//            this.points.add(new Point2D.Double(10, 100));
//            this.points.add(new Point2D.Double(100, 100));
//            this.points.add(new Point2D.Double(200, 100));
//            this.points.add(new Point2D.Double(300, 100));
//            this.points.add(new Point2D.Double(400, 100));
//            this.points.add(new Point2D.Double(500, 100));


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

//    public static void main(String[] args) {
//        new myGraphWindow();
//    }
}

