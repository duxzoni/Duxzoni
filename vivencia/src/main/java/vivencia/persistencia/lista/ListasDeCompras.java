package vivencia.persistencia.lista;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import vivencia.core.produto.lista.ListaDeCompras;

public class ListasDeCompras implements Serializable {

	private static final long serialVersionUID = -2074410268916440441L;
	private Map<Date, ListaDeCompras> listas = new LinkedHashMap<Date, ListaDeCompras>();

	public void add(ListaDeCompras lista) {
		listas.put(lista.getData(), lista);
	}

	public ListaDeCompras get(Date data) {
		return listas.get(data);
	}

	public int size() {
		return listas.size();
	}

	public void remove(String nome) {
		listas.remove(nome);
	}

	public List<ListaDeCompras> getProdutos() {
		return new ArrayList<ListaDeCompras>(listas.values());
	}

	public void atualizaLista(ListaDeCompras lista) {
		listas.remove(lista.getData());
		listas.put(lista.getData(), lista);
	}

	public ListaDeCompras getUltimaListaDeCompras() {
		Date max = Collections.max(listas.keySet());
		return get(max);
	}

	public List<ListaDeCompras> getListas(Date de, Date ate) {
		List<ListaDeCompras> listasRetorno = new ArrayList<ListaDeCompras>();

		for (Date data : listas.keySet()) {
			int compareToInicio = 0;
			if (de != null)
				compareToInicio = data.compareTo(de);

			int compareToFim = -1;
			if (ate != null)
				compareToFim = data.compareTo(ate);

			if (compareToInicio >= 0 && compareToFim < 0)
				listasRetorno.add(get(data));
		}
		return listasRetorno;
	}
}
