package hu.unideb.inf.rydergaming.matchthree;

import java.util.*;
import org.slf4j.*;

public class Board {
	private int[][] board = new int[8][8];
	public double[][] offset = new double[8][8];
	public int cellheight = 0;
	private int moves = 20;

	public int getMoves() {
		return moves;
	}

	public void setMoves(int moves) {
		this.moves = moves;
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
	
	public void showBoard() {
		
		for (int i=0; i<8; i++) {
			for (int j=0; j<8; j++)
				System.out.print(Integer.toString(board[i][j]) + " ");
				System.out.println();
		}
	}

	public void showOffset() {
		
		for (int i=0; i<8; i++) {
			for (int j=0; j<8; j++)
				System.out.print(Double.toString(offset[i][j]) + " ");
				System.out.println();
		}
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
							//board[i][j] = new Globe(board[i-1][j].getValue(),i,j);
							board[i][j] = board[i-1][j];
							offset[i][j] = offset[i-1][j];
							board[i-1][j] = -1;							
						}									
					}	
				}
			tmp--;
		}
		
		for (int i=0; i<8; i++)
			for (int j=0; j<8; j++)
				if (board[i][j] == -1)	{
					board[i][j] = rnd.nextInt(6);
					offset[i][j] = -46;
				}
		//showBoard();
		//showOffset();
					
	}
	
	public void checkBoard(boolean givePoints) {
		for (int i=0; i<8; i++) {
			for (int j=0; j<8; j++) {
				checkDirections(i,j,givePoints);
			}
		}
	}
	
	public void switchPositions(Globe a, Globe b) {
		if (a.getRow() == b.getRow() || a.getColumn() == b.getColumn()) {
			Globe c = new Globe(a.getValue(),a.getRow(),a.getColumn());
			
			a.setValue(b.getValue());
			a.setRow(b.getRow());
			a.setColumn(b.getColumn());
			b.setValue(c.getValue());
			b.setRow(c.getRow());
			b.setColumn(c.getColumn());

		}
				
	}
	
	public void switchPositions(int aX, int aY, int bX, int bY) {
		if (aX != bX && aY != bY)
			return;
/*		System.out.println("Switching");
		System.out.println(aX + " " + aY + " " + bX + " " + bY);
		System.out.println(board[aX][aY] + " " + board[bX][bY]);*/
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
		if (i == 6)
			System.out.println(start + " " + end);
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
		}
		return hasMatch;
	}
	
	public int checkRecursiveHor(int i, int j, int dir) {
		int debugValue = 0;
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
		System.out.println("start end: " + start + end);
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
		}
		return hasMatch;
	}
	
	public int checkRecursiveVer(int i, int j, int dir) {
		int debugValue = 0;
			if (i+dir<0 || i+dir>7)
				return 0;
			if (board[i][j] == -1 || board[i+dir][j] == -1)
				return 0;
			if (board[i][j] != board[i+dir][j])
				return 0;
		return 1 + checkRecursiveVer(i+dir, j, dir);

	}
	
	
	
	public boolean checkDirections(int i, int j, boolean givePoints) {

		if (checkVerticalMatchUp(i,j)) {
			if (givePoints) {
				//Magic will happen here;
			}
			board[i][j] = board[i][j-1] = board[i][j-2] = -1;	
			return true;
		}
		if (checkVerticalMatchMid(i,j)) {
			if (givePoints) {
				//magicl
			}
			board[i][j] = -1;
			board[i][j-1] = -1;
			board[i][j+1] = -1;
			return true;
		}
		if (checkVerticalMatchDown(i,j)) {
			if (givePoints) {
				//magic;
			}
			board[i][j] = board[i][j+1] = board[i][j+2] = -1;
			return true;
		}
		
		if (checkHorizontalMatchLeft(i,j)) {
			if (givePoints) {
				//Magic will happen here;
			}
			board[i][j] = board[i-1][j] = board[i-2][j] = -1;	
			return true;
		}
		if (checkHorizontalMatchMid(i,j)) {
			if (givePoints) {
				//magicl
			}
			board[i][j] = board[i-1][j] = board[i+1][j] = -1;
			return true;
		}
		if (checkHorizontalMatchRight(i,j)) {
			if (givePoints) {
				//magic;
			}
			board[i][j] = board[i+1][j] = board[i+2][j] = -1;
			return true;
		}
		return false;
	}
	
	
	public boolean checkVerticalMatchUp(int i, int j) {
		boolean hasMatch = false;
			if ((j-2)<0)
				return hasMatch;
			if (board[i][j] == -1)
				return hasMatch;
			if (board[i][j] == (board[i][j-1]))
				if (board[i][j] == (board[i][j-2]))
					hasMatch = true;
		return hasMatch;
	}
	
	public boolean checkVerticalMatchDown(int i, int j) {
		boolean hasMatch = false;
			if ((j+2)>7)
				return hasMatch;
			if (board[i][j] == -1)
				return hasMatch;
			if (board[i][j] == (board[i][j+1]))
				if (board[i][j]==(board[i][j+2]))
					hasMatch = true;
		return hasMatch;
	}
	
	public boolean checkVerticalMatchMid(int i, int j) {
		boolean hasMatch = false;
			if ((j-1)<0 || (j+1)>7)
				return hasMatch;
			if (board[i][j] == -1)
				return hasMatch;
			if (board[i][j] == (board[i][j-1]))
				if (board[i][j] ==(board[i][j+1]))
					hasMatch = true;
		return hasMatch;
	}
	
	public boolean checkHorizontalMatchLeft(int i, int j) {
		boolean hasMatch = false;
			if ((i-2)<0)
				return hasMatch;
			if (board[i][j] == -1)
				return hasMatch;
			if (board[i][j] == (board[i-1][j]))
				if (board[i][j] == (board[i-2][j]))
					hasMatch = true;
		return hasMatch;
	}
	
	public boolean checkHorizontalMatchRight(int i, int j) {
		boolean hasMatch = false;
			if ((i+2)>7)
				return hasMatch;
			if (board[i][j] == -1)
				return hasMatch;
			if (board[i][j] == (board[i+1][j]))
				if (board[i][j] == (board[i+2][j]))
					hasMatch = true;
		return hasMatch;
	}
	
	public boolean checkHorizontalMatchMid(int i, int j) {
		boolean hasMatch = false;
			if ((i-1)<0 || (i+1)>7)
				return hasMatch;
			if (board[i][j] == -1)
				return hasMatch;
			if (board[i][j] == (board[i-1][j]))
				if (board[i][j] == (board[i+1][j]))
					hasMatch = true;
		return hasMatch;
	}
}
