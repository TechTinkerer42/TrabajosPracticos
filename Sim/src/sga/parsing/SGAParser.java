package sga.parsing;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import sga.Plan;
import sga.Semester;

public class SGAParser {
		
	private CicleParser basicCicleParser;
	private CicleParser professionalCicleParser;
	private CicleParser electivesParser;
	
	public SGAParser() {
		basicCicleParser = new CicleParser(CicleParser.TYPE_INF_BASICO);
		professionalCicleParser = new CicleParser();
		electivesParser = new ElectivesParser();
	}
	
	public Plan parse(File f, int orientationType) throws IOException {
		Document doc = Jsoup.parse(f, "UTF-8");
		Element allCourses = doc.body().getElementsByClass("featurebox_center").get(0);
		String planName = allCourses.child(0).getElementsByTag("span").html();
		List<Semester> basic = basicCicleParser.parse(allCourses);
		professionalCicleParser.setType(orientationType);
		List<Semester> profesional = professionalCicleParser.parse(allCourses);
		List<Semester> electives = electivesParser.parse(allCourses);
		Plan p = new Plan(planName);
		p.setBasicSemesters(basic);
		p.setProfessionalSemesters(profesional);
		p.setElectives(electives);
		return p;
	}

}
