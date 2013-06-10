package persistencia.produto;

import java.io.Serializable;
import java.util.Date;

import org.prevayler.Transaction;

import persistencia.Persistence;

public class SalvaHistoricoEstoque  implements Transaction<Persistence>, Serializable {  
    private static final long serialVersionUID = 3L;  
    private Date now;
  
    public SalvaHistoricoEstoque(Date now) {
		this.now = now;
		
	}

	public void executeOn(Persistence lista, Date date) {  
		lista.adicionaHistoricoEstoque(now);  
    }

}  