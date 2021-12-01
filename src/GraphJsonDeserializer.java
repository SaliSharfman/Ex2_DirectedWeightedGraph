
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;


import api.DirectedWeightedGraph;
import api.NodeData;
import com.google.gson.*;

public class GraphJsonDeserializer implements JsonDeserializer<Graph>
{

    @Override
    public Graph deserialize(JsonElement json, Type arg1, JsonDeserializationContext arg2) throws JsonParseException
    {
        JsonObject jsonObject = json.getAsJsonObject();
//        String parkingName = jsonObject.get("parkingName").getAsString();
        DirectedWeightedGraph graph = new Graph();
//        Parking parking = new Parking(parkingName);

        JsonObject nodesJsonObj = jsonObject.get("Nodes").getAsJsonObject();

        for (Entry<String, JsonElement> set : nodesJsonObj.entrySet())
        {
            String hashKey = set.getKey(); //the key of the hashmap
            JsonElement jsonValueElement = set.getValue(); //the value of the hashmap as json element
            String nodePos = jsonValueElement.getAsJsonObject().get("pos").getAsString();
            //Maybe we need to do: set = set + 1
            int nodeId = jsonValueElement.getAsJsonObject().get("id").getAsInt();


            NodeData node = new Node(nodePos,nodeId);
            graph.addNode(node);

        }

        JsonObject edgesJsonObj = jsonObject.get("Edges").getAsJsonObject();

        for (Entry<String, JsonElement> set : edgesJsonObj.entrySet())
        {
            String hashKey = set.getKey(); //the key of the hashmap
            JsonElement jsonValueElement = set.getValue(); //the value of the hashmap as json element
            int edgeSrc = jsonValueElement.getAsJsonObject().get("src").getAsInt();
            double edgeW = jsonValueElement.getAsJsonObject().get("w").getAsDouble();
            int edgeDest = jsonValueElement.getAsJsonObject().get("dest").getAsInt();

//            Edge edge = new Edge(edgeSrc,edgeW,edgeDest);
            graph.connect(edgeSrc,edgeDest,edgeW);

        }


        return (Graph) graph;
    }

}

