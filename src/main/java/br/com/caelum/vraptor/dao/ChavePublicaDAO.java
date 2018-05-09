package br.com.caelum.vraptor.dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.caelum.vraptor.model.ChavePublica;

public class ChavePublicaDAO {
	
	private Connection connection;

	public ChavePublicaDAO(Connection connection) {
		
		this.connection = connection;
	}
	
	public void Insert(ChavePublica chavePublica) throws SQLException {
			
		String sql = "insert into chavepublica (e,n) values (?, ?)";
		
		PreparedStatement statement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		statement.setInt(1, chavePublica.getE());
		statement.setInt(2, chavePublica.getN());

		
		statement.execute();
		
		//Retornando chavePublica com id
		
		ResultSet resultSet = statement.getGeneratedKeys();
		
		resultSet.next();
		int id = resultSet.getInt("id");
		
		chavePublica.setId(id);	
	}

	public void Update(ChavePublica chavePublica) throws SQLException {
		
		String sql = "update chavepublica set e = ?, n = ? where id = ?";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, chavePublica.getE());
		statement.setInt(2, chavePublica.getN());
		statement.setInt(3, chavePublica.getId());
		
		statement.execute();
	}
	
	public void InsertOrUpdate(ChavePublica chavePublica) throws SQLException {
		
		if(chavePublica.getId() > 0) {
			Update(chavePublica);
		}else {
			Insert(chavePublica);
		}
	}
	
	public void Delete(ChavePublica chavePublica) throws SQLException {
		
		String sql = "delete from chavePublica where id = ?";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, chavePublica.getId());
		
		statement.execute();
	}
	
	public ArrayList<ChavePublica> lista() throws SQLException{
		ArrayList<ChavePublica> chavePublicas = new ArrayList<>();

		String sql = "select * from chavePublica";
		
		Statement statement = this.connection.createStatement();
		boolean resultado = statement.execute(sql);
		
		ResultSet resultSet = statement.getResultSet();
		
		while(resultSet.next()) {
			
			int id = resultSet.getInt("id");
			int e = resultSet.getInt("e");
			int d = resultSet.getInt("n");
			
			ChavePublica chavePublica = new ChavePublica(id,e,d);
			
			chavePublicas.add(chavePublica);
		}	
		return chavePublicas;
	}
	
}

