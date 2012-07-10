package sga.parsing;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import sga.Course;
import sga.Semester;

public class CicleParser {
	
	public static final int TYPE_INF_BASICO 			= 1;
	public static final int TYPE_INF_SIN_ORIENTACION 	= 3;
	public static final int TYPE_INF_CS_IMAGEN		 	= 4;
	public static final int TYPE_INF_CS_INFORMACION 	= 5;
	public static final int TYPE_INF_IA				 	= 6;
	
	protected CourseParser courseParser;
	protected int type;
	
	public CicleParser() {
		this(-1);
	}
	
	public CicleParser(int type) {
		courseParser = new CourseParser();
		this.type = type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public List<Semester> parse(Element allSemesters) {
		Element basic = allSemesters.child(type).child(0);
		return parseTable(basic);
	}
	
	protected List<Semester> parseTable(Element semestersTable) {
		Element semestersElements = semestersTable.child(1);
		ListIterator<Element> it = semestersElements.children().listIterator();
		List<Semester> semesters = new LinkedList<Semester>();
		while (it.hasNext()) {
			Element semesterEl = it.next();
			semesterEl = semesterEl.child(0).child(0);
			Semester s = parseSemester(semesterEl);
			semesters.add(s);
		}
		return semesters;
	}
	
	protected Semester parseSemester(Element elem) {
		String name = elem.child(1).getElementsByTag("span").html();
		Semester semeser = new Semester(name);
		Elements trs = elem.child(2).getElementsByTag("tr");
		for (int i = 1; i < trs.size(); i++) {
			Course c = courseParser.parse(trs.get(i));
			if (!"0".equals(c.getCredits())) {				
				semeser.add(c);
			}
		}
		return semeser;
	}
}
