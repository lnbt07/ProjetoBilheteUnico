package br.fiap.bilhete;

import br.fiap.tipo.TipoDeUsuario;
import br.fiap.usuario.Usuario;

public class BilheteUnico {
	private int numero;
	private Usuario usuario;
	private double saldo;
	private final static double valorDaPassagem = 4.40; //constante, inalterável durante a execução
	
	public BilheteUnico(int numero, Usuario usuario, double saldo) {
		this.numero = numero;
		this.usuario = usuario;
		this.saldo = saldo;
	}
	
	public void carregarBilhete(double valor) {
		saldo += valor;
	}
	
	public void passarNaCatraca() {
		if(usuario.getTipo().equals(TipoDeUsuario.NORMAL)) {
			saldo-= valorDaPassagem;
		} else {
			saldo -= valorDaPassagem/2;
		}
	}
	
	public String getDados() {
		String aux = "";
		aux += "Número do Bilhete -> "+numero+"\n";
		aux += usuario.getDados();
		aux += "Saldo -> R$ "+String.format("%.2f", saldo); //2f = 2 pontos flutuantes, número real
		return aux;
	}

	public int getNumero() {
		return numero;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public double getSaldo() {
		return saldo;
	}

	public static double getValordapassagem() {
		return valorDaPassagem;
	}
	
	
}
