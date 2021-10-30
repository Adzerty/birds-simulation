package birds.metier;

import birds.gui.Sky;

public abstract class FlyingObject {
	Vecteur v;
	int size;
	Sky sky;
	
	public Vecteur getV() {
		return v;
	}
	public double getX() {
		return v.getA().getX();
	}
	
	public double getY() {
		return v.getA().getY();
	}
	
	public int getSize() {
		return size;
	}
	
	public abstract void setX(double x);
	
	public abstract void setY(double y);
	
	
	
	
	public abstract void fly();
}
