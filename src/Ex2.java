import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;



import api.EdgeData;
import api.NodeData;
import com.google.gson.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import implementation.Graph;
import implementation.GraphAlgorithms;

import java.lang.reflect.Type;



/**
* This class is the main class for Ex2 - your implementation will be tested using this class.
*/
public class Ex2 {
   /**
    * This static function will be used to test your implementation
    * @param json_file - a json file (e.g., G1.json - G3.gson)
    * @return
    */
   public static DirectedWeightedGraph getGrapg(String json_file) {
       DirectedWeightedGraph ans = null;

        try
        {
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Graph.class, new GraphJsonDeserializer());
            Gson gson = builder.create();
            //continue as usual..

            FileReader reader = new FileReader(json_file);
            ans = gson.fromJson(reader, Graph.class);



            System.out.println("NODES:");
            Iterator<NodeData> nodeIter = ans.nodeIter();
            while(nodeIter.hasNext()){
                System.out.println(nodeIter.next());
            }

            System.out.println("EDGES:");
            Iterator<EdgeData> edgeIter = ans.edgeIter();
            while(edgeIter.hasNext()){
                System.out.println(edgeIter.next());
            }

            System.out.println("All Graph:");
            System.out.println(ans);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return ans;
   }
   /**
    * This static function will be used to test your implementation
    * @param json_file - a json file (e.g., G1.json - G3.gson)
    * @return
    */
   public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {
       DirectedWeightedGraphAlgorithms ans = new GraphAlgorithms(getGrapg(json_file));
       return ans;
   }
   /**
    * This static function will run your GUI using the json fime.
    * @param json_file - a json file (e.g., G1.json - G3.gson)
    *
    */
   public static void runGUI(String json_file) {
       DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
       // ****** Add your code here ******
       //
       // ********************************
   }


    public static void main(String[] args)
    {
//        DirectedWeightedGraph graph = new Graph();
//		serialize();
//        graph = getGrapg("data/G1.json");
        DirectedWeightedGraphAlgorithms graphAlgo = new GraphAlgorithms();
        graphAlgo = getGrapgAlgo("data/G1.json");
        System.out.println("new Graph Algo is:");
        System.out.println(graphAlgo);

    }
}
