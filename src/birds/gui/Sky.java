package birds.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import birds.metier.FlyingObject;
import birds.metier.Bird;

public class Sky extends JFrame {
	
	public static final int SIZE_FRAME = 500;
	
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
						Thread.sleep(1000);
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
	
	// Methods metier
	public List<Bird> getVoisins(int x, int y){
		
		List<Bird> lVoisins = new ArrayList<>();
		for(FlyingObject fO : panel.flyingObjects) {
			if(fO instanceof Bird) {
				
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
	
	public boolean isInConfortZone(int x, int y) {
		for(FlyingObject fO : panel.flyingObjects) {
			
			if( (x - fO.getX()) * (x - fO.getX()) +
		        (y - fO.getY()) * (y - fO.getY()) <= 
		        Bird.CONFORT_ZONE * Bird.CONFORT_ZONE) {
				
				return true;
			}				
			
		}
		
		return false;
	}
}
