package tsp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cycle {

	private List<Integer> cycle; 
	private List<City> cities;
	
	public Cycle(List<City> globalCityArray)
	{
		cities = globalCityArray;
		cycle = new ArrayList<Integer>();
	}
	
	public Cycle(List<City> globalCityArray, List<Integer> cycleIndices)
	{
		cities = globalCityArray;
		cycle = cycleIndices;
	}
	
	public void addCityIndex(int cityIndex)
	{
		if(cityIndex >= cities.size()) {
			throw new RuntimeException("Invalid city index " + cityIndex);
		}
		
		cycle.add(cityIndex);
	}
	
	public String toString()
	{
		StringBuilder ret = new StringBuilder();
		
		ret.append("[ ");
		
		for(int i: cycle) {
			ret.append(cities.get(i).getName());
			ret.append(" -> ");
		}
		
		ret.append(cities.get(cycle.get(0)).getName());
		ret.append("]");
		
		return ret.toString();
	}
	
	public double cost()
	{
		double ret = 0.0;
		
		for(int i = 0; i < cycle.size(); i++) {
			City a = cities.get(cycle.get(i));
			City b = cities.get(cycle.get((i+1) % cities.size()));
					
			ret += a.distanceTo(b);
		}
				
		return ret;
	}
	
	public List<Cycle> neighborStates()
	{
		List<Cycle> ret = new ArrayList<Cycle>();
		
		for(int i = 0; i < cycle.size(); i++) {
			
			for(int j = 0; j < cycle.size(); j++) {
				
				Cycle c = new Cycle(cities, cloneAndSwapCycle(i, j));
				
				ret.add(c);
			}			
		}
		
		return ret;
	}
	
	public Cycle randomNeighbor()
	{
		Random rand = new Random();
		
		int i = rand.nextInt(cities.size());
		int j = rand.nextInt(cities.size());
		
		while((i == j) || (i == 0) || (j == 0)) {
			i = rand.nextInt(cities.size());
			j = rand.nextInt(cities.size());
		}
		
		return new Cycle(cities, cloneAndSwapCycle(i, j));
	}
	
	private List<Integer> cloneAndSwapCycle(int i, int j)
	{
		List<Integer> ret = new ArrayList<Integer>();
		
		for(int c: cycle) {
			ret.add(c);
		}
		
		int tmp = ret.get(i);
		ret.set(i, ret.get(j));
		ret.set(j, tmp);
		
		return ret;
	}
	
	public static Cycle initialCycle(List<City> cities, int startingCityIndex)
	{
		Cycle ret = new Cycle(cities);
		
		for(int i = 0; i < cities.size(); i++) {
			ret.addCityIndex((startingCityIndex + i) % cities.size());
		}
		
		return ret;
	}

}
