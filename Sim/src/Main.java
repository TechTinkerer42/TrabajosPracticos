
public class Main {
	
	public static void main(String[] args) {
		Table t = createTable1();
		for (int i = 0; i < t.columns(); i++) {
			System.out.print(t.getNames(i) + "\t- ");
			System.out.println(EntropyUtils.G(t, i));
		}
	}

	private static Table createTable1() {
		Table table = new Table(new String[] {
			"cielo", "t aire", "humedad", "viento", "t agua", "pron del tiempo", "disfruta"
		});
		Row r;
		r = new Row(new String[] {"soleado", "cálida", "normal", "fuerte", "cálida", "igual", "si"});
		table.addRow(r);
		r = new Row(new String[] {"soleado", "cálida", "alta", "fuerte", "cálida", "igual", "si"});
		table.addRow(r);
		r = new Row(new String[] {"nublado", "fría", "alta", "fuerte", "cálida", "cambiante", "no"});
		table.addRow(r);
		r = new Row(new String[] {"soleado", "cálida", "alta", "fuerte", "fría", "cambiante", "si"});
		table.addRow(r);
		return table;
	}

}
