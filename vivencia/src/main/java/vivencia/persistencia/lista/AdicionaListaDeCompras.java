package vivencia.persistencia.lista;

import java.util.Date;

import org.prevayler.Transaction;

import vivencia.core.produto.lista.ListaDeCompras;
import vivencia.persistencia.Persistence;

public class AdicionaListaDeCompras implements Transaction<Persistence> {

	private static final long serialVersionUID = 8600571119233846784L;
	private ListaDeCompras lista;

	public AdicionaListaDeCompras(ListaDeCompras lista) {
		this.lista = lista;
	}

	public void executeOn(Persistence prevalentSystem, Date executionTime) {
		prevalentSystem.adicionaListaDeCompras(lista);
		
	}

}
