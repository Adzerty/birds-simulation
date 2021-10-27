package birds.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;

import birds.metier.Bird;
import birds.metier.FlyingObject;

public class PanelSky extends JPanel {
	
	private Sky s;
	public List<FlyingObject> flyingObjects = new ArrayList<>();

	public PanelSky(Sky s){
		super();
		
		this.s = s;
		
		int sF2 = Sky.SIZE_FRAME/2;
		for(int i = 0; i < s.getNbOiseaux(); i++) {
			flyingObjects.add(new Bird( (-sF2 +  (int)(Math.random() * (sF2 + sF2))), 
										(-sF2 +  (int)(Math.random() * (sF2 + sF2))),
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
		
		g.drawRect(0,0,Sky.SIZE_FRAME,Sky.SIZE_FRAME);
		
		
		
		for(FlyingObject fO : flyingObjects) {
			if(fO == flyingObjects.get(0))
				g.setColor(Color.red);
			else 
				g.setColor(Color.black);
			
			
			g.fillOval((int)fO.getX()+Sky.SIZE_FRAME/2, (int)fO.getY()+Sky.SIZE_FRAME/2, fO.getSize(), fO.getSize());
		}
		
		
		
        
    }

	
}
