package ihc.p7.statstips;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HandlerDB {
	private String db_url = "";
	private String username = "";
	private String password = "";
	private Connection conn = null;

	public HandlerDB(String db_url, String username, String password) {
		this.db_url = db_url;
		this.username = username;
		this.password = password;

		try{
			this.conn = DriverManager.getConnection(db_url, username, password);
		} catch (SQLException e){
			e.printStackTrace();
		}
	}

	/**
	 * Close connection with the database
	 */
	public void closeConnection(){
		try {
			if (conn != null && !conn.isClosed())
				conn.close();
			System.out.println("[CLOSED] Connection to the database was closed");
		} catch (SQLException e){
			e.printStackTrace();
		}
	}

	//TODO functions -> sql commands to fill the standings in the league/cup
}
