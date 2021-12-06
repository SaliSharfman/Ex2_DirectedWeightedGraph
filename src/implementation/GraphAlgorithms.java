package implementation;

import GUI.GraphJsonDeserializer;
import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

public class GraphAlgorithms implements DirectedWeightedGraphAlgorithms {
    private DirectedWeightedGraph graph;

    public GraphAlgorithms() {
        this.graph = new Graph();
    }

    public GraphAlgorithms(DirectedWeightedGraph graph) {
        this.graph = graph;
    }

    @Override
    public void init(DirectedWeightedGraph g) {
        this.graph = g;
    }

    private boolean BFS(LinkedList<Integer> queue, HashMap<Integer, Integer> colors, DirectedWeightedGraph g) {
        if (queue.isEmpty()) {
            for (int i : colors.values())
                if (i != 2)
                    return false;
            return true;
        }
        int v = queue.poll();
        Iterator<EdgeData> ei = g.edgeIter(v);
        while (ei.hasNext()) {
            int dest = ei.next().getDest();
            if (colors.get(dest) == 0) {
                colors.remove(dest);
                colors.put(dest, 1);//gray
                queue.add(dest);
            }
        }
        colors.remove(v);
        colors.put(v, 2);//black
        return BFS(queue, colors, this.graph);
    }

    @Override
    public boolean isConnected() {
        if (this.graph.nodeSize() == 0)
            return true;
        LinkedList<Integer> queue = new LinkedList<Integer>();
        HashMap<Integer, Integer> colors = new HashMap<Integer, Integer>();
        Iterator<NodeData> nitr = this.graph.nodeIter();
        NodeData start = nitr.next();
        colors.put(start.getKey(), 0);
        while (nitr.hasNext())
            colors.put(nitr.next().getKey(), 0);
        Iterator<EdgeData> ei = this.graph.edgeIter(start.getKey());
        while (ei.hasNext()) {
            int dest = ei.next().getDest();
            colors.remove(dest);
            colors.put(dest, 1);//gray
            queue.add(dest);
        }
        return BFS(queue, colors, this.graph);
    }
    private LinkedList findInit(DirectedWeightedGraph g1) {
        LinkedList<NodeData> queue = new LinkedList<NodeData>();
        Iterator<NodeData> nitr = g1.nodeIter();
        while (nitr.hasNext()) {
            NodeData ni = nitr.next();
            ni.setWeight(Integer.MAX_VALUE);
            ni.setTag(Integer.MIN_VALUE);
            ni.setInfo("");
            queue.add(ni);
        }
        return queue;
    }

    //Dijkstra
    //d-->weight
    //Pi-->tag
    private int ExtractMin(LinkedList<NodeData> q) {
        LinkedList<NodeData> qcopy = new LinkedList<NodeData>();
        NodeData ni = q.poll();
        qcopy.add(ni);
        double minweight = ni.getWeight();
        int key = ni.getKey();
        while (!q.isEmpty()) {
            ni = q.poll();
            qcopy.add(ni);
            if (ni.getWeight() < minweight) {
                minweight = ni.getWeight();
                key = ni.getKey();
            }
        }
        while (!qcopy.isEmpty()) {
            q.add(qcopy.poll());
        }
        return key;
    }

    private void relax(DirectedWeightedGraph g1, int src, int dest) {
        if ((g1.getNode(src).getWeight()<Integer.MAX_VALUE) && (g1.getNode(dest).getWeight() > g1.getNode(src).getWeight() + g1.getEdge(src, dest).getWeight())) {
            g1.getNode(dest).setWeight(g1.getNode(src).getWeight() + g1.getEdge(src, dest).getWeight());
            g1.getNode(dest).setTag(src);
        }
    }

    private void Dijkstra(DirectedWeightedGraph g1, int src) {
        LinkedList<NodeData> queue = findInit(g1);
        g1.getNode(src).setWeight(0);
        while (!queue.isEmpty()) {
            NodeData node=g1.getNode(ExtractMin(queue));
            queue.remove(node);
            Iterator<EdgeData>eitr=g1.edgeIter(node.getKey());
            while (eitr.hasNext())
            {
                relax(g1,node.getKey(),eitr.next().getDest());
            }
        }

    }

    @Override
    public double shortestPathDist(int src, int dest) {
        if (src == dest)
            return 0;
        DirectedWeightedGraph gcopy = this.copy();
        this.Dijkstra(gcopy,src);
        if ((int) (gcopy.getNode(dest).getWeight()) == Integer.MAX_VALUE)
            return -1;
        return gcopy.getNode(dest).getWeight();
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        LinkedList<NodeData> stack = new LinkedList<NodeData>();
        stack.push(this.graph.getNode(dest));
        if (src == dest)
            return stack;
        DirectedWeightedGraph gcopy = this.copy();
        this.Dijkstra(gcopy,src);
        NodeData node=gcopy.getNode(dest);
        if ((int) (node.getWeight()) == Integer.MAX_VALUE)
            return null;
        while (node.getTag()!=src) {
            node = gcopy.getNode(node.getTag());
            stack.push(node);
        }
        LinkedList list =new LinkedList();
        list.add(gcopy.getNode(src));
        while (!stack.isEmpty())
            list.add(stack.pop());
        return list;
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return this.graph;
    }

    @Override
    public DirectedWeightedGraph copy() {
        DirectedWeightedGraph graph = new Graph();
        Iterator<NodeData> nit = this.graph.nodeIter();
        while (nit.hasNext()) {
            NodeData n = nit.next();
            graph.addNode(new Node(n));
        }
        Iterator<EdgeData> eit = this.graph.edgeIter();
        while (eit.hasNext()) {
            EdgeData edge = new Edge(eit.next());
            graph.connect(edge.getSrc(), edge.getDest(), edge.getWeight());
            graph.getEdge(edge.getSrc(), edge.getDest()).setTag(edge.getTag());
            graph.getEdge(edge.getSrc(), edge.getDest()).setInfo(edge.getInfo());
        }
        return graph;
    }

    @Override
    public NodeData center() {
        if (this.graph.nodeSize() == 0 || !this.isConnected())
            return null;
        DirectedWeightedGraphAlgorithms algo = new GraphAlgorithms(this.copy());
        Iterator<NodeData> ni = algo.getGraph().nodeIter();
        while (ni.hasNext()) {
            ni.next().setWeight(-1);
        }
        ni = algo.getGraph().nodeIter();
        while (ni.hasNext()) {
            NodeData nodei = ni.next();
            Iterator<NodeData> nj = algo.getGraph().nodeIter();
            while (nj.hasNext()) {
                NodeData nodej = nj.next();
                if (nodei.getWeight() < algo.shortestPathDist(nodei.getKey(), nodej.getKey()))
                    nodei.setWeight(algo.shortestPathDist(nodei.getKey(), nodej.getKey()));
            }
        }
        ni = algo.getGraph().nodeIter();
        int center = ni.next().getKey();
        while (ni.hasNext())
        {
            NodeData node = ni.next();
            if(node.getWeight()<algo.getGraph().getNode(center).getWeight())
                center=node.getKey();

        }
        return this.graph.getNode(center);
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        if (this.graph.nodeSize() == 0 || !this.isConnected())
            return null;

        return new LinkedList<NodeData>();
    }

    @Override
    public boolean save(String file) {
        //Make JSON!!
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        //Write JSON to file
        try
        {
            PrintWriter pw = new PrintWriter(new File("data/"+file));
            pw.write(gson.toJson(this.graph));
            pw.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean load(String file) {
        DirectedWeightedGraph ans = null;
        try {
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Graph.class, new GraphJsonDeserializer());
            Gson gson = builder.create();
            //continue as usual..
            FileReader reader = new FileReader(file);
            ans = gson.fromJson(reader, Graph.class);

        } catch (FileNotFoundException e) {
            return false;
        }
        this.init(ans);
        return true;
    }
}
