package neuronalnetwork;

import java.util.List;

public class MSE {

	public static float calc(NeuralNetwork net, List<TrainItem> testExamples) {
		float mse = 0;
		for (TrainItem test : testExamples) {
			mse += calc(net, test.input, test.output);
		}
		return mse / (2 * testExamples.size());
	}

	public static float calc(NeuralNetwork net, float[] input, float[] expectedOutput) {
		float[] outout = net.evaluate(input);
		float mse = 0;
		for (int i = 0; i < outout.length; i++) {
			float diff = expectedOutput[i] - outout[i];
			mse += diff * diff;
		}
		return mse;
	}
}
