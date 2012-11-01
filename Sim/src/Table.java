import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class Table {
	
		private List<Row> rows;
		private String[] names;
		
		public Table(String[] names) {
			this.names = names;
			rows = new LinkedList<Row>();
		}
		
		public void addRow(Row r) {
			rows.add(r);
		}
		
		public int size() {
			return rows.size();
		}
		
		public int columns() {
			return names.length;
		}
		
		public String getNames(int i) {
			return names[i];
		}
		
		public List<Float> possibleClasses(int col) {
			Set<String> diffValue = conjVal(col);
			List<Float> count = new LinkedList<Float>();
			for (String val: diffValue) {
				int amount = 0;
				for (Row r: rows) {
					if (r.values[col].equals(val)) {
						amount++;
					}
				}
				count.add(amount / (float) rows.size());
			}
			return count;
		}
		
		public Set<String> conjVal(int col) {
			Set<String> diffValue = new HashSet<String>();
			for (Row r: rows) {
				diffValue.add(r.values[col]);
			}
			return diffValue;
		}
		
		public Table subConj(int col, String val) {
			Table t = new Table(names);
			for (Row r: rows) {
				if (r.values[col].equals(val)) {
					t.addRow(new Row(r.values.clone()));
				}
			}
			return t;
		}
		
		@Override
		public String toString() {
			String s = "";
			s += rows.toString();
			return s;
		}
	}