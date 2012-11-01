package sga;

import java.io.File;
import java.io.IOException;

import sga.parsing.CicleParser;
import sga.parsing.SGAParser;
import sga.tree.GraphvizTranslator;

public class Main {

	public static void main(String[] args) {
		try {
			// List<Semester> semesters = getSemesters("res/informatica_basico.html");
			Plan p = getPlan("res/informatica.html", CicleParser.TYPE_INF_IA);
			GraphvizTranslator translator = new GraphvizTranslator(p);
			translator.toGraphviz(true);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static Plan getPlan(String filename, int orientationType) throws IOException {
		File f = new File(filename);
		SGAParser parser = new SGAParser();
		return parser.parse(f, orientationType);
	}

}
