package finds;

import java.util.Collection;

public class HypothesisField {
	private static final String ALL_VALUES = "?";
	
	protected Collection<String> allValues;
	protected String values;

	public HypothesisField(Collection<String> allValues) {
		this.allValues = allValues;
		values = null;
	}

	public void addValue(String value) {
		if (!allValues.contains(value)) {
			throw new IllegalArgumentException(value + " is not within domain! => " + allValues);
		}
		if (values == null) {
			values = value;
		} else {
			values = ALL_VALUES;
		}
	}

	public void addAll(HypothesisField h) {
		if (h.values != null) {
			if (values == null) {
				values = h.values;
			}
			if (!h.values.equals(values)) {
				values = ALL_VALUES;
			} 
		}
	}

	public boolean isMoreGeneralThan(HypothesisField h) {
		if (values == null) {
			if (h.values == null) {
				return true;
			}
			return false;
		}
		if (values.equals(ALL_VALUES) || values.equals(h.values)) {
			return true;
		}
		return false;
	}

	public boolean isEmpty() {
		return values == null;
	}

	@Override
	public String toString() {
		return values == null ? "0" : values.toString();
	}

}
