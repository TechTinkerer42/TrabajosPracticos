package finds;

import java.util.List;

public class HypothesisField {
	private static final String ALL_VALUES = "?";
	
	protected List<String> allValues;
	protected String values;

	public HypothesisField(List<String> allValues) {
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
	
	public void setValue(String value) {
		if (value != null && !allValues.contains(value) && !ALL_VALUES.equals(value)) {
			throw new IllegalArgumentException(value + " is not within domain! => " + allValues);
		}
		this.values = value;
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

	public boolean isMoreGeneralThan(String value) {
		if (values == null) {
			if (value == null) {
				return true;
			}
			return false;
		}
		if (values.equals(ALL_VALUES) || values.equals(value)) {
			return true;
		}
		return false;
	}
	
	public boolean isMoreGeneralThan(HypothesisField h) {
		return isMoreGeneralThan(h.values);
	}

	public boolean isEmpty() {
		return values == null;
	}

	public List<String> getAllValues() {
		return allValues;
	}
	
	@Override
	public String toString() {
		return values == null ? "0" : values.toString();
	}

}
