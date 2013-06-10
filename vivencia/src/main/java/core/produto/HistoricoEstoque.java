package core.produto;

import java.util.Date;

public class HistoricoEstoque {

	private Estoque estoque;

	public Estoque getEstoque() {
		return estoque;
	}

	public HistoricoEstoque(Estoque estoque, Date data) {
		super();
		this.estoque = estoque;
		this.data = data;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	private Date data;

}
