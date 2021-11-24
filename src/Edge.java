import api.EdgeData;

public class Edge implements EdgeData {
    private int src, dest,tag;
    private double weigh;
    private String info;

    public Edge(int src, int dest, int tag, double weigh,String info){
        this.src=src;
        this.dest=dest;
        this.tag=tag;
        this.weigh=weigh;
        this.info=info;
    }
    public Edge(int src, int dest, double weigh){
        new Edge(src,dest,0,weigh,"");
    }
    @Override
    public int getSrc() {
        return  this.src;
    }
    @Override
    public int getDest() {
        return this.dest;
    }
    @Override
    public double getWeight() {
        return this.weigh;
    }
    @Override
    public String getInfo() {
        return this.info;
    }
    @Override
    public void setInfo(String s) {
        this.info=info;
    }
    @Override
    public int getTag() {
        return this.tag;
    }
    @Override
    public void setTag(int t) {
        this.tag=t;
    }
    

}

