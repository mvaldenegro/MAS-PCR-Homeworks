package tsp;

public class City
{
	private String name;
	private double positionX;
	private double positionY;
	
	public City()
	{		
	}	
	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public double getPositionX() {
		return positionX;
	}



	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}



	public double getPositionY() {
		return positionY;
	}



	public void setPositionY(double positionY) {
		this.positionY = positionY;
	}

	double distanceTo(City c)
	{
		double dx = getPositionX() - c.getPositionX();
		double dy = getPositionY() - c.getPositionY();
		
		return Math.sqrt(dx * dx + dy * dy);
	}
}
