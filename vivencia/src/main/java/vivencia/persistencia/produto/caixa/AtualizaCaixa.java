package vivencia.persistencia.produto.caixa;

import java.io.Serializable;
import java.util.Date;

import org.prevayler.Transaction;

import vivencia.core.produto.lista.ListaDeCompras;
import vivencia.persistencia.*;

public class AtualizaCaixa implements Transaction<Persistence>, Serializable {  
    private static final long serialVersionUID = 9L;  

    private Date data;
	private ListaDeCompras lista;
  
    public AtualizaCaixa(ListaDeCompras lista, Date data) {
    	this.data = data;
    	this.lista = lista;
	}

	public void executeOn(Persistence persistence, Date date) {  
		persistence.adicionaHistoricoCaixa(data);
        persistence.atualizaCaixa(lista);  
    }
}
