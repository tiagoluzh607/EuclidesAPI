package br.com.caelum.vraptor.model;

public class Mensagem {

	
	private int id;
	private String conteudo;
	
	public Mensagem() {
		
	}
	
	public Mensagem(String conteudo) {
		this.conteudo = conteudo;
	}
	
	public Mensagem(int id, String conteudo) {
		this(conteudo);
		this.id = id;
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	
	
	
}
