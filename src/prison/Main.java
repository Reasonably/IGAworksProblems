package prison;

import java.util.ArrayList;

public class Main {
	public static final String mapInput = "□■□■□■□■□□□□□■□□■□■□□■□■□□□□□□□□□□□■□□□□□□□□□■□■□■□□□□□□□□□□■□■□";
	public static final int MAXCRIMINAL = 64;
	public static final int MATRIX_SIZE = 8;
	
	
	public static ArrayList<int[][]> res = new ArrayList<int[][]>();
	
	public static int[][] map = new int[MATRIX_SIZE][MATRIX_SIZE];
	
	public static int t = 0;
	
	public static int[][] cloneArray(int[][] src) {
	    int length = src.length;
	    int[][] target = new int[length][src[0].length];
	    for (int i = 0; i < length; i++) {
	        System.arraycopy(src[i], 0, target[i], 0, src[i].length);
	    }
	    return target;
	}
	
	public static void main(String[] args) {

		
		makeMap();
		
		int max = 0;
		int cases = 0;

		for(int i=15; i<MAXCRIMINAL; i++) {
			res = new ArrayList<int[][]>();
			t = 0;
			

			calculateRecursively(i, 0, 0);
			

			if(t == 0) {
				break;
			}
			
			max = i;
			cases = t;
		}
		
		System.out.printf("최대 %d명, %d가지\n", max, cases);
		
		

	}
	
	public static void printMap(int[][] ma) {
		for(int i=0; i<MATRIX_SIZE; i++) {
			for(int j=0; j<MATRIX_SIZE; j++) {
				
				System.out.print(ma[i][j] == 0 ? '□' : (ma[i][j] == 1 ? '■' : 'A'));
				
			}
			System.out.print('\n');
		}
		
		System.out.print('\n');
	}
	
	public static void makeMap() {

		for(int i=0;i<mapInput.length();i++) {
			char block = mapInput.charAt(i);
			map[i/MATRIX_SIZE][i%MATRIX_SIZE] =  block == '□' ? 0 : (block == '■' ? 1 : 2);
		}
	}
	
	public static boolean isContain(ArrayList<int[][]> arr, int [][] target) {
		
		for(int i=0; i<arr.size(); i++) {
			boolean res = true;
			for(int j=0; j<MATRIX_SIZE; j++) {
				
				for(int k=0; k<MATRIX_SIZE; k++) {
					if(arr.get(i)[j][k] != target[j][k]) {
						res = false;
						break;
					}
				}
				if(!res)
					break;
			}
			if(res)
				return true;
		}
		
		
		return false;
	}
	

	public static void calculateRecursively(int count, int col, int row) {
		//map = cloneArray(map);
		if(row == MATRIX_SIZE) {
			calculateRecursively(count, col+1, 0);
		}
		if(count <= 0 || col >=MATRIX_SIZE || row >=MATRIX_SIZE) {
			//makeMap();
			if(count <=0) {
				
				
				if(!isContain(res, map)) {
					res.add(cloneArray(map));
					t++;
					
				}
			}
		}
		else {

			if(checkPlaceable(map, col, row)) {

				calculateRecursively(count, col, row+1);
				
				
				map[col][row] = 2;
				
				
				calculateRecursively(count-1, col, row+1);
				map[col][row] = 0;
				
			}
			else {
				calculateRecursively(count, col, row+1);
			}
		}
	}
	
	public static boolean checkPlaceable(int[][] curMap, int col, int row) {
		if(curMap[col][row] == 1)
			return false;
		
		for(int j=row+1; j<MATRIX_SIZE; j++) {
			if(curMap[col][j] == 1) 
				break;
			else if(curMap[col][j] == 2)
				return false;
		}
		
		for(int j=row-1; j>=0; j--) {
			if(curMap[col][j] == 1) 
				break;
			else if(curMap[col][j] == 2)
				return false;
		}
		
		for(int i=col+1; i<MATRIX_SIZE; i++) {
			if(curMap[i][row] == 1) 
				break;
			else if(curMap[i][row] == 2)
				return false;
		}
		
		for(int i=col-1; i>=0; i--) {
			if(curMap[i][row] == 1) 
				break;
			else if(curMap[i][row] == 2)
				return false;
		}
		
		return true;
	}

}
