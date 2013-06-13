package persistencia.produto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import core.produto.Produto;

public class ListaDeProdutos implements Serializable {
	private static final long serialVersionUID = 1L;

	private Map<String, Produto> produtos = new LinkedHashMap<String, Produto>();

	public void add(Produto produto) {
		produtos.put(produto.getNome(), produto);
	}

	public Produto get(String nome) {
		return produtos.get(nome);
	}

	public int size() {
		return produtos.size();
	}

	@Override
	public String toString() {
		StringBuffer retorno = new StringBuffer();
		for (Produto produto : produtos.values())
			retorno.append(produto + "\r\n");
		return retorno.toString();
	}

	public void remove(String nome) {
		produtos.remove(nome);
	}
	
	public List<Produto> getProdutos(){
		return new ArrayList<Produto>(produtos.values());
	}

	public void atualizaProduto(String nomeAntigo, Produto produto) {
		produtos.remove(nomeAntigo);
		produtos.put(produto.getNome(), produto);
	}

}
