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
		
		for(int i = 0; i < s.getNbOiseaux(); i++) {
			flyingObjects.add(new Bird( (int)( -Sky.SIZE_FRAME/2 + (Math.random() * ( Sky.SIZE_FRAME/2 + Sky.SIZE_FRAME/2))), 
										(int)( -Sky.SIZE_FRAME/2 + (Math.random() * ( Sky.SIZE_FRAME/2 + Sky.SIZE_FRAME/2))),
										s));
			
		}
		
		
	}
	
	//Method to call in order to update sky
	//Each flying object should call its fly method and the panel is repainted;
	public void update() {
		
		flyingObjects.get(0).fly();
		/*for(FlyingObject fO : flyingObjects) {
			fO.fly();
		}*/
		
		repaint();
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.red);
		
		for(FlyingObject fO : flyingObjects) {
			//System.out.println(fO.getX() + " : " + fO.getY() );
			g.fillOval(fO.getX()+Sky.SIZE_FRAME/2, fO.getY()+Sky.SIZE_FRAME/2, fO.getSize(), fO.getSize());
		}
		
		
		
        
    }

	
}
