package tsp;

import java.util.Random;

public class AnnealingSimulator {

	public static final int NUMBER_OF_ITERATIONS = 500000;
	
	public AnnealingSimulator()
	{		
	}
	
	public Cycle simulate(Cycle start)
	{
		Cycle current = start;		
		
		for(int i = 0; i < NUMBER_OF_ITERATIONS; i++) {
			Cycle next = current.randomNeighbor();
			double T = temperature(i);
			
			double deltaE = next.cost() - current.cost();
			
			//We check for negative deltaE since we want to minimize costs.
			if(deltaE < 0) {
				current = next;
				
				System.out.println("Current cost change (negative deltaT): " + current.cost());
				
			} else if(randomFalseMovement(deltaE, T)) {
				current = next;
				
				System.out.println("Current cost change (random movement): " + current.cost());
			}			
		}
		
		System.out.println("Best state has cost: " + current.cost());
		System.out.println(current.toString());
		
		return current;
	}
	
	private double temperature(int i)
	{
		return 1.0 * Math.pow(0.9, i);
	}
		
	private boolean randomFalseMovement(double deltaE, double T)
	{
		double p = Math.exp(-deltaE / T);
		
		return (new Random().nextDouble() < p);
	}
}
