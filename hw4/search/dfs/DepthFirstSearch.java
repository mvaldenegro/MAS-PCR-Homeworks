package search.dfs;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import search.SearchAlgorithm;
import search.astar.HeuristicState;

public class DepthFirstSearch implements SearchAlgorithm {

	public void search(HeuristicState startState)
	{
		Set<HeuristicState> closed = new HashSet<HeuristicState>();
		Stack<HeuristicState> open = new Stack<HeuristicState>();
				
		open.push(startState);
		
		int numberOfStatesExpanded = 0;
		boolean foundGoal = false;
		
		while(!open.isEmpty()) {
			HeuristicState current = open.pop();
						
			if(current.isGoal()) {
				System.out.println("Reached goal state: \n" + current.toString());
				System.out.println("Number of states expanded: " + numberOfStatesExpanded);
				
				foundGoal = true;
				
				return;
			}
			
			closed.add(current);

			List<HeuristicState> neighbors = current.nextStates();
			
			for(HeuristicState neighbor: neighbors) {
				
				if(closed.contains(neighbor)) {
					continue;
				}
				
				open.push(neighbor);
			}
			
			numberOfStatesExpanded++;
		}
		
		if(open.isEmpty() && !foundGoal) {
			System.out.println("Goal not found");
		}
	}	
}
