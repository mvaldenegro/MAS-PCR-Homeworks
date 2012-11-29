package robotcsp;

import java.util.Comparator;

public abstract class VariableComparator implements Comparator<Integer> {

	private Assignment assignment = null;
	
	public VariableComparator()
	{
	}
	
	public Assignment getAssignment()
	{
		return assignment;
	}
	
	public void setAssignment(Assignment assignment)
	{
		this.assignment = assignment;
	}
	
	public RobotCSP getProblem()
	{
		return this.assignment.getProblem();
	}	
}
