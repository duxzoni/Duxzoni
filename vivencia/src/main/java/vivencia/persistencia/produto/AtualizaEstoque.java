package vivencia.persistencia.produto;

import java.util.Date;

import org.prevayler.Transaction;

import vivencia.core.produto.Estoque;
import vivencia.persistencia.Persistence;

public class AtualizaEstoque implements Transaction<Persistence> {

	private static final long serialVersionUID = -1479868242875772131L;
	private Estoque estoque;
	
	public AtualizaEstoque(Estoque estoque) {
		this.estoque = estoque;
	}
	
	public void executeOn(Persistence prevalentSystem, Date executionTime) {
		prevalentSystem.atualizaEstoque(estoque);
		
	}

}
