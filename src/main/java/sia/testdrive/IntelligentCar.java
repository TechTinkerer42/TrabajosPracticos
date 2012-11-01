package sia.testdrive;

import java.util.List;

import sia.neuronalnetwork.BackPropagation;
import sia.neuronalnetwork.MSE;
import sia.neuronalnetwork.NeuralNetwork;
import sia.neuronalnetwork.TrainItem;
import sia.neuronalnetwork.function.TanhFunction;


public class IntelligentCar {

	private NeuralNetwork net;
	
	private Map map;
	private float row, column;
	private float dRow, dColumn;
	
	public IntelligentCar(Map map) {
		net = new NeuralNetwork(new int[] {2, 10, 5, 2});
		net.setTransferFunction(new TanhFunction(0.5f));
		this.map = map;
	}
	
	public void setPosition(float row, float column) {
		this.row = row;
		this.column = column;
	}
	
	public void memorize(List<TrainItem> items) {
		float eta = 0.3f;
		BackPropagation backProp = new BackPropagation(eta);
		backProp.train(net, items, 2500);
		float mse = MSE.calc(net, items);
		System.out.println("Error: " + mse);
	}
	
	public void update(int elapsedTime) {
		int maxRows = map.getRows();
		int maxColumns = map.getColumns();
		float[] out = net.evaluate(new float[] {row / maxRows, column / maxColumns});
		dColumn = out[0];
		dRow = out[1];
		float newRow = row + elapsedTime * dRow;
		float newColumn = column + elapsedTime * dColumn;
		setPosition(newRow, newColumn);
	}
	
	public float getDRow() {
		return dRow;
	}
	
	public float getDColumn() {
		return dColumn;
	}
	
	public float getRow() {
		return row;
	}
	
	public float getColumn() {
		return column;
	}
}
