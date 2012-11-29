package robotcsp;

public class Vector2 {
	public double x = 0.0, y = 0.0;
	
	public Vector2()
	{
		
	}
	
	public Vector2(double x)
	{
		this.x = x;
	}
	
	public Vector2(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public double length()
	{
		return Math.sqrt(x * x + y * y);
	}
	
	public double distanceTo(Vector2 p)
	{
		double dx = x - p.x;
		double dy = y - p.y;
		
		return Math.sqrt(dx * dx + dy * dy);
	}
	
	public Vector2 sum(Vector2 p)
	{
		return new Vector2(x + p.x, y + p.y);
	}
	
	public Vector2 clone()
	{
		return new Vector2(x, y);
	}
	
	public String toString()
	{
		return "[ " + x + ", " + y + " ]";
	}
}
