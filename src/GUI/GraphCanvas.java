package GUI;

import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;
import implementation.Edge;
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
    public boolean numbers=false;

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
        this.addMouseListener(this);
        this.frame.getContentPane().setBackground(new Color(21, 208, 178));
        this.setBackground(Color.GRAY);
        this.setMaximumSize(new Dimension(1200,1200));



    }

    /*
     * @param e sets whether the user can edit on the
     * canvas or not
     */
    public void setIsEnabled(boolean e) {
        isEnabled = e;
    }

    /*
     * @param v sets the first vertex the user
     * clicked on
     */
    public void setEndpt1(NodeData v) {
        endpt1=v;
    }

    /*
     * @param v sets the second vertex the user
     * clicked on
     */
    public void setEndpt2(NodeData v) {
        endpt2=v;
    }

    /*
     * @return the first vertex the user clicked on
     */
    public NodeData getEndpt1() {
        return endpt1;
    }

    public NodeData getEndpt2() {
        return endpt2;
    }
    public DirectedWeightedGraphAlgorithms getGraphDrawing(){return graphDrawing;}

    public boolean getIsEnabled() {
        return isEnabled;
    }

    public void setRadioButtonState(String s) {
        radioButtonState=s;
    }


    public String getRadioButtonState() {
        return radioButtonState;
    }

    @Override
    public void mouseClicked(MouseEvent e){

        int x = e.getX();
        int y = e.getY();

        //DEBUG CODE
        if(radioButtonState.equals("")) {
            this.paintComponent(this.getGraphics());
        }

        if(!isEnabled)return;

        /*
         * (Add A Vertex)
         * Adds a vertex to the canvas
         */

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
        super.paintComponent(g);


        Graphics2D g2 = (Graphics2D) g;
        //g2.scale(30,40);

        /*
         * Draws all the Vertices that the user has
         * placed on the canvas
         */

        Iterator<EdgeData>eitr=graphDrawing.getGraph().edgeIter();
        while (eitr.hasNext()) {
            EdgeData edge = eitr.next();
            Point from = new Point((int)(graphDrawing.getGraph().getNode(edge.getSrc()).getLocation().x()),(int)(graphDrawing.getGraph().getNode(edge.getSrc()).getLocation().y()));
            Point to = new Point((int)(graphDrawing.getGraph().getNode(edge.getDest()).getLocation().x()),(int)(graphDrawing.getGraph().getNode(edge.getDest()).getLocation().y()));
            switch (edge.getTag()) {
                case 0:g2.setColor(Color.darkGray);break;
                case 1:g2.setColor(Color.RED);break;
                case 2:g2.setColor(Color.GREEN);break;
            }
            g2.setStroke(new BasicStroke(5));
            drawArrowLine(g2,from.x,from.y,to.x-1,to.y-1,25,10);
                  if(edge.getWeight()!=0&&numbers) {
                      g2.setFont(new Font("Dialog",Font.BOLD,18));
                      g2.drawString(""+edge.getWeight(), ((from.x+to.x)/2)+20 , ((from.y+to.y)/2));
                }
        }
        Iterator<NodeData>nitr=graphDrawing.getGraph().nodeIter();
        while (nitr.hasNext()){
            NodeData node=nitr.next();
            Shape vertex = new Ellipse2D.Double(node.getLocation().x()-5, node.getLocation().y()-5, 12, 12);
            switch (node.getTag()) {
                case 0:g2.setColor(Color.white);break;
                case 1:g2.setColor(Color.RED);break;
                case 2:g2.setColor(Color.GREEN);break;
            }
            if (numbers) {
              g2.setFont(new Font("Dialog",Font.BOLD,18));
              g2.drawString(""+node.getKey(),(int)(node.getLocation().x()+20),(int) node.getLocation().y());
            }
            g2.fill(vertex);
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