package simple;
import java.awt.Color;
import java.awt.List;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextField;

import gui.GraphPainter;

public class Boruvka{
	
	 private int iter;
	 private ArrayList<Edge> sortEdges;
	 public int MSTweight;

	 public ArrayList<Edge> boruvkaMST(Graph graph, GraphPainter GraphPanel, boolean vizualization, JTextField textField) {

	      
	     	iter = 0;
	        int vertNum = graph.getVertNum(); // tops
	        int edgeNum = graph.getEdgeNum(); // edge
	        this.sortEdges = new ArrayList<Edge>();
	        if (graph.isolated) return sortEdges;
	        String[] vertNames = graph.getVertNames();
	        Edge[] edges = graph.getEdges();
	        
	        Subset[] subsets = new Subset[vertNum];
	        int[] cheapest = new int[vertNum];

	        for (int i = 0; i < vertNum; i++) {
	            subsets[i] = new Subset();
	            subsets[i].setParent(i);
	            subsets[i].setRank(0);
	            cheapest[i] = -1;
	        }

	        int numTree = vertNum;
	        MSTweight = 0;

	        System.out.println("Initializing Boruvka's MST");

	        //It will run until the MST is the only tree left
	        while (numTree > 1) {
	            System.out.println("Number of Trees:" + numTree);

	            //Reset the cheapest values every iteration
	            for (int i = 0; i < vertNum; i++) {
	                cheapest[i] = -1;
	            }

	            //Iterate over all edges to find the cheapest
	            //edge of every subtree
	            for (int i = 0; i < edgeNum; i++) {

	                //Find the subsets of the corners of the edge
	                int set1 = find(subsets, edges[i].getSrc());
	                int set2 = find(subsets, edges[i].getDest());

	                //If the two corners belong to the same subset,
	                //ignore the current edge
	                if (set1 != set2) {

	                    //If they belong to different subsets, check which
	                    //one is the cheapest
	                    if (cheapest[set1] == -1 || edges[cheapest[set1]].getWeight() > edges[i].getWeight()) {
	                    	//System.out.println(edges[cheapest[set1]].getWeight());
	                        cheapest[set1] = i;
	                    }

	                    if (cheapest[set2] == -1 || edges[cheapest[set2]].getWeight() > edges[i].getWeight()) {
	                    	//System.out.println(edges[cheapest[set1]].getWeight());
	                    	cheapest[set2] = i;
	                    }
	                    
	                }
	            }

	            //Add the cheapest edges obtained above to the MST
	            for (int j = 0; j < vertNum; j++) {
	            	//System.out.println("Number of Treeeeeeeeeeeeees:" + numTree);
	                //Check if the cheapest for current set exists
	                if (cheapest[j] != -1) {
	                    int set1 = find(subsets, edges[cheapest[j]].getSrc());
	                    int set2 = find(subsets, edges[cheapest[j]].getDest());

	                    if(set1 != set2){
	                        MSTweight += edges[cheapest[j]].getWeight();
	                        //System.out.println("Edge ("+ vertNames[edges[cheapest[j]].getSrc()] + ", " + vertNames[edges[cheapest[j]].getDest()]+") added to the MST");
	                        
	                        if (!vizualization) {
	                        	System.out.println("Edge ("+ vertNames[edges[cheapest[j]].getSrc()] + ", " + vertNames[edges[cheapest[j]].getDest()]+") added to the MST");
	                        	GraphPanel.fillEdge(edges[cheapest[j]].getSrc(), edges[cheapest[j]].getDest(), edges[cheapest[j]].getWeight(), Color.PINK, edges[cheapest[j]].m_flag);
	                        }
	                        else {
	                        	Edge e = new Edge(edges[cheapest[j]].getSrc(), edges[cheapest[j]].getDest(), edges[cheapest[j]].getWeight());
	                        	sortEdges.add(e);
	                        	iter++;
	                        }
	                        uniteSubsets(subsets, set1, set2);
		                    numTree--;  
	                    }
	                }
	            }
	           
	        }
	        if (!vizualization) textField.setText("Final weight of MST :" + MSTweight);
	        return sortEdges;
	 }
	 
	    //Method to find the set of a vert
	    //It utilizes path compression technique
	    private int find(Subset[] subsets, int vert) {
	        if (subsets[vert].getParent() != vert) {
	            subsets[vert].setParent(find(subsets, subsets[vert].getParent()));
	        }
	        return subsets[vert].getParent();
	    }

	    //Method to unite subsets, it uses the rank to select the parent
	    private void uniteSubsets(Subset[] subsets, int v1, int v2){

	        int rootv1 = find(subsets, v1);
	        int rootv2 = find(subsets, v2);

	        if(subsets[v1].getRank() < subsets[v2].getRank()){
	            subsets[v1].setParent(subsets[v2].getParent());
	        }else if(subsets[v1].getRank() > subsets[v2].getRank()){
	            subsets[v2].setParent(subsets[v1].getParent());
	        }else{
	            subsets[v2].setParent(subsets[v1].getParent());
	            subsets[v1].setRank(subsets[v1].getRank() + 1);
	        }

	    }
}
