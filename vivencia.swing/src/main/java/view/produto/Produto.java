package view.produto;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.prevayler.Prevayler;

import persistencia.Persistence;
import core.produto.TipoProduto;

public class Produto extends JDialog {

	private static final long serialVersionUID = 2L;
	private JPanel contentPane;
	private JTextField nomeTF;
	private JTextField precoTF;
	private JTextField quantidadeTf;
	private JComboBox tipoCb;
	private JLabel lblTipo;
	private Prevayler<Persistence> prevayler;
	private JTree produtosTree;
	private DefaultMutableTreeNode root;
	private JScrollPane scrollPane;
	private core.produto.Produto produto;

	public Produto(Prevayler<Persistence> prevayler) {
		this.prevayler = prevayler;
		setTitle("Produto");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 342);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0,
				1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblNome = new JLabel("Nome:");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.anchor = GridBagConstraints.EAST;
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 0;
		contentPane.add(lblNome, gbc_lblNome);

		nomeTF = new JTextField();
		GridBagConstraints gbc_nomeTF = new GridBagConstraints();
		gbc_nomeTF.insets = new Insets(0, 0, 5, 0);
		gbc_nomeTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_nomeTF.gridx = 1;
		gbc_nomeTF.gridy = 0;
		contentPane.add(nomeTF, gbc_nomeTF);
		nomeTF.setColumns(10);

		JLabel lblQuantidadeNecessria = new JLabel(
				"Quantidade Necess\u00E1ria:");
		GridBagConstraints gbc_lblQuantidadeNecessria = new GridBagConstraints();
		gbc_lblQuantidadeNecessria.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuantidadeNecessria.anchor = GridBagConstraints.EAST;
		gbc_lblQuantidadeNecessria.gridx = 0;
		gbc_lblQuantidadeNecessria.gridy = 1;
		contentPane.add(lblQuantidadeNecessria, gbc_lblQuantidadeNecessria);

		DecimalFormat decimalFormat = new DecimalFormat();  
		decimalFormat.applyPattern("#");  
		quantidadeTf = new JFormattedTextField(decimalFormat);
		quantidadeTf.setHorizontalAlignment(JTextField.RIGHT);  
		GridBagConstraints gbc_quantidadeTf = new GridBagConstraints();
		gbc_quantidadeTf.insets = new Insets(0, 0, 5, 0);
		gbc_quantidadeTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_quantidadeTf.gridx = 1;
		gbc_quantidadeTf.gridy = 1;
		contentPane.add(quantidadeTf, gbc_quantidadeTf);
		quantidadeTf.setColumns(10);

		JLabel lblPreoDeVenda = new JLabel("Pre\u00E7o de Venda:");
		GridBagConstraints gbc_lblPreoDeVenda = new GridBagConstraints();
		gbc_lblPreoDeVenda.insets = new Insets(0, 0, 5, 5);
		gbc_lblPreoDeVenda.anchor = GridBagConstraints.EAST;
		gbc_lblPreoDeVenda.gridx = 0;
		gbc_lblPreoDeVenda.gridy = 2;
		contentPane.add(lblPreoDeVenda, gbc_lblPreoDeVenda);

		decimalFormat = new DecimalFormat();  
		decimalFormat.applyPattern("#,##");  
		precoTF = new JFormattedTextField(decimalFormat);
		precoTF.setHorizontalAlignment(JTextField.RIGHT);
		GridBagConstraints gbc_precoTF = new GridBagConstraints();
		gbc_precoTF.insets = new Insets(0, 0, 5, 0);
		gbc_precoTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_precoTF.gridx = 1;
		gbc_precoTF.gridy = 2;
		contentPane.add(precoTF, gbc_precoTF);
		precoTF.setColumns(10);

		lblTipo = new JLabel("Tipo:");
		GridBagConstraints gbc_lblTipo = new GridBagConstraints();
		gbc_lblTipo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipo.anchor = GridBagConstraints.EAST;
		gbc_lblTipo.gridx = 0;
		gbc_lblTipo.gridy = 3;
		contentPane.add(lblTipo, gbc_lblTipo);

		tipoCb = new JComboBox();
		GridBagConstraints gbc_tipoCb = new GridBagConstraints();
		gbc_tipoCb.insets = new Insets(0, 0, 5, 0);
		gbc_tipoCb.fill = GridBagConstraints.HORIZONTAL;
		gbc_tipoCb.gridx = 1;
		gbc_tipoCb.gridy = 3;
		contentPane.add(tipoCb, gbc_tipoCb);
		preencheTiposProduto();

		root = new DefaultMutableTreeNode("Produtos");
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 4;
		contentPane.add(scrollPane, gbc_scrollPane);
		produtosTree = new JTree(root);
		produtosTree.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int selRow = produtosTree.getRowForLocation(e.getX(), e.getY());
				TreePath selPath = produtosTree.getPathForLocation(e.getX(), e.getY());
				if (selRow != -1) 
					if (e.getClickCount() == 2) 
						produtosTreeDoubleClick(selRow, selPath);
			}

		});
		scrollPane.setViewportView(produtosTree);

		refreshTreeView();
	}

	private void produtosTreeDoubleClick(int selRow, TreePath selPath) {
		Object userObject = ((DefaultMutableTreeNode)selPath.getLastPathComponent()).getUserObject();
		if(userObject instanceof core.produto.Produto) {
			core.produto.Produto produtoLocal = (core.produto.Produto) userObject;
			if(!possuiAlteracoes())
				setProduto(produtoLocal);
		}
		
	}

	private boolean possuiAlteracoes() {
		boolean retorno = true;
		core.produto.Produto produtoPreenchido = prevayler.prevalentSystem().getProduto(nomeTF.getText());
		
		if(produtoPreenchido != null){
			retorno = retorno && !produtoPreenchido.getNome().equals(nomeTF.getText());
			retorno = retorno && !produtoPreenchido.getQuantidadeNecessaria().equals(new Integer(quantidadeTf.getText()));
			retorno = retorno && !produtoPreenchido.getPrecoVenda().equals(new BigDecimal(precoTF.getText()));
			retorno = retorno && !produtoPreenchido.getTipoProduto().equals(tipoCb.getSelectedItem());
		}
		
		if(retorno)
			retorno = JOptionPane.showConfirmDialog(null ,"Existem alterações não salvas, deseja continuar?","Alerta",JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION;  
	
		return retorno;
	}

	private void setProduto(core.produto.Produto produtoLocal) {
		produto = produtoLocal;
		nomeTF.setText(produto.getNome());
		quantidadeTf.setText(produto.getQuantidadeNecessaria().toString());
		precoTF.setText(produto.getPrecoVenda().toString());
		tipoCb.setSelectedItem(produto.getTipoProduto());
	}
	
	private void refreshTreeView() {
		defaultChildNode();
		preencheProdutos();
	}

	private void defaultChildNode() {
		root.removeAllChildren();
		for (TipoProduto tipo : TipoProduto.values())
			root.add(new DefaultMutableTreeNode(tipo.getNome()));
	}

	private void preencheProdutos() {
		List<core.produto.Produto> produtos = prevayler.prevalentSystem()
				.getProdutos().getProdutos();
		Collections.sort(produtos);
		for (core.produto.Produto produto : produtos) {
			DefaultMutableTreeNode nextNode = root.getNextNode();

			while (nextNode != null) {
				if (nextNode.getUserObject().equals(
						produto.getTipoProduto().getNome()))
					nextNode.add(new DefaultMutableTreeNode(produto));
				nextNode = (DefaultMutableTreeNode) root
						.getChildAfter(nextNode);
			}
		}
	}

	private void preencheTiposProduto() {
		for (TipoProduto tipo : TipoProduto.values())
			tipoCb.addItem(tipo);
	}

}
