import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

import java.util.*;
import java.util.Iterator;

public class Graph implements DirectedWeightedGraph {

    private HashMap<String,NodeData> nodes;
    private HashMap<String,EdgeData> edges;
    public Graph() {
        nodes=new HashMap<String,NodeData>();
        edges=new HashMap<String,EdgeData>();
    }
    @Override
    public NodeData getNode(int key){
       return this.nodes.get(Integer.toString(key));
    }
    @Override
    public EdgeData getEdge(int src, int dest)
    {
        String str =Integer.toString(src)+","+Integer.toString(dest);
        return this.edges.get(str);
    }
    @Override
    public void addNode(NodeData n)
    {
        this.nodes.put(Integer.toString(n.getKey()),n);
    }
    @Override
    public void connect(int src, int dest, double w){
        if(nodes.get(Integer.toString(src))!=null&&nodes.get(Integer.toString(src))!=null) {
            String str = Integer.toString(src) + "," + Integer.toString(dest);
            EdgeData e = new Edge(src,dest,w);
            edges.put(str,e);
        }
    }
    @Override
    public Iterator<NodeData> nodeIter()
    {
        Set set = nodes.entrySet();
        Iterator<NodeData> iterator = set.iterator();
        return iterator;
    }
    public Iterator<EdgeData> edgeIter(){
        Set set = edges.entrySet();
        Iterator<EdgeData> iterator = set.iterator();
        return iterator;
    }
    public Iterator<EdgeData> edgeIter(int node_id)
    {

    }
    public NodeData removeNode(int key){
        nodes.remove(Integer.toString(key));

    }
    /**




    public Iterator<EdgeData> edgeIter(int node_id);




    public EdgeData removeEdge(int src, int dest);

    public int nodeSize();

    public int edgeSize();

    public int getMC();
*/
}
