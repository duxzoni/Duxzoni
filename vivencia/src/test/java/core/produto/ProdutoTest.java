package core.produto;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class ProdutoTest {

	@Test
	public void testProduto() {
		Produto produto = new Produto("CocaCola Litro", 10, new BigDecimal(1.69));
		
		assertEquals("CocaCola Litro", produto.getNome());
		assertEquals((Integer)10, produto.getQuantidadeNecessaria());
		assertEquals(new BigDecimal(1.69), produto.getPrecoVenda());
	}

}
