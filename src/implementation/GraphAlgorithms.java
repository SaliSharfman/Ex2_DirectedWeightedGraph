package implementation;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;

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
//        Iterator<NodeData> nit = this.graph.nodeIter();
//        while (nit.hasNext()) {
//            this.graph.removeNode(nit.next().getKey());
//        }
//        Iterator<NodeData> n1it = g.nodeIter();
//        while (n1it.hasNext()) {
//            this.graph.addNode(n1it.next());
//        }
//        Iterator<EdgeData> eit = g.edgeIter();
//        while (eit.hasNext()) {
//            EdgeData edge = new Edge(eit.next());
//            this.graph.connect(edge.getSrc(),edge.getDest(),edge.getWeight());
//        }
    }
    @Override
    public boolean isConnected(){
        Iterator<NodeData> i = this.graph.nodeIter();
        Iterator<NodeData> j = this.graph.nodeIter();
        while (i.hasNext()) {
            int ni = i.next().getKey();
            while (j.hasNext())
                if (this.shortestPathDist(ni, j.next().getKey()) == -1)
                    return false;
        }
        return true;
    }
    @Override
    public double shortestPathDist(int src, int dest){
//        double[][]paths = new double[this.graph.nodeSize()][this.graph.nodeSize()];
//        /*Iterator<NodeData> I = this.graph.nodeIter();
//        int i=0;
//        while (I.hasNext()){
//            Iterator<NodeData> J = this.graph.nodeIter();
//            int j=0;
//            while (J.hasNext()){
//                paths[i][j]=this.graph.getEdge(I.next().getKey(),J.next().getKey()).getWeight();
//                j++;
//            }
//            i++;
//        }
//        Iterator<NodeData> K = this.graph.nodeIter();
//        while (K.hasNext()){
//            I = this.graph.nodeIter();
//            while (I.hasNext()){
//                Iterator<NodeData> J = this.graph.nodeIter();
//                while (J.hasNext()){
//                    int k = K.next().getKey();
//                    i = I.next().getKey();
//                    int j = J.next().getKey();
//                    if(this.graph.getEdge(i,k).getWeight()!=0&&this.graph.getEdge(k,j).getWeight()!=0)
//                    {
//                        if(this.graph.getEdge(i,j).getWeight() > this.graph.getEdge(i,k).getWeight()+this.graph.getEdge(k,j).getWeight()||matrix[i][j]==0){
//                            String info=this.graph.getEdge(i,j).getInfo();
//                            int tag=this.graph.getEdge(i,j).getTag();
//                            this.graph.removeEdge(i,j);
//                            this.graph.connect(i,j,this.graph.getEdge(i,k).getWeight()+this.graph.getEdge(k,j).getWeight());
//                            this.graph.getEdge(i,j).setInfo(info);
//                            this.graph.getEdge(i,j).setTag(tag);
//                        }
//                    }
//                }
//            }
//        }
//         */
//        Iterator<EdgeData> eitr = this.graph.edgeIter();
//        int i=0;
//        while (I.hasNext()){
//            Iterator<NodeData> J = this.graph.nodeIter();
//            int j=0;
//            while (J.hasNext()){
//                paths[i][j]=this.graph.getEdge(I.next().getKey(),J.next().getKey()).getWeight();
//                j++;
//            }
//            i++;
//        }
//
        return -1;}
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
            graph.getEdge(edge.getSrc(),edge.getDest()).setTag(edge.getTag());
            graph.getEdge(edge.getSrc(),edge.getDest()).setInfo(edge.getInfo());
        }
        return graph;
    }
    @Override
    public List<NodeData> shortestPath(int src, int dest){return new LinkedList<NodeData>();
    }
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
