
public class Orcamento {

	protected String cpfCliente, cpfMecanico, valor, servico;

	public Orcamento(String cpfCliente, String cpfMecanico, String valor, String servico) {
		this.cpfCliente = cpfCliente;
		this.cpfMecanico = cpfMecanico;
		this.valor = valor;
		this.servico = servico;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public String getCpfMecanico() {
		return cpfMecanico;
	}

	public void setCpfMecanico(String cpfMecanico) {
		this.cpfMecanico = cpfMecanico;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getServico() {
		return servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}

}
