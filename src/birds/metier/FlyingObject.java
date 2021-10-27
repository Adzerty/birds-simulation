package birds.metier;

import birds.gui.Sky;

public abstract class FlyingObject {
	double x, y;
	int size;
	Sky sky;
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	
	public abstract void fly();
}
