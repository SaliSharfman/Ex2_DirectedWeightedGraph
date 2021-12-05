package implementation;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class GraphAlgorithms implements DirectedWeightedGraphAlgorithms {
    private DirectedWeightedGraph graph;
    public GraphAlgorithms()
    {
        this.graph=new Graph();
    }
//    private Queue<NodeData> BFS(int rootID)
//    {
//        Queue<NodeData>queue= new Queue<NodeData>();
//
//    }
    @Override
    public void init(DirectedWeightedGraph g){
        this.graph=g;
    }

    private boolean BFS(LinkedList<Integer> queue, HashMap<Integer,Integer> colors, DirectedWeightedGraph g) {
        if (queue.isEmpty()){
            for(int i:colors.values())
                if (i!=2)
                    return false;
            return true;
        }
        int v = queue.poll();
        Iterator<EdgeData> ei = g.edgeIter(v);
        while (ei.hasNext()) {
            int dest = ei.next().getDest();
            if (colors.get(dest)==0) {
                colors.remove(dest);
                colors.put(dest,1);//gray
                queue.add(dest);
            }
        }
        colors.remove(v);
        colors.put(v,2);//black
        return BFS(queue, colors, this.graph);
    }
    @Override
    public boolean isConnected(){
        if(this.graph.nodeSize()==0)
            return true;
        LinkedList<Integer> queue=new LinkedList<Integer>();
        HashMap<Integer,Integer>colors=new HashMap<Integer,Integer>();
        Iterator<NodeData> nitr = this.graph.nodeIter();
        NodeData start=nitr.next();
        colors.put(start.getKey(),0);
        while (nitr.hasNext())
            colors.put(nitr.next().getKey(),0);
        Iterator<EdgeData> ei = this.graph.edgeIter(start.getKey());
        while (ei.hasNext()) {
            int dest = ei.next().getDest();
            colors.remove(dest);
            colors.put(dest, 1);//gray
            queue.add(dest);
        }
       return BFS(queue,colors,this.graph);
    }
    private void findInit(DirectedWeightedGraph g1)
    {
        Iterator<NodeData> nitr = g1.nodeIter();
        while (nitr.hasNext()) {
            NodeData ni = nitr.next();
            ni.setWeight(Integer.MAX_VALUE);
            ni.setInfo("");
        }
    }
    private void findSortestPath(DirectedWeightedGraph g1,int src,int dest)
    {
        Iterator<EdgeData> eitr = g1.edgeIter(src);
        while (eitr.hasNext()) {
            EdgeData ei = eitr.next();
            if(ei.getDest()==dest)
            {
                if(ei.getWeight()<g1.getNode(src).getWeight())
                {
                    g1.getNode(src).setWeight(ei.getWeight());
                    g1.getNode(src).setInfo(g1.getNode(ei.getDest()).getKey()+"");
                }
            }
            else {
                findSortestPath(g1, ei.getDest(), dest);
                double path=g1.getNode(ei.getDest()).getWeight();
                if(path<Integer.MAX_VALUE)
                    path+=ei.getWeight();
                if(path<g1.getNode(src).getWeight()) {
                    g1.getNode(src).setWeight(path);
                    String info=g1.getNode(ei.getDest()).getInfo();
                    g1.getNode(src).setInfo(g1.getNode(ei.getDest()).getKey()+","+info);
                }
            }
        }
        System.out.println("info: "+g1.getNode(src).getInfo()+"path:"+g1.getNode(src).getWeight());
    }
    @Override
    public double shortestPathDist(int src, int dest) {
        if (src==dest)
            return 0;
        DirectedWeightedGraph gcopy = this.copy();
        this.findInit(gcopy);
        this.findSortestPath(gcopy,src,dest);
        if ((int)(gcopy.getNode(src).getWeight())==Integer.MAX_VALUE)
            return -1;
        return gcopy.getNode(src).getWeight();
    }
    @Override
    public List<NodeData> shortestPath(int src, int dest){
        List<NodeData>list=new LinkedList<NodeData>();
        list.add(this.graph.getNode(src));
        if (src==dest)
            return list;
        DirectedWeightedGraph gcopy =this.copy();
        this.findInit(gcopy);
        this.findSortestPath(gcopy,src,dest);
        if ((int)(gcopy.getNode(src).getWeight())==Integer.MAX_VALUE)
            return null;
        String[]keys=gcopy.getNode(src).getInfo().split(",");
        for(int i=0;i<keys.length;i++)
            list.add(this.graph.getNode(Integer.parseInt(keys[i])));
        return list;
    }
    @Override
    public DirectedWeightedGraph getGraph(){
        return this.graph;
    }
    @Override
    public DirectedWeightedGraph copy(){
        DirectedWeightedGraph graph = new Graph();
        Iterator<NodeData> nit = this.graph.nodeIter();
        while (nit.hasNext()) {
            NodeData n =nit.next();
            graph.addNode(new Node(n));
        }
        Iterator<EdgeData> eit = this.graph.edgeIter();
        while (eit.hasNext()) {
            EdgeData edge = new Edge(eit.next());
            graph.connect(edge.getSrc(),edge.getDest(),edge.getWeight());
            graph.getEdge(edge.getSrc(),edge.getDest()).setTag(edge.getTag());
            graph.getEdge(edge.getSrc(),edge.getDest()).setInfo(edge.getInfo());
        }
        return graph;
    }
    @Override
    public NodeData center() {
        if (this.graph.nodeSize() == 0 || !this.isConnected())
            return null;
        DirectedWeightedGraphAlgorithms algo = new GraphAlgorithms();
        algo.init(this.copy());
        double[] maxPath = new double[algo.getGraph().nodeSize()];
        Iterator<NodeData> ni = algo.getGraph().nodeIter();
        int i = 0;
        while (ni.hasNext()) {
            maxPath[i] = -1;
            NodeData nodei = new Node(ni.next());
            Iterator<NodeData> nj = algo.getGraph().nodeIter();
            while (nj.hasNext()) {
                NodeData nodej = new Node(nj.next());
                if (maxPath[i] < algo.shortestPathDist(nodei.getKey(), nodej.getKey()))
                    maxPath[i] = algo.shortestPathDist(nodei.getKey(), nodej.getKey());
            }
            i++;
        }
        int center=0;
        for(int j=0;i<maxPath.length;i++)
                if(maxPath[i]<maxPath[center])
                    center=i;
        Iterator<NodeData> n1itr = algo.getGraph().nodeIter();
        NodeData node=n1itr.next();
        for (int j=0;i<center;i++)
            node = n1itr.next();
        return node;
        }
    @Override
    public List<NodeData> tsp(List<NodeData> cities){return new LinkedList<NodeData>();}
    @Override
    public boolean save(String file){return true;}
    @Override
    public boolean load(String file) {
        return true;
    }
}
