package br.com.caelum.vraptor.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.dao.ChavePublicaDAO;
import br.com.caelum.vraptor.dao.ConnectionPool;
import br.com.caelum.vraptor.dao.MensagemDAO;
import br.com.caelum.vraptor.model.ChavePublica;
import br.com.caelum.vraptor.model.Mensagem;

@Controller
public class IndexController {

	
	public IndexController() {
		
	}
	
	public void home() throws SQLException {
		
		try(Connection connection = new ConnectionPool().getConnection()){
			
			
			ChavePublicaDAO chavePublicaDao = new ChavePublicaDAO(connection);
			MensagemDAO mensagemDAO = new MensagemDAO(connection);
			
			ChavePublica chavePublica = new ChavePublica(13,437);
			Mensagem mensagem = new Mensagem("teste");
			
			mensagemDAO.InsertOrUpdate(mensagem);
			chavePublicaDao.InsertOrUpdate(chavePublica);
			
			
			ArrayList<Mensagem> lista = mensagemDAO.lista();
			ArrayList<ChavePublica> lista2 = chavePublicaDao.lista();
			
			
		}
		
		
	}
}
