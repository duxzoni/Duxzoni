package persistencia.produto;

import java.io.Serializable;
import java.util.Date;

import org.prevayler.Transaction;

import persistencia.Persistence;
import core.produto.Produto;

public class AtualizaEstoqueProduto implements Transaction<Persistence>, Serializable {  
	    private static final long serialVersionUID = 3L;  
	    private Produto produto;
		private Integer quantidade;
		  
	  
	    
	  
	    public AtualizaEstoqueProduto(Produto produto, Integer quantidade) {
			this.produto = produto;
			this.quantidade = quantidade;
		}

		public void executeOn(Persistence lista, Date date) {  
	        lista.atualizaEstoqueProduto(produto, quantidade);  
	    }
}
