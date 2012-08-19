package ar.edu.itba.it.paw.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ar.edu.itba.it.paw.Config;

public class DBConnection {

	private static DBConnection instance = new DBConnection();

	public static DBConnection getInstance() {
		return instance;
	}

	private Connection connection;

	private DBConnection() {
	}

	public void start() throws ClassNotFoundException, SQLException {
		String driver = Config.db_driver;
		String user = Config.db_user;
		String passwd = Config.db_passwd;
		String address = Config.db_address;
		Class.forName(driver);
		connection = DriverManager.getConnection(address, user, passwd);
	}

	public Connection get() {
		return connection;
	}

	public void close() throws SQLException {
		if (connection != null) {
			connection.close();
			connection = null;
		}
	}
}
