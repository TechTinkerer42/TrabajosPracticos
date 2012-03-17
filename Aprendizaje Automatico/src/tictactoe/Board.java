package tictactoe;

public class Board implements Cloneable {
	public final static int SIZE = 3;

	private Mark[][] marks;
	private int completed;
	
	public Board() {
		this(null);
		clear();
	}
	
	public Board(Board b) {
		marks = new Mark[SIZE][SIZE];
		if (b != null) {
			setTo(b);
		}
	}
	
	public void clear() {
		completed = 0;
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
		if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
			return null;
		}
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
	
	public void setTo(Board b) {
		this.completed = b.completed;
		for (int i = 0; i < b.marks.length; i++) {
			for (int j = 0; j < b.marks[0].length; j++) {
				marks[i][j] = b.marks[i][j];
			}
		}
	}

	@Override
	public Board clone() {
		return new Board(this);
	}
	
	@Override
	public String toString() {
		String s = "Completed: " + completed + "\n";
		for (int i = 0; i < marks.length; i++) {
			for (int j = 0; j < marks[0].length; j++) {
				String mark = (marks[i][j] == Mark.O) ? "0" : (marks[i][j] == Mark.X ? "X" : "-"); 
				s += mark + " ";
			}
			s += "\n";
		}
		return s;
	}
}
