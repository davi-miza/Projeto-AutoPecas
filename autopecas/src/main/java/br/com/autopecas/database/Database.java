package br.com.autopecas.database;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.hsqldb.jdbc.JDBCPool;

public class Database {

	private DataSource dataSource;

	public Database() {
		JDBCPool pool = new JDBCPool();
		pool.setUrl("jdbc:hsqldb:hsql://localhost/auto-pecas");
		pool.setUser("davi");
		pool.setPassword("");
		this.dataSource = pool;
		
	}
	
	public Connection getConnection() throws SQLException {
		//Connection connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/auto-pecas", "davi", "123");
		Connection connection = dataSource.getConnection();
		return connection;
	}
}
