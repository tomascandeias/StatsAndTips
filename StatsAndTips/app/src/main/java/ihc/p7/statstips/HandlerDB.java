package ihc.p7.statstips;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class HandlerDB {
	private String db_url = "jdbc:jtds:sqlserver://tcp:mednat.ieeta.pt\\SQLSERVER,8101";
	private String username = "p9g2";
	private String password = "BD@1712193931";
	private Connection conn = null;
	private String  ConnectionResult;

	public HandlerDB() {
		System.err.println("DB criada");

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

		try{
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			String connection_url = db_url + username + password;

			this.conn = DriverManager.getConnection(db_url, username, password);

		} catch (Exception e){
			Log.e("Error ", e.getMessage());
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

	public String getClubePage(String id_clube) throws SQLException {
		Statement statement = this.conn.createStatement();

		// Create and execute a SELECT SQL statement.
		String selectSql = String.format("SELECT treinador, ngolos AS 'Numero de golos', nome, data_fundacao FROM (SAT.Equipa JOIN SAT.Clube ON Clubeid_clube=id_clube) WHERE id_clube=\'%s\'", id_clube);
		ResultSet resultSet = statement.executeQuery(selectSql);

		// Print results from select statement
		return resultSet.toString();
	}

	//TODO functions -> sql commands to fill the standings in the league/cup
}
