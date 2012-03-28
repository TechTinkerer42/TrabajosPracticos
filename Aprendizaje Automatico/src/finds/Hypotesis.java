package finds;

public abstract class Hypotesis {

	private static final String ALL = "?", NONE = "0";

	protected HypotesisField[] fields;

	/**
	 * Creates a new null hypotesis
	 */
	public Hypotesis() {
		initializeFields();
	}
	
	public Hypotesis(String[] values) {
		initializeFields();
		addValues(values);
	}

	protected abstract void initializeFields();

	public void generalize(Hypotesis h) {
		validateDomain(h);
		for (int i = 0; i < fields.length; i++) {
			fields[i].addAll(h.fields[i]);
		}
	}

	@Override
	public String toString() {
		String s = "{";
		for (int i = 0; i < fields.length; i++) {
			HypotesisField hf = fields[i];
			s += "<";
			if (hf.isComplete()) {
				s += ALL;
			} else if (hf.isEmpty()) {
				s += NONE;
			} else {
				s += hf;
			}
			s += ">";
			if (i != fields.length - 1)
				s += ", ";
		}
		s += "}";
		return s;
	}

	public void addValue(int index, String value) {
		fields[index].addValue(value);
	}

	public void addValues(String[] value) {
		for (int i = 0; i < value.length; i++) {
			fields[i].addValue(value[i]);
		}
	}

	public boolean isMoreGeneralThan(Hypotesis h) {
		validateDomain(h);
		for (int i = 0; i < fields.length; i++) {
			if (!fields[i].isMoreGeneralThan(h.fields[i])) {
				return false;
			}
		}
		return true;
	}

	private void validateDomain(Hypotesis h) {
		if (fields.length != h.fields.length) {
			throw new IllegalArgumentException(
					"Cant compare hypotesis wih different dimensions!");
		}
	}
}
