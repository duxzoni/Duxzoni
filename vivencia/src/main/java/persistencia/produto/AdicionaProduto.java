package persistencia.produto;

import java.io.Serializable;  
import java.math.BigDecimal;
import java.util.Date;  
  
import org.prevayler.Transaction;  

import persistencia.Persistence;

import core.produto.Produto;
import core.produto.TipoProduto;
  
public class AdicionaProduto implements Transaction<Persistence>, Serializable {  
    private static final long serialVersionUID = 3L;  
    private String nome;
	private Integer quantidadeNecessaria;
	private BigDecimal precoVenda;  
	private TipoProduto tipoProduto;  
  
    
  
    public AdicionaProduto(String nome, Integer quantidadeNecessaria,
			BigDecimal precoVenda, TipoProduto tipoProduto) {
		this.nome = nome;
		this.quantidadeNecessaria = quantidadeNecessaria;
		this.precoVenda = precoVenda;
		this.tipoProduto = tipoProduto;
	}

	public void executeOn(Persistence lista, Date date) {  
		if(lista.getProduto(nome)!= null)
			throw new RuntimeException("Produto já cadastrado");
		
		lista.addProduto(new Produto(nome, quantidadeNecessaria, precoVenda, tipoProduto));  
    }

}  