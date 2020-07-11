package gui;
import simple.Edge;
import simple.Graph;
import simple.Node;
import javax.swing.*;


import java.awt.*;
import java.util.*;
//import java.awt.geom.Arc2D;
import java.awt.geom.*;
public class GraphPainter extends JComponent{
	

	private static final long serialVersionUID = 1L;
	
	int width;
	int height;
	boolean type;
	private int vertNum;
    private int edgeNum;
    ArrayList<Node> nodes;
    ArrayList<Edge> edges;
    //Node[] nodes_list;
    ArrayList<Edge> delEdges;
    int x,y, red;
    boolean dragging, m_flag;
    int dragNode;
    
    public GraphPainter() {
		nodes = new ArrayList<Node>();
		edges = new ArrayList<Edge>();
		delEdges = new ArrayList<Edge>();
		width = 30;
		height = 30;
		type = false;
    };
	public void GraphPaint(Graph g){

		vertNum = 0;
		edgeNum = 0;
		for (int i = 0; i < g.getVertNum(); i++) {
			double d = 400 + 100 * Math.sin(2*i*Math.PI/g.getVertNum());
			double m = 250 + 100 * Math.cos(2*i*Math.PI/g.getVertNum());
			this.addNode(g.getVertNames()[i], (int) d, (int) m ) ;
			
		}
		for( int i = 0; i < g.getEdgeNum(); i++) {
			this.addEdge( g.getEdges()[i].getSrc(), g.getEdges()[i].getDest(), g.getEdges()[i].getWeight());
			
		}
		width = 30;
		height = 30;
		
		
	}
	 
	 public void addNode(String name, int x, int y) {
		 	
		    	nodes.add(new Node(name,x,y));
		    	vertNum++;
		    	//Graphics g = this.getGraphics();
		    	this.repaint();
		    	this.revalidate();
		    	//this.paintComponent(g);
		    	}
	 
	 public void removeNode(Node n) {
		 	
		 		for(int i = 0; i < edges.size(); i++) {
		 			//System.out.println(edges.get(i).getSrc() + " " + edges.get(i).getDest());
		 			if ((nodes.get(edges.get(i).getSrc()).name == n.name) || (nodes.get(edges.get(i).getDest()).name == n.name)) {
		 				//delEdges.add(e);
		 				//System.out.println("          " + edges.get(i).getSrc() + " " + edges.get(i).getDest());
		 				delEdges.add(edges.get(i));
		 				//System.out.println("deleted " + edges.get(i).getSrc() + edges.get(i).getDest());
		 				edges.remove(edges.get(i));
		 				i--;
		 				//System.out.println("deleted " + edges.get(i));
		 				//delEdges.add(edges.get(i));
		 				this.red++;
		 				this.edgeNum--;
		 				//this.repaint();
				 		//this.revalidate();
		 			}
		 		}
		 		//System.out.println(n.name);
		 		for(int j = 0; j < nodes.size(); j++) {
		 			System.out.println(nodes.get(j).name + "-----");
		 			if ( n.name == nodes.get(j).name) {
		 				Node nullnode = new Node("0");
		 				nodes.set(j, nullnode);
		 				this.vertNum--;
		 				//this.repaint();
		 				//this.revalidate();
		 				break;
		 			}
		 		}
		 		//System.out.println("vertNum" + vertNum + " " + "edgeNum" + edgeNum);
		 		//vertNum--;
		 		if (vertNum == 0) clearGraph();
		 		this.repaint();
		 		this.revalidate();
	 }
	 
	 public void addEdge(int i, int j, int k) {
		 
		 		edges.add(new Edge(i,j,k));
		  		for( int h = 0; h < edges.size(); h ++) {
		  			System.out.println(edges.get(h).getSrc() + " " + edges.get(h).getDest());
		  		}
		    	//edges.add(new Edge(i,j,k));
		    	edgeNum++;
		    	//Graphics g = this.getGraphics();
		    	this.repaint();
		    	this.revalidate();

		    	//this.paintComponent(g);
	 }
	 public void removeEdge(Edge n) {
		  
		    //edges.remove(edges.size() - 1);
		    //edgeNum--;
		    //Graphics g = this.getGraphics();
		 	for(int i = 0; i < edges.size(); i++) {
	 			//System.out.println(edges.get(i).getSrc() + " " + edges.get(i).getDest());
	 			if ((edges.get(i).getSrc() == n.getSrc()) && (edges.get(i).getDest() == n.getDest()) && (edges.get(i).getWeight() == n.getWeight())) {
	 				//delEdges.add(e);
	 				//System.out.println("          " + edges.get(i).getSrc() + " " + edges.get(i).getDest());
	 				delEdges.add(edges.get(i));
	 				System.out.println("deleted " + edges.get(i).getSrc() + edges.get(i).getDest());
	 				edges.remove(edges.get(i));
	 				//i--;
	 				//System.out.println("deleted " + edges.get(i));
	 				//delEdges.add(edges.get(i));
	 				//this.red++;
	 				
	 				this.edgeNum--;
	 				break;
	 				//this.repaint();
			 		//this.revalidate();
	 			}
	 		}
	    	this.repaint();
	    	this.revalidate();
	    	//this.paintComponent(g);
	 }
	 public void removeEdge(int i,  int j, int k) {
		
		 //Graphics g = this.getGraphics();
		 
		 for (int q = 0; q < edges.size(); q ++) {
			 
			 if(edges.get(q) != null) {
		 
				if ((edges.get(q).getSrc() == i)&&(edges.get(q).getDest() == j)&&(edges.get(q).getWeight() == k)) {
					 
					edges.remove(edges.get(q));
					this.repaint();
					 this.revalidate();
			 		//System.out.println(h.getWeight());
				}
		 }
		 }
		 for (Edge h: edges) {
			 System.out.println(h.getWeight());
			 
		 }
		 
					 //this.repaint();
					 //this.revalidate();
					 
		 
		 this.edgeNum--;
		 
		 //g.setColor(c);
		 //repaint();
		 //g.drawLine(nodes.get(e.getSrc()).getX(), nodes.get(e.getSrc()).getY(),nodes.get(e.getDest()).getX(),  nodes.get(e.getDest()).getY());
		 //g.drawString(e.getWeightStr(), (nodes.get(e.getSrc()).getX() + nodes.get(e.getDest()).getX())/2, (nodes.get(e.getSrc()).getY() + nodes.get(e.getDest()).getY())/2);
		 //repaint();
	 }
	 public void clearGraph() {
		 nodes = new ArrayList<Node>();
		 edges = new ArrayList<Edge>();
		 vertNum = 0;
		 edgeNum = 0;
		 delEdges = new ArrayList<Edge>();
		 getGraphics().setColor(Color.WHITE);
		 getGraphics().clearRect(0, 0, 1000, 1000);
		 this.repaint();
	 }
	 public void fillEdge(int i, int j, int k, Color c, boolean flag) {
		 //System.out.println(i + " " + j + " " + k);
		 Edge e = new Edge(i, j, k);
		 Graphics g = this.getGraphics();
		 if (!(nodes.get(e.getSrc()).getX()>0 && nodes.get(e.getSrc()).getY()>0 && nodes.get(e.getDest()).getX()>0 && nodes.get(e.getDest()).getY()>0)) return;
		 
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
		 if ((flag == false) ) {
			 //if (nodes.get(e.getSrc()).getX()>0 && nodes.get(e.getSrc()).getY()>0 && nodes.get(e.getDest()).getX()>0 && nodes.get(e.getDest()).getY()>0) {
			 g.drawLine(nodes.get(e.getSrc()).getX(), nodes.get(e.getSrc()).getY(),nodes.get(e.getDest()).getX(),  nodes.get(e.getDest()).getY());
			 //}
			 
		 }
		 else if (flag == true) {
		 //((Graphics2D) g).setArc(circlex - radius,circley - radius, 2*radius, 2*radius, (int)angle_3, (int)Math.abs(angle_2 - angle_1));
		 ((Graphics2D)g).draw(new Arc2D.Double(circlex - radius,circley - radius, 2*radius, 2*radius, (int)angle_3, (int)Math.abs(angle_2 - angle_1), Arc2D.OPEN));
		
		 }
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
		//((Graphics2D) g).setArc(400, 250, 100, 100, 60, 120, 30);
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
								//e.arc = true;
							
					}
				}
			}
		}
		//System.out.println("edgeNum" + edges.size());
		for (Edge e : edges) {
			//System.out.println(e. getSrc() + " " + e.getDest());
			if (delEdges.contains(e)) { 
			}
			else if (nodes.get(e.getSrc()).getX() > -100 && nodes.get(e.getDest()).getX() > -100){
			
			//if ((e.getSrc() < nodes.size())&&((e.getDest() < nodes.size()))){
			//g.drawLine(nodes.get(e.getSrc()).getX(), nodes.get(e.getSrc()).getY(),nodes.get(e.getDest()).getX(),  nodes.get(e.getDest()).getY());
			
			int circlex = (nodes.get(e.getSrc()).getX() + nodes.get(e.getDest()).getX())/2;
			int circley = (nodes.get(e.getSrc()).getY() + nodes.get(e.getDest()).getY())/2;
			int radius = (int) Math.sqrt((int)(Math.pow((int)nodes.get(e.getSrc()).getY() - circley, 2) + Math.pow((int)nodes.get(e.getSrc()).getX() - circlex, 2)));
			
			double angle_1 = Math.abs(Math.atan2(nodes.get(e.getSrc()).getY() - circley, nodes.get(e.getSrc()).getX() - circlex) * 180 / Math.PI);
			double angle_2 = Math.abs(Math.atan2(nodes.get(e.getDest()).getY() - circley, nodes.get(e.getDest()).getX() - circlex) * 180 / Math.PI);
			if (nodes.get(e.getSrc()).getY() > circley) 	{angle_1 = 360 - angle_1;}
			if (nodes.get(e.getDest()).getY() > circley)	{angle_2 = 360 - angle_2;}
			
			double angle_3;
			if (angle_1 < angle_2) 
				angle_3 = angle_1;
			else angle_3 = angle_2;
			
			
			if (e.flag == null) {
				//System.out.println("     AAAAA     " + e.getSrc() + " " + e.getDest());
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
					// create new QuadCurve2D.Float
					//QuadCurve2D q = new QuadCurve2D.Float();
					// draw QuadCurve2D.Float with set coordinates
					//q.setCurve(x1, y1, ctrlx, ctrly, x2, y2);
					//((Graphics2D)g).draw(q);
					//double main_angle =  Math.abs(Math.atan2(nodes.get(e.getSrc()).getY()-nodes.get(e.getDest()).getY(), nodes.get(e.getSrc()).getX()-nodes.get(e.getDest()).getX()) * 180 / Math.PI);
					//AffineTransform at = AffineTransform.getRotateInstance(-Math.PI/6);
	                //Shape shape = at.createTransformedShape(new Arc2D.Double(100, 100, 30, 300, angle_3,angle_2 - angle_1,  Arc2D.OPEN));
	                //((Graphics2D) g).draw(shape);
	                
					((Graphics2D)g).draw(new Arc2D.Double(circlex - radius,circley - radius, 2*radius, 2*radius, (int)angle_3, (int)Math.abs(angle_2 - angle_1), Arc2D.OPEN));
					
					e.flag.m_flag = false;
					e.m_flag = false;
					e.arc = true;
					e.flag.arc = false;
					//System.out.println (e.arc + "," + e.flag.arc);
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
			if (n.name != "0") {
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
		public void setDragging(boolean b) {
		dragging = b;
		
		}
		public boolean isSelecting(int x1, int y1) {
			for (int i = 0; i < nodes.size(); i++) {
				if ((Math.abs(nodes.get(i).getX()+15 - x1) < 30) && (Math.abs(nodes.get(i).getY()+15 - y1) < 30)){
					dragNode = i;
					return true;
				}
			}return false;
		}
		public boolean isDragging() {
			return dragging;
		}
		public void setCoordinates(int x2, int y2) {
			Node node = new Node(nodes.get(dragNode).name, x2, y2);
			nodes.set(dragNode, node);
			repaint();
		}
		public Node isNodeSelecting(int x1, int y1) {
			
			for (int i = 0; i < nodes.size(); i++) {
				if ((Math.abs(nodes.get(i).getX()+15 - x1) < 30) && (Math.abs(nodes.get(i).getY()+15 - y1) < 30)){
						//Node checkNode = new Node(nodes.get(i).name,nodes.get(i).getX(), nodes.get(i).getY());
						//nodes.remove(nodes.get(i));
						return	nodes.get(i);
				}
			}return null;
		}
		public Edge isEdgeSelecting(int x1, int y1) {
			
			for (int i = 0; i < edges.size(); i++) {
				if ((Math.abs(((nodes.get(edges.get(i).getSrc()).getX() + nodes.get(edges.get(i).getDest()).getX())/2) - x1) < 10) && (Math.abs(((nodes.get(edges.get(i).getSrc()).getY() + nodes.get(edges.get(i).getDest()).getY())/2) - y1) < 10)){
						//Node checkNode = new Node(nodes.get(i).name,nodes.get(i).getX(), nodes.get(i).getY());
						//nodes.remove(nodes.get(i));
						return	edges.get(i);
				}
			}return null;
		}
		public Edge isEdgeSelectingChange(int x1, int y1, int w) {
			
			for (int i = 0; i < edges.size(); i++) {
				if ((Math.abs(((nodes.get(edges.get(i).getSrc()).getX() + nodes.get(edges.get(i).getDest()).getX())/2) - x1) < 10) && (Math.abs(((nodes.get(edges.get(i).getSrc()).getY() + nodes.get(edges.get(i).getDest()).getY())/2) - y1) < 10)){
						Edge newEdge_ = new Edge(edges.get(i).getSrc(),edges.get(i).getDest(), w);
						//nodes.remove(nodes.get(i));
						edges.set(i, newEdge_);
						return edges.get(i);
				}
			}return null;
		}
		public boolean isEdgeSelectingCheck(int x1, int y1) {
			
			for (int i = 0; i < edges.size(); i++) {
				if ((Math.abs(((nodes.get(edges.get(i).getSrc()).getX() + nodes.get(edges.get(i).getDest()).getX())/2) - x1) < 10) && (Math.abs(((nodes.get(edges.get(i).getSrc()).getY() + nodes.get(edges.get(i).getDest()).getY())/2) - y1) < 10)){
						//Edge newEdge_ = new Edge(edges.get(i).getSrc(),edges.get(i).getDest(), w);
						//nodes.remove(nodes.get(i));
						return true;
				}
			}return false;
		}
	
}
