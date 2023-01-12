
public class Funcionario extends Pessoa {

	private String senha;

	public Funcionario(String nome, String cpf, String senha) {
		super(nome, cpf);
		this.senha = senha;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
