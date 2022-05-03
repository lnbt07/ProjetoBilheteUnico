package br.fiap.main;

import br.fiap.bilhete.BilheteUnico;
import br.fiap.tipo.TipoDeUsuario;
import br.fiap.usuario.Usuario;
import static javax.swing.JOptionPane.*;//importa tudo que for static da classe JOptionPane
import java.util.Random;
import static java.lang.Integer.parseInt;//importar apenas o método Static p arseInt
import static java.lang.Double.parseDouble;

public class Main {

	// variável global --> atributo --> objeto
	// variável global STATIC não é atributo --> não precisa de objeto
	private static BilheteUnico[] bilhete = new BilheteUnico[10];
	private static int posicao = 0;

	public static void main(String[] args) {

		String entrada;
		int indice;

		do {
			entrada = showInputDialog("Senha ou CPF ou SAIR");
			if (entrada.equals("admin")) {
				moduloAdministrativo();
			} else if (!entrada.equalsIgnoreCase("sair")) {
				indice = consultarCPF(entrada);
				if (indice == -1) { // -1 = CPF não cadastrado
					showMessageDialog(null, "CPF não cadastrado");
				} else {
					moduloUsuario(indice);
				}
			}
		} while (!entrada.equalsIgnoreCase("sair"));

	}

	private static void moduloUsuario(int indice) {
		int opcao;

		do {
			opcao = parseInt(showInputDialog(menuUsuario()));
			if (opcao < 1 || opcao > 4) {
				showMessageDialog(null, "Opção inválida!");
			} else {
				switch (opcao) {
				case 1: // carregar bilhete
					carregarBilhete(indice);
					break;

				case 2: // passar na catraca
					passarNaCatraca(indice);
					break;

				case 3: // consultar saldo do bilhete
					consultarSaldo(indice);
					break;
				}
			}
		} while (opcao != 4);
	}

	private static void passarNaCatraca(int indice) {
		TipoDeUsuario tipo = bilhete[indice].getUsuario().getTipo();
		if (tipo.equals(TipoDeUsuario.NORMAL)) {
			if (bilhete[indice].getSaldo() >= BilheteUnico.getValordapassagem()) {
				bilhete[indice].passarNaCatraca();
				consultarSaldo(indice);
			} else {
				showMessageDialog(null, "Saldo Insuficiente");
			}
		} else {
			if (bilhete[indice].getSaldo() >= BilheteUnico.getValordapassagem() / 2) {
				bilhete[indice].passarNaCatraca();
				consultarSaldo(indice);
			} else {
				showMessageDialog(null, "Saldo Insuficiente");
			}
		}
	}

	private static void carregarBilhete(int indice) {
		bilhete[indice].carregarBilhete(parseDouble(showInputDialog("Insira o valor para carregar o bilhete: ")));

	}

	private static void consultarSaldo(int indice) {
		showMessageDialog(null, String.format("%.2f", bilhete[indice].getSaldo()));
	}

	public static void moduloAdministrativo() {

		int opcao;

		do {
			opcao = parseInt(showInputDialog(menuAdministrativo()));
			if (opcao < 1 || opcao > 4) {
				showMessageDialog(null, "Opção inválida!");
			} else {
				switch (opcao) { // opcao (int e/ou variações) ou char ou string
				case 1:
					emitirBilhete();
					break;
				case 2:
					imprimirBilhete();
					break;
				case 3:
					consultarBilhete();
					break;
				}

			}
		} while (opcao != 4);

	}

	private static void consultarBilhete() {
		String cpf = showInputDialog("Informe o CPF para consulta: ");
		int indice = consultarCPF(cpf);
		if (indice == -1) {
			showMessageDialog(null, "CPF não encontrado");
		} else {
			showMessageDialog(null, bilhete[indice].getDados());
		}
	}

	private static void imprimirBilhete() {
		String aux = "";
		for (int i = 0; i < posicao; i++) {
			aux += bilhete[i].getDados() + "\n \n";
		}
		showMessageDialog(null, aux);
	}

	private static void emitirBilhete() {
		Random gerador = new Random();
		int numero;
		TipoDeUsuario tipo = null;
		String nome;
		int tipoaux;

		String cpf = showInputDialog("Informe o CPF para consulta");
		if (consultarCPF(cpf) != -1) { // -1 significa q o user n está cadastrado
			showMessageDialog(null, "CPF já tem bilhete emitido");
		} else {
			numero = gerador.nextInt(10000);
			nome = showInputDialog("Nome do Usuário:");
			tipoaux = parseInt(showInputDialog("1. Tarifa Normal \n2. Tarifa Estudante \n3. Tarifa Professor"));
			switch (tipoaux) {// só faz teste de igualdade
			case 1:
				tipo = TipoDeUsuario.NORMAL;
				break;

			case 2:
				tipo = TipoDeUsuario.ESTUDANTE;
				break;

			case 3:
				tipo = TipoDeUsuario.PROFESSOR;
				break;
			}

			// gerar os objetos e armazena no vetor(de objetos)
			Usuario usuario = new Usuario(nome, cpf, tipo);
			bilhete[posicao] = new BilheteUnico(numero, usuario, 0);
			posicao++;
		}

	}

	public static int consultarCPF(String cpf) {
		int aux = -1; // cpf not found

		for (int i = 0; i < posicao; i++) {
			if (bilhete[i].getUsuario().getCpf().equals(cpf)) {
				aux = i;
				break;
			}
		}

		return aux;
	}

	public static String menuAdministrativo() {
		String aux = "Escolha uma opção: \n";
		aux += "1. Emitir bilhete \n";
		aux += "2. Imprimir bilhete \n";
		aux += "3. Consultar bilhete \n";
		aux += "4. Sair";
		return aux;
	}

	public static String menuUsuario() {
		String aux = "Escolha uma opção \n";
		aux += "1. Carregar bilhete \n";
		aux += "2. Passar na catraca \n";
		aux += "3. Consultar saldo \n";
		aux += "4. Sair";
		return aux;
	}

}
