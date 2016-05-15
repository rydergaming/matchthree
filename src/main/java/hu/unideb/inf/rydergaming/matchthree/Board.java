package hu.unideb.inf.rydergaming.matchthree;

import java.util.*;
import org.slf4j.*;

public class Board {
	private Globe[][] board = new Globe[8][8];

	private int points = 0;
	private Random rnd = new Random();	
	public Board() {

		rnd.setSeed(19950622);
		for (int i=0; i<8; i++)
			for (int j=0; j<8; j++)
				board[i][j] = new Globe(rnd.nextInt(8),i,j);
		checkBoard(false);
		fallBoard();
		showBoard();
		
	}
	
	public void showBoard() {
		
		for (int i=0; i<8; i++) {
			for (int j=0; j<8; j++)
				System.out.print(Integer.toString(board[i][j].getValue()) + " ");
				System.out.println();
		}
	}
	
	public Globe getElement(int row, int column){
		return board[row][column];		
	}
	
	
	public void fallBoard() {
		int tmp = 8;
		while (tmp>0) {	
			for (int i=7; i>=1;i--)
				for (int j=7; j>=0;j--) {
					if (board[i][j] == null) {
						if (i != 0) {
							board[i][j] = board[i-1][j];
							board[i-1][j] = null;							
						}									
					}	
				}
			tmp--;
		}
		
		for (int i=0; i<8; i++)
			for (int j=0; j<8; j++)
				if (board[i][j] == null)
					board[i][j] = new Globe(rnd.nextInt(8),i,j);
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
			Globe c = new Globe(a.getValue(),0,0);
			
			a.setValue(b.getValue());
			b.setValue(c.getValue());

		}
				
	}
	
	public boolean checkDirections(int i, int j, boolean givePoints) {

		if (checkVerticalMatchUp(i,j)) {
			if (givePoints) {
				//Magic will happen here;
			}
			board[i][j] = board[i][j-1] = board[i][j-2] = null;	
			return true;
		}
		if (checkVerticalMatchMid(i,j)) {
			if (givePoints) {
				//magicl
			}
			board[i][j] = null;
			board[i][j-1] = null;
			board[i][j+1] = null;
			return true;
		}
		if (checkVerticalMatchDown(i,j)) {
			if (givePoints) {
				//magic;
			}
			board[i][j] = board[i][j+1] = board[i][j+2] = null;
			return true;
		}
		
		if (checkHorizontalMatchLeft(i,j)) {
			if (givePoints) {
				//Magic will happen here;
			}
			board[i][j] = board[i-1][j] = board[i-2][j] = null;	
			return true;
		}
		if (checkHorizontalMatchMid(i,j)) {
			if (givePoints) {
				//magicl
			}
			board[i][j] = board[i-1][j] = board[i+1][j] = null;
			return true;
		}
		if (checkHorizontalMatchRight(i,j)) {
			if (givePoints) {
				//magic;
			}
			board[i][j] = board[i+1][j] = board[i+2][j] = null;
			return true;
		}
		return false;
	}
	
	
	public boolean checkVerticalMatchUp(int i, int j) {
		boolean hasMatch = false;
			if ((j-2)<0)
				return hasMatch;
			if (board[i][j] == null)
				return hasMatch;
			if (board[i][j].equalsTo(board[i][j-1]))
				if (board[i][j].equalsTo(board[i][j-2]))
					hasMatch = true;
		return hasMatch;
	}
	
	public boolean checkVerticalMatchDown(int i, int j) {
		boolean hasMatch = false;
			if ((j+2)>7)
				return hasMatch;
			if (board[i][j] == null)
				return hasMatch;
			if (board[i][j].equalsTo(board[i][j+1]))
				if (board[i][j].equalsTo(board[i][j+2]))
					hasMatch = true;
		return hasMatch;
	}
	
	public boolean checkVerticalMatchMid(int i, int j) {
		boolean hasMatch = false;
			if ((j-1)<0 || (j+1)>7)
				return hasMatch;
			if (board[i][j] == null)
				return hasMatch;
			if (board[i][j].equalsTo(board[i][j-1]))
				if (board[i][j].equalsTo(board[i][j+1]))
					hasMatch = true;
		return hasMatch;
	}
	
	public boolean checkHorizontalMatchLeft(int i, int j) {
		boolean hasMatch = false;
			if ((i-2)<0)
				return hasMatch;
			if (board[i][j] == null)
				return hasMatch;
			if (board[i][j].equalsTo(board[i-1][j]))
				if (board[i][j].equalsTo(board[i-2][j]))
					hasMatch = true;
		return hasMatch;
	}
	
	public boolean checkHorizontalMatchRight(int i, int j) {
		boolean hasMatch = false;
			if ((i+2)>7)
				return hasMatch;
			if (board[i][j] == null)
				return hasMatch;
			if (board[i][j].equalsTo(board[i+1][j]))
				if (board[i][j].equalsTo(board[i+2][j]))
					hasMatch = true;
		return hasMatch;
	}
	
	public boolean checkHorizontalMatchMid(int i, int j) {
		boolean hasMatch = false;
			if ((i-1)<0 || (i+1)>7)
				return hasMatch;
			if (board[i][j] == null)
				return hasMatch;
			if (board[i][j].equalsTo(board[i-1][j]))
				if (board[i][j].equalsTo(board[i+1][j]))
					hasMatch = true;
		return hasMatch;
	}
}
