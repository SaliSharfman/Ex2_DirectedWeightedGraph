
<center><head><b>Ex2_DirectedWeightedGraphas</b></head></br>
<u>authors:  Sali Sharfman, Josef Sokolov.</u></br></br>
This program deals with the graphic realization of weighted and directed graphs. </br>As part of implementing the graphs we implemented Node, Edge, Graph, and algorithms on the graph.</br>The algorithms we implemented are: finding a central node, finding the shortest route between two nodes and its length, checking if the graph is connected, and finding a solution to the problem of finding shortest path that goes over all nodes in the graph  .</br>
In addition, the software has editing options for the graph, which are adding a node, deleting a node, connecting an edge, and deleting an edge.</br>


You can load graphs from json to decapitate the graphs and save again as a json file.</br>
We will now look at the graphical interface of the software </br>
<img width="1093" alt="Screen Shot 2021-11-18 at 0 26 08" src="https://user-images.githubusercontent.com/86108478/145414449-bdc0f100-b673-4853-9a1f-f18745dfdaf3.png">

<b>File Menu</b></br>By clicking <b>Load</b> you can load a graph.</br>By clicking <b>Save</b> the graph will be saved in data folder with the given name.</br>By clicking<b> Exit</b> the program will be closed.</br><img width="100" alt="Screen Shot 2021-11-18 at 0 26 08" src="https://user-images.githubusercontent.com/86108478/145414914-2169c8f6-b3c6-4d95-8db2-143b80588c8f.png"></br>

<b>Edit Menu</b></br>By clicking <b>show numbers </b>you can display numbers representing the node id's, and the edge weights,clicking again will remove the numbers.</br>By clicking <b>add node</b> it will be possible to add nodes on the graph (this is possible when this button is bold).</br>By clicking <b>remove node</b> you can remove node given node id.</br>By clicking <b>connect edge</b> you can connect edge with given src and dest that are the id of the start and end node.</br>By clicking <b>remove edge</b> you can also remove edge in the same way of the connection. </br><img width="100" alt="Screen Shot 2021-11-18 at 0 26 08" src="https://user-images.githubusercontent.com/86108478/145415876-cd4df199-65ab-46fc-bbc1-ef7fd17bff96.png">
</br>

<b>Graph Menu</b></br>By clicking on <b>Is Connected</b> you can check whether the graph is connected, which means that each node has a path for each node.</br>By clicking <b>Center</b> you will find the central node, the one from which the path to the other nodes is minimal,this node will be colored red. </br>By clicking on <b>Random Graph</b> you can choose number of nodes, then a new graph with the given number of nodes will be loaded on random locations, every node in the graph will be connected at leat 2 times, one as src and one as dest.</br>By clicking <b>Clear Colors</b> all the colors that have changed in the graph will return to their source.</br>By clicking <b>Clear Graph</b> the graph will be deleted and a blank grapg will be loaded to apply a job.</br><img width="100" alt="Screen Shot 2021-11-18 at 0 26 08" src="https://user-images.githubusercontent.com/86108478/145498897-8dacf099-44bc-4dfe-9db5-0c7f7f8b02dc.png"></br>


.</br><b>Path Menu</b></br>By clicking <b>Shortest Path</B> you can find out what is the shortest path between two nodes by entering their id, the route will be displayed and colored in green, and its length will also be displayed.</br>By clicking<b> TSP</b> and giving a list of nodes as a string of numbers, representing the comma-separated node id will be calculated in the shortest circular path passing through all the nodes. This path will be displayed and colored in green (note that you only use numbers and commas). Example of correct input: "0,1,2,3,4".</br><img width="100" alt="Screen Shot 2021-11-18 at 0 26 08" src="https://user-images.githubusercontent.com/86108478/145416329-5a05201a-5fb7-48b1-88e8-1e15987ab656.png">

<b>Help Menu</b></br>By clicking <b>Help</b> you can open this README file to help yourself to use the program</br><img width="200" alt="Screen Shot 2021-11-18 at 0 26 08" src="https://user-images.githubusercontent.com/86108478/145416436-499ab5f3-8e16-4212-8bae-f7179ca158e4.png"></br>







The UML diagram presents the division of our classes: [UML.class.Ex2.pdf](https://github.com/SaliSharfman/Ex2_DirectedWeightedGraph/files/7700031/UML.class.Ex2.pdf)

<img width="1093" alt="Screen Shot 2021-11-18 at 0 26 08" src="https://user-images.githubusercontent.com/86108478/145435553-20cdd525-4943-4b58-a51c-14224432c1bd.jpg">
</br>
<b>Run Time Log:</b></br>
</br><b>isConnected:</b></br>
G1.json (17 Nodes):500 ms</br>
G2.json (31 Nodes):500 ms</br>
G3.json (48 Nodes):531 ms</br>
1,000 Nodes:46 ms</br>
10,000 Nodes:1 sec 803 ms</br>
100,000 Nodes:2 min 37 sec</br>

</br><b>center:</b></br>
G1.json (17 Nodes):499 ms</br>
G2.json (31 Nodes):500 ms</br>
G3.json (48 Nodes):468 ms</br>
1,000 Nodes:7 sec 697 ms</br>
   10,000 Nodes:10 min 52 sec</br>
   100,000 Nodes:</br>
   
</br><b>shortestPathDist:</b></br>
G1.json (17 Nodes):4 sec 796 ms</br>
G2.json (31 Nodes):4 sec 863 ms</br>
G3.json (48 Nodes):4 sec 944 ms</br>
1,000 Nodes:4 sec 925 ms</br>
10,000 Nodes:2 min 58 sec</br>
100,000 Nodes:</br>

</br><b>shortestPath:</b></br>
G1.json (17 Nodes):4 sec 868 ms</br>
G2.json (31 Nodes):4 sec 815 ms</br>
G3.json (48 Nodes):4 sec 942 ms</br>
1,000 Nodes:5 sec 57 ms</br>
10,000 Nodes:6 sec 487 ms</br>
100,000 Nodes:2 min 36 sec</br>

</br><b>tsp:</b></br>
G1.json (17 Nodes):6 sec 911 ms</br>
G2.json (31 Nodes):9 sec 882 ms</br>
G3.json (48 Nodes):18 sec 931 ms</br>
   1,000 Nodes:</br>
   10,000 Nodes:</br>
   100,000 Nodes:</br>
   
   </br><b>Instructions for running the program:</b></br>
   🔹Download the following zip file- 
   [Ex2_jar.zip](https://github.com/SaliSharfman/Ex2_DirectedWeightedGraph/files/7700437/Ex2_jar.zip)
   🔹Extract the files to your computer.</br>
   🔹Open CMD.</br>
   🔹Cd the directory that you saved the files in.</br>
   🔹Write the next command: java -jar Ex2.jar (you can run with a json file argument for example: java -jar Ex2.jar G1.json ).</br>
   🔹The GUI of the project will run, a directory named "data" will appear in the directory that you saved the files    in for loading and saving files, if you runned with a json    file argument the graph that you runned will be drawn and saved in data directory, else, a new empty graph will be opened and saved in data dirctory. </br>
   🔹 Now you can use the program, edit graphs and run the algorithms.</br>
   <b>Have fun! </B></center> 
![icon](https://user-images.githubusercontent.com/86108478/145739726-14159ff4-8878-4ec0-81b6-be6af1a73085.jpg)




