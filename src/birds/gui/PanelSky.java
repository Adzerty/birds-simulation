package birds.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import java.awt.Graphics;

import birds.metier.Bird;
import birds.metier.FlyingObject;

public class PanelSky extends JPanel {
	
	private Sky s;
	public List<FlyingObject> flyingObjects = new ArrayList<>();

	public PanelSky(Sky s){
		super();
		
		this.s = s;
		
		for(int i = 0; i<s.getNbOiseaux(); i++) {
			flyingObjects.add(new Bird( (int)( -Sky.SIZE_FRAME/10 + (Math.random() * ( Sky.SIZE_FRAME/10 + Sky.SIZE_FRAME/10))), 
										(int)( -Sky.SIZE_FRAME/10 + (Math.random() * ( Sky.SIZE_FRAME/10 + Sky.SIZE_FRAME/10))),
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
		
		
		for(FlyingObject fO : flyingObjects) {
			g.fillOval(fO.getX()+Sky.SIZE_FRAME/2, fO.getY()+Sky.SIZE_FRAME/2, fO.getSize(), fO.getSize());
		}
		
        
    }

	
}
