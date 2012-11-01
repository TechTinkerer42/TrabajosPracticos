package aprendizajeautomatico.tictactoe.ai;

public class Position {

	public int row;
	public int column;
	
	public Position() {
	}
	
	public Position(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	@Override
	public String toString() {
		return "[" + row + ", " + column + "]";
	}
}
