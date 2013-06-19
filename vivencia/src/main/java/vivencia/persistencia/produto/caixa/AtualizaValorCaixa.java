package vivencia.persistencia.produto.caixa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.prevayler.Transaction;

import vivencia.persistencia.Persistence;

public class AtualizaValorCaixa implements Transaction<Persistence>, Serializable {  
    private static final long serialVersionUID = 9L;  

    private BigDecimal valor;
  
    public AtualizaValorCaixa(BigDecimal valor) {
    	this.valor = valor;
	}

	public void executeOn(Persistence persistence, Date date) {  
		persistence.getCaixa().setValor(valor);
    }
}
