package birds;

public abstract class FlyingObject {
	int x, y;
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	private FlyingObject() {
		// TODO Auto-generated constructor stub
	}
}
