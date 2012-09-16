package testdrive;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import neuronalnetwork.TrainItem;

public class TestDrive {

	public static void main(String[] args) {
		TestDrive test = new TestDrive();
		test.start();
	}
	
	public void start() {
		MapParser parser = new MapParser();
		try {
			parser.parse("./res/easy2.map");
			List<TrainItem> training = getPoints(parser);
			IntelligentCar car = new IntelligentCar(parser.getMap());
			car.memorize(training);
			car.setPosition(1, 1);	
			simulate(parser.getMap(), car);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void simulate(Map map, IntelligentCar car) {
		ConsoleDisplay display = new ConsoleDisplay(map.getRows(), map.getColumns());
		int totalUpdates = 100;
		int updates = 0;
		int elapsedTime = 1;
		while (updates++ < totalUpdates) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			display.draw(map);
			display.draw(car);
			System.out.println(display);
			car.update(elapsedTime);
		}
	}
	
	private List<TrainItem> getPoints(MapParser parser) {
		Point[] points = parser.getPoints();
		List<TrainItem> training = new LinkedList<TrainItem>();
		Map map = parser.getMap();
		float maxRows = map.getRows();
		float maxColumns = map.getColumns();
		for (int i = 0; i < points.length - 1; i++) {
			Point from = points[i];
			float[] input = new float[] {from.x / maxRows, from.y / maxColumns};
			Point to = points[i + 1];
			float dx = to.y - from.y;
			float dy = to.x - from.x;
			// noramlize
			float mod = (float) Math.sqrt(dx * dx + dy * dy);
			dx /= mod;
			dy /= mod;
			float[] output = new float[] {dx , dy};
			training.add(new TrainItem(input, output));	
		}
		return training;
	}

}
