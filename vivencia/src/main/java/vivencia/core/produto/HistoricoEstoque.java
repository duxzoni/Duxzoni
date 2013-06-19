package vivencia.core.produto;

import java.io.Serializable;
import java.util.Date;

import vivencia.util.DateManager;

public class HistoricoEstoque implements Serializable, Comparable<HistoricoEstoque>{

	private static final long serialVersionUID = -6009012898890255208L;
	private Estoque estoque;
	private Date data;

	public HistoricoEstoque(Estoque estoque, Date data) {
		super();
		this.estoque = estoque;
		this.data = data;
	}

	public Estoque getEstoque() {
		return estoque;
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

	public int compareTo(HistoricoEstoque hist) {
		return getData().compareTo(hist.getData());
	}

	@Override
	public String toString() {
		return DateManager.ddmmaaaahhMMssSeparated(getData());
	}
}
