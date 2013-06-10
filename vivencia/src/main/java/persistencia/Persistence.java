package persistencia;

import java.io.Serializable;
import java.util.Date;

import core.produto.Estoque;
import core.produto.Produto;
import persistencia.produto.HistoricosEstoque;
import persistencia.produto.ListaDeProdutos;

public class Persistence implements Serializable {

	private static final long serialVersionUID = 3L;
	private ListaDeProdutos produtos = new ListaDeProdutos();
	private Estoque estoque = Estoque.getInstancia();
	private HistoricosEstoque hitoricoEstoques = new HistoricosEstoque();

	public void addProduto(Produto produto) {
		produtos.add(produto);
		atualizaEstoqueProduto(produto, 0);
	}

	public Produto getProduto(String nome) {
		return produtos.get(nome);
	}

	public int quantidadeDeProdutos() {
		return produtos.size();
	}

	@Override
	public String toString() {
		return produtos.toString();
	}

	public void removeProduto(String nome) {
		estoque.remove(produtos.get(nome));
		produtos.remove(nome);

	}

	public void atualizaEstoqueProduto(Produto produto, Integer quantidade) {
		estoque.atualizaEstoque(produto, quantidade);
	}
	
	public void adicionaHistoricoEstoque(Date data) {
		hitoricoEstoques.addHistorico(data, estoque.copy());

	}
}
