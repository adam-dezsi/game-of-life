package hu.isrv.adamdezsi.gameoflife.service;

import org.springframework.stereotype.Service;

/**
 * Created by dezsiadam on 2/1/17.
 */
@Service
public class EngineService{

	/**
	 * 0;0 is at TOP LEFT corner
	 *
	 * .---> X
	 * |
	 * |
	 * |
	 * V
	 *
	 * Y
	 *
	 * @param board
	 * @return
	 */
	public boolean[][] step(boolean[][] board){
		if (board == null || board.length == 0 || board[0] == null){
			throw new IllegalArgumentException();
		}
		int height = board[0].length;
		for (boolean[] column : board){
			if (column.length != height){
				throw new IllegalArgumentException();
			}
		}

		boolean[][] nextBoard = new boolean[board.length][board[0].length];
		for (int y = 0; y < board[0].length; y++){
			for (int x = 0; x < board.length; x++){
				int numOfNeighbours = getNumberOfNeighbours(board, x, y);
				boolean nextLife = lifeRule(board[x][y], numOfNeighbours);
				nextBoard[x][y] = nextLife;
			}
		}
		return nextBoard;
	}

	public int getNumberOfNeighbours(boolean[][] board, int posX, int posY){
		int numOfNeighbours = 0;
		for (int y = Math.max(posY-1, 0); y <= Math.min(posY+1, board[0].length-1); y++){
			for (int x = Math.max(posX-1, 0); x <= Math.min(posX+1, board.length-1); x++){
				if (board[x][y] && !(x==posX && y==posY)){
					numOfNeighbours++;
				}
			}
		}
		return numOfNeighbours;
	}

	private boolean lifeRule(boolean isAlive, int numOfNeighbours){
		if (isAlive){
			if (numOfNeighbours < 2 || numOfNeighbours > 3){
				return false;
			} else {
				return isAlive;
			}
		} else {
			if (numOfNeighbours == 3){
				return true;
			} else {
				return false;
			}
		}
	}

}
