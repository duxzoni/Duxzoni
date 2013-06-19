package vivencia.persistencia.produto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vivencia.core.produto.Estoque;
import vivencia.core.produto.HistoricoEstoque;

public class HistoricosEstoque implements Serializable {

	private static final long serialVersionUID = 6L;

	private Map<Date, HistoricoEstoque> historicos = new HashMap<Date, HistoricoEstoque>();

	public void addHistorico(Date data, Estoque estoque) {
		historicos.put(data, new HistoricoEstoque(estoque, data));
	}

	public HistoricoEstoque getHistorico(Date date) {
		return historicos.get(date);
	}

	public List<HistoricoEstoque> getHistoricos(Date dataInicio, Date dataFim) {
		List<HistoricoEstoque> historicosFiltrados = new ArrayList<HistoricoEstoque>();

		for (Date data : historicos.keySet()) {
			int compareToInicio = 0;
			if (dataInicio != null)
				compareToInicio = data.compareTo(dataInicio);

			int compareToFim = -1;
			if (dataFim != null)
				compareToFim = data.compareTo(dataFim);

			if (compareToInicio >= 0 && compareToFim < 0)
				historicosFiltrados.add(getHistorico(data));
		}

		return historicosFiltrados;
	}

}
