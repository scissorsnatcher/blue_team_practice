package gui;
import simple.Edge;
import simple.Graph;
import simple.Node;
import javax.swing.*;


import java.awt.*;
import java.util.*;

public class GraphPainter extends JComponent{
	

	private static final long serialVersionUID = 1L;
	
	int width;
	int height;
	boolean type;
	private int vertNum;
    private int edgeNum;
    ArrayList<Node> nodes;
    ArrayList<Edge> edges;
    int x,y;
    boolean dragging, m_flag;
    int dragNode;
    
    public GraphPainter() {
		nodes = new ArrayList<Node>();
		edges = new ArrayList<Edge>();
		width = 30;
		height = 30;
		type = false;
    };
	public void GraphPaint(Graph g){

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
		    	//vertNum++;
		    	//Graphics g = this.getGraphics();
		    	this.repaint();
		    	this.revalidate();
		    	//this.paintComponent(g);
		    	}
	 public void removeNode() {
		 	
		 		nodes.remove(nodes.size() - 1);
		 		//vertNum--;
		 		this.repaint();
		 		this.revalidate();
	 }
	 
	 public void addEdge(int i, int j, int k) {
		  
		    	edges.add(new Edge(i,j,k));
		    	//edgeNum++;
		    	//Graphics g = this.getGraphics();
		    	this.repaint();
		    	this.revalidate();

		    	//this.paintComponent(g);
	 }
	 public void removeEdge() {
		  
		    edges.remove(edges.size() - 1);
		    //edgeNum--;
		    //Graphics g = this.getGraphics();
	    	this.repaint();
	    	this.revalidate();
	    	//this.paintComponent(g);
	 }
	 public void removeEdge(int i, int j, int k, Color c) {
		
		 Graphics g = this.getGraphics();
		 
		 //for (Edge h: edges) {
		for(int u = 0; u < edges.size(); u++) {
			if (edges.get(u) != null) {
				 if ((edges.get(u).getSrc() == i)&&(edges.get(u).getDest() == j)&&(edges.get(u).getWeight() == k)) {
					 
					 edges.remove(edges.get(u));
					 //this.edgeNum--;
					 this.repaint();
					 this.revalidate();
					 
				 }
				 //this.repaint();
				 //this.revalidate();
		 }
		}
		 //this.edgeNum--;
		 
		 g.setColor(c);
		 //repaint();
		 this.repaint();
		 this.revalidate();
		 //g.drawLine(nodes.get(e.getSrc()).getX(), nodes.get(e.getSrc()).getY(),nodes.get(e.getDest()).getX(),  nodes.get(e.getDest()).getY());
		 //g.drawString(e.getWeightStr(), (nodes.get(e.getSrc()).getX() + nodes.get(e.getDest()).getX())/2, (nodes.get(e.getSrc()).getY() + nodes.get(e.getDest()).getY())/2);
		 //repaint();
	 }
	 public void clearGraph() {
		 nodes = new ArrayList<Node>();
		 edges = new ArrayList<Edge>();
		 vertNum = 0;
		 edgeNum = 0;
		 getGraphics().setColor(Color.WHITE);
		 getGraphics().clearRect(0, 0, 1000, 1000);
		 this.repaint();
	 }
	 public void fillEdge(int i, int j, int k, Color c, boolean flag) {
		 
		 Edge e = new Edge(i, j, k);
		 Graphics g = this.getGraphics();
	
		 FontMetrics f = g.getFontMetrics();
		 int nodeHeight = Math.max(height, f.getHeight());;
		 
		 int circlex = (nodes.get(i).getX() + nodes.get(j).getX())/2;
		 int circley = (nodes.get(i).getY() + nodes.get(j).getY())/2;
		 int radius = (int) Math.sqrt((int)(Math.pow((int)nodes.get(i).getY() - circley, 2) + Math.pow((int)nodes.get(i).getX() - circlex, 2)));
			
		 double angle_1 = Math.abs(Math.atan2(nodes.get(i).getY() - circley, nodes.get(i).getX() - circlex) * 180 / Math.PI);
		 double angle_2 = Math.abs(Math.atan2(nodes.get(j).getY() - circley, nodes.get(j).getX() - circlex) * 180 / Math.PI);
		 if (nodes.get(i).getY() > circley) {angle_1 = 360 - angle_1;}
		 if (nodes.get(j).getY() > circley) angle_2 = 360 - angle_2;
		 double angle_3;
		 if (angle_1 < angle_2) 
				angle_3 = angle_1;
		 else angle_3 = angle_2;
		 
		 System.out.println(i + "," + j + "," + flag);
		 g.setColor(c);
		 if ((flag == false) ) 
			 g.drawLine(nodes.get(e.getSrc()).getX(), nodes.get(e.getSrc()).getY(),nodes.get(e.getDest()).getX(),  nodes.get(e.getDest()).getY());
		 else if (flag == true) 
			 g.drawArc(circlex - radius,circley - radius, 2*radius, 2*radius, (int)angle_3, (int)Math.abs(angle_2 - angle_1));
		 
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
		
		g.setColor(Color.black);
		
		//for (Edge e : edges) {
		//	g.drawLine(nodes.get(e.getSrc()).getX(), nodes.get(e.getSrc()).getY(),nodes.get(e.getDest()).getX(),  nodes.get(e.getDest()).getY());
		//	g.drawString(e.getWeightStr(), (nodes.get(e.getSrc()).getX() + nodes.get(e.getDest()).getX())/2, (nodes.get(e.getSrc()).getY() + nodes.get(e.getDest()).getY())/2);
		//}
		
		for (Edge e : edges) {
			for (Edge m :edges) {
				if((e.getSrc() < nodes.size())&&(e.getDest() < nodes.size())&&(m.getSrc() < nodes.size())&&(m.getDest() < nodes.size())) {
					
					if ((nodes.get(m.getSrc()).name == nodes.get(e.getSrc()).name) && (nodes.get(m.getDest()).name == nodes.get(e.getDest()).name) && (m.getWeight() == e.getWeight())) {continue;}
				
					//if ((nodes.get(m.getSrc()).name == nodes.get(e.getSrc()).name) && (nodes.get(m.getDest()).name == nodes.get(e.getDest()).name)){e.flag = m.getWeightStr();e.m_flag = false;}
			
					if ((nodes.get(m.getSrc()).name == nodes.get(e.getDest()).name) && (nodes.get(m.getDest()).name == nodes.get(e.getSrc()).name) || ((nodes.get(m.getSrc()).name == nodes.get(e.getSrc()).name) && (nodes.get(m.getDest()).name == nodes.get(e.getDest()).name))) {
						
							
								e.flag = m;
								e.m_flag = true;
							
					}
				}
			}
		}
		for (Edge e : edges) {
			if ((e.getSrc() < nodes.size())&&((e.getDest() < nodes.size()))){
			//g.drawLine(nodes.get(e.getSrc()).getX(), nodes.get(e.getSrc()).getY(),nodes.get(e.getDest()).getX(),  nodes.get(e.getDest()).getY());
			int circlex = (nodes.get(e.getSrc()).getX() + nodes.get(e.getDest()).getX())/2;
			int circley = (nodes.get(e.getSrc()).getY() + nodes.get(e.getDest()).getY())/2;
			int radius = (int) Math.sqrt((int)(Math.pow((int)nodes.get(e.getSrc()).getY() - circley, 2) + Math.pow((int)nodes.get(e.getSrc()).getX() - circlex, 2)));
			
			double angle_1 = Math.abs(Math.atan2(nodes.get(e.getSrc()).getY() - circley, nodes.get(e.getSrc()).getX() - circlex) * 180 / Math.PI);
			double angle_2 = Math.abs(Math.atan2(nodes.get(e.getDest()).getY() - circley, nodes.get(e.getDest()).getX() - circlex) * 180 / Math.PI);
			if (nodes.get(e.getSrc()).getY() > circley) {angle_1 = 360 - angle_1;}
			if (nodes.get(e.getDest()).getY() > circley) angle_2 = 360 - angle_2;
			double angle_3;
			if (angle_1 < angle_2) 
				angle_3 = angle_1;
			else angle_3 = angle_2;
			
			
			if (e.flag == null) {
				
				g.drawLine(nodes.get(e.getSrc()).getX(), nodes.get(e.getSrc()).getY(),nodes.get(e.getDest()).getX(),  nodes.get(e.getDest()).getY());
				g.drawString(e.getWeightStr(), (nodes.get(e.getSrc()).getX() + nodes.get(e.getDest()).getX())/2, (nodes.get(e.getSrc()).getY() + nodes.get(e.getDest()).getY())/2);
		

			}
			else {
				if(e.m_flag) {
					//g.setColor(Color.WHITE);
					g.setColor(Color.BLACK);
					//g.drawString(e.flag, (nodes.get(e.getSrc()).getX() + nodes.get(e.getDest()).getX())/2, (nodes.get(e.getSrc()).getY() + nodes.get(e.getDest()).getY())/2);
					//g.setColor(Color.BLACK);
					
					g.drawString(e.flag.getWeightStr() + ","  + e.getWeightStr(), (nodes.get(e.getSrc()).getX() + nodes.get(e.getDest()).getX())/2, (nodes.get(e.getSrc()).getY() + nodes.get(e.getDest()).getY())/2);
					//if (nodes.get(e.getSrc()).getY() < nodes.get(e.getDest()).getY())
					//	g.drawString(e.getWeightStr(), (int)(circlex + radius * Math.sin(angle_3 + 90)), (int)(circley + radius * Math.cos(angle_3 +  90)));
					//else 
					//	g.drawString(e.getWeightStr(), (int)(circlex + radius * Math.sin(angle_3 - 90)), (int)(circley + radius * Math.cos(angle_3 -  90)));
					//g.setColor(Color.BLACK);
					g.drawLine(nodes.get(e.getSrc()).getX(), nodes.get(e.getSrc()).getY(),nodes.get(e.getDest()).getX(),  nodes.get(e.getDest()).getY());
					g.drawArc(circlex - radius,circley - radius, 2*radius, 2*radius, (int)angle_3, (int)Math.abs(angle_2 - angle_1));
					
					e.flag.m_flag = false;
					e.m_flag = false;
					e.arc = false;
					e.flag.arc = true;
					System.out.println (e.arc + "," + e.flag.arc);
					e.flag.flag = null;
					e.flag = null;
					//g.drawOval(300, 150, 200, 200, (int)angle_3, (int)Math.abs(angle_2 - angle_1));
					//g.clearRect((nodes.get(e.getSrc()).getX() + nodes.get(e.getDest()).getX())/2, (nodes.get(e.getSrc()).getY() + nodes.get(e.getDest()).getY())/2 - 15, 15, 15);
					//e.setWeightStr(e.flag + "," + e.getWeightStr());
					//g.setColor(Color.BLACK);
					//g.drawString(e.getWeightStr(), (nodes.get(e.getSrc()).getX() + nodes.get(e.getDest()).getX())/2, (nodes.get(e.getSrc()).getY() + nodes.get(e.getDest()).getY())/2);
					//g.drawString(e.getWeightStr(), (int)(circlex + radius * Math.sin(angle_3 + (int)Math.abs(angle_2 - angle_1)/2)), (int)(circley + radius * Math.cos(angle_3 +  (int)Math.abs(angle_2 - angle_1)/2)));
					
				}
				else {
					//g.setColor(Color.BLACK);
					//g.drawString(e.flag, (nodes.get(e.getSrc()).getX() + nodes.get(e.getDest()).getX())/2, (nodes.get(e.getSrc()).getY() + nodes.get(e.getDest()).getY())/2);
					//g.setColor(Color.BLACK);
					//g.drawLine(nodes.get(e.getSrc()).getX(), nodes.get(e.getSrc()).getY(),nodes.get(e.getDest()).getX(),  nodes.get(e.getDest()).getY());//g.setColor(Color.BLACK);
					//g.drawString(e.getWeightStr(), (nodes.get(e.getSrc()).getX() + nodes.get(e.getDest()).getX())/2, (nodes.get(e.getSrc()).getY() + nodes.get(e.getDest()).getY())/2);
					//g.setColor(Color.BLACK);
					//g.drawString(e.getWeightStr(), (nodes.get(e.getSrc()).getX() + nodes.get(e.getDest()).getX())/2, (nodes.get(e.getSrc()).getY() + nodes.get(e.getDest()).getY())/2);
					//g.drawLine(nodes.get(e.getSrc()).getX(), nodes.get(e.getSrc()).getY(),nodes.get(e.getDest()).getX(),  nodes.get(e.getDest()).getY());
					//g.drawLine(nodes.get(e.getSrc()).getX(), nodes.get(e.getSrc()).getY(),nodes.get(e.getDest()).getX(),  nodes.get(e.getDest()).getY());
					//g.drawString(e.getWeightStr(), (nodes.get(e.getSrc()).getX() + nodes.get(e.getDest()).getX())/2, (nodes.get(e.getSrc()).getY() + nodes.get(e.getDest()).getY())/2);
				}
			}
			}
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
		public void setDragging(boolean b) {
		dragging = b;
		
		}
		public boolean isSelecting(int x, int y) {
			for (int i = 0; i < nodes.size(); i++) {
				if ((Math.abs(nodes.get(i).getX()+15 - x) < 30) && (Math.abs(nodes.get(i).getY()+15 - y) < 30)){
					dragNode = i;
					return true;
				}
			}return false;
		}
		public boolean isDragging() {
			return dragging;
		}
		public void setCoordinates(int x, int y) {
			Node node = new Node(nodes.get(dragNode).name, x, y);
			nodes.set(dragNode, node);
			repaint();
		}

	
}
