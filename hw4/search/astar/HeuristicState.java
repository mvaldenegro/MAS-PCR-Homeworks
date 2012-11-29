package search.astar;

import java.util.List;

public abstract class HeuristicState {
	
	private int curGCost = 0;
	private int curFCost = 0;
	
	public HeuristicState()
	{
	}
	
	public int gCost()
	{
		return curGCost;
	}
	
	public int fCost()
	{
		return curFCost;
	}
	
	public void setGCost(int cost)
	{
		curGCost = cost;
	}
	
	public void setFCost(int cost)
	{
		curFCost = cost;
	}
	
	public abstract int distanceToGoal();
	
	public abstract List<HeuristicState> nextStates();
	
	public abstract boolean isGoal();
	
	@Override
	public abstract int hashCode();
}
