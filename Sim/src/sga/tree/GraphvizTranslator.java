package sga.tree;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import sga.Course;
import sga.Plan;
import sga.Semester;

public class GraphvizTranslator {

	private Plan plan;
	
	public GraphvizTranslator(Plan plan) {
		this.plan = plan;
	}
	
	public void toGraphviz(boolean showElectives) {
		System.out.println("digraph {");
		List<Semester> toShow = new LinkedList<Semester>();
		if (showElectives) {
			toShow.addAll(plan.getElectives());
			printElectives(toShow);
		} else {
			toShow.addAll(plan.getBasicSemesters());
			toShow.addAll(plan.getProfessionalSemesters());
			printAll(toShow);
		}
		System.out.println("}");
	}
	
	private void printAll(List<Semester> semesters) {
		int yearStep = 100;
		int semesterStep = 100;
		int y = 0;
		int semester = 0;
		for(Semester s: semesters) {
			toGraphviz(s.getCourses(), y);
			semester++;
			y += semesterStep;
			if (semester%2 == 0) {	// nuevo año
				y += yearStep;
			}
		}
	}

	private void printElectives(List<Semester> electives) {
		List<Course> corr = getAllCoursesThatAreCorrelativesTo(electives);
		for (Course c: corr) {
			String s = buildNode(c);
			System.out.println(s);
		}
		Set<Course> alreadyDefined = new HashSet<Course>();
		for(Semester s: electives) {
			for (Course c: s.getCourses()) {
				if (alreadyDefined.contains(c)) {
					continue;
				}
				alreadyDefined.add(c);
				if (c.getCorrel() != null) {
					for (String correl: c.getCorrel()) {
						System.out.println(correl + "->" + c.getCode());
					}
				}
				System.out.println(buildNode(c));
			}
		}
	}

	private List<Course> getAllCoursesThatAreCorrelativesTo(List<Semester> semesters) {
		Set<String> corrCodes = new HashSet<String>();
		for (Semester s: semesters) {
			for (Course c: s.getCourses()) {
				if (c.getCorrel() != null) {
					for (String corrCode: c.getCorrel()) {
						corrCodes.add(corrCode);
					}
				}
			}
		}
		List<Course> corr = new LinkedList<Course>();
		List<Semester> allsemesters = new LinkedList<Semester>();
		allsemesters.addAll(plan.getProfessionalSemesters());
		allsemesters.addAll(plan.getBasicSemesters());
		for (String corrCode: corrCodes) {
			for (Semester s: allsemesters) {
				for (Course c: s.getCourses()) {
					if (c.getCode().equals(corrCode)) {
						if (!corr.contains(c)) {							
							corr.add(c);
						}
					}
				}
			}
		}
		return corr;
	}
	
	private void toGraphviz(List<Course> courses, int y) {
		int maxCourses = 5;
		int w_course = 150;
		int w_minSpace = 100;
		
		int coursesLen = courses.size();
		int w_totalWhiteSpace = (maxCourses - 1) * w_minSpace;
		int w_space = (coursesLen == maxCourses) ? w_minSpace : (w_totalWhiteSpace / coursesLen);
		int x = (coursesLen == maxCourses) ? 0 : w_space;
		
		for (Course c: courses) {
			System.out.println(buildNode(c, x, y));	
			List<String> correlList = c.getCorrel();
			if (correlList != null) {
				for (String correl: correlList) {
					System.out.println("\t" + correl + "->" + c.getCode());
				}
			}
			x += w_course + w_space;
		}
	}
	
	private String buildNode(Course c, int x, int y) {
		String nodeAtt = "shape=box, fixedsize=true";
		nodeAtt += ", pos=\"" + x + ","+ -y + "!\"";
		nodeAtt += ", width=2";
		String node = "\t" + c.getCode() + "[" + nodeAtt + "]" + "\n";
		node += "\t" + c.getCode() + " [ label = \"" + getLabel(c) + "\" ]";
		return node;
	}
	
	private String buildNode(Course c) {
		String nodeAtt = "shape=box, fixedsize=true";
		nodeAtt += ", width=2.5";
		String node = "\t" + c.getCode() + "[" + nodeAtt + "]" + "\n";
		node += "\t" + c.getCode() + " [ label = \"" + getLabel(c) + "\" ]";
		return node;
	}
	
	private String getLabel(Course c) {
		int maxLen = 25;
		String name = c.getName();
		if (name.length() > maxLen) {
			name = name.substring(0, maxLen - 1);
		}
		return name + " (" + c.getCredits() + ")";
	}
}
