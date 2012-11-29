package sudoku;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

import search.astar.HeuristicState;

public class SudokuState extends HeuristicState {

	public int puzzleArray [][] = new int [9][9];
	public int zeroLevel;
	
	public SudokuState(int zeros){
		zeroLevel = zeros;
		
	}
	
	public SudokuState (int [][] Matrix )
	{
		for(int k=0; k<9; k++){
			for(int m=0; m<9; m++){
				puzzleArray[k][m] = Matrix[k][m];
			}
		}
	}
	
	public void genSudoku(){
		Random generator = new Random();
		int putNumber;
		
		for(int k=0; k<9; k++){
			for(int m=0; m<9; m++){
				putNumber = generator.nextInt(zeroLevel);
				while(!isValid(puzzleArray, putNumber, k, m)){
					putNumber = generator.nextInt(zeroLevel);		
				}
				if(putNumber > 9){
					putNumber=0;
				}
				puzzleArray[k][m] = putNumber;
			}
		}
		
	}
	
	public void printSudoku() {
        for (int i = 0; i < 9; ++i) {
            if (i % 3 == 0)
                System.out.println(" -----------------------");
            for (int j = 0; j < 9; ++j) {
                if (j % 3 == 0) System.out.print("| ");
                System.out.print(puzzleArray[i][j] == 0
                                 ? " "
                                 : Integer.toString(puzzleArray[i][j]));

                System.out.print(' ');
            }
            System.out.println("|");
        }
        System.out.println(" -----------------------");
    }
	
	public List<HeuristicState> nextStates()
	{
		ArrayList<Integer>  invalidNums = new ArrayList<Integer>();
		ArrayList<Integer>  validNums = new ArrayList<Integer>();
		List<HeuristicState>  successors = new ArrayList<HeuristicState>();
		
		
		
		int rowIndex=nextEmptyBox(this)[0];		//This is the box coordinates that we are checking
		int colIndex=nextEmptyBox(this)[1];
		//System.out.print("NextRowCol"+rowIndex+colIndex);
		
		//check for the row
		
		for(int k = 0; k<9; k++){
				if(puzzleArray[k][colIndex] != 0 &&  !invalidNums.contains(puzzleArray[k][colIndex])){
					invalidNums.add(puzzleArray[k][colIndex]);
				}
		}
		
		//check for the column
		
		for(int k = 0; k<9; k++){
			if(puzzleArray[rowIndex][k] != 0 &&  !invalidNums.contains(puzzleArray[rowIndex][k])){
				invalidNums.add(puzzleArray[rowIndex][k]);
			}
		}
		
		//check for the small 3x3 box
		
		int boxNumCol = (colIndex/3) * 3;	//trying to find the 3x3 box which our small box belongs to
		int boxNumRow = (rowIndex/3) * 3;
		
		for (int k = boxNumRow; k<boxNumRow + 3; k++){
			for (int m = boxNumCol; m<boxNumCol + 3; m++){
				if(puzzleArray[k][m] != 0 &&  !invalidNums.contains(puzzleArray[k][m])){
					invalidNums.add(puzzleArray[k][m]);
				}
			}
		}
	
		//to find valid Numbers to a column
		for(int i = 1; i<10; i++){
			if(!invalidNums.contains(i)){
				validNums.add(i);
			}	
		}
		
		for (int i = 0; i<validNums.size(); i++){
			SudokuState states = new SudokuState(puzzleArray);
			states.puzzleArray[rowIndex][colIndex] = validNums.get(i);
			successors.add(states);
		}
		
		//System.out.println(validNums);
		//System.out.println("Successors: ");
		//for(int i=0; i<successors.size(); i++){
		//	successors.get(i).printSudoku();
		//}
		
		return successors; 
		
	}
	
	
	private int[] nextEmptyBox (SudokuState state){
		
		int rowCol [] = new int [2];
		for(int k=0; k<9; k++){
			for(int m=0; m<9; m++){
				if(state.puzzleArray[k][m] == 0){
					rowCol[0] = k;
					rowCol[1] = m;
					return rowCol;
				}
				
			}
		}
		return null;
	}
	
	
	public boolean isGoal()
	{
		for(int k=0; k<9; k++){
			for(int m=0; m<9; m++){
				if(puzzleArray[k][m] == 0) {
					return false;
				}
			}
		}
		return true;
			
		
		
	}
	
	private boolean isValid(int[][] puzzle, int number, int row, int col){
		
		//if the number is 0 it means it will be an empty box, so no need to check it.
				if(number == 0 || number > 9){	
					return true;
				}
				//check the column
				for(int k=0; k<9; k++){
					if(puzzle[row][k] == number)
						return false;
				}
				
				//check the row
				for(int k=0; k<9; k++){
					if(puzzle[k][col] == number)
						return false;
				}
				
				//check the box
				int boxNumCol = (col/3) * 3;	//trying to find the 3x3 box which our small box belongs to
				int boxNumRow = (row/3) * 3;
				
				for (int k = boxNumRow; k<boxNumRow + 3; k++){
					for (int m = boxNumCol; m<boxNumCol + 3; m++){
						if(puzzle[k][m] == number)
							return false;
					}
				}
				return true;
	}

	@Override
	public int distanceToGoal()
	{
		return emptyBoxes();
	}
	
	public int emptyBoxes()
	{
		int ret = 0;
		
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				
				ret += (puzzleArray[i][j] == 0) ? 1 : 0;
			}
		}
		
		return ret;
	}

	@Override
	public int hashCode()
	{
		int ret = 0;
		
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				
				 ret = 31 * ret + 24 * i + 15 * j + puzzleArray[i][j]; 
			}
		}
		
		return ret;
	}
	
	@Override
	public boolean equals(Object o)
	{
		SudokuState s = (SudokuState) o;
		
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				
				 if(s.puzzleArray[i][j] != puzzleArray[i][j]) {
					 return false;
				 }
			}
		}
		
		return true;
	}
	
	@Override
	public String toString()
	{
		StringBuilder ret = new StringBuilder();
		
        for (int i = 0; i < 9; ++i) {
        	
            if (i % 3 == 0) {
            	ret.append(" -----------------------\n");
            }
            
            for (int j = 0; j < 9; ++j) {
            	
                if (j % 3 == 0) {
                	ret.append("| ");
                }
                
                ret.append(puzzleArray[i][j] == 0 ? " " : Integer.toString(puzzleArray[i][j]));
                ret.append(' ');
            }
            ret.append("|\n");
        }
        
        ret.append(" -----------------------\n");
        
        return ret.toString();
    }
}
