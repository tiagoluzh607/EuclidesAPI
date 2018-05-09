package br.com.caelum.vraptor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.caelum.vraptor.model.Mensagem;

public class MensagemDAO {
	
	private Connection connection;

	public MensagemDAO(Connection connection) {
		
		this.connection = connection;
	}
	
	public void Insert(Mensagem mensagem) throws SQLException {
			
		String sql = "insert into mensagem (conteudo) values (?)";
		
		PreparedStatement statement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, mensagem.getConteudo());

		
		statement.execute();
		
		//Retornando mensagem com id
		
		ResultSet resultSet = statement.getGeneratedKeys();
		
		resultSet.next();
		int id = resultSet.getInt("id");
		
		mensagem.setId(id);	
	}

	public void Update(Mensagem mensagem) throws SQLException {
		
		String sql = "update mensagem set conteudo = ? where id = ?";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, mensagem.getConteudo());
		statement.setInt(2, mensagem.getId());
		
		statement.execute();
	}
	
	public void InsertOrUpdate(Mensagem mensagem) throws SQLException {
		
		if(mensagem.getId() > 0) {
			Update(mensagem);
		}else {
			Insert(mensagem);
		}
	}
	
	public void Delete(Mensagem mensagem) throws SQLException {
		
		String sql = "delete from mensagem where id = ?";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, mensagem.getId());
		
		statement.execute();
	}
	
	public ArrayList<Mensagem> lista() throws SQLException{
		ArrayList<Mensagem> mensagems = new ArrayList<>();

		String sql = "select * from mensagem";
		
		Statement statement = this.connection.createStatement();
		boolean resultado = statement.execute(sql);
		
		ResultSet resultSet = statement.getResultSet();
		
		while(resultSet.next()) {
			
			int id = resultSet.getInt("id");
			String conteudo = resultSet.getString("conteudo");
			
			Mensagem mensagem = new Mensagem(id,conteudo);
			
			mensagems.add(mensagem);
		}	
		return mensagems;
	}
	
}
