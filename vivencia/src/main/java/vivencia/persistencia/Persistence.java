package vivencia.persistencia;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import vivencia.core.produto.Estoque;
import vivencia.core.produto.HistoricoEstoque;
import vivencia.core.produto.Produto;
import vivencia.core.produto.caixa.Caixa;
import vivencia.core.produto.lista.ListaDeCompras;
import vivencia.persistencia.lista.ListasDeCompras;
import vivencia.persistencia.produto.HistoricosEstoque;
import vivencia.persistencia.produto.ListaDeProdutos;
import vivencia.persistencia.produto.caixa.HistoricosCaixa;






public class Persistence implements Serializable {

	private static final long serialVersionUID = 3L;
	private ListaDeProdutos produtos = new ListaDeProdutos();
	private Estoque estoque = Estoque.getInstancia();
	private HistoricosEstoque historicoEstoques = new HistoricosEstoque();
	private Caixa caixa = Caixa.getInstance();
	private HistoricosCaixa historicoCaixas = new HistoricosCaixa();
	private ListasDeCompras listasDeCompras = new ListasDeCompras();
	

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

	public void removeProduto(String nome) {
		estoque.remove(produtos.get(nome));
		produtos.remove(nome);

	}

	public void atualizaEstoqueProduto(Produto produto, Integer quantidade) {
		estoque.atualizaEstoque(produto, quantidade);
	}

	public void atualizaEstoque(Estoque estoque) {
		for(Produto produto : produtos.getProdutos()){
			Integer anterior = this.estoque.getEstoque(produto);
			Integer atual = estoque.getEstoque(produto);
			//TODO FAZER A ATUALIZAÇÃO DO CAIXA AQUI DE ACORDO COM O VALOR COBRADO OU PAGO
		}
		
		this.estoque = estoque;
		
		
	}
	
	
	public Estoque getEstoque() {
		return estoque;
	}

	public void adicionaHistoricoEstoque(Date data) {
		historicoEstoques.addHistorico(data, estoque.copy());
	}

	public List<HistoricoEstoque> getHistoricoEstoques(Date dataInicio, Date dataFim) {
		return historicoEstoques.getHistoricos(dataInicio, dataFim);
	}
	
	
	public void atualizaCaixa(ListaDeCompras lista) {
		caixa.atualizaValorEmCaixa(estoque, lista);
	}
	
	public void adicionaHistoricoCaixa(Date data) {
		historicoCaixas.addHistorico(data, caixa.copy());
		
	}

	public ListaDeProdutos getProdutos() {
		return produtos;
	}

	public void atualizaProduto(String nomeAntigo, Produto produto) {
		produtos.atualizaProduto(nomeAntigo, produto);
	}

	public void adicionaListaDeCompras(ListaDeCompras lista) {
		listasDeCompras.add(lista);
	}

	public ListaDeCompras getUltimaListaDeCompras() {
		return listasDeCompras.getUltimaListaDeCompras();
		
	}

	public List<ListaDeCompras> getListasDeCompras(Date de, Date ate) {
		return listasDeCompras.getListas(de, ate);
	}

	public Caixa getCaixa() {
		return caixa;
	}
}
