package neuronalnetwork;

import neuronalnetwork.function.TransferFunction;

public class NeuronalNetwork {

	private Layer[] layers;
	
	public NeuronalNetwork(int[] structure) {
		layers = new Layer[structure.length];
		for (int i = 0; i < structure.length; i++) {
			int neurons = structure[i];
			int connectionPerNeurons;
			if (i == structure.length - 1) {
				connectionPerNeurons = structure[i];
			} else {
				connectionPerNeurons = structure[i + 1];
			}
			layers[i] = new Layer(neurons, connectionPerNeurons);
		}
	}

	public float[] evaluate(float[] input, TransferFunction f) {
		float[] aux = input;
		for(Layer l: layers) {
			aux = l.evaluate(aux, f);
		}
		return aux;
	}
}
