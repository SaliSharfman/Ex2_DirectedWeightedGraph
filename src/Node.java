import api.GeoLocation;
import api.NodeData;
public class Node implements NodeData {

    private double weight;
    private int id,tag;
    private String info;
    public  GeoLocation location;

    public Node(int id,double weight,int tag,String info,GeoLocation location) {
        this.id = id;
        this.weight=weight;
        this.info=info;
        this.location=new Geo_Location(location);
    }
    public Node(String pos, int id) {
        this.id = id;
        this.weight=0;
        this.info="";
        double x= Double.parseDouble(pos.split(",")[0]);
        double y= Double.parseDouble(pos.split(",")[1]);
        double z= Double.parseDouble(pos.split(",")[2]);
        this.location=new Geo_Location(x,y,z);
    }

    @Override
    public int getKey() {
        return this.id;
    }
    @Override
    public GeoLocation getLocation()
    {
        return this.location;
    }
    @Override
    public void setLocation(GeoLocation p)
    {
        this.location=new Geo_Location(p);
    }
    @Override
    public double getWeight() {
        return this.weight;
    }
    @Override
    public void setWeight(double w)
    {
        this.weight=w;
    }
    @Override
    public void setInfo(String s)
    {
        this.info=s;
    }
    @Override
    public String getInfo()
    {
        return this.info;
    }
    @Override
    public int getTag()
    {
        return this.tag;
    }
    @Override
    public void setTag(int t)
    {
        this.tag=t;
    }

}