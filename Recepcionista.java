
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Recepcionista extends Funcionario {

	public Recepcionista(String nome, String cpf, String senha) {
		super(nome, cpf, senha);
	}

	public void menuRecepcionista() {

		// Cria o objeto para entrada de dados
		Scanner entrada = new Scanner(System.in);

		// Variáveis temporárias para os dados do cliente
		String nomeCliente, cpfCliente, emailCliente, telefoneCliente, enderecoCliente, placaCliente;

		// Variável para o loop dos menus
		Integer opcao = 0;

		do {

			// Opção do menu para recepcionista
			System.out.println("\n\t[0] - Encerrar Programa");
			System.out.println("\t[1] - Cadastrar Cliente");
			System.out.println("\t[2] - Visualizar Cliente");
			System.out.println("\t[3] - Cadastrar Ordem de Serviço");
			System.out.println("\t[4] - Visualizar Ordem de Seviço");
			System.out.print("\nInforme a sua opção: ");
			opcao = Integer.parseInt(entrada.nextLine());

			switch (opcao) {

			// Encerrar programa
			case 0:

				break;

			// Cadastar cliente
			case 1:

				// Entrada de informações do cliente
				System.out.println("\nInforme abaixo os dados do(a) cliente:\n");
				System.out.print("\tNome: ");
				nomeCliente = entrada.nextLine();
				System.out.print("\tCPF: ");
				cpfCliente = entrada.nextLine();
				System.out.print("\tE-Mail: ");
				emailCliente = entrada.nextLine();
				System.out.print("\tTelefone: ");
				telefoneCliente = entrada.nextLine();
				System.out.print("\tEndereço: ");
				enderecoCliente = entrada.nextLine();
				System.out.print("\tPlaca: ");
				placaCliente = entrada.nextLine();

				// Chama do método que cadastra cliente
				this.cadastrarCliente(nomeCliente, cpfCliente, emailCliente, telefoneCliente, enderecoCliente,
						placaCliente);

				break;

			// Visualizar cliente
			case 2:

				// Entrada do CPF do cliente
				System.out.print("\nInforme o CPF do(a) cliente: ");
				cpfCliente = entrada.nextLine();

				if (cpfCliente.length() == 11) {

					// Chamada do método que visualiza cliente
					this.visualizarCliente(cpfCliente);

				} else {

					// Caso o CPF não contenha 11 caracteres
					System.out.println("\nO CPF informado é inválido.");

				}

				break;

			// Cadastar ordem de serviço
			case 3:

				// Entrada do CPF do cliente
				System.out.print("\nInforme o CPF do(a) cliente: ");
				cpfCliente = entrada.nextLine();

				if (cpfCliente.length() == 11) {

					// Chama do método que cadastra ordem de serviço
					this.cadastrarOrdemDeServico(cpfCliente);

				} else {

					// Caso o CPF não contenha 11 caracteres
					System.out.println("\nO CPF informado é inválido.");

				}

				break;

			// Visualizar ordem de serviço
			case 4:

				// Entrada do CPF do cliente
				System.out.print("\nInforme o CPF do(a) cliente: ");
				cpfCliente = entrada.nextLine();

				if (cpfCliente.length() == 11) {

					// Chama do método que cadastra ordem de serviço
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

	// Cadastra um cliente no arquivo de dados
	public void cadastrarCliente(String nomeCliente, String cpfCliente, String emailCliente, String telefoneCliente,
			String endereçoCliente, String placaCliente) {

		// Cria um objeto com as informações do novo cliente
		Cliente novoCliente = new Cliente(nomeCliente, cpfCliente, emailCliente, telefoneCliente, endereçoCliente,
				placaCliente);

		// Cria o objeto para o diretório que armazena os arquivos de dados
		File diretorioDados = new File("/home/lurajo/Documents/Java/SistemaOficina/data");

		// Cria o objeto para o arquivo que armazena os dados
		File arquivoClientes = new File(diretorioDados, "Clientes.txt");

		// Código responsável pela escrita no arquivo
		try {

			// Caso o arquivo de dados exista, escreva novos dados no fim, caso não, crie e
			// escreva no começo
			if (arquivoClientes.exists()) {

				// Cria o objeto que armazena o arquivo na memória
				FileWriter arquivoEscrita = new FileWriter(arquivoClientes, true);

				// Escreve no fim do arquivo as informações do cliente, separadas por ;
				arquivoEscrita.append(novoCliente.getNome() + ";" + novoCliente.getCpf() + ";" + novoCliente.getEmail()
						+ ";" + novoCliente.getTelefone() + ";" + novoCliente.getEndereço() + ";"
						+ novoCliente.getPlaca() + "\n");

				// Fecha o objeto usado para escrever no arquivo
				arquivoEscrita.flush();
				arquivoEscrita.close();

			} else {

				// Cria um arquivo em branco para os dados
				arquivoClientes.createNewFile();

				// Cria o objeto que armazena o arquivo na memória
				FileWriter arquivoEscrita = new FileWriter(arquivoClientes, false);

				// Escreve no começo do arquivo as informações do cliente, separadas por ;
				arquivoEscrita.append(novoCliente.getNome() + ";" + novoCliente.getCpf() + ";" + novoCliente.getEmail()
						+ ";" + novoCliente.getTelefone() + ";" + novoCliente.getEndereço() + ";"
						+ novoCliente.getPlaca() + "\n");

				// Fecha o objetos usado para escrever no arquivo
				arquivoEscrita.flush();
				arquivoEscrita.close();

			}

			System.out.println("\nCliente cadastrado(a) com sucesso.");

		} catch (IOException arquivoFalha) {

			// Na falha de entrada e saída de dados, indique o erro
			arquivoFalha.printStackTrace();

		}

	}

	// Visualiza um cliente do arquivo de dados
	public void visualizarCliente(String cpfCliente) {

		// Cria o objeto para o diretório que armazena os arquivos de dados
		File diretorioDados = new File("/home/lurajo/Documents/Java/SistemaOficina/data");

		// Cria o objeto para o arquivo que armazena os dados
		File arquivoClientes = new File(diretorioDados, "Clientes.txt");

		// Variável que indica se o cliente foi encontrado
		Boolean encontrado = false;

		// Código responsável pela leitura do arquivo
		try {

			// Se o arquivo com dados dos clientes existe, leia, caso não, informe
			if (arquivoClientes.exists()) {

				// Cria o objeto que lê o conteúdo do arquivo
				FileReader arquivoLeitura = new FileReader(arquivoClientes);

				// Cria o objeto que armazena o arquivo na memória
				BufferedReader arquivoMemoria = new BufferedReader(arquivoLeitura);

				// Cria uma string provisória para uma linha lida do arquivo
				String linhaTemporaria;

				// Executa enquanto a linha lida do arquivo não for em branco
				while ((linhaTemporaria = arquivoMemoria.readLine()) != null) {

					// Caso a linha lida contenha o CPF procurado, imprima na tela
					if (linhaTemporaria.contains(cpfCliente)) {

						// Separa os dados do cliente e armazena em uma lista
						String[] dadosCliente = linhaTemporaria.split(";");

						// Imprime as informações do cliente na tela
						System.out.println("\nCliente encontrado(a) no banco de dados.\n");
						System.out.println("\tNome: " + dadosCliente[0]);
						System.out.println("\tCPF: " + dadosCliente[1]);
						System.out.println("\tE-Mail: " + dadosCliente[2]);
						System.out.println("\tTelefone: " + dadosCliente[3]);
						System.out.println("\tEndereço: " + dadosCliente[4]);
						System.out.println("\tPlaca: " + dadosCliente[5]);

						// Caso o cliente seja encontrado, retorne true
						encontrado = true;

					}

				}

				// Caso o cliente não foi encontrado, informe
				if (!encontrado) {

					System.out.println("\nCliente não encontrado(a) no banco de dados.");

				}

				// Fecha os objetos usados na leitura do arquivo
				arquivoLeitura.close();
				arquivoMemoria.close();

			} else {

				System.out.println("\nNão foi possível acessar o banco de dados de clientes.");

			}

		} catch (IOException arquivoFalha) {

			// Na falha de entrada e saída de dados, indique o erro
			arquivoFalha.printStackTrace();

		}

	}

	// Cadastra uma ordem de serviço
	public void cadastrarOrdemDeServico(String cpfCliente) {

		// Cria o objeto para o diretório que armazena os arquivos de dados
		File diretorioDados = new File("/home/lurajo/Documents/Java/SistemaOficina/data");

		// Cria o objeto para o arquivo que armazena os dados
		File arquivoOrcamentos = new File(diretorioDados, "Orcamentos.txt");

		// Variável que indica se o orçamento foi encontrado
		Boolean encontrado = false;

		// Código responsável pela leitura do arquivo
		try {

			// Se o arquivo com dados dos clientes existe, leia, caso não, informe
			if (arquivoOrcamentos.exists()) {

				// Cria o objeto que lê o conteúdo do arquivo
				FileReader arquivoLeitura = new FileReader(arquivoOrcamentos);

				// Cria o objeto que armazena o arquivo na memória
				BufferedReader arquivoMemoria = new BufferedReader(arquivoLeitura);

				// Cria uma string provisória para uma linha lida do arquivo
				String linhaTemporaria;

				// Cria uma lista que armazena em cada posição uma linha do arquivo
				List<String> arquivoLinhas = new ArrayList<String>();

				// Executa enquanto a linha lida do arquivo não for em branco
				while ((linhaTemporaria = arquivoMemoria.readLine()) != null) {

					// Usado no procedimento de remover um orçamento do banco de dados
					arquivoLinhas.add(linhaTemporaria);

					// Caso a linha lida contenha o CPF procurado, cadastre a ordem de serviço
					if (linhaTemporaria.contains(cpfCliente)) {

						// Separa os dados do orçamento e armazena em uma lista
						String[] dadosOrdemDeServico = linhaTemporaria.split(";");

						// Cria um objeto com as informações da nova ordem de serviço
						OrdemDeServico novaOrdemDeServico = new OrdemDeServico(dadosOrdemDeServico[0],
								dadosOrdemDeServico[1], dadosOrdemDeServico[2], dadosOrdemDeServico[3]);

						// Cria o objeto para o arquivo que armazena os dados
						File arquivoOrdemDeServico = new File(diretorioDados, "OrdensDeServicos.txt");

						// Código responsável pela escrita no arquivo
						try {

							// Caso o arquivo de dados exista, escreva novos dados no fim, caso não, crie e
							// escreva no começo
							if (arquivoOrdemDeServico.exists()) {

								// Cria o objeto que armazena o arquivo na memória
								FileWriter arquivoEscrita = new FileWriter(arquivoOrdemDeServico, true);

								// Escreve no fim do arquivo as informações da ordem de serviço, separadas por ;
								arquivoEscrita.append(novaOrdemDeServico.getCpfCliente() + ";"
										+ novaOrdemDeServico.getCpfMecanico() + ";" + novaOrdemDeServico.getValor()
										+ ";" + novaOrdemDeServico.getServico() + ";" + "Pendente" + "\n");

								// Fecha o objeto usado para escrever no arquivo
								arquivoEscrita.flush();
								arquivoEscrita.close();

							} else {

								// Cria um arquivo em branco para os dados
								arquivoOrdemDeServico.createNewFile();

								// Cria o objeto que armazena o arquivo na memória
								FileWriter arquivoEscrita = new FileWriter(arquivoOrdemDeServico, false);

								// Escreve no fim do arquivo as informações da ordem de serviço, separadas por ;
								arquivoEscrita.append(novaOrdemDeServico.getCpfCliente() + ";"
										+ novaOrdemDeServico.getCpfMecanico() + ";" + novaOrdemDeServico.getValor()
										+ ";" + novaOrdemDeServico.getServico() + ";" + "Pendente" + "\n");

								// Fecha o objetos usado para escrever no arquivo
								arquivoEscrita.flush();
								arquivoEscrita.close();

							}

							System.out.println("\nOrdem de Serviço cadastrada com sucesso.");

						} catch (IOException arquivoFalha) {

							// Na falha de entrada e saída de dados, indique o erro
							arquivoFalha.printStackTrace();

						}

						// Caso o orçamento seja encontrado, retorne true
						encontrado = true;

					}

				}

				// Cria o objeto que armazena o arquivo na memória
				FileWriter arquivoEscrita = new FileWriter(arquivoOrcamentos, false);

				// Execute para todos os elementos na array
				for (String arquivoLinha : arquivoLinhas) {

					// Se a linha lida não contém o CPF do cliente, reescreva no arquivo
					if (!arquivoLinha.contains(cpfCliente)) {

						// Escreve no fim arquivo as informações do orçamento
						arquivoEscrita.append(arquivoLinha + "\n");

					}

				}

				// Fecha os objetos usados para escrever no arquivo
				arquivoEscrita.flush();
				arquivoEscrita.close();

				// Caso o orçamento não foi encontrado, informe
				if (!encontrado) {

					System.out.println("\nOrçamento não encontrado no banco de dados.");

				}

				// Fecha os objetos usados na leitura do arquivo
				arquivoLeitura.close();
				arquivoMemoria.close();

			} else {

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
