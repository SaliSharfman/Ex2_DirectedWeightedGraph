import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;

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
       DirectedWeightedGraphAlgorithms ans = null;
       // ****** Add your code here ******
       //
       // ********************************
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

   private static void deserialize() {
       try
       {
           GsonBuilder builder = new GsonBuilder();
           builder.registerTypeAdapter(Graph.class, new GraphJsonDeserializer());
           Gson gson = builder.create();
           //continue as usual..

           FileReader reader = new FileReader("parking.json");
           DirectedWeightedGraph graph = gson.fromJson(reader, Graph.class);
           System.out.println(graph);
       }
       catch (FileNotFoundException e) {
           e.printStackTrace();
       }
   }
}
