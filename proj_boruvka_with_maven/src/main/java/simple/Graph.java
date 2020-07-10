package simple;

import java.io.*;
import java.security.SecureRandom;


public class Graph implements ILoadable, IChangable{

	//private static final String File = null;
	private int vertNum;
    private int edgeNum;
    private Edge[] edges = null;
    private String[] vertices = null;
    boolean isolated;
    
    public Graph(){
    	this.vertNum = 0;
        this.edgeNum = 0;
        this.vertices = new String[100];
        this.edges = new Edge[100];
    };


    public void generateGraph(int vertNum, int edgeNum) {
    	
        this.vertNum = vertNum;
        this.edgeNum = edgeNum;
        this.vertices = new String[100];
        this.edges = new Edge[100];
        
        System.out.println("sdfsffs");
        for(int i = 0; i < this.vertNum; i++){
        	
        	int c = i + 65;
        	char a = (char)c;
        	String str1 = Integer.toString(i);
        	String str2 = String.valueOf(a);
        	String str = str1 + " " + str2;
        	String[] aux = str.split(" ");
            vertices[Integer.parseInt(aux[0])] = aux[1];
            System.out.println("sdfsffs");
        }
        for(int i = 0; i < this.edgeNum; i++){
        	
        	SecureRandom rand = new SecureRandom();

        	//String[] edgeValues = line.split(" ");
        	int src =  rand.nextInt(vertNum);
        	System.out.println(src);
        	int dest =  rand.nextInt(vertNum);
        	System.out.println(dest);
        	for( int j = 0; j < i;j++) {
        		if (((edges[j].getSrc() == src) && (edges[j].getDest() == dest))||((edges[j].getDest() == src) && (edges[j].getSrc() == dest))) {
        			while((src != (edges[j].getSrc())) &&(src != (edges[j].getDest()))) src =  rand.nextInt(vertNum);
        			while((dest != (edges[j].getSrc())) &&(dest != (edges[j].getDest()))) dest =  rand.nextInt(vertNum);
        		}
        	}
        	while(src==dest) dest =  rand.nextInt(vertNum);
        	int weight = 1 + rand.nextInt(21);
        		
            edges[i] = new Edge(src, dest, weight);
            System.out.println("sdfsffs");
       
        }
        
        checkIsolatedVert();
    
    }
    public void saveGraph(String path) throws IOException {
    	
    	File f = new File(path);
        f.createNewFile();
        System.out.println(this.vertNum);
        //@SuppressWarnings("resource")
        //BufferedWriter writer = new BufferedWriter(new FileWriter(f));
        //BufferedReader reader = new BufferedReader(new FileReader(f));
		//FileWriter writer = new FileWriter(f);
        FileWriter out = new FileWriter(f);
        
        String info = Integer.toString(vertNum) + " " + Integer.toString(edgeNum) + "\n";
        try {
 			
 			out.write(info);
 			
 		}
 		catch (IOException ex) {
 			ex.printStackTrace();
 		}
        
        for(int i = 0; i < this.vertNum; i++){
        	
        	String str1 = Integer.toString(i);
        	String str2 = String.valueOf(vertices[i]);
        	String str = str1 + " " + str2 + "\n";
        	try {
     			
     			out.write(str);
     			
     		}
     		catch (IOException ex) {
     			ex.printStackTrace();
     		}
        }
        for(int i = 0; i < this.edgeNum; i++){
        	
        	String str1 = Integer.toString(edges[i].getSrc());
        	String str2 = String.valueOf(edges[i].getDest());
        	String str3 = String.valueOf(edges[i].getWeight());
        	String str = str1 + " " + str2 + " " + str3 + "\n";
        	try {
     			
     			out.write(str);
     		}
     		catch (IOException ex) {
     			ex.printStackTrace();
     		}
       
        }out.close();
    	
    	
    	
    }
    public void checkIsolatedVert() {
    	
    	int k = 0;
    	int check = 0;
    	for(int i = 0; i < this.vertNum; i++) {
    		for(int j = 0; j < this.edgeNum; j++) {
    			if(edges[j] != null) {
    				if((i == edges[j].getSrc())||(i == edges[j].getDest())) check++;
    			}
    		}if(check > 0) {k++;check = 0;}
    	}
    	if(k != vertNum) isolated = true;
    	else isolated = false;
    	
    }
    
    
    ;
    @Override
    public void readFromFile(String path){
        try {
        	
        	//String path = "test.txt";
        	
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

                iteration++;
            }
 
        } catch (IOException e) {
            e.printStackTrace();
        } checkIsolatedVert();
    }

    public void clear() {
    	this.vertNum = 0;
        this.edgeNum = 0;
        this.vertices = new String[100];
        this.edges = new Edge[100];
        isolated = true;
    	
    }
    
    public void addNode(String a, int x, int y) {
    	
    	vertices[vertNum] = a;
    	this.vertNum++;
    	System.out.println("vertNum: " + vertNum);
    	checkIsolatedVert();
   
    }
    public void deleteNode() {
    	vertices[vertNum] = null;
    	
    	this.vertNum--;
    	System.out.println("vertNum: " + vertNum);
    	checkIsolatedVert();
    	
    }
    public void deleteEdgeNum(int i) {
    	edges[i] = null;
    }
    public void deleteEdges() {
    	
    	
    	for(int i = edgeNum-1; i >= 0;i--) {
	    	if (edges[i] == null) {
	    		this.edgeNum--;
	    	}
	    	
	    }
    	checkIsolatedVert();
    	
    }
    public void addEdge(int x, int y, int z) {
    	
    	this.edgeNum++;
    	edges[edgeNum-1] = new Edge(x, y, z);
    	//System.out.println(edgeNum);
    	checkIsolatedVert();
    }
    public void deleteEdge_() {
    	/*
    	for(int i = 0; i < edges.length - 1; i++) {
	    	if (edges[i] != null) {
	    		edges[i] = null;
	    		this.edgeNum--;
	    		break;
	    	}
	    }
	    */
    	this.edgeNum--;
    	System.out.println("edgenum:" + edgeNum);
    }
    public void deleteEdge() {
    	
    	for(int i = edges.length - 1; i >= 0;i--) {
	    	if (edges[i] != null) {
	    		edges[i] = null;
	    		this.edgeNum--;
	    		System.out.println("edgenum   sf:" + edgeNum);
	    		break;
	    	}
	    	
	    }
    	System.out.println("edgenum   :" + edgeNum);
    	checkIsolatedVert();
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
