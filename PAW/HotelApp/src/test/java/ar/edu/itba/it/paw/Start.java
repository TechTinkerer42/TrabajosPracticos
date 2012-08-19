package ar.edu.itba.it.paw;
import java.sql.Connection;
import java.sql.Statement;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.bio.SocketConnector;
import org.mortbay.jetty.webapp.WebAppContext;

import ar.edu.itba.it.paw.db.DBConnection;

public class Start {

	private static DBConnection dbConnection;
	
	public static void main(String[] args) throws Exception {
		Server server = new Server();
		SocketConnector connector = new SocketConnector();
		connector.setMaxIdleTime(1000 * 60 * 60);
		connector.setSoLingerTime(-1);
		connector.setPort(8081);
		server.setConnectors(new Connector[] { connector });
		WebAppContext bb = new WebAppContext();
		bb.setServer(server);
		bb.setContextPath("/HotelApp");
		bb.setWar("src/main/webapp");
		server.addHandler(bb);
		try {
			System.out.println(">>> STARTING EMBEDDED JETTY SERVER, "
					+ "PRESS ANY KEY TO STOP");
			server.start();
			if (!initDB()) {
				System.out.println("DB could not be initialized");
				return;
			}
			while (System.in.available() == 0) {
				Thread.sleep(5000);
			}
			server.stop();
			server.join();
			dbConnection.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(100);
		}
	}
	
	private static boolean initDB() {
		dbConnection = DBConnection.getInstance();
		try {
			dbConnection.start();
			Connection connector = dbConnection.get();
			Statement statement = connector.createStatement();
			statement.execute("DROP TABLE IF EXISTS Hotel");
			statement.execute(
				"Create Table Hotel(" +
					"id int," +
					"name char(50)," +
					"address char(50)," +
					"price decimal(5,2)," +
					"description varchar," +
					"rating int" +
				")"
			);
			statement.execute("DROP TABLE IF EXISTS AppUser");
			statement.execute(
				"Create Table AppUser(" +
					"id int," +
					"username char(50)," +
					"password char(20)" +
				")"
			);
			statement.execute("INSERT INTO AppUser(id, username, password) VALUES ('1', 'asd', 'asd')");
			statement.execute("INSERT INTO Hotel(id, name, address, price, description, rating) VALUES " +
				"(0, 'Days Inn', 'A la vuelta de la esquina', 500, 'Con spa y cochera incuido!', 4)," +
				"(1, 'Hotel Atlantico', 'Av. Cordoba 8905', 450, 'La descripcion del hotel!', 3)," + 
				"(2, 'Adler', '-', 950, 'Descripcion 3!', 2)"
			);
			dbConnection.get().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

}
