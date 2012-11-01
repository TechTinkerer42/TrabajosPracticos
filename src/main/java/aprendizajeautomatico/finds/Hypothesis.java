package aprendizajeautomatico.finds;


public abstract class Hypothesis {

	private static final String NONE = "0";

	protected HypothesisField[] fields;
	protected boolean value;
	
	/**
	 * Creates a new null hypothesis
	 */
	public Hypothesis() {
		this(null, true);
	}

	/**
	 * Creates a new hypothesis with the specified values
	 */
	public Hypothesis(String[] values, boolean value) {
		initializeFields();
		this.value = value;
		if (values != null) 
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

	public void setValue(int index, String value) {
		fields[index].setValue(value);
	}
	
	public void setValues(String[] values) {
		validateDimention(values.length);
		for (int i = 0; i < values.length; i++) {
			setValue(i, values[i]);
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

	public void setValue(boolean value) {
		this.value = value;
	}
	
	public int length() {
		return fields.length;
	}
	
	public HypothesisField getField(int index) {
		return fields[index];
	}
	
	public boolean getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		String s = "{";
		for (int i = 0; i < fields.length; i++) {
			HypothesisField hf = fields[i];
			s += "<";
			if (hf.isEmpty()) {
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
