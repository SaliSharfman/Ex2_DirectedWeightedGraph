package GUI;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;
import implementation.Graph;
import implementation.GraphAlgorithms;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;

public class ButtonListener implements ActionListener {

    private GraphGUI gui;

    public ButtonListener(GraphGUI gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        String buttonName = e.getActionCommand();


        if (buttonName.equals("load")) {
            String filename = gui.fileinput.getText();
            if (gui.canvas.getGraphDrawing().load(filename)) {
                gui.canvas.paintComponent(gui.canvas.getGraphics());
                JOptionPane.showMessageDialog(null, filename + " was loaded.");
            } else JOptionPane.showMessageDialog(null, "load failed.");


        }
        if (buttonName.equals("save")) {
            String filename = gui.fileinput.getText();
            if (gui.canvas.getGraphDrawing().save(filename)) {
                JOptionPane.showMessageDialog(null, "Graph saved.");
            } else JOptionPane.showMessageDialog(null, "save failed.");


        }
        if (buttonName.equals("ok")) {
            if (this.gui.canvas.radioButtonState.equals("remove node")) {
                int id;
                try {
                    if (this.gui.canvas.frame.nodeinput.getText() == "") throw new NullPointerException();
                    id = Integer.parseInt(this.gui.canvas.frame.nodeinput.getText());
                } catch (NullPointerException ex) {
                    return;
                }
                this.gui.canvas.graphDrawing.getGraph().removeNode(id);
                this.gui.canvas.paintComponent(this.gui.canvas.getGraphics());
            }
            if (this.gui.canvas.radioButtonState.equals("connect edge")) {
                int src, dest;
                double w;
                try {
                    if (this.gui.canvas.frame.srcinput.getText() == "") throw new NullPointerException();
                    if (this.gui.canvas.frame.destinput.getText() == "") throw new NullPointerException();
                    src = Integer.parseInt(this.gui.canvas.frame.srcinput.getText());
                    dest = Integer.parseInt(this.gui.canvas.frame.destinput.getText());}

                 catch (NullPointerException ex) {
                    return;
                }
                String str="";
                if(this.gui.canvas.frame.winput.getText().equals(str)){
                    w = 1;}
                else{
                    w=Double.parseDouble(this.gui.canvas.frame.winput.getText());}
                this.gui.canvas.graphDrawing.getGraph().connect(src, dest, w);
                this.gui.canvas.paintComponent(this.gui.canvas.getGraphics());
            }
            if (this.gui.canvas.radioButtonState.equals("remove edge")) {
                int src, dest;
                try {
                    if (this.gui.canvas.frame.srcinput.getText() == "") throw new NullPointerException();
                    if (this.gui.canvas.frame.destinput.getText() == "") throw new NullPointerException();
                    src = Integer.parseInt(this.gui.canvas.frame.srcinput.getText());
                    dest = Integer.parseInt(this.gui.canvas.frame.destinput.getText());
                } catch (NullPointerException ex) {
                    return;
                }
                this.gui.canvas.graphDrawing.getGraph().removeEdge(src, dest);
                this.gui.canvas.paintComponent(this.gui.canvas.getGraphics());
            }



            if (this.gui.canvas.radioButtonState.equals("shortest path")) {
                int src, dest;
                try {
                    if (this.gui.canvas.frame.srcinput.getText() == "") throw new NullPointerException();
                    if (this.gui.canvas.frame.destinput.getText() == "") throw new NullPointerException();
                    src = Integer.parseInt(this.gui.canvas.frame.srcinput.getText());
                    dest = Integer.parseInt(this.gui.canvas.frame.destinput.getText());
                } catch (NullPointerException ex) {
                    return;
                }
                List<NodeData> list = this.gui.canvas.graphDrawing.shortestPath(src, dest);
                String path="";
                int[]arr=new int[list.size()];
                int i=0;
                for (NodeData n : list) {
                    arr[i]=n.getKey();i++;
                    path+=this.gui.canvas.graphDrawing.getGraph().getNode(n.getKey()).getKey()+"->";
                }
                for (i = 0; i < arr.length-1; i++) {
                    this.gui.canvas.graphDrawing.getGraph().getEdge(arr[i],arr[i+1]).setTag(2);
                }
                path=path.substring(0,path.length()-2);

                this.gui.canvas.paintComponent(this.gui.canvas.getGraphics());
                JOptionPane.showMessageDialog(null, "The shortest path distance from " + src + " to " + dest + " is " + this.gui.canvas.graphDrawing.shortestPathDist(src, dest) +", the way is "+path+ ".");

            }


            if (this.gui.canvas.radioButtonState.equals("tsp")) {
                String path;
                try {
                    path=this.gui.canvas.frame.tspinput.getText();
                    if (path.equals("")) throw new NullPointerException();
                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(null, "No input");
                    return;
                }
                String[]keys;
                try {
                    keys=path.split(",");
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null, "Invalid path. Write only numbers and ','");
                    return;
                }
                List<NodeData>tspnodes=new LinkedList<NodeData>();
                for (int i = 0; i <keys.length ; i++) {
                    try {
                        tspnodes.add(this.gui.canvas.getGraphDrawing().getGraph().getNode(Integer.parseInt(keys[i])));
                    }
                    catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Invalid path. Write only numbers and ','");
                    }
                }
                DirectedWeightedGraph graph1=this.gui.canvas.getGraphDrawing().copy();
                DirectedWeightedGraphAlgorithms algo1=new GraphAlgorithms(graph1);
                try {
                    tspnodes=algo1.tsp(tspnodes);
                    if (tspnodes==null) throw new NullPointerException();
                }
                catch (NullPointerException ex)
                {
                    JOptionPane.showMessageDialog(null, "No tsp path with that nodes");

                }

                path="";
                int[]arr=new int[tspnodes.size()];
                int i=0;
                for (NodeData n : tspnodes) {
                    arr[i]=n.getKey();i++;
                    path+=this.gui.canvas.graphDrawing.getGraph().getNode(n.getKey()).getKey()+"->";
                }
                for (i = 0; i < arr.length-1; i++) {
                    this.gui.canvas.graphDrawing.getGraph().getEdge(arr[i],arr[i+1]).setTag(2);
                }
                path=path.substring(0,path.length()-2);

                this.gui.canvas.paintComponent(this.gui.canvas.getGraphics());
                JOptionPane.showMessageDialog(null, "The tsp path is: "+path+ ".");
            }

        }
        if (buttonName.equals("center")) {
            int center;
            try {
                if (this.gui.canvas.graphDrawing.center() == null) throw new NullPointerException();
                center = this.gui.canvas.graphDrawing.center().getKey();
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, "The graph is not connected, no center.");
                return;
            }
            this.gui.canvas.graphDrawing.getGraph().getNode(center).setTag(1);
            this.gui.canvas.paintComponent(this.gui.canvas.getGraphics());
            JOptionPane.showMessageDialog(null, "The center of the graph is node number " + this.gui.canvas.graphDrawing.center().getKey());

        }
        if (buttonName.equals("is connected")) {
            if (this.gui.canvas.graphDrawing.isConnected())
                JOptionPane.showMessageDialog(null, "The graph is connected!");
            else
                JOptionPane.showMessageDialog(null, "The graph is not connected!");
        }
        if (buttonName.equals("cancel")) {
           this.gui.fileinput.setText("");
            this.gui.nodeinput.setText("");
            this.gui.srcinput.setText("");
            this.gui.destinput.setText("");
            this.gui.winput.setText("");
            this.gui.tspinput.setText("");

        }

        if (buttonName.equals("clear colors")) {
            DirectedWeightedGraph g1 =this.gui.canvas.getGraphDrawing().getGraph();
            Iterator<NodeData>nitr=g1.nodeIter();
            while (nitr.hasNext())
                nitr.next().setTag(0);
            Iterator<EdgeData>eitr=g1.edgeIter();
            while (eitr.hasNext())
                eitr.next().setTag(0);
            this.gui.canvas.paintComponent(this.gui.canvas.getGraphics());
        }

        if (buttonName.equals("clear graph")) {
            DirectedWeightedGraph g1 =new Graph();
            this.gui.canvas.getGraphDrawing().init(g1);
            this.gui.canvas.paintComponent(this.gui.canvas.getGraphics());
        }
        if (buttonName.equals("numbers")) {
            this.gui.canvas.numbers=!this.gui.canvas.numbers;
            this.gui.canvas.paintComponent(this.gui.canvas.getGraphics());
        }



    }
}

