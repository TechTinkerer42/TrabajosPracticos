import java.util.Arrays;

public class Row {
	
	String[] values;

	public Row(String... values) {
		this.values = values;
	}
	
	@Override
	public String toString() {
		String s = "";
		s += Arrays.toString(values);
		return s;
	}
}
