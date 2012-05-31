package neuronalnetwork;

import neuronalnetwork.function.TransferFunction;

public class Layer {
	
	private float[][] weights;
	private int input; 
	private int output;
	
	public Layer(int input, int output) {
		// input + 1 because of the Bias input for each neuron
		weights = new float[input + 1][output];
		this.input = input;
		this.output = output;
		initWeightMantrix();
	}
	
	private void initWeightMantrix() {
		for (int i = 0; i < weights.length; i++) {
			for (int j = 0; j < weights[0].length; j++) {
				weights[i][j] = MathUtils.random(-0.5f, 0.5f);
			}
		}
	}

	public float[][] getWeights() {
		return weights;
	}
	
	public int getInput() {
		return input;
	}
	
	public int getOutput() {
		return output;
	}
	
	public float[] evaluate(float[] in, TransferFunction f) {
		validateInputDimention(in.length);
		float[] out = new float[output];
		for (int i = 0; i < output; i++) {
			float sum = 0;
			for (int j = 0; j < input; j++) {
				sum += weights[j][i] * in[j];
			}
			sum -= weights[input][i];	// Bias
			out[i] = f.valueAt(sum);
		}
		return out;
	}
	
	private void validateInputDimention(int dim) {
		if (dim != input) {
			throw new IllegalArgumentException("Invalid input dimention given: " 
				+ dim + ". Should be " + input);
		}
	}

}
