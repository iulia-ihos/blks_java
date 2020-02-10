package cs.blokus.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cs.blokus.service.impl.MatrixUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MatrixServiceTest {
	
	@Autowired 
	private MatrixUtils matrixService;
	
	@Test
	public void testRotation(){
		int[][] t3 = new int[2][3];
		t3[0][1] = 1;
		t3[1][0] = 1;
		t3[1][1] = 1;
		t3[1][2] = 1;
		int[][] rotatedT3 = new int[3][2];
		rotatedT3[0][0] = 1;
		rotatedT3[1][0] = 1;
		rotatedT3[1][1] = 1;
		rotatedT3[2][0] = 1;
		
		assertArrayEquals(rotatedT3, matrixService.rotate90Clockwise(t3));
		
	}
	
	@Test
	public void testFlipX(){
	
		int[][] f = new int[3][3];
		f[0][1] = 1;
		f[0][2] = 1;
		f[1][0] = 1;
		f[1][1] = 1;
		f[2][1] = 1;
		
		int[][] XFlippedF = new int[3][3];
		XFlippedF[0][1] = 1;
		XFlippedF[1][0] = 1;
		XFlippedF[1][1] = 1;
		XFlippedF[2][1] = 1;
		XFlippedF[2][2] = 1;

		assertArrayEquals(XFlippedF, matrixService.flipX(f));
		
	}
	
	@Test
	public void testFlipY(){
		int[][] f = new int[3][3];
		f[0][1] = 1;
		f[0][2] = 1;
		f[1][0] = 1;
		f[1][1] = 1;
		f[2][1] = 1;
		
		int[][] YFlippedF = new int[3][3];
		YFlippedF[0][0] = 1;
		YFlippedF[0][1] = 1;
		YFlippedF[1][1] = 1;
		YFlippedF[1][2] = 1;
		YFlippedF[2][1] = 1;
		
		assertArrayEquals(YFlippedF, matrixService.flipY(f));
		
	}


}
