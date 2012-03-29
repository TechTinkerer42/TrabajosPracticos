package finds;

public abstract class Hypothesis {

	private static final String ALL = "?", NONE = "0";

	protected HypothesisField[] fields;

	/**
	 * Creates a new null hypothesis
	 */
	public Hypothesis() {
		this(new String[0]);
	}

	/**
	 * Creates a new hypothesis with the specified values
	 */
	public Hypothesis(String[] values) {
		initializeFields();
		addValues(values);
	}

	protected abstract void initializeFields();

	public void generalize(Hypothesis h) {
		validateDimention(h.fields.length);
		for (int i = 0; i < fields.length; i++) {
			fields[i].addAll(h.fields[i]);
		}
	}

	public void addValue(int index, String value) {
		fields[index].addValue(value);
	}

	public void addValues(String[] value) {
		validateDimention(value.length);
		for (int i = 0; i < value.length; i++) {
			fields[i].addValue(value[i]);
		}
	}

	public boolean isMoreGeneralThan(Hypothesis h) {
		validateDimention(h.fields.length);
		for (int i = 0; i < fields.length; i++) {
			if (!fields[i].isMoreGeneralThan(h.fields[i])) {
				return false;
			}
		}
		return true;
	}

	private void validateDimention(int dim) {
		if (fields.length != dim) {
			throw new IllegalArgumentException(
				"Cant compare arguments wih different dimensions!");
		}
	}

	@Override
	public String toString() {
		String s = "{";
		for (int i = 0; i < fields.length; i++) {
			HypothesisField hf = fields[i];
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
}
