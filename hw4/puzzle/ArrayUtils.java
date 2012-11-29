package puzzle;

import java.util.Random;

public class ArrayUtils {
	
	public static void shuffle(int[] a)
	{
	    int n = a.length;
	    Random random = new Random();
	    random.nextInt();
	    
	    for (int i = 0; i < n; i++) {
	      int change = i + random.nextInt(n - i);
	      swap(a, i, change);	
	    }
	}
	
	private static void swap(int[] a, int i, int change)
	{
	    int helper = a[i];
	    a[i] = a[change];
	    a[change] = helper;
	} 
	
	public static int [][] toMatrix(int[][] puzzle, int [] temp)
	{
		int s=0;
		
		for(int k = 0; k < 3 ; k++){
			for(int m = 0; m < 3 ; m++){
				
				puzzle[k][m] = temp[s];
				s++;
			}
		}
		return puzzle;
	}
}
