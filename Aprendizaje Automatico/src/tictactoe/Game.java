package tictactoe;

public class Game {

	private Board board;
	private boolean gameFinished;
	private Mark winner;
	
	public Game() {
		board = new Board();
	}
	
	public void start() {
		winner = null;
		gameFinished = false;
		board.clear();
	}
	
	public void put(Mark mark, int row, int col) {
		if (gameFinished) {
			throw new IllegalArgumentException("Board is already completed...");
		}
		if (board.isSet(row, col)) {
			throw new IllegalArgumentException("Cell is already set");
		}
		board.set(mark, row, col);
		checkEndOfGame(mark);
	}
	
	public boolean isSet(int row, int col) {
		return board.isSet(row, col);
	}
	
	public boolean isGameFinished() {
		return gameFinished;
	}
	
	public Mark getWinner() {
		return winner;
	}
	
	private void checkEndOfGame(Mark player) {
		gameFinished = (checkRows() != Mark.NONE) || 
			(checkColumns() != Mark.NONE) || (checkDiagonals() != Mark.NONE);
		if (gameFinished) {
			winner = player;
		} else {
			gameFinished = board.isComplete();
			if (gameFinished) {
				winner = Mark.NONE;
			}
		}
	}

	private Mark checkRows() {
		for (Mark[] row: board.getMarks()) {
			if (row[0] != Mark.NONE && row[0] == row[1] && row[1] == row[2]) {
				return row[0]; 
			}
		}
		return Mark.NONE;
	}
	
	private Mark checkColumns() {
		Mark[][] marks = board.getMarks();
		for (int col = 0; col < marks.length; col++) {
			if (marks[0][col] != Mark.NONE && 
					marks[0][col] == marks[1][col] && marks[1][col] == marks[2][col]) {
				return marks[0][col];
			}
		}
		return Mark.NONE;
	}
	
	public Mark checkDiagonals() {
		Mark[][] marks = board.getMarks();
		if (marks[0][0] != Mark.NONE &&
				marks[0][0] == marks[1][1] && marks[1][1] == marks[2][2]) {
			return marks[0][0];
		}
		if (marks[0][2] != Mark.NONE &&
				marks[0][2] == marks[1][1] && marks[1][1] == marks[2][0]) {
			return marks[0][2];
		}
		return Mark.NONE;
	}
	
	public Board getBoard() {
		return board;
	}
}
