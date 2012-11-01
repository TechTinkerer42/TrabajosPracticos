package sia.testdrive;

import sia.testdrive.Map.TileType;

public class ConsoleDisplay {

	private char[][] display;
	private int rows, columns;
	
	public ConsoleDisplay(int rows, int columns) {
		display = new char[rows][columns];
		this.rows = rows;
		this.columns = columns;
	}
	
	public void draw(Map map) {
		for (int row = 0; row < rows; row++) {
			char[] displayRow = display[row];
			for (int column = 0; column < columns; column++) {
				TileType tile = map.get(row, column);
				switch (tile) {
				case WALL:
					displayRow[column] = 'X';
					break;
				case ROAD:
					displayRow[column] = ' ';
				}
			}
		}
	}

	public void draw(IntelligentCar car) {
		int row = Math.round(car.getRow());
		int column = Math.round(car.getColumn());
		display[row][column] = '0';
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (char[] row : display) {
			for (char c : row) {
				s.append(c + " ");
			}
			s.append("\n");
		}
		return s.toString();
	}
}
