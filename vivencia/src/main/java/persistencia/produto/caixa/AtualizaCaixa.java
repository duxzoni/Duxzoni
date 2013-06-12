package persistencia.produto.caixa;

import java.io.Serializable;
import java.util.Date;

import org.prevayler.Transaction;

import persistencia.Persistence;

public class AtualizaCaixa implements Transaction<Persistence>, Serializable {  
    private static final long serialVersionUID = 9L;  

  
    public AtualizaCaixa() {

	}

	public void executeOn(Persistence lista, Date date) {  
        lista.atualizaCaixa();  
    }
}
