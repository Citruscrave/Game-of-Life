package game;

public class Board {

	private boolean[][] cells;

	public Board(int rows, int cols) {
		cells = new boolean[rows][cols];
	}

	public boolean changeState(int row, int col) {
		if (cellInBounds(row, col)) {
			cells[row][col] = !cells[row][col];
			return cells[row][col];
		}
		throw new IndexOutOfBoundsException();
	}

	public boolean getStateAt(int row, int col) {
		return cells[row][col];
	}

	public boolean isAlive(boolean alive, int numLiveNeighbors) {
		if (!alive) {
			if (numLiveNeighbors == 3)
				return true;
		}
		if (alive) {
			if (numLiveNeighbors > 1 && numLiveNeighbors < 4)
				return true;
		}
		return false;
	}

	public int countNumberOfLiveNeighbors(int row, int column) {
		int totalLive = 0;
		int[][] neighbors = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 },
				{ 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };
		for (int[] neighbor : neighbors) {
			if (cellInBounds(row + neighbor[0], column + neighbor[1]) && cells[row + neighbor[0]][column + neighbor[1]])
				totalLive++;
		}
		return totalLive;
	}

	public boolean cellInBounds(int row, int column) {
		return (row >=0 && row < cells.length && column >=0 && column < cells[0].length);
	}

	public void update() {
		boolean[][] tempBoard = new boolean[cells.length][cells[0].length];
		for (int r = 0; r < tempBoard.length; r++) {
			for (int c = 0; c < tempBoard[0].length; c++) {
				tempBoard[r][c] = isAlive(cells[r][c],
						countNumberOfLiveNeighbors(r, c));
			}
		}

		for (int r = 0; r < tempBoard.length; r++) {
			for (int c = 0; c < tempBoard[0].length; c++) {
				cells[r][c] = tempBoard[r][c];
			}
		}
	}
}