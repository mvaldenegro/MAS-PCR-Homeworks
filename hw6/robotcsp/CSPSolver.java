package robotcsp;

import java.util.Collections;
import java.util.List;

public class CSPSolver {

	private int recursiveCallCount = 0;
	private VariableComparator varComparator = null;
	
	public CSPSolver(VariableComparator comparator)
	{
		varComparator = comparator;
	}
	
	public int getRecursiveCallCount()
	{
		return recursiveCallCount;
	}
	
	public Assignment solve(RobotCSP problem)
	{
		Assignment a = new Assignment(problem);
		
		a = recursiveBacktracking(a);
		
		return a;
	}
	
	public Assignment recursiveBacktracking(Assignment assignment)
	{
		System.out.println("Assignment: " + assignment);
		
		recursiveCallCount++;
		
		if(assignment.isComplete()) {			
			return assignment;
		}
		
		List<Integer> vars = orderUnassignedVariables(assignment.unassignedIndices(), assignment);
		
		for(int variable: vars) {

			if(assignment.consistent(variable)) {
				Assignment result = assignment.addAssignment(variable);				
				
				result = recursiveBacktracking(result);
												
				if(result != null) {
										
					return result; 
				}
			}
		}		
		
		return null;
	}
	
	public List<Integer> orderUnassignedVariables(List<Integer> variables, final Assignment assignment)
	{	
		varComparator.setAssignment(assignment);
		
		Collections.sort(variables, varComparator);
		
		return variables;
	}
}
