package robotcsp;

public class LineNumberComparator extends VariableComparator
{
	public LineNumberComparator()
	{
	}
	
	@Override
	public int compare(Integer a, Integer b)
	{
		if(a > b) {
			return 1;
		}
		  
		if(b > a) {
			return -1;
		}	
		  
		return 0;
	}

}
