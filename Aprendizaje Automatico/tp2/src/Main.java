import java.util.Arrays;

import finds.HypothesisField;

public class Main {

	public static void main(String[] args) {
		HypothesisField field = new HypothesisField(Arrays.asList(new String[] {"A", "B"}));
		System.out.println(field);
		field.addValue("A");
		System.out.println(field);
		field.addValue("B");
		System.out.println(field);
		/*FileInputStream fstream = new FileInputStream(
				"./Aprendizaje Automatico/res/Intelligent Player.log");
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		int reads = 0;
		final int totalReads = 2;
		int lastWins = 0, lastTies = 0, lastLoses = 0;
		// Read File Line By Line
		while ((strLine = br.readLine()) != null) {
			String[] values = strLine.split("\t");
			int totalWins = Integer.parseInt(values[0]);
			int totalTies = Integer.parseInt(values[1]);
			int totalLosts = Integer.parseInt(values[2]);
			reads++;
			if (reads == totalReads) {
				int wins = totalWins - lastWins;
				int ties = totalTies - lastTies;
				int losts = totalLosts - lastLoses;
				float nonLooses = ((wins + ties) / (float) totalReads) * 100;
				System.out.println(wins + "\t" + ties + "\t" + losts + "\t-> " + nonLooses);
				lastWins = totalWins;
				lastTies = totalTies;
				lastLoses = totalLosts;
				reads = 0;
			}
		}
		in.close();*/
	}
}
