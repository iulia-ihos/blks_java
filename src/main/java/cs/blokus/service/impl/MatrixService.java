package cs.blokus.service.impl;

import org.springframework.stereotype.Component;

@Component
public class MatrixService {
	
	public int[][] rotate90Clockwise(int[][] m) {
		int result[][] = new int[m[0].length][m.length];
		int c = result[0].length - 1;
		for(int i = 0; i < m.length; i++) 
			for(int j = 0; j < m[0].length; j++) {
				result[j][c-i] = m[i][j];
			}
			
		return result;
	}
	
	public int[][] flipX(int[][] m) {
		int result[][] = new int[m.length][m[0].length];
		int r = result.length - 1;
		for(int i = 0; i < m.length; i++) 
			for(int j = 0; j < m[0].length; j++) {
				result[r-i][j] = m[i][j];
			}
			
		return result;
	}
	
	public int[][] flipY(int[][] m) {
		int result[][] = new int[m.length][m[0].length];
		int c = result[0].length - 1;
		for(int i = 0; i < m.length; i++) 
			for(int j = 0; j < m[0].length; j++) {
				result[i][c-j] = m[i][j];
			}
			
		return result;
	}

}
