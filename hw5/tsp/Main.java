package tsp;

import java.util.List;

public class Main {

	public static void main(String[] args)
	{
		List<City> cities = new TSPLoader().loadCities("100_biggest_cities_germany.txt");
		
		for(City city: cities) {
			System.out.println("Loaded city " + city.getName() + " at " + city.getPositionX() + ", " + city.getPositionY());
		}
		
		System.out.println("Starting simulated annealing");
		
		int initialCityINdex = findCityIndex(cities, "Bonn");
		Cycle initial = Cycle.initialCycle(cities, initialCityINdex);		
		
		new AnnealingSimulator().simulate(initial);
	}
	
	public static int findCityIndex(List<City> cities, String cityName)
	{
		for(int i = 0; i < cities.size(); i++) {
			City c = cities.get(i);
			
			if(cityName.equalsIgnoreCase(c.getName())) {
				return i;
			}
		}
		
		return -1;
	}
}
