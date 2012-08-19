package ar.edu.itba.it.paw;

import ar.edu.itba.it.paw.session.HttpRequestSessionManager;

public class Config {

	public static HttpRequestSessionManager sessionManager;
	public static String db_driver = "org.postgresql.Driver";
	public static String db_user = "postgres";
	public static String db_passwd = "admin";
	public static String db_address = "jdbc:postgresql://localhost:5432/hotelapp";
	
	static {
		initialize();
	}

	private static void initialize() {
		sessionManager = new HttpRequestSessionManager();
	}
}
