package birds.metier;

import java.util.List;

import birds.gui.Sky;

// aucun leader
// Reagit qu'à ses 7 voisins les plus proches
// Si pas de danger / obstacles -> suit les 7 voisins
// Sinon evite l'obstacle

public class Bird extends FlyingObject {
	
	public static final int BIRD_SIZE = 4;
	public static final int NB_BIRD_TO_FOLLOW = 7; 	// le nombre d'oiseaux que suit cet oiseau
	public static final int FIELD_OF_VIEW = 50; 	// le champ de vision de l'oiseau (permet de chercher les voisins)
	public static final int CONFORT_ZONE = 1; 		// zone de confort de l'oiseau, pour ne pas qu'il se chevauche
	public static final int OBSTACLE_ZONE = 75; 	// zone de confort de l'oiseau, pour ne pas qu'il se chevauche
	
	public static final int SPEED = 10;
	
	float angle;
    
    //temps
    private double deltaT;
    private double tpsDebut;
    private double tpsFin = System.currentTimeMillis();
    private double vitesse = SPEED; 
    private boolean obstacle = false;
    

	public Bird(int x, int y, Sky s) {
		v = new Vecteur(new Point(x,y), new Point(x+1, y+1));
		this.angle = (float) (Math.random() * (360));
		this.size = BIRD_SIZE;
		
		this.sky = s;
		
	}
	@Override
	public void setX(double x) {
		this.v.setA(new Point(x, v.getA().getY()));
		this.v.setB(new Point(	Math.cos(Math.toRadians(angle)) * v.getNorme() + v.getA().getX(), 
				  				Math.sin(Math.toRadians(angle)) * v.getNorme() + v.getA().getY()));
	}
	
	@Override
	public void setY(double y) {
		this.v.setA(new Point(v.getA().getX(), y ));
		this.v.setB(new Point(	Math.cos(Math.toRadians(angle)) * v.getNorme() + v.getA().getX(), 
				  				Math.sin(Math.toRadians(angle)) * v.getNorme() + v.getA().getY()));
	}
	
	@Override
	public void fly() {
		
		tpsDebut = System.currentTimeMillis();
        deltaT = (tpsDebut - tpsFin)/SPEED;
		
		

		//On déplace l'oiseau
		FlyingObject fOInConfort = sky.isInConfortZone(getX(), getY(), size);
		vitesse = SPEED;
		
		double tetha = 0;
		
		if(fOInConfort != null){
			if(fOInConfort instanceof Obstacle)
			{
				// On trace le segment entre l'oiseau et l'obstacle
				// On regarde l'angle entre la direction de l'oiseau et la droite
				// Si l'angle est < à 90 on tourne de X degres (X = a * 1/longueur du segment)
				//double tetha = v.getAngleBetweenVector(fOInConfort.getV());
				
				Vecteur vOiseauObstacle = new Vecteur(new Point(getX(), getY()), new Point(fOInConfort.getX(), fOInConfort.getY())); 
				
				double oppose = vOiseauObstacle.getB().getY() <  v.getB().getY() ? vOiseauObstacle.getB().getY() - v.getB().getY() 
																				 : v.getB().getY() - vOiseauObstacle.getB().getY();  
				
				double angleVOiseauObs = Math.atan( (oppose)/( vOiseauObstacle.getB().getX() - vOiseauObstacle.getA().getX()) );
				double angleMax = angle < angleVOiseauObs ? angleVOiseauObs : angle;
				double angleMin = angle > angleVOiseauObs ? angleVOiseauObs : angle;
				
				
				
				if(angleMax > 180) {
					tetha = angleMax - angleMin;
				}else {
					tetha = angleMin + (360-angleMax);
				}
	
				double distance = vOiseauObstacle.getNorme();
												
				
			}
		}
		
		
		if(tetha>90) {
			angle = (float)tetha + 10;
			//if(angle > 180) angle = 180;
		} else
		{
			//On regarde ses 7 voisins
			List<Bird> lVoisins = sky.getVoisins(getX(), getY());
			
			//On fait la moyenne des angles des voisins et du sien et on l'affecte à l'oiseau courant
			float angleTot = angle*4; //L'angle de base de l'oiseau est plus fort
			for(Bird b : lVoisins) {
				//System.out.println(lVoisins.size());
				angleTot+=b.angle;
			}
			
			angle = (angleTot/(lVoisins.size()+4))%360;	
		}
			
		
	
        //On calcul la nouvelle position
        setX(getX() + (Math.sin(Math.toRadians(angle)) * deltaT));
        setY(getY() - (Math.cos(Math.toRadians(angle)) * deltaT));
        	        
        
        tpsFin = System.currentTimeMillis();
        
        //Plateau fini non torique
 
    	if(getX() > Sky.SIZE_FRAME) {
    		setX(0);
        }
        if(getY() > Sky.SIZE_FRAME) {
        	setY(0);
        }
            

        		
        if(getX() < 0){
        	setX(Sky.SIZE_FRAME);
        }
        
        if(getY() < 0) {
        	setY(Sky.SIZE_FRAME);
        }
                		
        
        
  
       	
	}
	
	private double produitScal(Vecteur v){
		return getX() * v.getA().getX() + getY() * v.getA().getY();
	}
	
	public String toString() {
		return ""+getX()+':'+getY()+" | " + angle+'°';
	}

}
