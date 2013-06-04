package core.produto;

import java.util.HashMap;
import java.util.Map;

public class Estoque {
	
	private Map<Produto, Integer> estoque = new HashMap<Produto, Integer>();

	public Map<Produto, Integer> getEstoque() {
		return estoque;
	}

	public void atualizaEstoque(Produto produto, Integer quantidadeAtual) {
		estoque.put(produto, quantidadeAtual);
	}

}
