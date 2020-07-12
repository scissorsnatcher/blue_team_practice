package simple;

<<<<<<< Updated upstream
import org.junit.Assert;
=======
import java.io.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
>>>>>>> Stashed changes
import org.junit.Test;

import static org.junit.Assert.*;

public class GraphTest {

<<<<<<< Updated upstream
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
=======
    Graph a;
    @Before
    public void before() {
        a = new Graph();
    }

    @Test
    public void generateGraph() {
        a.generateGraph(4, 5);
        Assert.assertEquals(a.getEdgeNum(), 5);
    }

    @Test
    public void readFromFile() {
        a.readFromFile("test");
        Assert.assertEquals(a.getVertNum(), 4);
    }

    @Test
    public void saveGraph() {
        a.generateGraph(5, 5);
        try{
            a.saveGraph("test1");
            BufferedReader br = new BufferedReader(new FileReader("test1"));
            String FirstLine = br.readLine();
            Assert.assertFalse(FirstLine.isEmpty());
        }
        catch (IOException e)
        {}

    }
    @Test
    public void clear() {
        a.generateGraph(5, 5);
        a.clear();
        Assert.assertTrue((a.getVertNum() == 0 && a.factEdgeNum == 0));
    }

    @Test
    public void addNode() {
        a.readFromFile("test");
        int node = a.getVertNum();
        a.addNode("Z", 10, 10);
        Assert.assertEquals((node + 1), a.getVertNum());
    }

    @Test
    public void addEdge() {
        a.readFromFile("test");
        int edge = a.getEdgeNum();
        a.addEdge(10, 10, 10);
        Assert.assertEquals((edge + 1), a.getEdgeNum());
    }

}
>>>>>>> Stashed changes
