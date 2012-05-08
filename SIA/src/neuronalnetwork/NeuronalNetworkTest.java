package neuronalnetwork;

import java.util.Arrays;

public class NeuronalNetworkTest {

	public static void main(String[] args) {
		NeuronalNetwork net = new NeuronalNetwork(new int[] {3});
		float[] input = new float[3];
		input[0] = 5;
		input[1] = 1;
		input[2] = -2;
		System.out.println(Arrays.toString(net.evaluate(input)));
	}
}
