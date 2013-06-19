package vivencia.core.produto;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import vivencia.core.produto.Produto;
import vivencia.core.produto.TipoProduto;

public class ProdutoTest {

	@Test
	public void testProduto() {
		Produto produto = new Produto("CocaCola Litro", 10, new BigDecimal(1.69), TipoProduto.REFRIGERANTE);
		
		assertEquals("CocaCola Litro", produto.getNome());
		assertEquals((Integer)10, produto.getQuantidadeNecessaria());
		assertEquals(new BigDecimal(1.69), produto.getPrecoVenda());
	}

}
