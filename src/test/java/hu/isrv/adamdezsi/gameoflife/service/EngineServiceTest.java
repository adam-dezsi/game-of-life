package hu.isrv.adamdezsi.gameoflife.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by dezsiadam on 2/1/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EngineServiceTest{

	@Autowired
	private EngineService engineService;

	@Test
	public void countNeighbours_1x1(){
		boolean[][] board = {{true}};

		int numOfNeighbours = engineService.getNumberOfNeighbours(board, 0, 0);

		assertEquals(0, numOfNeighbours);
	}

	@Test
	public void countNeighbours_2x2_01(){
		boolean[][] board = {{true, false},{false, false}};

		int numOfNeighbours = engineService.getNumberOfNeighbours(board, 0, 0);

		assertEquals(0, numOfNeighbours);
	}

	@Test
	public void countNeighbours_2x2_02(){
		boolean[][] board = {{true, false},{false, false}};

		int numOfNeighbours = engineService.getNumberOfNeighbours(board, 1, 0);

		assertEquals(1, numOfNeighbours);
	}

	@Test
	public void countNeighbours_2x2_03(){
		boolean[][] board = {{true, false},{false, false}};

		int numOfNeighbours = engineService.getNumberOfNeighbours(board, 0, 1);

		assertEquals(1, numOfNeighbours);
	}

	@Test
	public void countNeighbours_2x2_04(){
		boolean[][] board = {{true, false},{false, false}};

		int numOfNeighbours = engineService.getNumberOfNeighbours(board, 1, 1);

		assertEquals(1, numOfNeighbours);
	}

	@Test
	public void countNeighbours_3x3_01(){
		boolean[][] board = {{false, false, false},{false, false, false},{false, false, true}};

		int numOfNeighbours = engineService.getNumberOfNeighbours(board, 2, 2);

		assertEquals(0, numOfNeighbours);
	}

	@Test
	public void countNeighbours_3x3_02(){
		boolean[][] board = {{false, false, false},{false, false, false},{false, false, true}};

		int numOfNeighbours = engineService.getNumberOfNeighbours(board, 1, 1);

		assertEquals(1, numOfNeighbours);
	}

	@Test
	public void countNeighbours_3x3_03(){
		boolean[][] board = {{false, false, false},{false, false, false},{false, false, true}};

		int numOfNeighbours = engineService.getNumberOfNeighbours(board, 2, 1);

		assertEquals(1, numOfNeighbours);
	}

	@Test
	public void countNeighbours_3x3_04(){
		boolean[][] board = {{false, false, false},{false, false, false},{false, false, true}};

		int numOfNeighbours = engineService.getNumberOfNeighbours(board, 1, 2);

		assertEquals(1, numOfNeighbours);
	}

	@Test
	public void countNeighbours_3x3_05(){
		boolean[][] board = {{true, true, true},{true, true, true},{true, true, true}};

		int numOfNeighbours = engineService.getNumberOfNeighbours(board, 1, 1);

		assertEquals(8, numOfNeighbours);
	}

	@Test
	public void stepStillLife_block(){
		boolean[][] board = {{false, false, false, false},{false, true, true, false},{false, true, true, false},{false, false, false, false}};

		boolean[][] nextBoard = engineService.step(board);

		assertTrue(Arrays.deepEquals(board, nextBoard));
	}

	@Test
	public void stepStillLife_beeHive(){
		boolean[][] board = {
				{false, false, false, false, false},
				{false, false, true, false, false},
				{false, true, false, true, false},
				{false, true, false, true, false},
				{false, false, true, false, false},
				{false, false, false, false, false}};

		printBoard(board);
		boolean[][] nextBoard = engineService.step(board);
		printBoard(nextBoard);

		assertTrue(Arrays.deepEquals(board, nextBoard));
	}

	@Test
	public void stepOscillators_toad_01(){
		boolean[][] board = {
				{false, false, false, false, false, false},
				{false, false, true, true, false, false},
				{false, false, false, false, true, false},
				{false, true, false, false, false, false},
				{false, false, true, true, false, false},
				{false, false, false, false, false, false}};

		printBoard(board);
		boolean[][] nextBoard = engineService.step(board);
		printBoard(nextBoard);

		boolean[][] expectedBoard = {
				{false, false, false, false, false, false},
				{false, false, false, true, false, false},
				{false, false, true, true, false, false},
				{false, false, true, true, false, false},
				{false, false, true, false, false, false},
				{false, false, false, false, false, false}};

		assertTrue(Arrays.deepEquals(expectedBoard, nextBoard));
	}

	@Test
	public void stepOscillators_toad_02(){
		boolean[][] board = {
				{false, false, false, false, false, false},
				{false, false, true, true, false, false},
				{false, false, false, false, true, false},
				{false, true, false, false, false, false},
				{false, false, true, true, false, false},
				{false, false, false, false, false, false}};

		printBoard(board);
		boolean[][] nextBoard = engineService.step(board);
		boolean[][] nextNextBoard = engineService.step(nextBoard);
		printBoard(nextNextBoard);

		assertTrue(Arrays.deepEquals(board, nextNextBoard));
	}

	private void printBoard(boolean[][] board){
		System.out.println();
		for (int y = 0; y < board[0].length; y++){
			StringBuilder builder = new StringBuilder();
			for (int x = 0; x < board.length; x++){
				builder.append(board[x][y] ? "O" : "-");
			}
			System.out.println(builder.toString());
		}
	}
}
