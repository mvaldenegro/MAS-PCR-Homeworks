package robotcsp;

import java.util.ArrayList;
import java.util.List;

public class Assignment {
	private List<Integer> assignments;
	private int lastAssignedIndex = 0;
	
	private Vector2 currentPosition;
	private double currentTime = 0.0;
	
	private RobotCSP problem;
	
	public Assignment(RobotCSP problem)
	{
		this.assignments = new ArrayList<Integer>();
		
		//for(int i = 0; i < problem.size(); i++) {
		//	assignments.add(0);
		//}
		
		this.currentPosition = problem.getStartPosition();		
		this.problem = problem;
	}
	
	int get(int i)
	{
		return assignments.get(i);
	}
	
	Assignment addAssignment(int index)
	{
		Assignment ret = clone();
		
		ret.assignments.add(index);
		
		ret.currentTime += 1.0 * ret.currentPosition.distanceTo(ret.problem.get(index).position);
		ret.currentPosition = ret.problem.get(index).position;
		
		ret.lastAssignedIndex++;
		
		return ret;
	}
	
	public Vector2 getCurrentPosition()
	{
		return currentPosition;
	}

	public double getCurrentTime()
	{
		return currentTime;
	}
	
	public RobotCSP getProblem()
	{
		return problem;
	}
	
	public boolean isComplete()
	{
		return (assignments.size() == problem.size()); 
	}
	
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		
		builder.append("[ ");
		
		for(int i = 0; i < assignments.size(); i++) {
			
			builder.append(assignments.get(i));
			builder.append(" ");	
		}
		
		builder.append("]");
		
		return builder.toString();
	}
	
	List<Integer> unassignedIndices()
	{
		List<Integer> ret = new ArrayList<Integer>();
		
		for(int i = 0; i < problem.size(); i++) {
			
			if(!assignments.contains(i)) {
				ret.add(i);
			}
		}
		
		return ret;
	}
	
	public boolean consistent(int index)
	{
		RobotConstraint cstr = problem.get(index);
		
		double hipoteticalTime = currentTime + 1.0 * currentPosition.distanceTo(cstr.position);
		
		return (cstr.deadline >= hipoteticalTime);
	}
	
	public Assignment clone()
	{
		Assignment ret = new Assignment(problem);
		
		for(int ass: assignments) {
			ret.assignments.add(ass);
		}
		
		ret.lastAssignedIndex = lastAssignedIndex;
		ret.currentPosition = currentPosition.clone();
		ret.currentTime = currentTime;
		
		return ret;
	}
}
