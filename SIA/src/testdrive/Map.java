package testdrive;

public class Map {

	public enum TileType {WALL, ROAD};
	
	private TileType[][] tiles;
	
	public Map(int rows, int columns) {
		tiles = new TileType[rows][columns];
	}
	
	public int getRows() {
		return tiles.length;
	}
	
	public int getColumns() {
		return tiles[0].length;
	}
	
	public void set(int row, int column, TileType t) {
		tiles[row][column] = t;
	}
	
	public TileType get(int row, int column) {
		return tiles[row][column];
	}
}
