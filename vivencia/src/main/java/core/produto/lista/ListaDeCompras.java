package core.produto.lista;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import core.produto.Produto;

public class ListaDeCompras {
	
	List<Item> itens = new ArrayList<Item>();

	public void add(Produto produto, int quantidade) {
		Item item = new Item(produto, quantidade);
		itens.add(item );
	}
	
	@Override
	public String toString() {
		StringBuilder retorno = new StringBuilder();
		retorno.append("Qtde.\t\tProduto\r\n");
		Collections.sort(itens);
		for (Item item : itens) {
			retorno.append( item.getQuantidade() + "\t\t\t" +item.getProduto() + "\r\n");
		}
		
		return retorno.toString();
	}

}
