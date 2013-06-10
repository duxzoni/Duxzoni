package persistencia.produto;

import java.io.Serializable;  
import java.math.BigDecimal;
import java.util.Date;  
  
import org.prevayler.Transaction;  

import persistencia.Persistence;

import core.produto.Produto;
  
public class AdicionaProduto implements Transaction<Persistence>, Serializable {  
    private static final long serialVersionUID = 3L;  
    private String nome;
	private Integer quantidadeNecessaria;
	private BigDecimal precoVenda;  
  
    
  
    public AdicionaProduto(String nome, Integer quantidadeNecessaria,
			BigDecimal precoVenda) {
		this.nome = nome;
		this.quantidadeNecessaria = quantidadeNecessaria;
		this.precoVenda = precoVenda;
	}

	public void executeOn(Persistence lista, Date date) {  
		if(lista.getProduto(nome)!= null)
			throw new RuntimeException("Produto já cadastrado");
		
		lista.addProduto(new Produto(nome, quantidadeNecessaria, precoVenda));  
    }

}  