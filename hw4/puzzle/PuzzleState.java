package puzzle;

import java.util.ArrayList;
import java.util.List;

import search.astar.HeuristicState;

public class PuzzleState extends HeuristicState {
	private int[][] stateMatrix = new int[3][3];
	private int heuristicCode = MANHATTAN_HEURISTIC;

	public static final int BLANK_TILE = 0;
	
	public static final int MANHATTAN_HEURISTIC = 0;
	public static final int MISPLACED_TILES_HEURISTIC = 1;
	public static final int LINEAR_CONFLICT_HEURISTIC = 2;
	
	public PuzzleState()
	{		
		heuristicCode = MANHATTAN_HEURISTIC;
		
		stateMatrix = randomPuzzle();
	}
	
	public int[][] randomPuzzle()
	{
		int[][] puzzle = new int[3][3];
		int [] temp = new int [] {0, 1, 2, 3, 4, 5, 6, 7, 8};
		ArrayUtils.shuffle(temp);
		
		return ArrayUtils.toMatrix(puzzle, temp);
	}
	
	public PuzzleState(int a0, int a1, int a2,
			     int a3, int a4, int a5,
			     int a6, int a7, int a8)
	{
		stateMatrix[0][0] = a0;
		stateMatrix[0][1] = a1;
		stateMatrix[0][2] = a2;
		
		stateMatrix[1][0] = a3;
		stateMatrix[1][1] = a4;
		stateMatrix[1][2] = a5;
		
		stateMatrix[2][0] = a6;
		stateMatrix[2][1] = a7;
		stateMatrix[2][2] = a8;
	}
	
	public PuzzleState(int[][] matrix)
	{		
		stateMatrix[0][0] = matrix[0][0];
		stateMatrix[0][1] = matrix[0][1];
		stateMatrix[0][2] = matrix[0][2];
		
		stateMatrix[1][0] = matrix[1][0];
		stateMatrix[1][1] = matrix[1][1];
		stateMatrix[1][2] = matrix[1][2];
		
		stateMatrix[2][0] = matrix[2][0];
		stateMatrix[2][1] = matrix[2][1];
		stateMatrix[2][2] = matrix[2][2];
	}
	
	public int[][] getMatrix() {
		return stateMatrix;
	}

	public void setMatrix(int[][] stateMatrix) {
		this.stateMatrix = stateMatrix;
	}
	
	public int getTile(int i, int j)
	{
		return stateMatrix[i][j];
	}
	
	public void setTile(int i, int j, int value)
	{
		stateMatrix[i][j] = value;
	}		
	
	public int getHeuristicCode() {
		return heuristicCode;
	}

	public void setHeuristicCode(int heuristicCode) {
		this.heuristicCode = heuristicCode;
	}

	private int[] goalPosition(int number)
	{
		switch(number) {
						
			case 1: return new int[] {0, 0};
			case 2: return new int[] {0, 1};
			case 3: return new int[] {0, 2};
			case 4: return new int[] {1, 0};
			case 5: return new int[] {1, 1};
			case 6: return new int[] {1, 2};
			case 7: return new int[] {2, 0};
			case 8: return new int[] {2, 1};
			case 0: return new int[] {2, 2};
			
			default: return null;
		}
	}
	
	public int linearConflictHeuristic()
	{
		int ret = manhattanDistance();
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				int numberShouldBeHere = numberAtTile(i, j);
				
				int[] whereIsNumber = findTile(numberShouldBeHere);
								
				//If it's in the same row
				if(whereIsNumber[0] == i) {
					
					if(whereIsNumber[1] > j) {
						ret += 1;
					}
				}
				
				//If it's in the same column
				if(whereIsNumber[1] == j) {
					
					if(whereIsNumber[0] < i) {
						ret += 1;
					}
				}
				
				int[] goalPos = goalPosition(stateMatrix[i][j]);
				
				if(whereIsNumber[0] == goalPos[0]) {
					
					if(whereIsNumber[1] > goalPos[1]) {
						ret += 1;
					}
				}
				
				if(whereIsNumber[1] == goalPos[1]) {
					if(whereIsNumber[0] < goalPos[0]) {
						ret += 1;
					}
				}
			}
		}
		
		return ret;
	}
		
	public int manhattanDistance()
	{
		int ret = 0;
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				
				//if(stateMatrix[i][j] != BLANK_TILE) {
										
					int[] where = findTile(numberAtTile(i, j));
					
					ret += Math.abs(i - where[0]) + Math.abs(j - where[1]);
				//}
			}
		}
		
		return ret;
	}
	
	public int misplacedTiles()
	{
		int ret = 0;
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				
				if((stateMatrix[i][j] != numberAtTile(i, j))) {
					
					ret += 1;
				}
			}
		}
		
		return ret;
	}
	
	public int numberAtTile(int i, int j)
	{
		if(i == 2 && j == 2) {
			return 0;
		}
		
		return 3 * i + j + 1;
	}
	
	public List<HeuristicState> nextStates()
	{
		List<HeuristicState> ret = new ArrayList<HeuristicState>();
		
		int[] bt = findTile(BLANK_TILE);
		int i = bt[0];
		int j = bt[1];
				
		//Move tile right.
		if(validPosition(i, j + 1)) {
			PuzzleState r = swapTile(i, j, i, j + 1);
			
			ret.add(r);
		}
		
		//Move tile left.
		if(validPosition(i, j - 1)) {
			PuzzleState l = swapTile(i, j, i, j - 1);
			
			ret.add(l);
		}
		
		//Move tile up.
		if(validPosition(i - 1, j)) {
			PuzzleState u = swapTile(i, j, i - 1, j);
			
			ret.add(u);
		}
		
		//Move tile down.
		if(validPosition(i + 1, j)) {
			PuzzleState d = swapTile(i, j, i + 1, j);
			
			ret.add(d);
		}
	
		//System.out.println("Successor states from state\n" + toString());
		//System.out.println("----------");
		
		for(HeuristicState s: ret) {
			//System.out.println(s);
			//System.out.println("----------");
		}
		
		return ret;
	}
	
	private boolean validPosition(int i, int j)
	{
		return (i >= 0) && (i < 3) && (j >= 0) && (j < 3);
	}
	
	private PuzzleState swapTile(int oldI, int oldJ, int i, int j)
	{
		PuzzleState ret = new PuzzleState(stateMatrix);
		
		int tmp = ret.getTile(oldI, oldJ);
		ret.setTile(oldI, oldJ, ret.getTile(i, j));
		ret.setTile(i, j, tmp);
				
		return ret;
	}
	
	private int[] findTile(int number)
	{
		int[] ret = new int[2];
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				
				if(stateMatrix[i][j] == number) {
					ret[0] = i;
					ret[1] = j;
					
					break;
				}
			}
		}
		
		return ret;
	}
	
	@Override
	public String toString()
	{
		StringBuilder build = new StringBuilder();
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				
				build.append(stateMatrix[i][j] + " ");						
			}
			
			if(i != 2) {
				build.append("\n");
			}
		}
		
		return build.toString();
	}
	
	@Override
	public int distanceToGoal()
	{
		switch(heuristicCode) {
			case MANHATTAN_HEURISTIC: {
				return manhattanDistance();
			}
			
			case MISPLACED_TILES_HEURISTIC: {
				return misplacedTiles();
			}
			
			case LINEAR_CONFLICT_HEURISTIC: {
				return linearConflictHeuristic();
			}
			
			default: {
				throw new RuntimeException("Invalid heuristic code");
			}
		}
	}

	@Override
	public boolean isGoal()
	{
		return misplacedTiles() == 0;
	}

	@Override
	public int hashCode()
	{
		int ret = 0;
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				
				 ret = 31 * ret + 24 * i + 15 * j + stateMatrix[i][j]; 
			}
		}
		
		return ret;
	}
	
	public boolean equals(Object o)
	{
		PuzzleState s = (PuzzleState) o;
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				
				 if(s.getTile(i, j) != getTile(i, j)) {
					 return false;
				 }
			}
		}
		
		return true;
	}
	
	public PuzzleState clone()
	{
		PuzzleState ret = new PuzzleState(stateMatrix);
		ret.setHeuristicCode(heuristicCode);
		
		return ret;
	}
}
