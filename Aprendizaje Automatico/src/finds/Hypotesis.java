package finds;

public abstract class Hypotesis {

	public static final String ALL = "?";
	public static final String NONE = "0";
	
	protected String[] values;
	
	protected void setToNullHypotesis() {
		for (int i = 0; i < values.length; i++) {
			values[i] = NONE;
		}
	}
}
