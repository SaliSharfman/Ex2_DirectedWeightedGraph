import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;
import com.google.gson.*;

import java.io.FileReader;
import java.util.Iterator;
import java.util.List;
public class GraphAlgorithms implements DirectedWeightedGraphAlgorithms {
    private DirectedWeightedGraph graph;
    public GraphAlgorithms()
    {
        this.graph=new Graph();
    }
    @Override
    public void init(DirectedWeightedGraph g){
        Iterator<NodeData> nit = g.nodeIter();
        while (nit.hasNext()) {
            this.graph.addNode(nit.next());
        }
        Iterator<EdgeData> eit = g.edgeIter();
        while (eit.hasNext()) {
            EdgeData edge = new Edge(eit.next());
            this.graph.connect(edge.getSrc(),edge.getDest(),edge.getWeight());
        }
    }
    @Override
    public boolean isConnected(){}
    @Override
    public double shortestPathDist(int src, int dest){
        double[][]paths = new double[this.graph.nodeSize()][this.graph.nodeSize()];
        /*Iterator<NodeData> I = this.graph.nodeIter();
        int i=0;
        while (I.hasNext()){
            Iterator<NodeData> J = this.graph.nodeIter();
            int j=0;
            while (J.hasNext()){
                paths[i][j]=this.graph.getEdge(I.next().getKey(),J.next().getKey()).getWeight();
                j++;
            }
            i++;
        }
        Iterator<NodeData> K = this.graph.nodeIter();
        while (K.hasNext()){
            I = this.graph.nodeIter();
            while (I.hasNext()){
                Iterator<NodeData> J = this.graph.nodeIter();
                while (J.hasNext()){
                    int k = K.next().getKey();
                    i = I.next().getKey();
                    int j = J.next().getKey();
                    if(this.graph.getEdge(i,k).getWeight()!=0&&this.graph.getEdge(k,j).getWeight()!=0)
                    {
                        if(this.graph.getEdge(i,j).getWeight() > this.graph.getEdge(i,k).getWeight()+this.graph.getEdge(k,j).getWeight()||matrix[i][j]==0){
                            String info=this.graph.getEdge(i,j).getInfo();
                            int tag=this.graph.getEdge(i,j).getTag();
                            this.graph.removeEdge(i,j);
                            this.graph.connect(i,j,this.graph.getEdge(i,k).getWeight()+this.graph.getEdge(k,j).getWeight());
                            this.graph.getEdge(i,j).setInfo(info);
                            this.graph.getEdge(i,j).setTag(tag);
                        }
                    }
                }
            }
        }
         */
        Iterator<EdgeData> eitr = this.graph.edgeIter();
        int i=0;
        while (I.hasNext()){
            Iterator<NodeData> J = this.graph.nodeIter();
            int j=0;
            while (J.hasNext()){
                paths[i][j]=this.graph.getEdge(I.next().getKey(),J.next().getKey()).getWeight();
                j++;
            }
            i++;
        }


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
            graph.addNode(nit.next());
        }
        Iterator<EdgeData> eit = this.graph.edgeIter();
        while (eit.hasNext()) {
            EdgeData edge = new Edge(eit.next());
            graph.connect(edge.getSrc(),edge.getDest(),edge.getWeight());
        }
        return graph;
    }/*
    @Override
    public List<NodeData> shortestPath(int src, int dest){}*/
    @Override
    public NodeData center(){
        if(this.graph.nodeSize()==0)
            return null;
        DirectedWeightedGraphAlgorithms algo = new GraphAlgorithms();
        algo.init(this.graph);
        if(algo.isConnected())
        {
            double[] maxPath=new double[algo.getGraph().nodeSize()];
            Iterator<NodeData> nitr = algo.getGraph().nodeIter();
            while (nitr.hasNext())
            {
                int i =0;
                maxPath[i]=0;
                NodeData node = new Node(nitr.next());
                Iterator<EdgeData> eitr = algo.getGraph().edgeIter(node.getKey());
                while (eitr.hasNext())
                {
                    EdgeData edge = new Edge(eitr.next());
                    if (maxPath[i]<shortestPathDist(node.getKey(),edge.getDest()))
                        maxPath[i]=shortestPathDist(node.getKey(),edge.getDest());
                }
                i++;
            }
            Iterator<NodeData> n1itr = algo.getGraph().nodeIter();
            int center=0;
            int i=0;
            while (nitr.hasNext())
            {
                if(maxPath[center]>=maxPath[i]) {
                    center = nitr.next().getKey();
                }
                else
                    n1itr.next();
                i++;
            }
            NodeData node = new Node(algo.getGraph().getNode(center));
            return node;

        }
        return null;
    }/*
    @Override
    List<NodeData> tsp(List<NodeData> cities){}
    @Override
    public boolean save(String file){}
    public DirectedWeightedGraph deserialize(JsonElement json, )
    @Override
    public boolean load(String file){
        try {
            FileReader fr = new FileReader(file);
            Gson g = new Gson();

        }
    }*/
}
