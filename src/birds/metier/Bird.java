package birds.metier;

import java.util.List;

import birds.gui.Sky;

// aucun leader
// Reagit qu'à ses 7 voisins les plus proches
// Si pas de danger / obstacles -> suit les 7 voisins
// Sinon evite l'obstacle

public class Bird extends FlyingObject {
	
	public static final int NB_BIRD_TO_FOLLOW = 7; 	// le nombre d'oiseaux que suit cet oiseau
	public static final int FIELD_OF_VIEW = 100; 	// le champ de vision de l'oiseau (permet de chercher les voisins)
	public static final int CONFORT_ZONE = 10; 		// zone de confort de l'oiseau, pour ne pas qu'il se chevauche
	
	float angle;
	
	//vitesse
    private final double MAX_VITESSE = 0.001;
    private final double MIN_VITESSE = 0.0001;
    private Vecteur vitesse = new Vecteur(0,0);

    //acceleration
    private double acceleration = 0.1;
    private double decelerarion = 0.00001;
    
    //temps
    private double deltaT;
    private double tpsDebut;
    private double tpsFin;
    

	public Bird(int x, int y, Sky s) {
		this.x = x;
		this.y = y;
		this.angle = (int)(Math.random() * (360));
		this.size = 4;
		
		this.sky = s;
		
	}
	@Override
	public void fly() {
		tpsDebut = System.currentTimeMillis();
        deltaT = tpsDebut - tpsFin;
        
		//On regarde ses 7 voisins
		List<Bird> lVoisins = sky.getVoisins(x,y);
		
		//On fait la moyenne des angles des voisins et du sien et on l'affecte à l'oiseau courant
		float angleTot = angle;
		for(Bird b : lVoisins) {
			angleTot+=b.angle;
		}
		
		angle = (int)(angleTot/lVoisins.size()+1);
		//System.out.println(" ANGLE : " + angle);
		
		//TODO On verifie s'il y a un obstacle 
		//On déplace l'oiseau
		int nbIter = 0;
		
		do {
		
		if(nbIter > 0) {
			angle = angle + Math.random()==0?5:-5;
		}
		
		if(vitesse.calculNormeAvecDeuxValeurs( //On verifie que la vitesse ne depassera pas la vitesse max
                vitesse.getvX() + acceleration * Math.cos(Math.toRadians(angle)),
                vitesse.getvY()) + acceleration * Math.sin(Math.toRadians(angle))
                < MAX_VITESSE )
        {
            vitesse.setvX(vitesse.getvX() + acceleration * Math.cos(Math.toRadians(angle)));
            vitesse.setvY(vitesse.getvY() + acceleration * Math.sin(Math.toRadians(angle)));
        }

        //On calcul la nouvelle position
        x = (int) (x + (vitesse.getvX() * deltaT));
        y = (int) (y + (vitesse.getvY() * deltaT));
        nbIter++;
        
		}while(sky.isInConfortZone(x,y));

        //Calcul d'un nouveau vecteur permettant la deceleration
        Vecteur vitInit = new Vecteur(vitesse.getvX(), vitesse.getvY());
        double vitFinal = vitInit.getNorme() - (deltaT * decelerarion);

        // On reduit la vitesse
        if(vitInit.getNorme() != 0)
        {
            vitesse.setvX(vitesse.getvX() * (vitFinal / vitInit.getNorme()));
            vitesse.setvY(vitesse.getvY() * (vitFinal / vitInit.getNorme()));
        }

        //On stop la fusee quand elle arrive a < min_vitesse
        if(vitesse.getNorme() < MIN_VITESSE)
        {
            vitesse.setvX(0);
            vitesse.setvY(0);
        }
        tpsFin = System.currentTimeMillis();
        
        //Plateau fini non torique
        if(x > Sky.SIZE_FRAME/2)x = Sky.SIZE_FRAME/2;
        if(y > Sky.SIZE_FRAME/2)y = Sky.SIZE_FRAME/2;
        
        if(x < -Sky.SIZE_FRAME/2)x = -Sky.SIZE_FRAME/2;
        if(y < -Sky.SIZE_FRAME/2)y = -Sky.SIZE_FRAME/2;
        		
	}

}
