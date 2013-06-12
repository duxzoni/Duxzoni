package persistencia.produto.caixa;

import java.io.Serializable;
import java.util.Date;

import org.prevayler.Transaction;

import persistencia.Persistence;

public class SalvaHistoricoCaixa  implements Transaction<Persistence>, Serializable {  
    private static final long serialVersionUID = 10L;  
    private Date now;
  
    public SalvaHistoricoCaixa(Date now) {
		this.now = now;
		
	}

	public void executeOn(Persistence lista, Date date) {  
		lista.adicionaHistoricoCaixa(now);  
    }
}
