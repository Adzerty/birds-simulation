package birds;

import birds.gui.Sky;

public class Main {
	
	static final int NB_OISEAUX = 3000;
	static final int NB_OBSTACLES = 0;
	public static void main(String[] args) {
		
		new Sky(NB_OISEAUX, NB_OBSTACLES);
	}
}
