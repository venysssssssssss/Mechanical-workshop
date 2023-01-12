
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class SistemaOficina {

	public static void main(String[] args) {

		// Cria o objeto para entrada de dados
		Scanner entrada = new Scanner(System.in);

		// Menu do sistema
		System.out.println("\n\t[0] - Encerrar Programa");
		System.out.println("\t[1] - Fazer Login");
		System.out.print("\nInforme sua opção: ");
		Integer opcao = Integer.parseInt(entrada.nextLine());

		// Com base na opção escolhida, o programa é executado
		switch (opcao) {

		// O programa é encerrado caso 0 seja informado
		case 0:

			System.out.println("\nPrograma encerrado com sucesso!");
			break;

		// O programa pede as credeciais do usuário caso 1 seja informado
		case 1:

			// Varíavel para confirmar se o usuário é válido
			Boolean usuarioEncontrado = false;

			// Enquanto o usuário e senhas não forem válidos, repita
			do {

				// Entrada das credenciais do usuário
				System.out.println("\nInforme as suas credeciais abaixo:");
				System.out.print("\n\tCPF: ");
				String cpfUsuario = entrada.nextLine();
				System.out.print("\tSenha: ");
				String senhaUsuario = entrada.nextLine();

				// Cria o objeto para o diretório que armazena os arquivos de dados
				File diretorioDados = new File("/home/lurajo/Documents/Java/SistemaOficina/data");

				// Cria o objeto para o arquivo que armazena os dados
				File arquivoFuncionarios = new File(diretorioDados, "Funcionarios.txt");

				// Código responsável pela leitura do arquivo
				try {

					// Se o arquivo com dados dos funcionários existe, leia
					if (arquivoFuncionarios.exists()) {

						// Cria o objeto que lê o conteúdo do arquivo
						FileReader arquivoLeitura = new FileReader(arquivoFuncionarios);

						// Cria o objeto que armazena o arquivo na memória
						BufferedReader arquivoMemoria = new BufferedReader(arquivoLeitura);

						// Cria uma string provisória para uma linha lida do arquivo
						String linhaTemporaria;

						// Executa enquanto a linha lida do arquivo não for em branco
						while ((linhaTemporaria = arquivoMemoria.readLine()) != null) {

							// Separa os dados do funcionário e armazena em uma lista
							String[] dadosFuncionario = linhaTemporaria.split(";");

							// Se o usuario e senha existir no banco, dê acesso ao funcionário
							if (dadosFuncionario[1].contentEquals(cpfUsuario)
									&& dadosFuncionario[2].contentEquals(senhaUsuario)) {

								// O usuário é válido, altere a variável
								usuarioEncontrado = true;

								// Armazena qual é o cargo do funcionário
								String cargoUsuario = dadosFuncionario[3];

								// Mensagem de boas-vindas ao funcionário
								System.out.println("\nSeja bem-vindo(a), " + dadosFuncionario[0] + "!");

								// Executa o método de menu correspondente ao cargo
								switch (cargoUsuario) {

								// Caso o cargo seja Gerente, execute o menu correspondente
								case "Gerente":

									new Gerente(dadosFuncionario[0], dadosFuncionario[1], dadosFuncionario[2])
											.menuGerente();
									break;

								// Caso o cargo seja Recepcionista, execute o menu correspondente
								case "Recepcionista":

									new Recepcionista(dadosFuncionario[0], dadosFuncionario[1], dadosFuncionario[2])
											.menuRecepcionista();
									break;

								// Caso o cargo seja Mecânico, execute o menu correspondente
								case "Mecânico":

									new Mecanico(dadosFuncionario[0], dadosFuncionario[1], dadosFuncionario[2])
											.menuMecanico();
									break;

								// Caso o cargo cadastrado seja inválido, informe
								default:

									System.out.println("\nFuncionário(a) cadastrado(a) com cargo inválido.");
									System.out.println("Entre em contato com o(a) Gerente ou Suporte do Sistema.");
									System.out.println("\nPrograma encerrado com sucesso!");
									break;

								}

								// Encerra o loop caso o usuário termine a seção
								opcao = 0;
								break;

							}

						}

						// Caso o usuário e senha forem incorretos, informe
						if (!usuarioEncontrado) {

							System.out.println("\nCPF ou senha incorretos. Deseja tentar novamente?");
							System.out.println("\n\t[0] - Encerrar Programa");
							System.out.println("\t[1] - Fazer Login");
							System.out.print("\nInforme sua opção: ");
							opcao = Integer.parseInt(entrada.nextLine());

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

			} while (opcao != 0);

			// O programa é encerrado caso 0 seja informado
			if (opcao == 0) {

				System.out.println("\nPrograma encerrado com sucesso!");

			}

			break;

		// Caso seja informada uma opção inválida, informe
		default:

			System.out.println("\nA opção escolhida é inválida.");
			break;

		}

		// Encerra o objeto de entrada de dados
		entrada.close();

	}

}
