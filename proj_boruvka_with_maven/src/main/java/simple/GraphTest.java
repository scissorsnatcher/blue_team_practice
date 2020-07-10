package simple;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class GraphTest {

    private static Graph createEmptyGraph() {
        return new Graph();
    }

    @Test
    public void Test_Del_Edge() {
        Graph a = createEmptyGraph();
        a.readFromFile("test");
        a.deleteEdge();
        Assert.assertEquals(a.getEdgeNum(), 4);
    }

    @Test
    public void Test_Edge_Num() {
        Graph a = createEmptyGraph();
        a.readFromFile("test");
        Assert.assertEquals(a.getEdgeNum(), 5);
    }

    @Test
    public void Test_Vertices_Num() {
        Graph a = createEmptyGraph();
        a.readFromFile("test");
        Assert.assertEquals(a.getVertNum(), 4);
    }
    
}