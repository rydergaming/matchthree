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
		assertEquals(2,br.getElement(0, 0));
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
		assertEquals(1,br.getElement(0, 0));
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

}
