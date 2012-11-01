package aprendizajeautomatico;

import java.util.List;

import aprendizajeautomatico.finds.FindS;
import aprendizajeautomatico.finds.Hypothesis;
import aprendizajeautomatico.finds.implementation.LikesSportHypothesis;


public class FindSEx5 {

	public static void main(String[] args) {
		LikesSportHypothesis target = new LikesSportHypothesis();
		System.out.println(target);
		target.setValues(new String[] {"nublado", "fria", "alta", "debil", "fria", "?"});
		int measures = 100;
		int totalMeasures = 0;
		for (int i = 0; i < measures; i++) {
			totalMeasures += teachTargetHypotesis(target);
		}
		System.out.println("\nPromedio: " + (totalMeasures / measures) + " ");
	}
	
	private static int teachTargetHypotesis(LikesSportHypothesis target) {
		FindS finds = new FindS(new LikesSportHypothesis());
		int total = 0;
		while (!finds.validates(target)) {
			Hypothesis randomValidHypotesis = randomHypotesis(target); 
			finds.train(randomValidHypotesis);
			total++;
		}
//		System.out.print(total + " ");
		return total;
	}
	
	private static LikesSportHypothesis randomHypotesis(LikesSportHypothesis match) {
		LikesSportHypothesis random = new LikesSportHypothesis();
		for (int i = 0; i < random.length(); i++) {
			List<String> allValues = random.getField(i).getAllValues();
			String randomValue = allValues.get((int) (Math.random() * allValues.size()));
			random.setValue(i, randomValue);
		}
		if (match.isMoreGeneralThan(random)) {
			random.setValue(true);
		} else {
			random.setValue(false);
		}
		return random;
	}
	
}
