package neuronalnetwork;

public class NeuronalNetwork {

	private Layer[] layers;
	
	public NeuronalNetwork(int[] neuronsPerLayer) {
		layers = new Layer[neuronsPerLayer.length];
		for (int i = 0; i < neuronsPerLayer.length; i++) {
			layers[i] = new Layer(neuronsPerLayer[i]);
		}
		interConnectLayers();
	}

	private void interConnectLayers() {
		layers[0].createInputConnections();
		for (int i = 1; i < layers.length; i++) {
			layers[i].createConnections(layers[i - 1]);
		}
	}
	
	public float[] evaluate(float[] input) {
		float[] output = layers[0].evalInput(input);
		for (Layer l: layers) {
			// output from layer i is the input of layer i + 1
			output = l.evalInput(output);
		}
		return output;
	}
}
