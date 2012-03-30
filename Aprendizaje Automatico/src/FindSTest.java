import java.util.LinkedList;
import java.util.List;

import finds.FindS;
import finds.Hypothesis;
import finds.implementation.LikesSportHypothesis;

public class FindSTest {

	public static void main(String[] args) {
		FindS finds = new FindS(new LikesSportHypothesis());
		List<Hypothesis> training = getTrainingExamples();

		System.out.println("Training set to use: " + training + "\n\n");
		System.out.println("Starting the training\n");
		finds.train(training);
		System.out.println("*** Find-S after the training ***\n");
		System.out.println(finds);
//		testPositiveExamplesWithAlgorithm(finds, training);
	}

	private static List<Hypothesis> getTrainingExamples() {
		String[] values;
		List<Hypothesis> training = new LinkedList<Hypothesis>();
		// first set
		values = new String[] {"soleado", "calido", "normal", "fuerte", "calida", "igual"};
		training.add(new LikesSportHypothesis(values, true));
		// second set
		values = new String[] {"soleado", "calido", "alta", "fuerte", "calida", "igual"};
		training.add(new LikesSportHypothesis(values, true));
		// third set
		values = new String[] {"nublado", "fria", "alta", "fuerte", "calida", "cambiante"};
		training.add(new LikesSportHypothesis(values, false));
		// forth set
		values = new String[] {"soleado", "calido", "alta", "fuerte", "fria", "cambiante"};
		training.add(new LikesSportHypothesis(values, true));
		return training;
	}
	
//	private static void testPositiveExamplesWithAlgorithm(FindS algorithm, List<Hypothesis> training) {
//		for (Hypothesis h: training) {
//			System.out.println(h.getValue() + " ==> " + algorithm.validates(h));
//		}
//	}
}
