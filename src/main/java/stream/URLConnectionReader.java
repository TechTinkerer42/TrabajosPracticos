package stream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class URLConnectionReader {

	public static void main(String[] args) throws Exception {
		File page = new URLConnectionReader().get("http://www.yahoo.com/");
		System.out.println(page.getAbsolutePath());
	}
	
	public File get(String url) throws IOException {
		File page = File.createTempFile("page_", ".html");
		FileWriter pageWriter = new FileWriter(page);
		URL yahoo = new URL(url);
		URLConnection yc = yahoo.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			pageWriter.append(inputLine + "\r\n");
		}
		in.close();
		pageWriter.close();
		return page;
	}
}
