package persistencia.produto;

import java.io.Serializable;
import java.util.Date;

import org.prevayler.Transaction;

import persistencia.Persistence;

public class RemoveProduto implements Transaction<Persistence>, Serializable {  
    private static final long serialVersionUID = 3L;  
    private String nome;
  
    public RemoveProduto(String nome) {
		this.nome = nome;
	}

	public void executeOn(Persistence lista, Date date) {  
		lista.removeProduto(nome);  
    }
}