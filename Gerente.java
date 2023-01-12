
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gerente extends Funcionario {

	public Gerente(String nome, String cpf, String senha) {
		super(nome, cpf, senha);
	}

	public void menuGerente() {

		// Cria o objeto para entrada de dados
		Scanner entrada = new Scanner(System.in);

		// Variáveis temporárias para os dados do funcionário
		String nomeFuncionario, cpfFuncionario, senhaFuncionario, cargoFuncionario;

		// Variável temporária para os dados do cliente e mecânico
		String cpfCliente;

		// Variável para o loop dos menus
		Integer opcao = 0;

		do {

			// Opções do menu para gerente
			System.out.println("\n\t[0] - Encerrar Programa");
			System.out.println("\t[1] - Cadastrar Funcionário");
			System.out.println("\t[2] - Visualizar Funcionário");
			System.out.println("\t[3] - Apagar Funcionário");
			System.out.println("\t[4] - Visualizar Cliente");
			System.out.println("\t[5] - Apagar Cliente");
			System.out.println("\t[6] - Concluir Ordem de Serviço");
			System.out.print("\nInforme a sua opção: ");
			opcao = Integer.parseInt(entrada.nextLine());

			switch (opcao) {

			// Encerrar programa
			case 0:

				break;

			// Cadastar funcionário
			case 1:

				// Entrada de informações do funcionário
				System.out.println("\nInforme abaixo os dados do(a) funcionário(a):");
				System.out.print("\n\tNome: ");
				nomeFuncionario = entrada.nextLine();
				System.out.print("\tCPF: ");
				cpfFuncionario = entrada.nextLine();
				System.out.print("\tSenha: ");
				senhaFuncionario = entrada.nextLine();
				System.out.print("\tCargo: ");
				cargoFuncionario = entrada.nextLine();

				// Chamada do método que cadastra funcionário
				this.cadastrarFuncionario(nomeFuncionario, cpfFuncionario, senhaFuncionario, cargoFuncionario);

				break;

			// Visualizar funcionário
			case 2:

				// Entrada do CPF do funcionário
				System.out.print("\nInforme o CPF do(a) funcionário(a): ");
				cpfFuncionario = entrada.nextLine();

				if (cpfFuncionario.length() == 11) {

					// Chamada do método que visualiza funcionário
					this.visualizarFuncionario(cpfFuncionario);

				} else {

					// Caso o CPF não contenha 11 caracteres
					System.out.println("\nO CPF informado é inválido.");

				}

				break;

			// Apagar funcionário
			case 3:

				// Entrada do CPF do funcionário
				System.out.print("\nInforme o CPF do(a) funcionário(a): ");
				cpfFuncionario = entrada.nextLine();

				if (cpfFuncionario.length() == 11) {

					// Chamada do método que visualiza funcionário
					this.apagarFuncionario(cpfFuncionario);

				} else {

					// Caso o CPF não contenha 11 caracteres
					System.out.println("\nO CPF informado é inválido.");

				}

				break;

			// Visualizar cliente
			case 4:

				// Entrada do CPF do cliente
				System.out.print("\nInforme o CPF do(a) cliente: ");
				cpfCliente = entrada.nextLine();

				if (cpfCliente.length() == 11) {

					// Chamada do método que visualiza o cliente
					this.visualizarCliente(cpfCliente);

				} else {

					// Caso o CPF não contenha 11 caracteres
					System.out.println("\nO CPF informado é inválido.");

				}

				break;

			// Apagar cliente
			case 5:

				// Entrada do CPF do cliente
				System.out.print("\nInforme o CPF do(a) cliente: ");
				cpfCliente = entrada.nextLine();

				if (cpfCliente.length() == 11) {

					// Chamada do método que apaga o cliente
					this.apagarCliente(cpfCliente);

				} else {

					// Caso o CPF não contenha 11 caracteres
					System.out.println("\nO CPF informado é inválido.");

				}

				break;

			// Concluir ordem de serviço
			case 6:

				// Entrada do CPF do cliente
				System.out.print("\nInforme o CPF do(a) cliente: ");
				cpfCliente = entrada.nextLine();

				if (cpfCliente.length() == 11) {

					// Chama do método que aprova ordem de serviço
					this.concluirOrdemDeServico(cpfCliente);

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

	// Cadastra um funcionário no arquivo de dados
	public void cadastrarFuncionario(String nomeFuncionario, String cpfFuncionario, String senhaFuncionario,
			String cargoFuncionario) {

		// Cria um objeto com as informações do funcionário
		Funcionario novoFuncionario = new Funcionario(nomeFuncionario, cpfFuncionario, senhaFuncionario);

		// Cria o objeto para o diretório que armazena os arquivos de dados
		File diretorioDados = new File("/home/lurajo/Documents/Java/SistemaOficina/data");

		// Cria o objeto para o arquivo que armazena os dados
		File arquivoFuncionarios = new File(diretorioDados, "Funcionarios.txt");

		// Código responsável pela escrita no arquivo
		try {

			// Caso o arquivo de dados exista, escreva novos dados no fim, caso não, crie e
			// escreva no começo
			if (arquivoFuncionarios.exists()) {

				// Cria o objeto que armazena o arquivo na memória
				FileWriter arquivoEscrita = new FileWriter(arquivoFuncionarios, true);

				// Escreve no fim do arquivo as informações do funcionário, separadas por ;
				arquivoEscrita.append(novoFuncionario.getNome() + ";" + novoFuncionario.getCpf() + ";"
						+ novoFuncionario.getSenha() + ";" + cargoFuncionario + "\n");

				// Fecha o objeto usado para escrever no arquivo
				arquivoEscrita.flush();
				arquivoEscrita.close();

			} else {

				// Cria um arquivo em branco para os dados
				arquivoFuncionarios.createNewFile();

				// Cria o objeto que armazena o arquivo na memória
				FileWriter arquivoEscrita = new FileWriter(arquivoFuncionarios, false);

				// Escreve no começo do arquivo as informações do funcionário, separadas por ;
				arquivoEscrita.append(novoFuncionario.getNome() + ";" + novoFuncionario.getCpf() + ";"
						+ novoFuncionario.getSenha() + ";" + cargoFuncionario + "\n");

				// Fecha o objetos usado para escrever no arquivo
				arquivoEscrita.flush();
				arquivoEscrita.close();

			}

			System.out.println("\nFuncionário(a) cadastrado(a) com sucesso!");

		} catch (IOException arquivoFalha) {

			// Na falha de entrada e saída de dados, indique o erro
			arquivoFalha.printStackTrace();

		}

	}

	// Visualiza um funcionário do arquivo de dados
	public void visualizarFuncionario(String cpfFuncionario) {

		// Cria o objeto para o diretório que armazena os arquivos de dados
		File diretorioDados = new File("/home/lurajo/Documents/Java/SistemaOficina/data");

		// Cria o objeto para o arquivo que armazena os dados
		File arquivoFuncionarios = new File(diretorioDados, "Funcionarios.txt");

		// Variável que indica se o funcionário foi encontrado
		Boolean encontrado = false;

		// Código responsável pela leitura do arquivo
		try {

			// Se o arquivo com dados dos funcionários existe, leia, caso não, informe
			if (arquivoFuncionarios.exists()) {

				// Cria o objeto que lê o conteúdo do arquivo
				FileReader arquivoLeitura = new FileReader(arquivoFuncionarios);

				// Cria o objeto que armazena o arquivo na memória
				BufferedReader arquivoMemoria = new BufferedReader(arquivoLeitura);

				// Cria uma string provisória para uma linha lida do arquivo
				String linhaTemporaria;

				// Executa enquanto a linha lida do arquivo não for em branco
				while ((linhaTemporaria = arquivoMemoria.readLine()) != null) {

					// Caso a linha lida contenha o CPF procurado, imprima na tela
					if (linhaTemporaria.contains(cpfFuncionario)) {

						// Separa os dados do funcionário e armazena em uma lista
						String[] dadosFuncionario = linhaTemporaria.split(";");

						// Imprime as informações do funcionário na tela
						System.out.println("\nFuncionário(a) encontrado(a) no banco de dados.\n");
						System.out.println("\tNome: " + dadosFuncionario[0]);
						System.out.println("\tCPF: " + dadosFuncionario[1]);
						// dadosFuncionario[2] é a senha do funcionário
						System.out.println("\tCargo: " + dadosFuncionario[3]);

						// Caso o funcionário seja encontrado, retorne true
						encontrado = true;

					}

				}

				// Caso o funcionário não foi encontrado, informe
				if (!encontrado) {

					System.out.println("\nFuncionário(a) não encontrado(a) no banco de dados.");

				}

				// Fecha os objetos usados na leitura do arquivo
				arquivoLeitura.close();
				arquivoMemoria.close();

			} else {

				// Caso não seja encontrado o banco de dados de funcionários, informe
				System.out.println("\nNão foi possível acessar o banco de dados de funcionários.");

			}

		} catch (IOException arquivoFalha) {

			// Na falha de entrada e saída de dados, indique o erro
			arquivoFalha.printStackTrace();

		}

	}

	// Apaga um funcionário do arquivo de dados
	public void apagarFuncionario(String cpfFuncionario) {

		// Cria o objeto para o diretório que armazena os arquivos de dados
		File diretorioDados = new File("/home/lurajo/Documents/Java/SistemaOficina/data");

		// Cria o objeto para o arquivo que armazena os dados
		File arquivoFuncionarios = new File(diretorioDados, "Funcionarios.txt");

		// Código responsável por apagar o funcionário do arquivo
		try {

			// Se o arquivo com as informações dos funcionários existe, apague o procurado
			if (arquivoFuncionarios.exists()) {

				// Cria o objeto que lê o conteúdo do arquivo
				FileReader arquivoLeitura = new FileReader(arquivoFuncionarios);

				// Cria o objeto que armazena o arquivo na memória
				BufferedReader arquivoMemoria = new BufferedReader(arquivoLeitura);

				// Cria uma string provisória para uma linha lida do arquivo
				String linhaTemporaria;

				// Cria uma lista que armazena em cada posição uma linha do arquivo
				List<String> arquivoLinhas = new ArrayList<String>();

				// Executa enquanto a linha lida do arquivo não for em branco, continue
				while ((linhaTemporaria = arquivoMemoria.readLine()) != null) {

					arquivoLinhas.add(linhaTemporaria);

				}

				// Fecha os objetos usados na leitura do arquivo
				arquivoLeitura.close();
				arquivoMemoria.close();

				// Cria o objeto que armazena o arquivo na memória
				FileWriter arquivoEscrita = new FileWriter(arquivoFuncionarios, false);

				// Contador para o número de funcionários que foram reescritos no arquivo
				Integer contador = 0;

				// Execute para todos os elementos na array
				for (String arquivoLinha : arquivoLinhas) {

					// Se a linha lida não contém o CPF do funcionário, reescreva no arquivo
					if (!arquivoLinha.contains(cpfFuncionario)) {

						// Escreve no fim arquivo as informações do funcionário
						arquivoEscrita.append(arquivoLinha + "\n");

						// Incrementa o contador caso o funcionário foi reescrito
						contador++;

					}

				}

				// Caso o CPF do funcionário for encontrado, informe
				if (arquivoLinhas.size() - 1 == contador) {

					System.out.println("\nFuncionário(a) removido(a) com sucesso!");

				} else {

					// Informa que o funcionário não foi encontrado
					System.out.println("\nFuncionário(a) não encontrado(a) no banco de dados.");

				}

				// Fecha os objetos usados para escrever no arquivo
				arquivoEscrita.flush();
				arquivoEscrita.close();

			} else {

				System.out.println("\nNão foi possível acessar o banco de dados de funcionários.");

			}

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

				// Caso não seja encontrado o banco de dados de clientes, informe
				System.out.println("\nNão foi possível acessar o banco de dados de clientes.");

			}

		} catch (IOException arquivoFalha) {

			// Na falha de entrada e saída de dados, indique o erro
			arquivoFalha.printStackTrace();

		}

	}

	// Apaga um cliente do arquivo de dados
	public void apagarCliente(String cpfCliente) {

		// Cria o objeto para o diretório que armazena os arquivos de dados
		File diretorioDados = new File("/home/lurajo/Documents/Java/SistemaOficina/data");

		// Cria o objeto para o arquivo que armazena os dados
		File arquivoClientes = new File(diretorioDados, "Clientes.txt");

		// Código responsável por apagar o cliente do arquivo
		try {

			// Se o arquivo com as informações dos clientes existe, apague o procurado
			if (arquivoClientes.exists()) {

				// Cria o objeto que lê o conteúdo do arquivo
				FileReader arquivoLeitura = new FileReader(arquivoClientes);

				// Cria o objeto que armazena o arquivo na memória
				BufferedReader arquivoMemoria = new BufferedReader(arquivoLeitura);

				// Cria uma string provisória para uma linha lida do arquivo
				String linhaTemporaria;

				// Cria uma lista que armazena em cada posição uma linha do arquivo
				List<String> arquivoLinhas = new ArrayList<String>();

				// Executa enquanto a linha lida do arquivo não for em branco, continue
				while ((linhaTemporaria = arquivoMemoria.readLine()) != null) {

					arquivoLinhas.add(linhaTemporaria);

				}

				// Fecha os objetos usados na leitura do arquivo
				arquivoLeitura.close();
				arquivoMemoria.close();

				// Cria o objeto que armazena o arquivo na memória
				FileWriter arquivoEscrita = new FileWriter(arquivoClientes, false);

				// Contador para o número de funcionários que foram reescritos no arquivo
				Integer contador = 0;

				// Execute para todos os elementos na array
				for (String arquivoLinha : arquivoLinhas) {

					// Se a linha lida não contém o CPF do cliente, reescreva no arquivo
					if (!arquivoLinha.contains(cpfCliente)) {

						// Escreve no fim arquivo as informações do cliente
						arquivoEscrita.append(arquivoLinha + "\n");

						// Incrementa o contador caso o cliente foi reescrito
						contador++;

					}

				}

				// Caso o CPF do cliente for encontrado, informe
				if (arquivoLinhas.size() - 1 == contador) {

					System.out.println("\nCliente removido(a) com sucesso!");

				} else {

					// Informa que o cliente não foi encontrado
					System.out.println("\nCliente não encontrado(a) no banco de dados.");

				}

				// Fecha os objetos usados para escrever no arquivo
				arquivoEscrita.flush();
				arquivoEscrita.close();

			} else {

				System.out.println("\nNão foi possível acessar o banco de dados de clientes.");

			}

		} catch (IOException arquivoFalha) {

			// Na falha de entrada e saída de dados, indique o erro
			arquivoFalha.printStackTrace();

		}

	}

	// Concliu uma ordem de serviço, remove ela e o cliente de seus bancos de dados
	public void concluirOrdemDeServico(String cpfCliente) {

		// Cria o objeto para o diretório que armazena os arquivos de dados
		File diretorioDados = new File("/home/lurajo/Documents/Java/SistemaOficina/data");

		// Cria o objeto para o arquivo que armazena os dados
		File arquivoOrdemDeServico = new File(diretorioDados, "OrdensDeServicos.txt");

		// Cria o objeto para o arquivo que armazena os dados
		File arquivoClientes = new File(diretorioDados, "Clientes.txt");

		// Código responsável pela leitura do arquivo
		try {

			// Se o arquivo com dados das ordens de serviços existe, leia, caso não, informe
			if (arquivoOrdemDeServico.exists() && arquivoClientes.exists()) {

				// Cria o objeto que lê o conteúdo do arquivo
				FileReader arquivoLeituraOrdem = new FileReader(arquivoOrdemDeServico);

				// Cria o objeto que armazena o arquivo na memória
				BufferedReader arquivoMemoriaOrdem = new BufferedReader(arquivoLeituraOrdem);

				// Cria uma string provisória para uma linha lida do arquivo
				String linhaTemporariaOrdem;

				// Cria uma lista que armazena em cada posição uma linha do arquivo
				List<String> arquivoLinhasOrdem = new ArrayList<String>();

				// Executa enquanto a linha lida do arquivo não for em branco, continue
				while ((linhaTemporariaOrdem = arquivoMemoriaOrdem.readLine()) != null) {

					arquivoLinhasOrdem.add(linhaTemporariaOrdem);

				}

				// Fecha os objetos usados na leitura do arquivo
				arquivoLeituraOrdem.close();
				arquivoMemoriaOrdem.close();

				// Cria o objeto que lê o conteúdo do arquivo
				FileReader arquivoLeituraCliente = new FileReader(arquivoClientes);

				// Cria o objeto que armazena o arquivo na memória
				BufferedReader arquivoMemoriaCliente = new BufferedReader(arquivoLeituraCliente);

				// Cria uma string provisória para uma linha lida do arquivo
				String linhaTemporariaCliente;

				// Cria uma lista que armazena em cada posição uma linha do arquivo
				List<String> arquivoLinhasCliente = new ArrayList<String>();

				// Executa enquanto a linha lida do arquivo não for em branco, continue
				while ((linhaTemporariaCliente = arquivoMemoriaCliente.readLine()) != null) {

					arquivoLinhasCliente.add(linhaTemporariaCliente);

				}

				// Fecha os objetos usados na leitura do arquivo
				arquivoLeituraCliente.close();
				arquivoMemoriaCliente.close();

				// Cria o objeto que armazena o arquivo na memória
				FileWriter arquivoEscritaOrdem = new FileWriter(arquivoOrdemDeServico, false);

				// Contador para o número de ordens de serviço que foram reescritas no arquivo
				Integer contadorOrdem = 0;

				// Execute para todos os elementos na array
				for (String arquivoLinhaOrdem : arquivoLinhasOrdem) {

					// Se a linha lida não contém o CPF do cliente, reescreva no arquivo
					if (!arquivoLinhaOrdem.contains(cpfCliente)) {

						// Escreve no fim arquivo as informações da ordem de serviço
						arquivoEscritaOrdem.append(arquivoLinhaOrdem + "\n");

						// Incrementa o contador caso a ordem foi reescrita
						contadorOrdem++;

					}

				}

				// Cria o objeto que armazena o arquivo na memória
				FileWriter arquivoEscritaCliente = new FileWriter(arquivoClientes, false);

				// Contador para o número de clientes que foram reescritos no arquivo
				Integer contadorCliente = 0;

				// Execute para todos os elementos na array
				for (String arquivoLinhaCliente : arquivoLinhasCliente) {

					// Se a linha lida não contém o CPF do cliente, reescreva no arquivo
					if (!arquivoLinhaCliente.contains(cpfCliente)) {

						// Escreve no fim arquivo as informações do cliente
						arquivoEscritaCliente.append(arquivoLinhaCliente + "\n");

						// Incrementa o contador caso o cliente foi reescrito
						contadorCliente++;

					}

				}

				// Caso o CPF do cliente for encontrado, informe
				if (arquivoLinhasOrdem.size() - 1 == contadorOrdem
						&& arquivoLinhasCliente.size() - 1 == contadorCliente) {

					System.out.println("\nOrdem de serviço e cliente removidos com sucesso!");

				} else {

					// Informa que o cliente não foi encontrado
					System.out.println("\nOrdem de Serviço ou cliente não encontrados no banco de dados.");

				}

				// Fecha os objetos usados para escrever no arquivo
				arquivoEscritaOrdem.flush();
				arquivoEscritaOrdem.close();
				arquivoEscritaCliente.flush();
				arquivoEscritaCliente.close();

			} else {

				// Caso não seja encontrado o banco de dados de ordens de serviços, informe
				System.out.println("\nNão foi possível acessar o banco de dados de ordens de serviço ou clientes.");

			}

		} catch (IOException arquivoFalha) {

			// Na falha de entrada e saída de dados, indique o erro
			arquivoFalha.printStackTrace();

		}

	}

}
