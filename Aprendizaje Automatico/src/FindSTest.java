import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import finds.FindS;
import finds.Hypothesis;
import finds.implementation.LikesSportHypothesis;

public class FindSTest {

	public static void main(String[] args) {
		FindS finds = new FindS(new LikesSportHypothesis());
		Map<Hypothesis, Boolean> training = getTrainingExamples();

		System.out.println("About to train with: " + training);
		// train
		finds.train(training);
		System.out.println("*** Find-S after the training ***");
		System.out.println(finds);
		testPositiveExamplesWithAlgorithm(finds, training);
	}

	private static Map<Hypothesis, Boolean> getTrainingExamples() {
		String[] values;
		Map<Hypothesis, Boolean> training = new HashMap<Hypothesis, Boolean>();
		// first set
		values = new String[] {"soleado", "calido", "normal", "fuerte", "calida", "igual"};
		training.put(new LikesSportHypothesis(values), true);
		// second set
		values = new String[] {"soleado", "calido", "alta", "fuerte", "calida", "igual"};
		training.put(new LikesSportHypothesis(values), true);
		// third set
		values = new String[] {"nublado", "fria", "alta", "fuerte", "calida", "cambiante"};
		training.put(new LikesSportHypothesis(values), false);
		// forth set
		values = new String[] {"soleado", "calido", "alta", "fuerte", "fria", "cambiante"};
		training.put(new LikesSportHypothesis(values), true);
		return training;
	}
	
	private static void testPositiveExamplesWithAlgorithm(FindS algorithm, Map<Hypothesis, Boolean> training) {
		for (Entry<Hypothesis, Boolean> entry: training.entrySet()) {
			System.out.println(entry.getValue() + " ==> " + algorithm.validates(entry.getKey()));
		}
	}
}
