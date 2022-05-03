package br.fiap.usuario;

import br.fiap.tipo.TipoDeUsuario;

public class Usuario {
	private String nome;
	private String cpf;
	private TipoDeUsuario tipo;

	public Usuario(String nome, String cpf, TipoDeUsuario tipo) {
		this.nome = nome;
		this.cpf = cpf;
		this.tipo = tipo;
	}
	
	public String getDados() {
		String aux = "";
		aux+= "Nome -> "+nome+"\n";
		aux+= "CPF -> "+cpf+"\n";
		aux+= "Tipo de usuário -> "+tipo+"\n";
		return aux;
	}

	public TipoDeUsuario getTipo() {
		return tipo;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setTipo(TipoDeUsuario tipo) {
		this.tipo = tipo;
	}
	
	
	
}
