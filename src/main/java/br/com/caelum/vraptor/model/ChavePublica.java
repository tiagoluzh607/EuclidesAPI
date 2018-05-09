package br.com.caelum.vraptor.model;

public class ChavePublica {

	private int id;
	private int e;
	private int n;
	
	public ChavePublica() {
		
	}
	
	public ChavePublica(int e, int n) {
		this.e = e;
		this.n = n;
	}
	
	public ChavePublica(int id, int e, int n) {
		this(e,n);
		this.id = id;
	}
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getE() {
		return e;
	}
	public void setE(int e) {
		this.e = e;
	}
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
	
	
	
	
}
