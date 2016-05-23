package hu.unideb.inf.rydergaming.matchthree.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import hu.unideb.inf.rydergaming.matchthree.model.Board;

public class BoardTest {

	@Test
	public void testGetMoves() {
		Board br = new Board();
		assertEquals(20, br.getMoves());
	}

	@Test
	public void testGetPoints() {
		Board br = new Board();
		assertEquals(0,br.getPoints());
	}

	@Test
	public void testBoard() {
		Board br = new Board();
		assertEquals(0,br.getElement(4, 4));
	}

	@Test
	public void testGetBoard() {
		Board br = new Board();
		StringBuilder sb = new StringBuilder();
		for (int i=0; i< 8; i++) {
			for (int j=0; j < 8; j++)
				sb.append(br.getElement(i, j)).append(" ");
			sb.append("\n");
		}
		assertEquals(sb.toString(), br.getBoard());
			
	}

	@Test
	public void testGetOffset() {
		Board br = new Board();
		StringBuilder sb = new StringBuilder();
		for (int i=0; i< 8; i++) {
			for (int j=0; j < 8; j++)
				sb.append((double)i*46).append(" ");
			sb.append("\n");
		}
		assertEquals(sb.toString(), br.getOffset());
	}

	@Test
	public void testGetElement() {
		Board br = new Board();
		assertEquals(3,br.getElement(0, 0));
	}

	@Test
	public void testFallBoard() {
		Board br = new Board();
		String tmp = br.getBoard();
		br.fallBoard();
		assertEquals(tmp, br.getBoard());
	}

	@Test
	public void testSwitchPositions() {
		Board br = new Board();
		br.switchPositions(0, 0, 1, 0);
		assertEquals(0,br.getElement(0, 0));
	}

	@Test
	public void testCheckRecursiveHorStart() {
		Board br = new Board();
		assertEquals(false, br.checkRecursiveHorStart(5, 5, false));
	}

	@Test
	public void testCheckRecursiveHor() {
		Board br = new Board();
		assertEquals(0, br.checkRecursiveHor(6, 1, 1));
	}

	@Test
	public void testCheckRecursiveVerStart() {
		Board br = new Board();
		assertEquals(false, br.checkRecursiveVerStart(5, 2, false));
	}

	@Test
	public void testCheckRecursiveVer() {
		Board br = new Board();
		assertEquals(0, br.checkRecursiveVer(2, 3, -1));
	}
	@Test
	public void testToString() {
		Board br = new Board();
		String tmp = 	  "[ 3  2  3  4  5  5  1  5 ]    [ 0    0    0    0    0    0    0    0   ]\n"
						+ "[ 0  4  3  6  2  6  2  1 ]    [ 46   46   46   46   46   46   46   46  ]\n"
						+ "[ 0  6  1  3  6  0  3  4 ]    [ 92   92   92   92   92   92   92   92  ]\n"
						+ "[ 6  1  3  6  5  3  1  3 ]    [ 138  138  138  138  138  138  138  138 ]\n"
						+ "[ 2  2  0  5  0  4  6  3 ]    [ 184  184  184  184  184  184  184  184 ]\n"
						+ "[ 3  3  4  6  4  6  1  0 ]    [ 230  230  230  230  230  230  230  230 ]\n"
						+ "[ 2  0  3  6  5  2  4  4 ]    [ 276  276  276  276  276  276  276  276 ]\n"
						+ "[ 1  4  4  2  5  1  1  5 ]    [ 322  322  322  322  322  322  322  322 ]\n";		
		assertEquals(tmp, br.toString());
	}
	
	@Test
	public void testSetMoves() {
		Board br = new Board();
		br.setMoves(1);
		assertEquals(1, br.getMoves());
	}

}
