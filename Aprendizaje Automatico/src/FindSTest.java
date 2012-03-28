import java.util.HashMap;
import java.util.Map;

import finds.FindS;
import finds.Hypotesis;
import finds.implementation.LikesSportHypotesis;

public class FindSTest {

	public static void main(String[] args) {
		FindS finds = new FindS(new LikesSportHypotesis());
		Map<Hypotesis, Boolean> training = getTrainingExamples();

		System.out.println("About to traing with: " + training);
		// train
		finds.train(training);
		System.out.println("*** Find-S after the training ***");
		System.out.println(finds);
		System.out.println(finds.validates(getTestHypotesis()));
	}

	private static Map<Hypotesis, Boolean> getTrainingExamples() {
		String[] values;
		Map<Hypotesis, Boolean> training = new HashMap<Hypotesis, Boolean>();
		// first set
		values = new String[] {"soleado", "calido", "normal", "fuerte", "calida", "igual"};
		training.put(new LikesSportHypotesis(values), true);
		// second set
		values = new String[] {"soleado", "calido", "alta", "fuerte", "calida", "igual"};
		training.put(new LikesSportHypotesis(values), true);
		// third set
		values = new String[] {"nublado", "fria", "alta", "fuerte", "calida", "cambiante"};
		training.put(new LikesSportHypotesis(values), false);
		// forth set
		values = new String[] {"soleado", "calido", "alta", "fuerte", "fria", "cambiante"};
		training.put(new LikesSportHypotesis(values), true);
		return training;
	}

	private static Hypotesis getTestHypotesis() {
		Hypotesis h = new LikesSportHypotesis();
		h.addValue(0, "nublado");
		return h;
	}
}
