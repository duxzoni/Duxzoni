package vivencia.persistencia;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.prevayler.Prevayler;
import org.prevayler.PrevaylerFactory;

import vivencia.core.produto.Estoque;
import vivencia.core.produto.HistoricoEstoque;
import vivencia.core.produto.Produto;
import vivencia.core.produto.TipoProduto;
import vivencia.core.produto.caixa.Caixa;
import vivencia.core.produto.caixa.HistoricoCaixa;
import vivencia.core.produto.lista.ListaDeCompras;
import vivencia.persistencia.lista.AdicionaListaDeCompras;
import vivencia.persistencia.produto.AdicionaProduto;
import vivencia.persistencia.produto.AtualizaEstoque;
import vivencia.persistencia.produto.AtualizaEstoqueProduto;
import vivencia.persistencia.produto.RemoveProduto;
import vivencia.persistencia.produto.SalvaHistoricoEstoque;
import vivencia.persistencia.produto.caixa.AtualizaCaixa;
import vivencia.persistencia.produto.caixa.AtualizaValorCaixa;
import vivencia.persistencia.produto.caixa.SalvaHistoricoCaixa;
import vivencia.util.DateManager;

public class MyPrevaylerPersistence {
	Persistence persistence;
	Prevayler<Persistence> prevayler;

	public MyPrevaylerPersistence(String endereco) {
		try {
			persistence = new Persistence();
			prevayler = PrevaylerFactory.createPrevayler(persistence, endereco);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void adicionaProduto(String nome, Integer quantidadeNecessaria, BigDecimal precoVenda, TipoProduto tipoProduto) {
		prevayler.execute(new AdicionaProduto(nome, quantidadeNecessaria, precoVenda, tipoProduto));
	}

	public void removeProduto(String nome) {
		prevayler.execute(new RemoveProduto(nome));
	}

	public void atualizaEstoqueProduto(Produto produto, Integer quantidade) {
		prevayler.execute(new AtualizaEstoqueProduto(produto, quantidade));
	}

	public void atualizaEstoque(Estoque estoque) {
		Date today = DateManager.today();
		ListaDeCompras lista = estoque.geraListaDeCompras(today);
		prevayler.execute(new AdicionaListaDeCompras(lista));
		prevayler.execute(new AtualizaCaixa(lista, today));
		prevayler.execute(new SalvaHistoricoEstoque(today));
		prevayler.execute(new AtualizaEstoque(estoque));
	}
	
	public void atualizaCaixa(BigDecimal valor) {
		prevayler.execute(new SalvaHistoricoCaixa( DateManager.today()));
		prevayler.execute(new AtualizaValorCaixa( valor ));
	}

//	public void atualizaCaixa() {
//		prevayler.execute(new AtualizaCaixa(new Date()));
//	}

	
	
	
	public Produto getProduto(String nome) {
		return persistence.getProduto(nome);
	}

	public int quantidadeDeProdutos() {
		return persistence.quantidadeDeProdutos();
	}
	
	public Estoque getEstoque() {
		return persistence.getEstoque();
	}

	public List<Produto> getProdutos() {
		return persistence.getProdutos().getProdutos();
	}

	public void atualizaProduto(String nomeAntigo, Produto produto) {
		persistence.atualizaProduto(nomeAntigo, produto);
	}

	public List<HistoricoEstoque> getHistoricoEstoques(Date dataInicio, Date dataFim) {
		return persistence.getHistoricoEstoques(dataInicio, DateManager.addDays(dataFim, 1));
	}

	public void escreveArquivoListaDeCompras(File file) {
		ListaDeCompras lista = persistence.getUltimaListaDeCompras();
		prevayler.execute(new AdicionaListaDeCompras(lista));
		
		try {
			FileOutputStream fos = new FileOutputStream(file);  
        	fos.write(lista.print().getBytes());
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}

	public List<ListaDeCompras> getListasDeCompras(Date de, Date ate) {
		return persistence.getListasDeCompras(de, DateManager.addDays(ate, 1));
	}

	public Caixa getCaixa() {
		return persistence.getCaixa();
	}
	
//	public void adicionaHistoricoCaixa() {
//		prevayler.execute(new SalvaHistoricoCaixa(new Date()));
//	}

}
