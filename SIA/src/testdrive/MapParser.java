package testdrive;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import testdrive.Map.TileType;

public class MapParser {

	private final static char WALL = 'X';
	private final static char ROAD = '.';
	
	private Map map;
	private Point[] points;
	
	public MapParser() {
	}
	
	public boolean parse(String file) throws FileNotFoundException {
		points = new Point[0];
		Scanner scanner = new Scanner(new File(file));
		createEmptyMap(scanner);
		boolean validFile = map != null;
		if (validFile) {
			int row = 0;
			while (scanner.hasNext() && validFile) {
				String line = scanner.next();
				for (int column = 0; column < line.length() && validFile; column++) {
					setChar(line.charAt(column), row, column);
				}
				row++;
			}
		}
		scanner.close();
		return validFile;
	}
	
	public Map getMap() {
		return map;
	}
	
	public Point[] getPoints() {
		return points;
	}
	
	private void createEmptyMap(Scanner scanner) {
		map = null;
		if (scanner.hasNext()) {
			String line = scanner.next();
			String[] dims = line.split(",");
			int rows = Integer.parseInt(dims[0].trim());
			int columns = Integer.parseInt(dims[1].trim());
			map = new Map(rows, columns);
		}
	}
	
	private void setChar(char c, int row, int column) {
		switch(c) {
			case WALL:
				map.set(row, column, TileType.WALL);
				break;
			case ROAD:
				map.set(row, column, TileType.ROAD);
				break;
			default:
				int pointIndex = Integer.parseInt(c + "");
				ensureCapacity(pointIndex + 1);
				points[pointIndex] = new Point(row, column);
				map.set(row, column, TileType.ROAD);
		}
	}
	
	private void ensureCapacity(int capacity) {
		if (points.length < capacity) {
			Point[] newPoints = new Point[capacity];
			System.arraycopy(points, 0, newPoints, 0, points.length);
			points = newPoints;
		}
	}
}
