import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

import java.util.*;
import java.util.Iterator;

public class Graph implements DirectedWeightedGraph {

    private HashMap<Integer,NodeData> nodes;
    private HashMap<Integer,HashMap<Integer,EdgeData>> edges;
    private static int Edgescount=0,MC=0;
    public Graph() {
        nodes=new HashMap<Integer,NodeData>();
        edges=new HashMap<Integer,HashMap<Integer,EdgeData>>();
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
    }
    @Override
    public void connect(int src, int dest, double w){
        if(nodes.get(src)!=null&&nodes.get(src)!=null) {
            EdgeData e = new Edge(src,dest,w);
            edges.get(src).put(dest,e);
            Edgescount++;
            MC++;
        }
    }
    @Override
    public Iterator<NodeData> nodeIter()
    {
        return nodes.values().iterator();
    }
    @Override
    public Iterator<EdgeData> edgeIter(){
        LinkedList<EdgeData>list=new LinkedList<>();
        for(int i:edges.keySet())
            for(int j:edges.get(i).keySet())
                list.add(edges.get(i).get(j));
        return list.iterator();
    }
    @Override
    public Iterator<EdgeData> edgeIter(int node_id)
    {
        return edges.get(node_id).values().iterator();
    }
    @Override
    public NodeData removeNode(int key){
        if(nodes.get(key)!=null) {
            Edgescount -= edges.get(key).size();
            edges.remove(key);
            for (int i : edges.keySet())
                if (this.removeEdge(i, key) != null)
                    Edgescount--;
            MC++;
        }
       return nodes.remove(key);
    }
    @Override
    public EdgeData removeEdge(int src, int dest)
    {
        if(edges.get(src).get(dest)!=null) {
            MC++;
            Edgescount--;
        }
        return edges.get(src).remove(dest);
    }
    @Override
    public int nodeSize()
    {
        return nodes.size();
    }
    @Override
    public int edgeSize()
    {
        return Edgescount;
    }
    @Override
    public int getMC()
    {
        return MC;
    }
}
