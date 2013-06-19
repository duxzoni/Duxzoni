package vivencia.persistencia.produto.caixa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vivencia.core.produto.caixa.Caixa;
import vivencia.core.produto.caixa.HistoricoCaixa;

public class HistoricosCaixa implements Serializable{
	
	private static final long serialVersionUID = 8L;
	
	private Map<Date,HistoricoCaixa> historicos = new HashMap<Date, HistoricoCaixa>();
	
	public void addHistorico(Date data, Caixa caixa) {
		historicos.put(data, new HistoricoCaixa(caixa, data));
	}
	
	public HistoricoCaixa getHistorico( Date date) {
		return historicos.get(date);
	}
	
	public List<HistoricoCaixa> getHistoricos(Date dataInicio, Date dataFim) {
		List<HistoricoCaixa> historicosFiltrados = new ArrayList<HistoricoCaixa>();
		
		for (Date data : historicos.keySet()) {
			int compareToInicio = data.compareTo(dataInicio);
			int compareToFim = data.compareTo(dataFim);
			if (compareToInicio >= 0 || compareToFim <= 0)
				historicosFiltrados.add(getHistorico(data));
		}
		
		return historicosFiltrados;
	}
	
}
