package neuronalnetwork;

import neuronalnetwork.function.TransferFunction;

public class Neuron {

	private Connection[] inConnections;
	private float threshold;
	private TransferFunction transferFunction;
	
	public Neuron() {
		threshold = (float) Math.random() - 0.5f;
	}
	
	public void setTransferFunction(TransferFunction transferFunction) {
		this.transferFunction = transferFunction;
	}

	public void createInputConnections(int inputSize) {
		createInputConnections(inputSize, null);
	}
	
	public void createInputConnections(Layer fromLayer) {
		createInputConnections(fromLayer.getNumOfNeurons(), fromLayer.getNeurons());
	}
	
	private void createInputConnections(int inputSize, Neuron[] fromNeurons) {
		inConnections = new Connection[inputSize];
		for (int i = 0; i < inConnections.length; i++) {
			Neuron from = (fromNeurons == null) ? null : fromNeurons[i];
			inConnections[i] = new Connection(from);
		}
	}

	public float evalInput(float[] input) {
		float value = 0;
		int i = 0;
		for (Connection c: inConnections) {
			value += c.weight * input[i++];
		}
		return transferFunction.valueAt(value - threshold);
	}
	
	@Override
	public String toString() {
		String s = "Neuron{\n";
		for(Connection c: inConnections) {
			s += c + "me \n,";
		}
		s += "threshold: " + threshold + "\n";
		return s + "}";
	}
	
}