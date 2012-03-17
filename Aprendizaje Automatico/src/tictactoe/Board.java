package tictactoe;

public class Board {
	public final static int SIZE = 3;

	private Mark[][] marks;
	private int completed;
	
	public Board() {
		completed = 0;
		marks = new Mark[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				marks[i][j] = Mark.NONE;
			}
		}
	}
	
	public Mark[][] getMarks() {
		return marks;
	}
	
	public void set(Mark mark, int row, int col) {
		completed++;
		marks[row][col] = mark;
	}
	
	public Mark get(int row, int col) {
		return marks[row][col];
	}
	
	public boolean isSet(int row, int col) {
		if (marks[row][col] == Mark.NONE) {
			return false;
		}
		return true;
	}
	
	public boolean isComplete() {
		return completed == SIZE * SIZE;
	}
}
