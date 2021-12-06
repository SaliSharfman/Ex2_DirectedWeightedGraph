package tests;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import implementation.Geo_Location;
import implementation.Graph;
import implementation.GraphAlgorithms;
import implementation.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphAlgorithmsTest {
    DirectedWeightedGraphAlgorithms algog1;
    DirectedWeightedGraph graph1;
    @BeforeEach
    void setUp() {
        algog1 = new GraphAlgorithms();
        graph1 = new Graph();
        graph1.addNode(new Node("0,0,0",0));
        graph1.addNode(new Node("0,0,0",1));
        graph1.addNode(new Node("0,0,0",2));
        graph1.addNode(new Node("0,0,0",3));
        graph1.connect(0,1,0);
        graph1.connect(2,1,0);
        graph1.connect(3,2,0);
    }

    @Test
    void init() {
        algog1.init(graph1);
        assertEquals("Graph{nodes={0=Node{id=0, weight=0.0, tag=0, info='', location=Geo_Location{x=0.0, y=0.0, z=0.0}}, 1=Node{id=1, weight=0.0, tag=0, info='', location=Geo_Location{x=0.0, y=0.0, z=0.0}}, 2=Node{id=2, weight=0.0, tag=0, info='', location=Geo_Location{x=0.0, y=0.0, z=0.0}}, 3=Node{id=3, weight=0.0, tag=0, info='', location=Geo_Location{x=0.0, y=0.0, z=0.0}}}, edges={0={1=Edge{src=0, dest=1, tag=0, weigh=0.0, info=''}}, 1={}, 2={1=Edge{src=2, dest=1, tag=0, weigh=0.0, info=''}}, 3={2=Edge{src=3, dest=2, tag=0, weigh=0.0, info=''}}}, MC=7}",algog1.getGraph().toString());
    }

    @Test
    void isConnected() {
        graph1.connect(1,3,0);
        graph1.connect(2,0,0);
       // graph1.removeEdge(3,2);
        algog1.init(graph1);
        algog1.isConnected();
        assertTrue(algog1.isConnected());
    }

    @Test
    void shortestPathDist() {
        DirectedWeightedGraphAlgorithms alg = new GraphAlgorithms();
        alg.getGraph().addNode(new Node("0,0,0",1));
        alg.getGraph().addNode(new Node("0,0,0",2));
        alg.getGraph().addNode(new Node("0,0,0",3));
        alg.getGraph().addNode(new Node("0,0,0",4));
        alg.getGraph().addNode(new Node("0,0,0",5));
        alg.getGraph().addNode(new Node("0,0,0",6));
        alg.getGraph().addNode(new Node("0,0,0",7));
        alg.getGraph().connect(1,3,2);
        alg.getGraph().connect(3,5,3);
        alg.getGraph().connect(5,7,4);
        alg.getGraph().connect(1,2,1);
       // alg.getGraph().connect(1,5,8);
        alg.getGraph().connect(2,4,2);
        alg.getGraph().connect(4,6,3);
        alg.getGraph().connect(6,7,4);

        alg.getGraph().connect(7,2,6);
        alg.getGraph().connect(6,1,4);
        alg.getGraph().connect(7,1,13);
        alg.getGraph().connect(2,1,1);
       // assertEquals(7,alg.shortestPathDist(7,1));
        assertEquals(9,alg.shortestPathDist(1,7));
        System.out.println(alg.isConnected());
        System.out.println(alg.shortestPath(1,7));
       // System.out.println(alg.center());

    }

    @Test
    void getGraph() {
        algog1.init(graph1);
        DirectedWeightedGraph get1=algog1.getGraph();
        assertEquals("Graph{nodes={0=Node{id=0, weight=0.0, tag=0, info='', location=Geo_Location{x=0.0, y=0.0, z=0.0}}, 1=Node{id=1, weight=0.0, tag=0, info='', location=Geo_Location{x=0.0, y=0.0, z=0.0}}, 2=Node{id=2, weight=0.0, tag=0, info='', location=Geo_Location{x=0.0, y=0.0, z=0.0}}, 3=Node{id=3, weight=0.0, tag=0, info='', location=Geo_Location{x=0.0, y=0.0, z=0.0}}}, edges={0={1=Edge{src=0, dest=1, tag=0, weigh=0.0, info=''}}, 1={}, 2={1=Edge{src=2, dest=1, tag=0, weigh=0.0, info=''}}, 3={2=Edge{src=3, dest=2, tag=0, weigh=0.0, info=''}}}, MC=7}",algog1.getGraph().toString());
        assertEquals(get1.toString(),algog1.getGraph().toString());
        get1.removeNode(0);
        assertEquals(get1.toString(),algog1.getGraph().toString());
    }

    @Test
    void copy() {
        algog1.init(graph1);
        DirectedWeightedGraph copy1=algog1.copy();
        assertEquals("Graph{nodes={0=Node{id=0, weight=0.0, tag=0, info='', location=Geo_Location{x=0.0, y=0.0, z=0.0}}, 1=Node{id=1, weight=0.0, tag=0, info='', location=Geo_Location{x=0.0, y=0.0, z=0.0}}, 2=Node{id=2, weight=0.0, tag=0, info='', location=Geo_Location{x=0.0, y=0.0, z=0.0}}, 3=Node{id=3, weight=0.0, tag=0, info='', location=Geo_Location{x=0.0, y=0.0, z=0.0}}}, edges={0={1=Edge{src=0, dest=1, tag=0, weigh=0.0, info=''}}, 1={}, 2={1=Edge{src=2, dest=1, tag=0, weigh=0.0, info=''}}, 3={2=Edge{src=3, dest=2, tag=0, weigh=0.0, info=''}}}, MC=7}",copy1.toString());
        assertEquals(copy1.toString(),algog1.getGraph().toString());
        copy1.removeNode(0);
        assertNotEquals(copy1.toString(),algog1.getGraph().toString());
    }

    @Test
    void shortestPath() {
    }

    @Test
    void center() {
    }

    @Test
    void tsp() {
    }

    @Test
    void save() {
    }

    @Test
    void load() {
    }
}