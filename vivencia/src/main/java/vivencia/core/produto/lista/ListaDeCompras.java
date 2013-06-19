package vivencia.core.produto.lista;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import vivencia.core.produto.Produto;
import vivencia.core.produto.TipoProduto;
import vivencia.core.produto.lista.Item;
import vivencia.util.DateManager;

public class ListaDeCompras implements Serializable, Comparable<ListaDeCompras>{
	
	private static final long serialVersionUID = -3832717437321463794L;
	private Date data;
	private List<Item> itens = new ArrayList<Item>();

	public ListaDeCompras(Date data) {
		this.data = data;
	}
	
	public void add(Produto produto, int quantidade) {
		Item item = new Item(produto, quantidade);
		itens.add(item );
	}
	
	public List<Item> itens() {
		return itens;
	}
	
	@Override
	public String toString() {
		return DateManager.ddmmaaaahhMMssSeparated(getData());
	}
	
	public String print() {
		StringBuilder retorno = new StringBuilder();
		retorno.append("\tQtde.\t\tProduto\r\n");
		Collections.sort(itens);
		TipoProduto tipoAnterior = null;
		TipoProduto tipoAtual;
		
		for (Item item : itens) {
			tipoAtual = item.getProduto().getTipoProduto();
			if(tipoAnterior == null || !tipoAtual.equals(tipoAnterior))
				retorno.append( tipoAtual.getNome() + "\r\n");
				
			retorno.append( "\t" + item.getQuantidade() + "\t\t\t" +item.getProduto() + "\r\n");
			tipoAnterior = item.getProduto().getTipoProduto();
		}
		
		return retorno.toString();
	}

	public Date getData() {
		return data;
	}

	public int compareTo(ListaDeCompras lista) {
		return getData().compareTo(lista.getData());
	}
}
