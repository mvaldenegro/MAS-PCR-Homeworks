package tsp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TSPLoader
{
	public TSPLoader()
	{
		
	}
	
	public List<City> loadCities(String fileName)
	{
		List<City> ret = new ArrayList<City>();
	
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));

			String line = null;
			
			while((line = reader.readLine()) != null) {
				
				String[] parts = line.split("\t");
				
				//Skip header
				if("Name".equalsIgnoreCase(parts[0])) {
					continue;
				}
				
				String name = parts[0].trim();
				double x = Double.parseDouble(parts[2].trim());
				double y = Double.parseDouble(parts[3].trim());
				
				City city = new City();
				
				city.setName(name);
				city.setPositionX(x);
				city.setPositionY(y);
				
				ret.add(city);
			}
			
			reader.close();
			
		} catch(IOException e) {
			
			throw new RuntimeException(e);
		}
		
		return ret;
	}
}
