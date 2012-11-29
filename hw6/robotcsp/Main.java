package robotcsp;

public class Main {

	public static void main(String[] args)
	{
		if(args.length != 1) {
			System.out.println("Usage: java robot.Main scenariofile.txt");
			
			System.exit(-1);
		}
		
		RobotCSP problem = new ProblemReader().read(args[0]);
		CSPSolver solver = new CSPSolver(new LineNumberComparator());
		
		System.out.println("Read problem of size " + problem.size());
		Assignment ass = solver.solve(problem);
		
		if(ass != null) {
			System.out.println("Found solution: " + ass);
			System.out.println("Path length: " + ass.getCurrentTime());
			System.out.println("Recursive calls: " + solver.getRecursiveCallCount());
			
		} else {
			System.out.println("Solution not found!");
		}
	}

}
