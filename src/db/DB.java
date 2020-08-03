package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {
	
	private static Connection conn = null;
	
	// METODO PARA ABRIR CONEXÃO COM O BANCO DE DADOS
	public static Connection getConnection() {
		if(conn == null) {
			try {
				Properties props = loadProperties();
				String url =props.getProperty("dburl");
				conn =  DriverManager.getConnection(url, props);
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}
	
	// METODO PARA FECHAR A CONEXÃO COM BANCO DE DADOS
	public static void closedConnection() {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	// METODO AUXILIAR PARA CARREGAR AS PROPRIEDADES DO ARQUIvO DB.PROPERTIES
	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")){
			Properties props = new Properties();
			props.load(fs);
			return props;
		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	// METODO PARA FECHAR O STATEMENT
	public static void closedStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	// METODO PARA FECHAR O RESULTSET
	public static void closedResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

}
