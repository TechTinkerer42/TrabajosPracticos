package tictactoe;

public class Movement {

	public int row;
	public int column;
	
	public Movement() {
	}
	
	public Movement(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	@Override
	public String toString() {
		return "[" + row + ", " + column + "]";
	}
}
