package neuronalnetwork;

import neuronalnetwork.function.SigmoidFunction;


public class Layer {
	
	private Neuron[] neurons;
	
	public Layer(int totalNeurons) {
		neurons = new Neuron[totalNeurons];
		for (int i = 0; i < totalNeurons; i++) {
			neurons[i] = new Neuron();
			neurons[i].setTransferFunction(new SigmoidFunction());
		}
	}
	
	public int getNumOfNeurons() {
		return neurons.length;
	}
	
	public Neuron[] getNeurons() {
		return neurons;
	}

	public void createInputConnections() {
		for (Neuron n: neurons) {
			n.createInputConnections(neurons.length);
		}
	}
	
	public void createConnections(Layer layer) {
		for (Neuron n: neurons) {
			n.createInputConnections(layer);
		}
	}

	public float[] evalInput(float[] input) {
		float[] output = new float[input.length];
		int i = 0;
		for(Neuron n: neurons) {
			output[i++] = n.evalInput(input);
		}
		return output;
	}
}
