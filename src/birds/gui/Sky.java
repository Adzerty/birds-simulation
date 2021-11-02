package birds.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import birds.metier.FlyingObject;
import birds.metier.Bird;

public class Sky extends JFrame {
	
	
	public static final int SIZE_FRAME = 800;
	
	private PanelSky panel;
	
	private Thread thdSimulation;
	
	private int nbOiseaux;
	private int nbObstacles;
	
	public Sky(int nbOiseaux, int nbObstacles) {
		super("Bird-simulation");
		
		this.nbOiseaux = nbOiseaux;
		this.nbObstacles = nbObstacles;
		
		initFrame();
		
		startSimulation();
		
		finalizeFrame();
		
	}
	
	private void initFrame() {
		this.setSize(SIZE_FRAME,SIZE_FRAME);
		this.setLocation(250,250);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.panel = new PanelSky(this);
		add(panel);
	}
	
	private void finalizeFrame() {
		this.setResizable(true);
		this.setVisible(true);
	}
	
	private void startSimulation() {
		this.thdSimulation = new Thread(new Runnable() {
			public void run() {
				
				while(true) {	
					
					panel.update();
					
					try {
						Thread.sleep(0);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
		}});
		
		this.thdSimulation.start();
		
	}

	
	//GETTERS
	
	public int getNbOiseaux() {
		return nbOiseaux;
	}
	
	public int getNbObstacles() {
		return nbObstacles;
	}
	
	// Methods metier
	public List<Bird> getVoisins(double x, double y){
		
		List<Bird> lVoisins = new ArrayList<>();
		for(FlyingObject fO : panel.flyingObjects) {
			if(fO instanceof Bird && fO.getX() != x && fO.getY() != y) {
				
				if( (fO.getX() - x) * (fO.getX() - x) +
			        (fO.getY() - y) * (fO.getY() - y) <= 
			        Bird.FIELD_OF_VIEW * Bird.FIELD_OF_VIEW) {
					
					lVoisins.add((Bird)fO);
					if(lVoisins.size() == Bird.NB_BIRD_TO_FOLLOW) {
						return lVoisins;
					}	
				}				
			}
			
		}
		return lVoisins;
	}
	
	public FlyingObject isInConfortZone(double x, double y, int size) {
		for(FlyingObject fO : panel.flyingObjects) {
			if(fO.getX() != x && fO.getY() != y) {
				if(intersect(x, y, fO.getX(), fO.getY(), size + (fO instanceof Bird ? Bird.CONFORT_ZONE : Bird.OBSTACLE_ZONE), fO.getSize()/2)) 
				{	
					return fO;
				}				
			
			}
		}
		
		return null;
	}
	
	public boolean intersect(double x1, double y1, double x2, double y2, int r1, int r2)
	{
		double distSq = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
		double radSumSq = (r1 + r2) * (r1 + r2);
		
		if (distSq <= radSumSq)
		  return true;

		 return false;

	}
}
