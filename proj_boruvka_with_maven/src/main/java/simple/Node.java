package simple;

public class Node {
	private int x, y;
	public String name;
	
	public Node(String myName, int myX, int myY) {
	    x = myX;
	    y = myY;
	    name = myName;
	}
	public int getX() {
        return x;
	}

    public int getY() {
        return y;
    }
    
}
