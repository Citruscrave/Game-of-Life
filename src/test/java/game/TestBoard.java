package game;

import game.Board;
import org.junit.*;
import static org.junit.Assert.*;

public class TestBoard {

	Board board;

	@Test
	public void canary() {
		assertTrue(true);
	}

	final boolean ALIVE = true;
	final boolean DEAD = false;

	@Test
	public void aLiveCellWithZeroLiveNeighborsDies() {
		board = new Board(5, 5);
		int numberOfLiveNeighbors = 0;
		assertEquals(DEAD, board.isAlive(ALIVE, numberOfLiveNeighbors));
	}

	@Test
	public void aLiveCellWithOneLiveNeighborsDies() {
		board = new Board(5, 5);
		int numberOfLiveNeighbors = 1;
		assertEquals(DEAD, board.isAlive(ALIVE, numberOfLiveNeighbors));
	}

	@Test
	public void aLiveCellWithTwoLiveNeighborsLives() {
		board = new Board(5, 5);
		int numberOfLiveNeighbors = 2;
		assertEquals(ALIVE, board.isAlive(ALIVE, numberOfLiveNeighbors));
	}

	@Test
	public void aLiveCellWithThreeLiveNeighborsLives() {
		board = new Board(5, 5);
		int numberOfLiveNeighbors = 3;
		assertEquals(ALIVE, board.isAlive(ALIVE, numberOfLiveNeighbors));
	}

	@Test
	public void aLiveCellWithFourOrMoreLiveNeighborsDies() {
		board = new Board(5, 5);
		int numberOfLiveNeighbors = 4;
		assertEquals(DEAD, board.isAlive(ALIVE, numberOfLiveNeighbors));
	}

	@Test
	public void aDeadCellWithExactlyZeroLiveNeighborsDies() {
		board = new Board(5, 5);
		int numberOfLiveNeighbors = 0;
		assertEquals(DEAD, board.isAlive(DEAD, numberOfLiveNeighbors));
	}

	@Test
	public void aDeadCellWithExactlyOneLiveNeighborsDies() {
		board = new Board(5, 5);
		int numberOfLiveNeighbors = 1;
		assertEquals(DEAD, board.isAlive(DEAD, numberOfLiveNeighbors));
	}

	@Test
	public void aDeadCellWithExactlyTwoLiveNeighborsDies() {
		board = new Board(5, 5);
		int numberOfLiveNeighbors = 2;
		assertEquals(DEAD, board.isAlive(DEAD, numberOfLiveNeighbors));
	}

	@Test
	public void aDeadCellWithExactlyThreeLiveNeighborsLives() {
		board = new Board(5, 5);
		int numberOfLiveNeighbors = 3;
		assertEquals(ALIVE, board.isAlive(DEAD, numberOfLiveNeighbors));
	}

	@Test
	public void aDeadCellWithExactlyFourLiveNeighborsDies() {
		board = new Board(5, 5);
		int numberOfLiveNeighbors = 4;
		assertEquals(DEAD, board.isAlive(DEAD, numberOfLiveNeighbors));
	}

	@Test
	public void checkForNoLiveCellsAroundTheDeadCenterCell() {
		board = new Board(3, 3);
		assertEquals(0, board.countNumberOfLiveNeighbors(1, 1));
	}

	@Test
	public void checkForExactlyOneLiveCellAroundTheDeadCenterCell() {
		board = new Board(3, 3);
		board.changeState(0, 1);
		assertEquals(1, board.countNumberOfLiveNeighbors(1, 1));
	}

	@Test
	public void checkForExactlyTwoLiveCellsAroundTheDeadCenterCell() {
		board = new Board(3, 3);
		board.changeState(0, 1);
		board.changeState(1, 0);
		assertEquals(2, board.countNumberOfLiveNeighbors(1, 1));
	}

	@Test
	public void checkForNoLiveCellsAroundTheAliveCenterCell() {
		board = new Board(3, 3);
		board.changeState(1, 1);
		assertEquals(0, board.countNumberOfLiveNeighbors(1, 1));
	}

	@Test
	public void checkForNoLiveCellsAroundTheAliveTopLeftCell() {
		board = new Board(3, 3);
		board.changeState(0, 0);
		assertEquals(0, board.countNumberOfLiveNeighbors(0, 0));
	}

	@Test
	public void checkForNoLiveCellsAroundTheAliveTopRightCell() {
		board = new Board(3, 3);
		board.changeState(0, 2);
		assertEquals(0, board.countNumberOfLiveNeighbors(0, 2));
	}

	@Test
	public void checkForNoLiveCellsAroundTheAliveBottomLeftCell() {
		board = new Board(3, 3);
		board.changeState(2, 0);
		assertEquals(0, board.countNumberOfLiveNeighbors(2, 0));
	}

	@Test
	public void checkForNoLiveCellsAroundTheAliveBottomRightCell() {
		board = new Board(3, 3);
		board.changeState(2, 2);
		assertEquals(0, board.countNumberOfLiveNeighbors(2, 2));
	}
	
	@Test
	public void accessCellOutOfRowRange(){
		board = new Board(3,3);
		assertFalse(board.cellInBounds(5, 0));
	}
	
	@Test
	public void accessCellOutOfColumnRange(){
		board = new Board(3,3);
		assertFalse(board.cellInBounds(0, 5));
	}

	@Test
	public void changeCellStateOutOfRowRange() {
		board = new Board(3, 3);
		try {
			board.changeState(5, 2);
			fail("Expected exception for stepping out of row bounds");
		} catch (IndexOutOfBoundsException e) {
			// :)
		}
	}
	
	@Test
	public void changeCellStateOutOfColumnRange() {
		board = new Board(3, 3);
		try {
			board.changeState(2, 5);
			fail("Expected exception for stepping out of row bounds");
		} catch (IndexOutOfBoundsException e) {
			// :)
		}
	}
}