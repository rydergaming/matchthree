package hu.unideb.inf.rydergaming.matchthree.model;

import java.util.*;

public class Board {
	private int[][] board = new int[8][8];
	public double[][] offset = new double[8][8];
	public int cellheight = 0;
	private int moves = 21;

	public int getMoves() {
		return moves;
	}


	private int points = 0;
	public int getPoints() {
		return points;
	}

	private Random rnd = new Random();	
	public Board() {

		rnd.setSeed(19950622);
		for (int i=0; i<8; i++)
			for (int j=0; j<8; j++){
				board[i][j] = rnd.nextInt(6);
				offset[i][j] = -1000;
			}

		//checkBoard(false);
		
		for (int i=0;i<8; i++)
			for (int j=0; j<8; j++) {
				checkRecursiveHorStart(i, j, false);
				checkRecursiveVerStart(i, j, false);
			}	
		fallBoard();
		//showBoard();
		for (int i=0; i<8 ;i++)
			for (int j=0;j<8;j++) {
				offset[i][j] = i*46;
			}
		
	}
	
	public String getBoard() {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<8; i++) {
			for (int j=0; j<8; j++)
				sb.append(Integer.toString(board[i][j])).append(" ");
				sb.append("\n");
		}
		return sb.toString();
	}

	public String getOffset() {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<8; i++) {
			for (int j=0; j<8; j++)
				sb.append(Double.toString(offset[i][j])).append(" ");
				sb.append("\n");
		}
		return sb.toString();
	}
	
	public int getElement(int row, int column){
		return board[row][column];		
	}
	
	
	public void fallBoard() {
		int tmp = 8;
		while (tmp>0) {	
			for (int i=7; i>=1;i--)
				for (int j=7; j>=0;j--) {
					if (board[i][j] == -1) {
						if (i != 0) {
							board[i][j] = board[i-1][j];
							offset[i][j] = offset[i-1][j];
							board[i-1][j] = -1;							
						}									
					}	
				}
			tmp--;
		}

		for (int i=7; i>=0; i--)
			for (int j=0; j<8; j++)
				if (board[i][j] == -1)	{
					board[i][j] = rnd.nextInt(6);
					offset[i][j] = -46;
				}
		//showBoard();
		//showOffset();
					
	}

	public void switchPositions(int aX, int aY, int bX, int bY) {
		if (aX != bX && aY != bY)
			return;
		int c = board[aX][aY];
			board[aX][aY] = board[bX][bY];
			board[bX][bY] = c;
	}
	
	public boolean checkRecursiveHorStart(int i, int j, boolean givePoints) {
		boolean hasMatch = false;
		int start, end;
		start = end = 0;
		//System.out.println("I'm started at: " + i + " " + j);
		start = checkRecursiveHor(i,j,-1);
		end = checkRecursiveHor(i,j,1);
		/*if (i == 6)
			System.out.println(start + " " + end);*/
		if (((j+end) - (j - start)) >=2)
			for (int k=j - start; k<=j + end; k++) {
				if (givePoints) {
					points += board[i][k] + 3;
				}
				board[i][k] = -1;
				hasMatch = true;
		}	
		if (hasMatch) {
			//MainApp.logger.warn(Integer.toString(i) + " " + Integer.toString(j));
			this.moves--;
		}
		return hasMatch;
	}
	
	public int checkRecursiveHor(int i, int j, int dir) {
		//int debugValue = 0;
			if (j+dir<0 || j+dir>7)
				return 0;
			if (board[i][j] == -1 || board[i][j+dir] == -1)
				return 0;
			if (board[i][j] != board[i][j+dir])
				return 0;
		return 1 + checkRecursiveHor(i, j+dir, dir);

	}
	
	public boolean checkRecursiveVerStart(int i, int j, boolean givePoints) {
		boolean hasMatch = false;
		int start, end;
		start = end = 0;
		//System.out.println("I'm started at: " + i + " " + j);
		start = checkRecursiveVer(i,j,-1);
		end = checkRecursiveVer(i,j,1);
		//if (i == 3)
		//System.out.println("start end: " + start + end);
		if (end + start >=2)
			for (int k=i - start; k<=i + end; k++) {
				if (givePoints) {
					points += board[k][j] + 3;
				}
				board[k][j] = -1;
				hasMatch = true;
		}	
		if (hasMatch) {
			MainApp.logger.warn(Integer.toString(i) + " " + Integer.toString(j));
			this.moves--;
		}
		return hasMatch;
	}
	
	public int checkRecursiveVer(int i, int j, int dir) {
		//int debugValue = 0;
			if (i+dir<0 || i+dir>7)
				return 0;
			if (board[i][j] == -1 || board[i+dir][j] == -1)
				return 0;
			if (board[i][j] != board[i+dir][j])
				return 0;
		return 1 + checkRecursiveVer(i+dir, j, dir);

	}
}
