package br.com.mph.mphutils.tests.reflection;

public class Client {

	private String identification;
	
	private String nome;

	public Client(String cpf, String nome) {
		super();
		this.identification = cpf;
		this.nome = nome;
	}

	public String getIdentification() {
		return identification;
	}

	public String getNome() {
		return nome;
	}
	
	
}
