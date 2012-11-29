package robotcsp;

public class DistanceComparator extends VariableComparator
{
	public DistanceComparator()
	{
	}
	
	@Override
	public int compare(Integer a, Integer b)
	{
		RobotCSP problem = getProblem();
		Assignment assignment = getAssignment();
		
		Vector2 posA = problem.get(a).position;
		Vector2 posB = problem.get(b).position;
		  
		double distCurToA = assignment.getCurrentPosition().distanceTo(posA);
		double distCurToB = assignment.getCurrentPosition().distanceTo(posB);
		  
		if(distCurToA > distCurToB) {
			return 1;
		}
		  
		if(distCurToB > distCurToA) {
			return -1;
		}	
		  
		return 0;
	}

}
