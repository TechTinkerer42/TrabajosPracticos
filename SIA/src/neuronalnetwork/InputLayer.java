package neuronalnetwork;

public class InputLayer {
	
	private float[] input;
	private Connection[] connections;
	
	public InputLayer(int inputSize) {
		input = new float[inputSize];
	}
	
	public void createConnection(Layer layer) {
		Neuron[] to = layer.getNeurons();
		connections = new Connection[layer.getNumOfNeurons()];
		for (int i = 0; i < connections.length; i++) {
			connections[i] = new Connection(to[i]);
		}
	}
}
