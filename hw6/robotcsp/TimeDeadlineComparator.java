package robotcsp;

public class TimeDeadlineComparator extends VariableComparator
{
	public TimeDeadlineComparator()
	{
	}
	
	@Override
	public int compare(Integer a, Integer b)
	{
		RobotCSP problem = getProblem();
		Assignment assignment = getAssignment();
		double curTime = assignment.getCurrentTime();
		
		double dA = problem.get(a).deadline;
		double dB = problem.get(b).deadline;
		  
		double timeCurToA = dA - curTime;
		double timeCurToB = dB - curTime;
		  
		if(timeCurToA > timeCurToB) {
			return 1;
		}
		  
		if(timeCurToB > timeCurToA) {
			return -1;
		}	
		  
		return 0;
	}

}
