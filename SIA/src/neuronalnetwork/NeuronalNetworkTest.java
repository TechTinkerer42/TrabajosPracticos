package neuronalnetwork;

import java.util.Arrays;

import neuronalnetwork.function.SgFunction;
import neuronalnetwork.function.TransferFunction;

public class NeuronalNetworkTest {

	public static void main(String[] args) {
		NeuralNetwork net = new NeuralNetwork(new int[] {3});
		float[] input = new float[] {5, 1, -2};
		TransferFunction f = new SgFunction();
		float[] output = net.evaluate(input, f);
		System.out.println(Arrays.toString(output));
	}
}
