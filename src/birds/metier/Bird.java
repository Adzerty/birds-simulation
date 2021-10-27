package birds.metier;

import java.util.List;

import birds.gui.Sky;

// aucun leader
// Reagit qu'à ses 7 voisins les plus proches
// Si pas de danger / obstacles -> suit les 7 voisins
// Sinon evite l'obstacle

public class Bird extends FlyingObject {
	
	public static final int NB_BIRD_TO_FOLLOW = 7; 	// le nombre d'oiseaux que suit cet oiseau
	public static final int FIELD_OF_VIEW = 25; 	// le champ de vision de l'oiseau (permet de chercher les voisins)
	public static final int CONFORT_ZONE = 1; 		// zone de confort de l'oiseau, pour ne pas qu'il se chevauche
	public static final int SPEED = 20;
	
	float angle;
    
    //temps
    private double deltaT;
    private double tpsDebut;
    private double tpsFin = System.currentTimeMillis();
    

	public Bird(int x, int y, Sky s) {
		this.x = x;
		this.y = y;
		this.angle = (float) (Math.random() * (360));
		this.size = 4;
		
		this.sky = s;
		
	}
	@Override
	public void fly() {
		
		tpsDebut = System.currentTimeMillis();
        deltaT = (tpsDebut - tpsFin)/SPEED;
        
		//On regarde ses 7 voisins
		List<Bird> lVoisins = sky.getVoisins(x,y);
		
		//On fait la moyenne des angles des voisins et du sien et on l'affecte à l'oiseau courant
		float angleTot = angle;
		for(Bird b : lVoisins) {
			//System.out.println(lVoisins.size());
			angleTot+=b.angle;
		}
		
		angle = (angleTot/(lVoisins.size()+1))%360;		

		
		
		
		
		//TODO On verifie s'il y a un obstacle 
		//On déplace l'oiseau
		int nbIter = 0;
		boolean isInConfort;
		
		do {
			//System.out.println(" NBITER " + nbIter + " : ANGLE " + angle);
			if(nbIter > 0) {
				angle = (angle + ((Math.random()>0.5)? 10 : -10));
			}
			
	
	        //On calcul la nouvelle position
	        x = (x + (Math.sin(Math.toRadians(angle)) * deltaT));
	        y = (y + (Math.cos(Math.toRadians(angle)) * deltaT));
	        nbIter++;
	        
	        /*
	        System.out.println(	" X : " + x + 
	        					" |  Y : " + y + 
	        					" | angle : " + Math.toRadians(angle) +
	        					" | deltaT" + deltaT);
	        */
	        
	         isInConfort = sky.isInConfortZone(x,y);
	         //System.out.println(isInConfort);
		}while(isInConfort);


        
        tpsFin = System.currentTimeMillis();
        
        //Plateau fini non torique
 
    	if(x > Sky.SIZE_FRAME/2) {
        	x = -Sky.SIZE_FRAME/2;
        }
        if(y > Sky.SIZE_FRAME/2) {
        	y = -Sky.SIZE_FRAME/2;
        }
            

        		
        if(x < -Sky.SIZE_FRAME/2){
        	x = Sky.SIZE_FRAME/2;
        }
        
        if(y < -Sky.SIZE_FRAME/2) {
        	y = Sky.SIZE_FRAME/2;
        }
                		
        
        
  
       	
	}
	
	public String toString() {
		return ""+x+':'+y+" | " + angle+'°';
	}

}
