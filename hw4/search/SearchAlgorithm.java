package search;

import search.astar.HeuristicState;

public interface SearchAlgorithm
{
	void search(HeuristicState initialState);
}
