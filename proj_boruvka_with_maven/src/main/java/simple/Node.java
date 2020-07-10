package simple;

public class Node {
	private int x, y;
	public String name;
	
	public Node(String myName, int myX, int myY) {
	    x = myX;
	    y = myY;
	    name = myName;
	}
	public void set(int x_, int y_) {
    	x = x_;
    	y = y_;
    }
	public int getX() {
        return x;
	}

    public int getY() {
        return y;
    }
    
    
}
