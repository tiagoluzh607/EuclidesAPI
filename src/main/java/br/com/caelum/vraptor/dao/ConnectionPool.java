package br.com.caelum.vraptor.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.postgresql.jdbc3.Jdbc3ConnectionPool;
import org.postgresql.jdbc3.Jdbc3PoolingDataSource;


public class ConnectionPool {
	
	
	private DataSource dataSource;

	public ConnectionPool(){
		Jdbc3PoolingDataSource pool = new Jdbc3PoolingDataSource();
		
		pool.setUrl("jdbc:postgresql://localhost:5432/euclidesapi");
		pool.setUser("postgres");
		pool.setPassword("postgres");
		
		this.dataSource = pool;
	}

	public Connection getConnection() throws SQLException {
		//Connection connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/loja-virtual", "SA", "");
		Connection connection = dataSource.getConnection();
		return connection;
	}
}
