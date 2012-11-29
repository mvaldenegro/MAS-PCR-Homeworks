package robotcsp;

import java.util.ArrayList;
import java.util.List;

public class RobotCSP {
	
	private List<RobotConstraint> constraints = new ArrayList<RobotConstraint>();
	private Vector2 startPosition;
	
	public RobotCSP()
	{
		
	}
	
	RobotConstraint get(int i)
	{
		return constraints.get(i);
	}
	
	public int size()
	{
		return constraints.size();
	}
	
	void add(RobotConstraint c)
	{
		constraints.add(c);
	}
	
	public Vector2 getStartPosition()
	{
		return startPosition;
	}
	
	public void setStartPosition(Vector2 startPosition)
	{
		this.startPosition = startPosition;
	}
}
