package simple;

import java.io.*;

public class Graph implements ILoadable, IChangable{

	private int vertNum;
    private int edgeNum;
    private Edge[] edges;
    private String[] vertices;
    
    public Graph(){
    	this.vertNum = 0;
        this.edgeNum = 0;
        this.vertices = new String[100];
        this.edges = new Edge[100];
    };
    
    
    
    public Graph(int vertNum, int edgeNum){
    	/*
        this.vertNum = vertNum;
        this.edgeNum = edgeNum;
        this.vertices = new String[vertNum];
        this.edges = new Edge[edgeNum];
        int iteration = 0;

        for(int i = 0; i < this.vertNum; i++){
        	
        	String str = Integer.toString(i);
        	System.out.println(str);  
        	int i1 = Character.getNumericValue(ch);
            String[] aux = line.split(" ");
            vertices[Integer.parseInt(aux[0])] = aux[1];
        }
        for(int i = 0; i < this.edgeNum; i++){
        	
        	String[] edgeValues = line.split(" ");
            edges[iteration] = new Edge(Integer.parseInt(edgeValues[0]), Integer.parseInt(edgeValues[1]), Integer.parseInt(edgeValues[2]));
            iteration++;
        }
        */
    
    }
    
    @Override
    public void readFromFile(){
        try {
        	
        	String path = "test.txt";
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line = br.readLine();
            String[] entryValues = line.split(" ");
            this.vertNum = Integer.parseInt(entryValues[0]);
            this.edgeNum = Integer.parseInt(entryValues[1]);
            this.vertices = new String[100];
            this.edges = new Edge[100];
            int iteration = 0;

            for(int i = 0; i < this.vertNum; i++){
                line = br.readLine();
                String[] aux = line.split(" ");
                vertices[Integer.parseInt(aux[0])] = aux[1];
            }

            while((line = br.readLine()) != null){
                String[] edgeValues = line.split(" ");
                edges[iteration] = new Edge(Integer.parseInt(edgeValues[0]), Integer.parseInt(edgeValues[1]), Integer.parseInt(edgeValues[2]));
                //edges[iteration].setSrc(Integer.parseInt(edgeValues[0]));
                //edges[iteration].setDest(Integer.parseInt(edgeValues[1]));
                //edges[iteration].setWeight(Integer.parseInt(edgeValues[2]));
                iteration++;
            }
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public void addNode(String a, int x, int y) {
    	
    	vertices[vertNum] = a;
    	this.vertNum++;
    	System.out.println(vertNum);
   
    }
    public void addEdge(int x, int y, int z) {
    	edges[edgeNum] = new Edge(x, y, z);
    	this.edgeNum++;
    }

	@Override
	public void generateGraph() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void AddVertice() {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void CommonVertices() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Boruvka() {
		
		
	}

	@Override
	public void DeleteVertice() {
		// TODO Auto-generated method stub
		
	}
	
	 	public String[] getVertNames(){
	        return this.vertices;
	    }

	    public Edge[] getEdges() {
	        return edges;
	    }

	    public int getVertNum() {
	        return vertNum;
	    }

	    public int getEdgeNum() {
	        return edgeNum;
	    }



		@Override
		public void changeGraph() {
			// TODO Auto-generated method stub
			
		}


}
