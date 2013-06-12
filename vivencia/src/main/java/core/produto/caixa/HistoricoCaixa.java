package core.produto.caixa;

import java.util.Date;

public class HistoricoCaixa {

	private Caixa caixa;
	private Date data;

	public HistoricoCaixa(Caixa caixa, Date data) {
		super();
		this.caixa = caixa;
		this.data = data;
	}

	public Caixa getCaixa() {
		return caixa;
	}

	public void setCaixa(Caixa caixa) {
		this.caixa = caixa;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
