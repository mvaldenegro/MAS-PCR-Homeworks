package robotcsp;

import java.io.BufferedReader;
import java.io.FileReader;

public class ProblemReader {

	public ProblemReader()
	{
		
	}

	RobotCSP read(String fileName)
	{
		RobotCSP ret = new RobotCSP();		
		BufferedReader reader;
		
		try {
			reader = new BufferedReader(new FileReader(fileName));
			
			String line = null;
			
			String firstLine = reader.readLine();
			
			if(firstLine != null) {
				String[] parts = firstLine.split(" ");
				double x = Double.parseDouble(parts[0]);
				double y = Double.parseDouble(parts[1]);
				
				ret.setStartPosition(new Vector2(x, y));
			}

	        while((line = reader.readLine()) != null) {
	        	
	        	String[] parts = line.split(" ");
				double x = Double.parseDouble(parts[0]);
				double y = Double.parseDouble(parts[1]);
				double dl = Double.parseDouble(parts[2]);
				
				RobotConstraint c = new RobotConstraint();
				c.position = new Vector2(x, y);
				c.deadline = dl;
				
				ret.add(c);
	        }
	        
	        reader.close();
	        
		} catch (Exception e) {
			throw new RuntimeException(e);
		}        
		
		return ret;
	}
}
