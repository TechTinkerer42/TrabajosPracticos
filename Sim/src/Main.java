
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
		r = new Row(new String[] {"soleado", "c�lida", "normal", "fuerte", "c�lida", "igual", "si"});
		table.addRow(r);
		r = new Row(new String[] {"soleado", "c�lida", "alta", "fuerte", "c�lida", "igual", "si"});
		table.addRow(r);
		r = new Row(new String[] {"nublado", "fr�a", "alta", "fuerte", "c�lida", "cambiante", "no"});
		table.addRow(r);
		r = new Row(new String[] {"soleado", "c�lida", "alta", "fuerte", "fr�a", "cambiante", "si"});
		table.addRow(r);
		return table;
	}

}
