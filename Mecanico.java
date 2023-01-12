
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Mecanico extends Funcionario {

	public Mecanico(String nome, String cpf, String senha) {
		super(nome, cpf, senha);
	}

	public void menuMecanico() {

		// Cria o objeto para entrada de dados
		Scanner entrada = new Scanner(System.in);

		// Variáveis temporárias para os dados do orçamento
		String cpfCliente, cpfMecanico, valor, servico;

		// Variável para o loop dos menus
		Integer opcao = 0;

		do {

			// Opção do menu para mecânico
			System.out.println("\n\t[0] - Encerrar Programa");
			System.out.println("\t[1] - Cadastrar Orçamento");
			System.out.println("\t[2] - Visualizar Orçamento");
			System.out.println("\t[3] - Visualizar Ordem de Seviço");
			System.out.print("\nInforme a sua opção: ");
			opcao = Integer.parseInt(entrada.nextLine());

			switch (opcao) {

			// Encerrar programa
			case 0:

				break;

			// Cadastar orçamento
			case 1:

				// Entrada de informações do orçamento
				System.out.println("\nInforme abaixo os dados do orçamento:\n");
				System.out.print("\tCPF do(a) Cliente: ");
				cpfCliente = entrada.nextLine();
				System.out.print("\tCPF do(a) Mecânico(a): ");
				cpfMecanico = entrada.nextLine();
				System.out.print("\tValor do Serviço: ");
				valor = entrada.nextLine();
				System.out.print("\tServiço: ");
				servico = entrada.nextLine();

				// Chama do método que cadastra orçamento
				this.cadastrarOrcamento(cpfCliente, cpfMecanico, valor, servico);

				break;

			// Visualizar orçamento
			case 2:

				// Entrada do CPF do cliente
				System.out.print("\nInforme o CPF do(a) cliente: ");
				cpfCliente = entrada.nextLine();

				if (cpfCliente.length() == 11) {

					// Chamada do método que visualiza orçamento
					this.visualizarOrcamento(cpfCliente);

				} else {

					// Caso o CPF não contenha 11 caracteres
					System.out.println("\nO CPF informado é inválido.");

				}

				break;

			// Visualizar ordem de serviço
			case 3:

				// Entrada do CPF do cliente
				System.out.print("\nInforme o CPF do(a) cliente: ");
				cpfCliente = entrada.nextLine();

				if (cpfCliente.length() == 11) {

					// Chamada do método que visualiza orçamento
					this.visualizarOrdemDeServico(cpfCliente);

				} else {

					// Caso o CPF não contenha 11 caracteres
					System.out.println("\nO CPF informado é inválido.");

				}

				break;

			// Opção inválida
			default:

				System.out.println("\nA opção escolhida é inválida.");
				break;

			}

		} while (opcao != 0);

		// Encerra o objeto de entrada de dados
		entrada.close();

	}

	// Cadastra um orçamento no arquivo de dados
	public void cadastrarOrcamento(String cpfCliente, String cpfMecanico, String valor, String servico) {

		// Cria um objeto com as informações do orçamento
		Orcamento novoOrcamento = new Orcamento(cpfCliente, cpfMecanico, valor, servico);

		// Cria o objeto para o diretório que armazena os arquivos de dados
		File diretorioDados = new File("/home/lurajo/Documents/Java/SistemaOficina/data");

		// Cria o objeto para o arquivo que armazena os dados
		File arquivoOrcamentos = new File(diretorioDados, "Orcamentos.txt");

		// Código responsável pela escrita no arquivo
		try {

			// Caso o arquivo de dados exista, escreva novos dados no fim, caso não, crie e
			// escreva no começo
			if (arquivoOrcamentos.exists()) {

				// Cria o objeto que armazena o arquivo na memória
				FileWriter arquivoEscrita = new FileWriter(arquivoOrcamentos, true);

				// Escreve no fim do arquivo as informações do orcamento, separadas por ;
				arquivoEscrita.append(novoOrcamento.getCpfCliente() + ";" + novoOrcamento.getCpfMecanico() + ";"
						+ novoOrcamento.getValor() + ";" + novoOrcamento.getServico() + "\n");

				// Fecha o objeto usado para escrever no arquivo
				arquivoEscrita.flush();
				arquivoEscrita.close();

			} else {

				// Cria um arquivo em branco para os dados
				arquivoOrcamentos.createNewFile();

				// Cria o objeto que armazena o arquivo na memória
				FileWriter arquivoEscrita = new FileWriter(arquivoOrcamentos, false);

				// Escreve no começo do arquivo as informações do funcionário, separadas por ;
				arquivoEscrita.append(novoOrcamento.getCpfCliente() + ";" + novoOrcamento.getCpfMecanico() + ";"
						+ novoOrcamento.getValor() + ";" + novoOrcamento.getServico() + "\n");

				// Fecha o objetos usado para escrever no arquivo
				arquivoEscrita.flush();
				arquivoEscrita.close();

			}

			System.out.println("\nOrçamento cadastrado com sucesso!");

		} catch (IOException arquivoFalha) {

			// Na falha de entrada e saída de dados, indique o erro
			arquivoFalha.printStackTrace();

		}

	}

	// Visualiza um orçamento no arquivo de dados
	public void visualizarOrcamento(String cpfMecanico) {

		// Cria o objeto para o diretório que armazena os arquivos de dados
		File diretorioDados = new File("/home/lurajo/Documents/Java/SistemaOficina/data");

		// Cria o objeto para o arquivo que armazena os dados
		File arquivoOrcamentos = new File(diretorioDados, "Orcamentos.txt");

		// Variável que indica se o orçamento foi encontrado
		Boolean encontrado = false;

		// Código responsável pela leitura do arquivo
		try {

			// Se o arquivo com dados dos orçamentos existe, leia, caso não, informe
			if (arquivoOrcamentos.exists()) {

				// Cria o objeto que lê o conteúdo do arquivo
				FileReader arquivoLeitura = new FileReader(arquivoOrcamentos);

				// Cria o objeto que armazena o arquivo na memória
				BufferedReader arquivoMemoria = new BufferedReader(arquivoLeitura);

				// Cria uma string provisória para uma linha lida do arquivo
				String linhaTemporaria;

				// Executa enquanto a linha lida do arquivo não for em branco
				while ((linhaTemporaria = arquivoMemoria.readLine()) != null) {

					// Caso a linha lida contenha o CPF procurado, imprima na tela
					if (linhaTemporaria.contains(cpfMecanico)) {

						// Separa os dados do orçamento e armazena em uma lista
						String[] dadosOrcamento = linhaTemporaria.split(";");

						// Imprime as informações do orçamento na tela
						System.out.println("\nOrçamento encontrado no banco de dados.\n");
						System.out.println("\tCPF Cliente: " + dadosOrcamento[0]);
						System.out.println("\tCPF Mecânico: " + dadosOrcamento[1]);
						System.out.println("\tValor: " + dadosOrcamento[2]);
						System.out.println("\tServiço: " + dadosOrcamento[3]);

						// Caso o orçamento seja encontrado, retorne true
						encontrado = true;

					}

				}

				// Caso o orcamento não foi encontrado, informe
				if (!encontrado) {

					System.out.println("\nOrcamento não encontrado no banco de dados.");

				}

				// Fecha os objetos usados na leitura do arquivo
				arquivoLeitura.close();
				arquivoMemoria.close();

			} else {

				// Caso não seja encontrado o banco de dados de orçamentos, informe
				System.out.println("\nNão foi possível acessar o banco de dados de orçamentos.");

			}

		} catch (IOException arquivoFalha) {

			// Na falha de entrada e saída de dados, indique o erro
			arquivoFalha.printStackTrace();

		}

	}

	// Visualiza uma ordem de serviço no arquivo de dados
	public void visualizarOrdemDeServico(String cpfMecanico) {

		// Cria o objeto para o diretório que armazena os arquivos de dados
		File diretorioDados = new File("/home/lurajo/Documents/Java/SistemaOficina/data");

		// Cria o objeto para o arquivo que armazena os dados
		File arquivoOrdemDeServico = new File(diretorioDados, "OrdensDeServicos.txt");

		// Variável que indica se a ordem de serviço foi encontrada
		Boolean encontrado = false;

		// Código responsável pela leitura do arquivo
		try {

			// Se o arquivo com dados das ordens de serviços existe, leia, caso não, informe
			if (arquivoOrdemDeServico.exists()) {

				// Cria o objeto que lê o conteúdo do arquivo
				FileReader arquivoLeitura = new FileReader(arquivoOrdemDeServico);

				// Cria o objeto que armazena o arquivo na memória
				BufferedReader arquivoMemoria = new BufferedReader(arquivoLeitura);

				// Cria uma string provisória para uma linha lida do arquivo
				String linhaTemporaria;

				// Executa enquanto a linha lida do arquivo não for em branco
				while ((linhaTemporaria = arquivoMemoria.readLine()) != null) {

					// Caso a linha lida contenha o CPF procurado, imprima na tela
					if (linhaTemporaria.contains(cpfMecanico)) {

						// Separa os dados da ordem de serviço e armazena em uma lista
						String[] dadosOrdemDeServico = linhaTemporaria.split(";");

						// Imprime as informações da ordem de serviço na tela
						System.out.println("\nOrdem de serviço encontrada no banco de dados.\n");
						System.out.println("\tCPF Cliente: " + dadosOrdemDeServico[0]);
						System.out.println("\tCPF Mecânico: " + dadosOrdemDeServico[1]);
						System.out.println("\tServiço: " + dadosOrdemDeServico[3]);
						System.out.println("\tSituação: " + dadosOrdemDeServico[4]);

						// Caso a ordem de serviço seja encontrada, retorne true
						encontrado = true;

					}

				}

				// Caso a ordem de serviço não foi encontrada, informe
				if (!encontrado) {

					System.out.println("\nOrdem de serviço não encontrada no banco de dados.");

				}

				// Fecha os objetos usados na leitura do arquivo
				arquivoLeitura.close();
				arquivoMemoria.close();

			} else {

				// Caso não seja encontrado o banco de dados de ordens de serviços, informe
				System.out.println("\nNão foi possível acessar o banco de dados de ordens de serviço.");

			}

		} catch (IOException arquivoFalha) {

			// Na falha de entrada e saída de dados, indique o erro
			arquivoFalha.printStackTrace();

		}

	}

}
