package hu.unideb.inf.rydergaming.matchthree.model;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A class representing the player's board, moves and score.
 * @author Ryder
 *
 */

public class Board {
	/**
	 * Logger variable.
	 */
	final static Logger logger = LoggerFactory.getLogger(Board.class);
	
	/**
	 * A 2D array containing the values.
	 */
	private int[][] board = new int[8][8];
	
	/**
	 * A 2D array containing the y coordinates of the values.
	 */
	public int[][] offset = new int[8][8];
	
	/**
	 * Variable containing the total moves of the player.
	 */
	private int moves = 20;

	/**
	 * The points of the player.
	 */
	private int points = 0;
	
	/**
	 * Returns the remaining moves of the player.
	 * @return int the remaining moves
	 */
	public int getMoves() {
		return moves;
	}

	/**
	 * Sets the points of the player.
	 * @param moves
	 */
	public void setMoves(int moves) {
		this.moves = moves;
	}
	/**
	 * Return the current points of the player.
	 * @return int the current points
	 */

	public int getPoints() {
		return points;
	}

	/**
	 * A Random variable so that it can be seeded.
	 */
	private Random rnd = new Random();	
	
	/**
	 * Constructor of Board class.
	 */
	public Board() {

		rnd.setSeed(19950622);
		for (int i=0; i<8; i++)
			for (int j=0; j<8; j++){
				board[i][j] = rnd.nextInt(7);
				offset[i][j] = -1000;
			}

		//checkBoard(false);
		
		for (int i=0;i<8; i++)
			for (int j=0; j<8; j++) {
				checkRecursiveHorStart(i, j, false);
				checkRecursiveVerStart(i, j, false);
			}	
		fallBoard();
		for (int i=0; i<8 ;i++)
			for (int j=0;j<8;j++) {
				offset[i][j] = i*46;
			}
		
	}
	
	/**
	 * Return the current values in the board.
	 * @return String A string representing the board.
	 */	
	public String getBoard() {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<8; i++) {
			for (int j=0; j<8; j++)
				sb.append(Integer.toString(board[i][j])).append(" ");
				sb.append("\n");
		}
		return sb.toString();
	}

	/**
	 * Return the current y coordinate values in the board.
	 * @return String A string representing the y coordinates.
	 */
	public String getOffset() {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<8; i++) {
			for (int j=0; j<8; j++)
				sb.append(Double.toString(offset[i][j])).append(" ");
				sb.append("\n");
		}
		return sb.toString();
	}
	
	/**
	 * Returns the selected element from the board.
	 * @param row index of the board array.
	 * @param column index of the board array.
	 * @return int the selected element's value from the board.
	 */
	public int getElement(int row, int column){
		return board[row][column];		
	}
	
	/**
	 * Moves elements downwards and fills the board.
	 */
	public void fallBoard() {
		int tmp = 8;
		while (tmp>0) {	
			for (int i=7; i>=1;i--)
				for (int j=0; j<=7;j++) {
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

		for (int i=0; i<8; i++)
			for (int j=0; j<8; j++)
				if (board[i][j] == -1)	{
					board[i][j] = rnd.nextInt(7);
					offset[i][j] = -46;
				}
					
	}

	/**
	 * Switches the position of two elements on the board.
	 * @param aX row index of the first element.
	 * @param aY column index of the first element.
	 * @param bX row index of the second element.
	 * @param bY column index of the second element.
	 */
	public void switchPositions(int aX, int aY, int bX, int bY) {
		if (aX != bX && aY != bY)
			return;
		int c = board[aX][aY];
			board[aX][aY] = board[bX][bY];
			board[bX][bY] = c;
	}
	
	/**
	 * Checks horizontally if there's a match at the given parameters.
	 * @param i int row index of starting element. 
	 * @param j int column index inf the starting element.
	 * @param givePoints boolean whether give points or not. 
	 * @return boolean returns true if there was a match.
	 */
	public boolean checkRecursiveHorStart(int i, int j, boolean givePoints) {
		boolean hasMatch = false;
		int start, end;
		start = end = 0;
		start = checkRecursiveHor(i,j,-1);
		end = checkRecursiveHor(i,j,1);
		
		if (end + start>=2)
			for (int k=j - start; k<=j + end; k++) {
				if (givePoints) {
					points += board[i][k] + 3;
				}
				board[i][k] = -1;
				hasMatch = true;
		}	
		if (hasMatch) {
			if (givePoints)
				logger.info("Found horizontal match at " + i + " " + j);
		}
		return hasMatch;
	}

	/**
	 * Checks how far there are matches in a given direction.
	 * @param i int starting row index.
	 * @param j int starting column index.
	 * @param dir int direction of the checking. 1 checks left, -1 checks right.
	 * @return int returns 1 if the neighbor node is the same.
	 */
	public int checkRecursiveHor(int i, int j, int dir) {
			if (j+dir<0 || j+dir>7)
				return 0;
			if (board[i][j] == -1 || board[i][j+dir] == -1)
				return 0;
			if (board[i][j] != board[i][j+dir])
				return 0;
		return 1 + checkRecursiveHor(i, j+dir, dir);

	}
	
	/**
	 * Checks vertically if there's a match at the given parameters.
	 * @param i int row index of starting element. 
	 * @param j int column index inf the starting element.
	 * @param givePoints boolean whether give points or not. 
	 * @return boolean returns true if there was a match.
	 */	
	public boolean checkRecursiveVerStart(int i, int j, boolean givePoints) {
		boolean hasMatch = false;
		int start, end;
		start = end = 0;
		start = checkRecursiveVer(i,j,-1);
		end = checkRecursiveVer(i,j,1);

		if (end + start >=2)
			for (int k=i - start; k<=i + end; k++) {
				if (givePoints) {
					points += board[k][j] + 3;
				}
				board[k][j] = -1;
				hasMatch = true;
		}	
		if (hasMatch) {
			if (givePoints)
				logger.info("Found vertical match at " + i + " " + j);			
		}
		return hasMatch;
	}

	/**
	 * Checks how far there are matches in a given direction.
	 * @param i int starting row index.
	 * @param j int starting column index.
	 * @param dir int the direction of the checking. 1 checks down, -1 checks up.
	 * @return int returns 1 if the neighbor node is the same. 
	 */
	public int checkRecursiveVer(int i, int j, int dir) {
			if (i+dir<0 || i+dir>7)
				return 0;
			if (board[i][j] == -1 || board[i+dir][j] == -1)
				return 0;
			if (board[i][j] != board[i+dir][j])
				return 0;
		return 1 + checkRecursiveVer(i+dir, j, dir);

	}
}
