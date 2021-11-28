import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

import java.util.*;
import java.util.Iterator;

public class Graph implements DirectedWeightedGraph {

    private HashMap<Integer,NodeData> nodes;
    private HashMap<Integer,HashMap<Integer,EdgeData>> edges;
    private static int MC=0;
    public Graph() {
        this.nodes=new HashMap<Integer,NodeData>();
        this.edges=new HashMap<Integer,HashMap<Integer,EdgeData>>();
    }
    @Override
    public NodeData getNode(int key){
       return this.nodes.get(key);
    }
    @Override
    public EdgeData getEdge(int src, int dest)
    {
        return this.edges.get(src).get(dest);
    }
    @Override
    public void addNode(NodeData n)
    {
        MC++;
        this.nodes.put(n.getKey(),n);
        this.edges.put(n.getKey(),new HashMap<Integer,EdgeData>());
    }
    @Override
    public void connect(int src, int dest, double w){
        if(this.nodes.get(src) != null && this.nodes.get(dest) != null) {
            EdgeData e = new Edge(src,w,dest);
            this.edges.get(src).put(dest,e);
            MC++;
        }
    }

    @Override
    public Iterator<NodeData> nodeIter()
    {
        return this.nodes.values().iterator();
    }
    @Override
    public Iterator<EdgeData> edgeIter(){
        ArrayList<EdgeData>list=new ArrayList<EdgeData>();
        for(int i:this.edges.keySet())
            for(int j:this.edges.get(i).keySet())
                list.add(this.edges.get(i).get(j));
        return list.iterator();
    }
    @Override
    public Iterator<EdgeData> edgeIter(int node_id)
    {
        return this.edges.get(node_id).values().iterator();
    }
    @Override
    public NodeData removeNode(int key){
            this.edges.remove(key);
            for (int i : this.edges.keySet())
                this.removeEdge(i, key);
            MC++;
       return nodes.remove(key);
    }
    @Override
    public EdgeData removeEdge(int src, int dest)
    {
        if(edges.get(src).get(dest)!=null)
            MC++;
        return this.edges.get(src).remove(dest);
    }
    @Override
    public int nodeSize()
    {
        return this.nodes.size();
    }
    @Override
    public int edgeSize()
    {
        int count=0;
        for(int i:this.edges.keySet())
            count+=this.edges.get(i).size();
        return count;
    }
    @Override
    public int getMC()
    {
        return MC;
    }
}
