package tests;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
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
        assertEquals("Graph{nodes={0=Node{weight=0.0, id=0, tag=0, info='', location=Geo_Location{x=0.0, y=0.0, z=0.0}}, 1=Node{weight=0.0, id=1, tag=0, info='', location=Geo_Location{x=0.0, y=0.0, z=0.0}}, 2=Node{weight=0.0, id=2, tag=0, info='', location=Geo_Location{x=0.0, y=0.0, z=0.0}}, 3=Node{weight=0.0, id=3, tag=0, info='', location=Geo_Location{x=0.0, y=0.0, z=0.0}}}, edges={0={1=Edge{src=0, dest=1, tag=0, weigh=0.0, info=''}}, 1={}, 2={1=Edge{src=2, dest=1, tag=0, weigh=0.0, info=''}}, 3={2=Edge{src=3, dest=2, tag=0, weigh=0.0, info=''}}}, MC=7}",algog1.getGraph().toString());
    }

    @Test
    void isConnected() {
    }

    @Test
    void shortestPathDist() {
    }

    @Test
    void getGraph() {
        algog1.init(graph1);
        DirectedWeightedGraph get1=algog1.getGraph();
        assertEquals("Graph{nodes={0=Node{weight=0.0, id=0, tag=0, info='', location=Geo_Location{x=0.0, y=0.0, z=0.0}}, 1=Node{weight=0.0, id=1, tag=0, info='', location=Geo_Location{x=0.0, y=0.0, z=0.0}}, 2=Node{weight=0.0, id=2, tag=0, info='', location=Geo_Location{x=0.0, y=0.0, z=0.0}}, 3=Node{weight=0.0, id=3, tag=0, info='', location=Geo_Location{x=0.0, y=0.0, z=0.0}}}, edges={0={1=Edge{src=0, dest=1, tag=0, weigh=0.0, info=''}}, 1={}, 2={1=Edge{src=2, dest=1, tag=0, weigh=0.0, info=''}}, 3={2=Edge{src=3, dest=2, tag=0, weigh=0.0, info=''}}}, MC=7}",algog1.getGraph().toString());
        assertEquals(get1.toString(),algog1.getGraph().toString());
        get1.removeNode(0);
        assertEquals(get1.toString(),algog1.getGraph().toString());
    }

    @Test
    void copy() {
        algog1.init(graph1);
        DirectedWeightedGraph copy1=algog1.copy();
        assertEquals("Graph{nodes={0=Node{weight=0.0, id=0, tag=0, info='', location=Geo_Location{x=0.0, y=0.0, z=0.0}}, 1=Node{weight=0.0, id=1, tag=0, info='', location=Geo_Location{x=0.0, y=0.0, z=0.0}}, 2=Node{weight=0.0, id=2, tag=0, info='', location=Geo_Location{x=0.0, y=0.0, z=0.0}}, 3=Node{weight=0.0, id=3, tag=0, info='', location=Geo_Location{x=0.0, y=0.0, z=0.0}}}, edges={0={1=Edge{src=0, dest=1, tag=0, weigh=0.0, info=''}}, 1={}, 2={1=Edge{src=2, dest=1, tag=0, weigh=0.0, info=''}}, 3={2=Edge{src=3, dest=2, tag=0, weigh=0.0, info=''}}}, MC=7}",copy1.toString());
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