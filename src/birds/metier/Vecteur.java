package birds.metier;

public class Vecteur
{
    private Point a;
    private Point b;

    public Vecteur(Point a, Point b) {
    	this.a = a;
    	this.b = b;
    }

    public Point getA() {
		return a;
	}



	public void setA(Point a) {
		this.a = a;
	}



	public Point getB() {
		return b;
	}



	public void setB(Point b) {
		this.b = b;
	}



	public double getNorme()
    {
        return Math.sqrt( (b.getX()-a.getX()) * (b.getX()-a.getX()) + (b.getY()-a.getY())*(b.getY()-a.getY()) );
    }

    public double calculNormeAvecDeuxValeurs(double x, double y){
        return Math.sqrt( (x*x) + (y*y) );
    }
    
   

}
