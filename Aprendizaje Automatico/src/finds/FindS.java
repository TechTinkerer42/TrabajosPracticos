package finds;

import java.util.Collection;

/**
 * <pre>
 * 	1. Initialize h to the most specific hypothesis in H
 * 	2. For each positive training instance x
 * 			For each attribute constraint a, in h
 * 				If the constraint a, is satisfied by x
 * 					Then do nothing
 * 				Else 
 * 					replace a, in h by the next more general constraint that is satisfied by x
 * 	3. Output hypothesis h
 * </pre>
 */
public class FindS {
	
	private Hypothesis hypotesis;
	
	public FindS(Hypothesis hypotesis) {
		this.hypotesis = hypotesis;
	}
	
	public void train(Collection<Hypothesis> training) {
		for (Hypothesis h: training) {
			if (h.getValue()) {	// only evaluate positive instances of x
				System.out.println(h + " = positive");
				hypotesis.generalize(h);
				System.out.println("\tCurrent Hypotesis: " + hypotesis + "\n");
			} else {
				System.out.println(h + " was ignored\n");
			}
		}
	}

	@Override
	public String toString() {
		return "Find-S algorithm. Current hypotesis: " + hypotesis;
	}
	
	public boolean validates(Hypothesis h) {
		return hypotesis.isMoreGeneralThan(h);
	}
}
