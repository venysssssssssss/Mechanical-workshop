
public class Cliente extends Pessoa {

	private String email, telefone, endereço, placa;

	public Cliente(String nome, String cpf, String email, String telefone, String endereço, String placa) {
		super(nome, cpf);
		this.email = email;
		this.telefone = telefone;
		this.endereço = endereço;
		this.placa = placa;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereço() {
		return endereço;
	}

	public void setEndereço(String endereço) {
		this.endereço = endereço;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

}
