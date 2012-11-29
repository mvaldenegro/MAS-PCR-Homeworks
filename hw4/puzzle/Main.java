package puzzle;

import search.SearchAlgorithm;
import search.astar.AStarSearch;
import search.dfs.DepthFirstSearch;

public class Main {

	public static void main(String[] args)
	{
		//PuzzleState s = new PuzzleState(1, 2, 3, 0, 5, 6, 4, 7, 8);
		PuzzleState s = new PuzzleState();
		
		System.out.println("Generated state: \n" + s.toString()); 
		
		System.out.println("Doing A* search");		
		SearchAlgorithm alg = new AStarSearch();
		
		System.out.println("Using Manhattan Heuristic");
		s.setHeuristicCode(PuzzleState.MANHATTAN_HEURISTIC);
		alg.search(s.clone());
		
		System.out.println("Using Misplaced Tiles Heuristic");
		s.setHeuristicCode(PuzzleState.MISPLACED_TILES_HEURISTIC);
		alg.search(s.clone());
		
		System.out.println("Using Linear Conflict Heuristic");
		s.setHeuristicCode(PuzzleState.LINEAR_CONFLICT_HEURISTIC);
		alg.search(s.clone());
	}	
}
