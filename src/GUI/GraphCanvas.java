package GUI;

import api.*;
import implementation.Edge;
import implementation.Geo_Location;
import implementation.GraphAlgorithms;
import implementation.Node;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.util.*;

import javax.swing.*;

public class GraphCanvas extends JPanel implements MouseListener{

    //Instance Variables of a GraphCanvas
    public GraphGUI frame;
    public static String radioButtonState;
    private static boolean isEnabled;
    protected static DirectedWeightedGraphAlgorithms graphDrawing;
    private static NodeData endpt1,endpt2;
    public boolean numbers=true;
    double minx,miny,maxx,maxy;

    /*
     * One Parameter Constructor of a GraphCanvas
     * @param frame the current JFrame the canvas
     * is on
     */
    public GraphCanvas(GraphGUI frame)
    {
        this.frame=frame;
        isEnabled = false;
        radioButtonState="1";
        graphDrawing = new GraphAlgorithms();
        graphDrawing.load(frame.getFileName());
        preload(graphDrawing.getGraph());
        this.addMouseListener(this);
        this.setBackground(Color.GRAY);
        this.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
    }
    public void setIsEnabled(boolean e) {
        isEnabled = e;
    }
    public DirectedWeightedGraphAlgorithms getGraphDrawing(){return graphDrawing;}
    public void setRadioButtonState(String s) {
        radioButtonState=s;
    }

    @Override
    public void mouseClicked(MouseEvent e){
        //preload(this.getGraphDrawing().getGraph());
        double unitX=this.getWidth()/Math.abs(maxx-minx)*0.975;
        double unitY=this.getWidth()/Math.abs(maxy-miny)*0.9;
        double x = (e.getX()/unitX)+minx;
        double y = (e.getY()/unitY)+miny;

        if(radioButtonState.equals("")) {
            this.paintComponent(this.getGraphics());
        }

        if(!isEnabled)return;

        if(radioButtonState.equals("add node")) {
            int id=0;
            for (int i = 0; i <graphDrawing.getGraph().nodeSize()+1 ; i++) {
                if(graphDrawing.getGraph().getNode(i)==null){
                    id=i;break;}
            }
            String pos=x+","+y+",0.0";
            NodeData v = new Node(pos,id);
            graphDrawing.getGraph().addNode(v);
            this.paintComponent(this.getGraphics());
        }

}
    private void preload(DirectedWeightedGraph graph)
    {
        if (graph.nodeSize()==0)
            return;
        Iterator<NodeData>nitr=graph.nodeIter();
        NodeData node= nitr.next();
        minx=node.getLocation().x();
        miny=node.getLocation().y();
        maxx=node.getLocation().x();
        maxy=node.getLocation().y();

        while (nitr.hasNext())
        {
            node= nitr.next();
            if(node.getLocation().x()<minx)minx=node.getLocation().x();
            if(node.getLocation().x()>maxx)maxx=node.getLocation().x();
            if(node.getLocation().y()<miny)miny=node.getLocation().y();
            if(node.getLocation().y()>maxy)maxy=node.getLocation().y();

        }
    }

    private void drawArrowLine(Graphics g, int x1, int y1, int x2, int y2, int d, int h) {
        int dx = x2 - x1, dy = y2 - y1;
        double D = Math.sqrt(dx * dx + dy * dy);
        double xm = D - d, xn = xm, ym = h, yn = -h, x;
        double sin = dy / D, cos = dx / D;

        x = xm * cos - ym * sin + x1;
        ym = xm * sin + ym * cos + y1;
        xm = x;

        x = xn * cos - yn * sin + x1;
        yn = xn * sin + yn * cos + y1;
        xn = x;

        int[] xpoints = {x2, (int) xm, (int) xn};
        int[] ypoints = {y2, (int) ym, (int) yn};

        g.drawLine(x1, y1, x2, y2);
        g.fillPolygon(xpoints, ypoints, 3);
    }
    public void paintComponent(Graphics g){
        preload(this.getGraphDrawing().getGraph());
        super.paintComponent(g);
        double unitX=this.getWidth()/Math.abs(maxx-minx)*0.975;
        double unitY=this.getWidth()/Math.abs(maxy-miny)*0.9;


        DirectedWeightedGraph graph=graphDrawing.getGraph();
        Iterator<EdgeData>eitr=graph.edgeIter();
        while (eitr.hasNext()) {
            EdgeData edge = eitr.next();

            double xsrc=graph.getNode(edge.getSrc()).getLocation().x();
            xsrc=((xsrc-minx)*unitX)+12;

            double ysrc=graph.getNode(edge.getSrc()).getLocation().y();
            ysrc=((ysrc-miny)*unitY)+12;

            double xdest=graph.getNode(edge.getDest()).getLocation().x();
            xdest=((xdest-minx)*unitX)+12;

            double ydest=graph.getNode(edge.getDest()).getLocation().y();
            ydest=((ydest-miny)*unitY)+12;

            switch (edge.getTag()) {
                case 0:g.setColor(Color.darkGray);break;
                case 1:g.setColor(Color.RED);break;
                case 2:g.setColor(Color.GREEN);break;
            }
           // g.setStroke(new BasicStroke(5));
            drawArrowLine(g,(int)xsrc,(int)ysrc,(int)xdest,(int)ydest,30,7);
                  if(edge.getWeight()!=0&&numbers) {
                      g.setFont(new Font("Dialog",Font.BOLD,18));
                      g.drawString(""+edge.getWeight(), (((int)xsrc+(int)xdest)/2)+20 , (((int)ysrc+(int)ydest)/2));
                }
        }
        Iterator<NodeData>nitr=graph.nodeIter();
        while (nitr.hasNext()){
            NodeData node=nitr.next();
            int x = (int)((node.getLocation().x()-minx)*unitX);
            int y = (int)((node.getLocation().y()-miny)*unitY);
            switch (node.getTag()) {
                case 0:g.setColor(Color.darkGray);break;
                case 1:g.setColor(Color.RED);break;
                case 2:g.setColor(Color.GREEN);break;
            }
            g.fillOval(x,y,24,24);
            if (numbers) {
                g.setColor(Color.white);
                g.setFont(new Font("Dialog",Font.BOLD,18));
                g.drawString(""+node.getKey(),x+7,y+17);
            }
        }

    }

    @Override
    public void mouseEntered(MouseEvent arg0) {}

    @Override
    public void mouseExited(MouseEvent arg0) {}

    @Override
    public void mousePressed(MouseEvent arg0) {}

    @Override
    public void mouseReleased(MouseEvent arg0) {}


}