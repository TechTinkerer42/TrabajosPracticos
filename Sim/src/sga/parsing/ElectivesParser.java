package sga.parsing;

import java.util.LinkedList;
import java.util.List;

import org.jsoup.nodes.Element;

import sga.Semester;

public class ElectivesParser extends CicleParser {

	public List<Semester> parse(Element allSemesters) {
		int firstDiv = 7;
		int lastDiv = 17;
		List<Semester> semesters = new LinkedList<Semester>();
		for (int i = firstDiv; i <= lastDiv; i++) {
			Element e = allSemesters.child(i).child(1);
			semesters.addAll(parseTable(e));
		}
		return semesters;
	}
	
}
