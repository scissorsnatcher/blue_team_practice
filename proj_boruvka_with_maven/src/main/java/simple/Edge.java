package simple;
public class Edge {

    private int src;
    private String srcName;
    private int dest;
    private int desrName;
    private int weight;
    private String weightStr;
    public boolean m_flag, arc;
    public Edge flag;
    
	public Edge(int i, int j, int k) {
	    src = i;
	    dest = j;	 
	    weight = k;
	    flag = null;
	    m_flag = false;
	    flag = null;
	    arc = false;
	    weightStr = Integer.toString(weight);
	}
    public String getSrcName() {
        return srcName;
    }

    public void setSrcName(String srcName) {
        this.srcName = srcName;
    }

    public int getDesrName() {
        return desrName;
    }

    public void setDesrName(int desrName) {
        this.desrName = desrName;
    }

    public int getSrc() {
        return src;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    public int getDest() {
        return dest;
    }

    public void setDest(int dest) {
        this.dest = dest;
    }

    public int getWeight() {
        return weight;
    }
    public String getWeightStr() {
    	return weightStr;
    }
    public  void setWeightStr(String a) {
    	weightStr = a;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
}