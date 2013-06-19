package vivencia.core.produto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import vivencia.core.produto.lista.ListaDeCompras;

public class Estoque implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Map<Produto, Integer> estoqueProdutos = new HashMap<Produto, Integer>();
	private static Estoque estoque;
	
	private Estoque() {
	}
	
	public static Estoque getInstancia() {
		if(estoque == null)
			estoque = new Estoque();
		
		return estoque;
	}

	public Integer getEstoque(Produto produto) {
		return estoqueProdutos.get(produto);
	}

	public void atualizaEstoque(Produto produto, Integer quantidadeAtual) {
		estoqueProdutos.put(produto, quantidadeAtual);
	}
	
	public int getQuantidadeDeProdutos(){
		return estoqueProdutos.size();
	}
	
	public ListaDeCompras geraListaDeCompras(Date data){
		ListaDeCompras lista = new ListaDeCompras(data);
		for (Produto produto : estoqueProdutos.keySet()) {
			Integer quantidadeEmEstoque = getEstoque(produto);
			Integer quantidadeNecessaria = produto.getQuantidadeNecessaria();
			if(quantidadeEmEstoque < quantidadeNecessaria)
				lista.add(produto, quantidadeNecessaria - quantidadeEmEstoque);
		}
		
		return lista;
	}

	public void remove(Produto produto) {
		estoqueProdutos.remove(produto);
	}

	private void inicialiaEstoque(Map<Produto, Integer> estoqueProdutos){
		this.estoqueProdutos = estoqueProdutos;
	}
	
	public Estoque copy() {
		Estoque copy = new Estoque();
		copy.inicialiaEstoque(estoqueProdutos);
		return copy;
	}

}
