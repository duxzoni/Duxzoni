package persistencia.produto;

import java.io.Serializable;
import java.util.Date;

import org.prevayler.Transaction;

import persistencia.Persistence;
import core.produto.Produto;

public class AtualizaProduto  implements Transaction<Persistence>, Serializable {  
    private static final long serialVersionUID = 11L;  
   
	private Produto produto;
	private String nomeAntigo;
  
    
  
    public AtualizaProduto(String nomeAntigo, Produto produto) {
		this.produto = produto;
		this.nomeAntigo = nomeAntigo;
	}

	public void executeOn(Persistence lista, Date date) {  
		lista.atualizaProduto(nomeAntigo, produto);  
    }

}
