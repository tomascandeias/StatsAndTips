package ihc.p7.statstips;

import android.os.Build;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class HandlerDB {
	private final String db_url = "jdbc:jtds:sqlserver://mednat.ieeta.pt:8101";
	private final String username = "p9g2";
	private final String password = "BD@1712193931";
	private Connection conn = null;

	public HandlerDB() {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

		try{
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			String connection_url = db_url + ";"
					+ "databasename=p9g2;"
					+ "instance=SQLServer;"
					+ "user=" + username + ";"
					+ "password=" + password + ";";

			this.conn = DriverManager.getConnection(connection_url);

			System.err.println("HandlerDB()");
			System.err.println(connection_url);
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

	//TODO functions -> sql commands to fill the standings in the league/cup

	public String getClubeByNome(String nome) throws SQLException {
		Statement statement = this.conn.createStatement();

		// Create and execute a SELECT SQL statement.
		String selectSql = String.format("EXEC SAT.Get_Clube_byName \'%s\'", nome);
		ResultSet rs = statement.executeQuery(selectSql);

		StringBuilder ret = new StringBuilder();
		while(rs.next()){
			// treinador; ngolos; nome, data_fundacao, id_clube
			ret.append(rs.getString(1) + ";"
					+ rs.getString(2) + ";"
					+ rs.getString(3) + ";"
					+ rs.getString(4) + ";"
					+ rs.getString(5) + ";\n");
		}
		return ret.toString();
	}

	public String getClubeByID(String id_clube) throws SQLException {
		Statement statement = this.conn.createStatement();

		// Create and execute a SELECT SQL statement.
		String selectSql = String.format("EXEC SAT.Get_Clube_ID %s", id_clube);
		ResultSet rs = statement.executeQuery(selectSql);

		StringBuilder ret = new StringBuilder();
		while(rs.next()){
			// treinador; ngolos; nome, data_fundacao, id_clube
			ret.append(rs.getString(1) + ";"
					+ rs.getString(2) + ";"
					+ rs.getString(3) + ";"
					+ rs.getString(4) + ";"
					+ rs.getString(5) + ";\n");
		}
		return ret.toString();
	}

	public String getPlayer(String id_clube) throws SQLException {
		Statement statement = this.conn.createStatement();

		// Create and execute a SELECT SQL statement.
		String selectSql = String.format("EXEC SAT.Get_Jogador_Of_Club %s", id_clube);
		ResultSet rs = statement.executeQuery(selectSql);

		StringBuilder ret = new StringBuilder();
		while(rs.next()){
			// nome, posicao, id_jogador
			ret.append(rs.getString(1) + ";"
					+ rs.getString(2) + ";"
					+ rs.getString(3) + ";"
					+ rs.getString(4) + ";\n");
		}

		return ret.toString();
	}

	public String getPlayerPage(String id_jogador) throws SQLException {
		Statement statement = this.conn.createStatement();

		// Create and execute a SELECT SQL statement.
		String selectSql = String.format("EXEC SAT.Get_Jogador %s", id_jogador);
		ResultSet rs = statement.executeQuery(selectSql);

		StringBuilder ret = new StringBuilder();
		while(rs.next()){
			// nome, njogos, nacionalidade, posicao, amarelos, vermelhos, id_clube
			ret.append(rs.getString(1) + ";"
					+ rs.getString(2) + ";"
					+ rs.getString(3) + ";"
					+ rs.getString(4) + ";"
					+ rs.getString(5) + ";"
					+ rs.getString(6) + ";"
					+ rs.getString(7) +";\n");
		}

		return ret.toString();
	}

	public String getOdds() throws SQLException {
		Statement statement = this.conn.createStatement();

		// Create and execute a SELECT SQL statement.
		String selectSql = String.format("EXEC SAT.List_ODDS");
		ResultSet rs = statement.executeQuery(selectSql);

		StringBuilder ret = new StringBuilder();
		while(rs.next()){
			// equipa1, equipa2, local, data_hora, win, draw, loss, win or draw, total goals, both score
			ret.append(rs.getString(1) + ";"
					+ rs.getString(2) + ";"
					+ rs.getString(3) + ";"
					+ rs.getString(4) + ";"
					+ rs.getString(5) + ";"
					+ rs.getString(6) + ";"
					+ rs.getString(7) + ";"
					+ rs.getString(8) + ";"
					+ rs.getString(9) + ";"
					+ rs.getString(10) + ";\n");
		}

		return ret.toString();

	}

	public String getClubes() throws SQLException {
		Statement statement = this.conn.createStatement();

		// Create and execute a SELECT SQL statement.
		String selectSql = String.format("EXEC SAT.List_Clubs");
		ResultSet rs = statement.executeQuery(selectSql);

		StringBuilder ret = new StringBuilder();
		while(rs.next()){
			// id_clube, nome
			ret.append(rs.getString(1) + ";"
					+ rs.getString(2) + ";\n");
		}

		return ret.toString();
	}

	public String getNameByIdEquipa(String id_equipa) throws SQLException {
		Statement statement = this.conn.createStatement();

		// Create and execute a SELECT SQL statement.
		String selectSql = String.format("EXEC SAT.Get_TeamName_byID %s", id_equipa);
		ResultSet rs = statement.executeQuery(selectSql);

		StringBuilder ret = new StringBuilder();
		while(rs.next()){
			// nome
			ret.append(rs.getString(1) + ";\n");
		}

		return ret.toString();
	}
}