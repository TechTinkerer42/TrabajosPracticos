package finds;

import java.util.Map;
import java.util.Map.Entry;

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
	
	private Hypotesis hypotesis;
	
	public FindS(Hypotesis hypotesis) {
		this.hypotesis = hypotesis;
	}
	
	public void train(Map<Hypotesis, Boolean> training) {
		for (Entry<Hypotesis, Boolean> entry: training.entrySet()) {
			if (entry.getValue()) {	// only evaluate positive instances of x
				hypotesis.generalize(entry.getKey());
			}
		}
	}

	@Override
	public String toString() {
		return "Find-S algorithm. Current hypotesis: " + hypotesis;
	}
	
	public boolean validates(Hypotesis h) {
		return hypotesis.isMoreGeneralThan(h);
	}
}
