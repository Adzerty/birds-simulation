package birds.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;

import birds.metier.Bird;
import birds.metier.FlyingObject;
import birds.metier.Obstacle;

public class PanelSky extends JPanel {
	
	private Sky s;
	public List<FlyingObject> flyingObjects = new ArrayList<>();

	public PanelSky(Sky s){
		super();
		
		this.s = s;
		
		int spawnBird = Sky.SIZE_FRAME;
		int spawnObs  = Sky.SIZE_FRAME;
		for(int i = 0; i < s.getNbOiseaux(); i++) {
			flyingObjects.add(new Bird( (int)(Math.random() * spawnBird), 
										(int)(Math.random() * spawnBird),
										s));
			
		}
		
		for(int i = 0; i<s.getNbObstacles(); i++) {
			flyingObjects.add(new Obstacle( (int)(Math.random() * spawnObs), 
											(int)(Math.random() * spawnObs),
											(Obstacle.SIZE_MIN +  (int)(Math.random() * (Obstacle.SIZE_MAX - Obstacle.SIZE_MIN))),
											s));
		}
		
		
	}
	
	//Method to call in order to update sky
	//Each flying object should call its fly method and the panel is repainted;
	public void update() {
		
		//flyingObjects.get(0).fly();
		for(FlyingObject fO : flyingObjects) {
				fO.fly();
		}
		
		
		repaint();
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//g.drawRect(0,0,Sky.SIZE_FRAME,Sky.SIZE_FRAME);
		
		for(FlyingObject fO : flyingObjects) {
			if(fO instanceof Obstacle)
				g.setColor(Color.red);
			else 
				g.setColor(Color.black);
			
			
			g.fillOval( (int)(fO.getX()-fO.getSize()/2), (int)(fO.getY()-fO.getSize()/2), fO.getSize(), fO.getSize());
		}
		
		
		
        
    }

	
}
