package finds;

import java.util.Collection;
import java.util.HashSet;

public class HypotesisField {

	protected Collection<String> allValues;
	protected Collection<String> values;

	public HypotesisField(Collection<String> allValues) {
		this.allValues = allValues;
		values = new HashSet<String>();
	}

	public void addValue(String value) {
		if (!allValues.contains(value)) {
			throw new IllegalArgumentException(value
					+ " is not within domain! => " + allValues);
		}
		values.add(value);
	}

	public void addAll(HypotesisField h) {
		if (getClass() != h.getClass()) {
			throw new IllegalArgumentException(getClass()
					+ "is not comparable with " + h.getClass());
		}
		if (!allValues.containsAll(h.values)) {
			throw new IllegalArgumentException(h.values
					+ " are not within domain! => " + allValues);
		}
		values.addAll(h.values);
	}

	public boolean isMoreGeneralThan(HypotesisField h) {
		return values.containsAll(h.values);
	}

	public boolean isComplete() {
		return values.containsAll(allValues);
	}

	public boolean isEmpty() {
		return values.size() == 0;
	}

	@Override
	public String toString() {
		return values.toString();
	}

}
