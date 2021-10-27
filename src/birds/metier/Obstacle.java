package birds.metier;

import birds.gui.Sky;

public class Obstacle extends FlyingObject {
	
	public static final int SIZE_MAX = 50;
	public static final int SIZE_MIN = 30;

	public Obstacle(int x, int y, int size, Sky s) {
		this.x = x;
		this.y = y;
		
		this.size = size;
		this.sky = s;
		
	}
	
	@Override
	public void fly() {}

}
