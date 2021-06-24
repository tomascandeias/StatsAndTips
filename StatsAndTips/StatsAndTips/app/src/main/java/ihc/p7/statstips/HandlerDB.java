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
	private String db_url = "jdbc:jtds:sqlserver://mednat.ieeta.pt:8101";
	private String username = "p9g2";
	private String password = "BD@1712193931";
	private Connection conn = null;
	private String  ConnectionResult;

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
		String selectSql = String.format("SELECT treinador, ngolos, nome, data_fundacao, id_clube " +
											"FROM (SAT.Equipa JOIN SAT.Clube ON Clubeid_clube=id_clube) WHERE id_clube=\'%s\'", id_clube);
		ResultSet rs = statement.executeQuery(selectSql);

		StringBuilder ret = new StringBuilder();
		while(rs.next()){
			// treinador; ngolos; nome, data_fundacao
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
		String selectSql = String.format("SELECT SAT.Jogador.nome , posicao, id_jogador, Clubeid_clube " +
											"FROM ((SAT.Clube JOIN SAT.Equipa ON id_clube=Clubeid_clube) JOIN SAT.Jogador ON id_equipa=Equipaid) WHERE id_clube=\'%s\'", id_clube);
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
		String selectSql = String.format("SELECT SAT.Jogador.nome , njogos, nacionalidade, posicao, amarelos, vermelhos, SAT.Clube.id_clube " +
											"FROM ((SAT.Clube JOIN SAT.Equipa ON id_clube=Clubeid_clube) JOIN SAT.Jogador ON id_equipa=Equipaid) WHERE SAT.Jogador.id_jogador=\'%s\';", id_jogador);
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
		String selectSql = String.format("SELECT Equipaid_equipa, Equipaid_equipa2, localizacao, data_hora, vitoria, empate, derrota, vitoria_empate, total_golos, ambas_marcam \n" +
											"FROM ((SAT.Odd JOIN SAT.Jogo ON Jogoid_jogo=id_jogo) JOIN SAT.Equipa ON Equipaid_equipa=id_equipa);");
		ResultSet rs = statement.executeQuery(selectSql);

		StringBuilder ret = new StringBuilder();
		while(rs.next()){
			// equipa1, equipa2, win, draw, loss, win or draw, total goals, both score
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

	public String getNameByIdEquipa(String id_equipa) throws SQLException {
		Statement statement = this.conn.createStatement();

		// Create and execute a SELECT SQL statement.
		String selectSql = String.format("SELECT nome FROM (SAT.Equipa JOIN SAT.Clube ON Clubeid_clube=id_clube) WHERE id_equipa=\'%s\';", id_equipa);
		ResultSet rs = statement.executeQuery(selectSql);

		StringBuilder ret = new StringBuilder();
		while(rs.next()){
			// nome
			ret.append(rs.getString(1) + ";\n");
		}

		return ret.toString();
	}
}