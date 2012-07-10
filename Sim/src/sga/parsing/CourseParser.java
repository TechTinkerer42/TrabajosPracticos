package sga.parsing;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import sga.Course;

public class CourseParser {
	
	public Course parse(Element courseElem) {
		Elements tds = courseElem.getElementsByTag("td");
		String nameAndCode;
		Elements nameElem = tds.get(0).getElementsByTag("span");
		nameAndCode = nameElem.last().html();
		String separator = " - ";
		int separatorIndex = nameAndCode.indexOf(separator);
		String code, name;
		if (separatorIndex != -1) {
			code = nameAndCode.substring(0, separatorIndex);
			name = nameAndCode.substring(separatorIndex + separator.length());			
		} else {
			// optional
			name = nameAndCode;
			code = "e";
		}
		List<String> correl = parseCorrelCodes(tds.get(3));
		String credits = tds.get(1).html();
		String reqCredits = tds.get(2).html();
		Course c = new Course();
		c.setCode(code);
		c.setName(name);
		c.setCorrel(correl);
		c.setCredits(credits);
		c.setReqCredits(reqCredits);
		return c;
	}
	
	private List<String> parseCorrelCodes(Element correlElem) {
		Elements e = correlElem.getElementsByTag("span");
		if (e.isEmpty()) {
			return null;
		}
		List<String> correl = new ArrayList<String>(e.size());
		ListIterator<Element> it = e.listIterator();
		while (it.hasNext()) {
			String s = it.next().html();
			s = StringUtils.replaceHTMLChars(s).trim();
			correl.add(s);
		}
		return correl;
	}
}
