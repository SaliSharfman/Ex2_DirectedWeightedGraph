package implementation;

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
    public Edge(int src, double weigh,int dest){
        this.src=src;
        this.weigh=weigh;
        this.dest=dest;
        this.tag=0;
        this.info="";
    }
    public Edge(EdgeData edge){
        this.src=edge.getSrc();
        this.dest=edge.getDest();
        this.tag=edge.getTag();
        this.weigh=edge.getWeight();
        this.info=edge.getInfo();
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

