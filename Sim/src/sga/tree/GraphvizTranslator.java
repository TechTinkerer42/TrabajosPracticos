package sga.tree;

import java.util.List;

import sga.Course;
import sga.Plan;
import sga.Semester;

public class GraphvizTranslator {

	private Plan plan;
	
	public GraphvizTranslator(Plan plan) {
		this.plan = plan;
	}
	
	public void toGraphviz() {
		int yearStep = 100;
		int semesterStep = 100;
		System.out.println("digraph {");
		int y = 0;
		int semester = 0;
		for(Semester s: plan.getBasicSemesters()) {
			toGraphviz(s, y);
			semester++;
			y += semesterStep;
			if (semester%2 == 0) {	// nuevo año
				y += yearStep;
			}
		}
		for(Semester s: plan.getProfessionalSemesters()) {
			toGraphviz(s, y);
			semester++;
			y += semesterStep;
			if (semester%2 == 0) {	// nuevo año
				y += yearStep;
			}
		}
		System.out.println("}");
	}

	private void toGraphviz(Semester s, int y) {
		int maxCourses = 5;
		int w_course = 150;
		int w_minSpace = 100;
		
		int courses = s.getCourses().size();
		int w_totalWhiteSpace = (maxCourses - 1) * w_minSpace;
		int w_space = (courses == maxCourses) ? w_minSpace : (w_totalWhiteSpace / courses);
		int x = (courses == maxCourses) ? 0 : w_space;
		
		for (Course c: s.getCourses()) {
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
	
	private String getLabel(Course c) {
		int maxLen = 19;
		String name = c.getName();
		if (name.length() > maxLen) {
			name = name.substring(0, maxLen - 1);
		}
		return name + " (" + c.getCredits() + ")";
	}
}
