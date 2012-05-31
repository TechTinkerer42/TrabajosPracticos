package neuronalnetwork;

import java.util.Map;
import java.util.Map.Entry;

import neuronalnetwork.function.TransferFunction;

public class BackPropagation {

	private NeuralNetwork net;
	private TransferFunction f;
	private float eta;
	
	public BackPropagation(TransferFunction f, float eta) {
		this.f = f;
		this.eta = eta;
	}
	
	public void train(NeuralNetwork net, Map<float[], float[]> train) {
		this.net = net;
		for (Entry<float[], float[]> entryTrain: train.entrySet()) {
			float[] input = entryTrain.getKey();
			float[] expectedOutput = entryTrain.getValue();
			train(input, expectedOutput);
		}
	}
	
	private void train(float[] input, float[] expectedOutput) {
		float[] output = net.evaluate(input, f);
		float[][] deltas = new float[net.getTotalLayers()][];
		int k = net.getTotalLayers() - 1;
		Layer l = net.getLayer(k);
		deltas[k] = getLastLayerDelta(l, output, expectedOutput);
		for (int j = k - 1; j > 0; j--) {
			deltas[j] = getLayerDelta(j, output, expectedOutput);
		}
	}
	
	private float[] getLastLayerDelta(Layer last, float[] output, float[] expectedOutput) {
		float[] delta = new float[last.getNeurons()];
		float[] lastInput = last.getLastInput();
		for (int i = 0; i < delta.length; i++) {
			delta[i] = (expectedOutput[i] - output[i]) * f.valueAtDerivated(lastInput[i]);
		}
		return delta;
	}
	
	private float[] getLayerDelta(int layerIndex, float[] output, float[] expectedOutput) {
		Layer layer = net.getLayer(layerIndex);
		float[] delta = new float[layer.getNeurons()];
		float[] lastInput = layer.getLastInput();
		for (int i = 0; i < delta.length; i++) {
			delta[i] = f.valueAtDerivated(lastInput[i]);
			// por cada j en layerINdex + 1 hasta n....
		}
		return delta;
	}
	

	private void validateDimation(float[] v1, float[] v2) {
		if (v1.length != v2.length) {
			throw new IllegalStateException("Vector dimation does not match: " + 
				v1.length + " != " + v2.length);
		}
	}
}
