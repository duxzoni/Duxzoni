package core.produto;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class EstoqueTest {

	
	private List<Produto> produtos;
	private Estoque estoque = Estoque.getInstancia();
	
	
	@Before
	public void before(){
		produtos = new ArrayList<Produto>();
		Produto cocaLata = new Produto("Coca Lata", 6, new BigDecimal(1.7), TipoProduto.REFRIGERANTE);
		produtos.add(cocaLata);
		
		Produto cocaLitro = new Produto("Coca Litro", 10, new BigDecimal(1.7), TipoProduto.REFRIGERANTE);
		produtos.add(cocaLitro);
		
		Produto ruffles = new Produto("Ruffles", 10, new BigDecimal(2.5), TipoProduto.SALGADINHO);
		produtos.add(ruffles);
		
		Produto torcida = new Produto("Torcida", 16, new BigDecimal(1.95), TipoProduto.SALGADINHO);
		produtos.add(torcida);
		
	
		estoque.atualizaEstoque(cocaLata, 2);
		estoque.atualizaEstoque(cocaLitro, 0);
		estoque.atualizaEstoque(ruffles, 1);
		estoque.atualizaEstoque(torcida, 3);
	}
	
	@Test
	public void getEstoque() {
		assertEquals((Integer)2, estoque.getEstoque(produtos.get(0)));
	}

	@Test
	public void atualizaEstoque() {
		estoque.atualizaEstoque(produtos.get(0), 6);
		assertEquals((Integer)6, estoque.getEstoque(produtos.get(0)));
	}

	@Test
	public void getQuantidadeDeProdutos() {
		assertEquals(4, estoque.getQuantidadeDeProdutos());
	}

	@Test
	public void getListaDeCompras() {
		assertEquals(
				"Qtde.		Produto\r\n" +
				"4			Coca Lata\r\n" +
				"10			Coca Litro\r\n"+
				"9			Ruffles\r\n" +
				"13			Torcida\r\n", estoque.geraListaDeCompras().toString());
	}

}
