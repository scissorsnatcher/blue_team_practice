package gui;
import simple.Edge;
import simple.Graph;
import simple.Node;
import javax.swing.*;


import java.awt.*;
import java.util.*;

public class GraphPainter extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int width;
	int height;
	boolean type;
	private int vertNum;
    private int edgeNum;
    ArrayList<Node> nodes;
    ArrayList<Edge> edges;
    
    public GraphPainter() {
		nodes = new ArrayList<Node>();
		edges = new ArrayList<Edge>();
		width = 30;
		height = 30;
		type = false;
    };
	public void GraphPaint(Graph g){
		
		//this.repaint();
		vertNum = g.getVertNum();
		edgeNum = g.getEdgeNum();
	    
		for (int i = 0; i < vertNum; i++) {
			double d = 400 + 100 * Math.sin(2*i*Math.PI/vertNum);
			double m = 250 + 100 * Math.cos(2*i*Math.PI/vertNum);
			this.addNode(g.getVertNames()[i], (int) d, (int) m ) ;
		}
		for( int i = 0; i < edgeNum; i++) {
			this.addEdge( g.getEdges()[i].getSrc(), g.getEdges()[i].getDest(), g.getEdges()[i].getWeight());
			
		}
		width = 30;
		height = 30;
	}
	 
	 public void addNode(String name, int x, int y) {
		 
		    	nodes.add(new Node(name,x,y));
		    	//Graphics g = this.getGraphics();
		    	this.repaint();
		    	//this.paintComponent(g);
		    	}
	 
	 
	 public void addEdge(int i, int j, int k) {
		  
		    	edges.add(new Edge(i,j,k));
		    	//Graphics g = this.getGraphics();
		    	this.repaint();
		    	//this.paintComponent(g);
	 }
	 public void fillEdge(int i, int j, int k) {
		 
		 Edge e = new Edge(i, j, k);
		 Graphics g = this.getGraphics();
		 
		 type = true;

		 
		 FontMetrics f = g.getFontMetrics();
		 int nodeHeight = Math.max(height, f.getHeight());
		 g.setColor(Color.red);
		 
		 //addEdge(i,j,k);
		 //addNode(nodes.get(e.getSrc()).name, nodes.get(e.getSrc()).getX(), nodes.get(e.getSrc()).getY());
		 //addNode(nodes.get(e.getDest()).name, nodes.get(e.getDest()).getX(), nodes.get(e.getDest()).getY());
		 //this.repaint();
		 
		 g.drawLine(nodes.get(e.getSrc()).getX(), nodes.get(e.getSrc()).getY(),nodes.get(e.getDest()).getX(),  nodes.get(e.getDest()).getY());
		 
		 ArrayList<Node> fillnodes  = new ArrayList<Node>();
		 fillnodes.add(nodes.get(e.getSrc()));
		 fillnodes.add(nodes.get(e.getDest()));
		 for (Node n : fillnodes) {
			 int nodeWidth = Math.max(width, f.stringWidth(n.name)+width/2);
			    g.setColor(Color.white);
			    g.fillOval(n.getX()-nodeWidth/2, n.getY()-nodeHeight/2, 
				       nodeWidth, nodeHeight);
			    g.setColor(Color.black);
			    g.drawOval(n.getX()-nodeWidth/2, n.getY()-nodeHeight/2, 
				       nodeWidth, nodeHeight);
			    
			    g.drawString(n.name, n.getX()-f.stringWidth(n.name)/2,
					 n.getY() + f.getHeight()/2);
		 }
	 }
    //@Override	 
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		FontMetrics f = g.getFontMetrics();
		int nodeHeight = Math.max(height, f.getHeight());
		
		if (!type)
		g.setColor(Color.black);
		else 
		g.setColor(Color.red);
		
		for (Edge e : edges) {
			g.drawLine(nodes.get(e.getSrc()).getX(), nodes.get(e.getSrc()).getY(),nodes.get(e.getDest()).getX(),  nodes.get(e.getDest()).getY());
			g.drawString(e.getWeightStr(), (nodes.get(e.getSrc()).getX() + nodes.get(e.getDest()).getX())/2, (nodes.get(e.getSrc()).getY() + nodes.get(e.getDest()).getY())/2);
		}

		for (Node n : nodes) {
		    int nodeWidth = Math.max(width, f.stringWidth(n.name)+width/2);
		    g.setColor(Color.white);
		    g.fillOval(n.getX()-nodeWidth/2, n.getY()-nodeHeight/2, 
			       nodeWidth, nodeHeight);
		    g.setColor(Color.black);
		    g.drawOval(n.getX()-nodeWidth/2, n.getY()-nodeHeight/2, 
			       nodeWidth, nodeHeight);
		    
		    g.drawString(n.name, n.getX()-f.stringWidth(n.name)/2,
				 n.getY() + f.getHeight()/2);
		    
		    
		}
		
		 
	 }

	
}
