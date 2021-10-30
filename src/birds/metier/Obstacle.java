package birds.metier;

import birds.gui.Sky;

public class Obstacle extends FlyingObject {
	
	public static final int SIZE_MAX = 150;
	public static final int SIZE_MIN = 50;
	
	public static final double OBS_ANGLE = 45;

	public Obstacle(int x, int y, int size, Sky s) {
		v = new Vecteur(new Point(x,y),new Point(x+1,y+1));
		
		this.size = size;
		this.sky = s;
		
	}
	
	@Override
	public void fly() {}
	
	public void setX(double x) {
		this.v.setA(new Point(x, v.getA().getY()));
		this.v.setB(new Point(x+1, v.getA().getY()+1));
	}
	
	public void setY(double y) {
		this.v.setA(new Point(v.getA().getX(), y ));
		this.v.setB(new Point(v.getA().getX()+1, y+1));
	}

}
