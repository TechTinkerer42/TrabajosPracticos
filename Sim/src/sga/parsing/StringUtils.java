package sga.parsing;

public class StringUtils {

	public static String replaceHTMLChars(String s) {
		String[][] replaces = {
			{"&gt;", ">"},
			{"&lt;", "<"},
			{"&nbsp;", " "},
		};
		for (String[] replace: replaces) {
			s = s.replace(replace[0], replace[1]);
		}
		return s;
	}
}
