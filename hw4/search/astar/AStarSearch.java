package search.astar;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import puzzle.PuzzleState;

import search.SearchAlgorithm;

public class AStarSearch implements SearchAlgorithm {
	
	public AStarSearch()
	{
	}

	public void search(HeuristicState startState)
	{
		Set<HeuristicState> closed = new LinkedHashSet<HeuristicState>();
		Set<HeuristicState> open = new LinkedHashSet<HeuristicState>();
		
		startState.setGCost(0);
		startState.setFCost(startState.distanceToGoal());
		
		open.add(startState);
		
		int numberOfStatesExpanded = 0;
		
		boolean foundGoal = false;
		
		if(startState instanceof PuzzleState) {
			PuzzleState p = (PuzzleState) startState;
			
			System.out.println("Doing A* search with heuristic " + p.getHeuristicCode());
		}
		
		while(!open.isEmpty()) {
			HeuristicState current = best(open);
			
			if(current.isGoal()) {
				System.out.println("Reached goal state: \n" + current.toString());
				System.out.println("Current cost: " + current.gCost());
				System.out.println("Number of states expanded: " + numberOfStatesExpanded);
				
				foundGoal = true;
				
				return;
			}
			
			open.remove(current);
			closed.add(current);
			
			for(HeuristicState neighbor: current.nextStates()) {
				
				if(closed.contains(neighbor)) {
					continue;
				}
				
				int tentativeGCost = current.gCost() + 1;
				
				if(open.contains(neighbor)) {
					HeuristicState t = searchState(open, neighbor);
					
					int newF = tentativeGCost + neighbor.distanceToGoal();
					
					t.setFCost(Math.min(t.fCost(), newF));
					
				} else {
										
					neighbor.setGCost(tentativeGCost);
					neighbor.setFCost(tentativeGCost + neighbor.distanceToGoal());
					
					open.add(neighbor);
				}
			}
			
			numberOfStatesExpanded++;
		}
		
		if(open.isEmpty() && !foundGoal) {
			System.out.println("Goal not found");
		}
	}
	
	private HeuristicState searchState(Set<HeuristicState> states, HeuristicState target)
	{
		for(HeuristicState s: states) {
			
			if(s.equals(target)) {
				return s;
			}
		}
		
		return null;
	}
	
	private HeuristicState best(Set<HeuristicState> states)
	{
		int minCost = Integer.MAX_VALUE;
		HeuristicState ret = null;
		
		for(HeuristicState s: states) {
			int cost = s.fCost();
			
			if(cost < minCost) {
				minCost = cost;
				ret = s;
			}
		}
	
		//System.out.println("The best is state: \n" + ret.toString());
		//System.out.println("With F-Cost: " + minCost + " from " + states.size() + " states");
		
		return ret;
	}
}
