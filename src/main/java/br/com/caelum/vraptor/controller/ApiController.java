package br.com.caelum.vraptor.controller;

import java.sql.Connection;
import java.util.ArrayList;

import javax.inject.Inject;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.dao.ChavePublicaDAO;
import br.com.caelum.vraptor.dao.ConnectionPool;
import br.com.caelum.vraptor.dao.MensagemDAO;
import br.com.caelum.vraptor.model.ChavePublica;
import br.com.caelum.vraptor.model.Mensagem;
import br.com.caelum.vraptor.view.Results;


@Controller
public class ApiController {

	
	private final Result result; //usado para redirecionar usuario e adicionar parametros para view
	
	@Inject
 	public ApiController(Result result) {
 	    this.result = result;
 	}
	
 	public ApiController() { //necessario criar um construtor sem argumentos
 		this(null);
 	}
	
 	
	@Post("/chave")
	@Consumes("application/json")
	public void adicionaChave(ChavePublica chavePublica) {
		try(Connection connection = new ConnectionPool().getConnection()){
			chavePublica.setId(1);
			new ChavePublicaDAO(connection).InsertOrUpdate(chavePublica);
			result.use(Results.http()).setStatusCode(200);
			
		} catch (Exception e) {
			result.use(Results.http()).sendError(500);
		}
	}
 	
	@Get("/chave")
	public void buscaChave() {
		
		try(Connection connection = new ConnectionPool().getConnection()){
			ChavePublica chavePublica = new ChavePublicaDAO(connection).lista().get(0);
			result.use(Results.json()).withoutRoot().from(chavePublica).serialize();
			
		} catch (Exception e) {
			result.use(Results.http()).sendError(500);
		}
	}
	
	@Post("/mensagem")
	@Consumes("application/json")
	public void adicionaMensagem(Mensagem mensagem) {
		try(Connection connection = new ConnectionPool().getConnection()){
			mensagem.setId(1);
			new MensagemDAO(connection).InsertOrUpdate(mensagem);
			result.use(Results.http()).setStatusCode(200);
			
		} catch (Exception e) {
			result.use(Results.http()).sendError(500);
		}
	}
 	
	@Get("/mensagem")
	public void buscaMensagem() {
		
		try(Connection connection = new ConnectionPool().getConnection()){
			Mensagem mensagem = new MensagemDAO(connection).lista().get(0);
			result.use(Results.json()).withoutRoot().from(mensagem).serialize();
			
		} catch (Exception e) {
			result.use(Results.http()).sendError(500);
		}
	}
	
}
