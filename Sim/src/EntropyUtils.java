import java.util.List;
import java.util.Set;

public class EntropyUtils {
	
	private static float E(Table t) {
		int last = t.columns() - 1;
		List<Float> classes = t.possibleClasses(last);
		float e = 0;
		for (Float p: classes) {
			e += -p * log_2(p);
		}
		return e;
	}
	
	private static float log_2(float n) {
		return (float) (Math.log(n) / Math.log(2));
	}
	
	public static float G(Table table, int col) {
		float sum = 0;
		int S = table.size();
		Set<String> conjVal = table.conjVal(col);
		for (String val: conjVal) {
			Table subConj = table.subConj(col, val);
			sum += (subConj.size() * E(subConj)) / S;
		}
		return E(table) - sum;
	}
}
