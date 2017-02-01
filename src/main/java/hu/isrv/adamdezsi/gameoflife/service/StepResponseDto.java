package hu.isrv.adamdezsi.gameoflife.service;

/**
 * Created by dezsiadam on 2/1/17.
 */
public class StepResponseDto{

	private boolean[][] board;

	public StepResponseDto(final boolean[][] board){
		this.board = board;
	}

	public boolean[][] getBoard(){
		return board;
	}

	public void setBoard(final boolean[][] board){
		this.board = board;
	}

}
