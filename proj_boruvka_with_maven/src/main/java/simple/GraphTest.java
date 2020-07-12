package simple;

import java.io.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class GraphTest {

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
