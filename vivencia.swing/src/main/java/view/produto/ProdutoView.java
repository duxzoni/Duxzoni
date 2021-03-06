package view.produto;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import util.JNumberFormatField;
import vivencia.core.produto.Produto;
import vivencia.core.produto.TipoProduto;
import vivencia.persistencia.MyPrevaylerPersistence;

public class ProdutoView extends JDialog {

	private static final long serialVersionUID = 2L;
	private JPanel contentPane;
	private JTextField nomeTF;
	private JNumberFormatField precoTF;
	private JFormattedTextField quantidadeTf;
	private JComboBox tipoCb;
	private JLabel lblTipo;
	private MyPrevaylerPersistence myPersistence;
	private JTree produtosTree;
	private DefaultMutableTreeNode root;
	private JScrollPane scrollPane;
	private Produto produto;
	private JToolBar toolBar;
	private JButton novoBtn;
	private JButton salvaBtn;
	private JButton excluiBtn;

	public ProdutoView(MyPrevaylerPersistence myPersistence) {
		this.myPersistence = myPersistence;
		setTitle("Produto");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 342);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				1.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		GridBagConstraints gbc_toolBar = new GridBagConstraints();
		gbc_toolBar.anchor = GridBagConstraints.NORTH;
		gbc_toolBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_toolBar.gridwidth = 2;
		gbc_toolBar.insets = new Insets(0, 0, 5, 0);
		gbc_toolBar.gridx = 0;
		gbc_toolBar.gridy = 0;
		contentPane.add(toolBar, gbc_toolBar);

		novoBtn = new JButton("");
		novoBtn.setToolTipText("Cria novo produto.");
		novoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				novoClicked();
			}
		});
		novoBtn.setIcon(new ImageIcon(getClass()
				.getResource("/Icones/novo.PNG")));
		toolBar.add(novoBtn);

		salvaBtn = new JButton("");
		salvaBtn.setToolTipText("Salva alterações no produto.");
		salvaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvaClicked();
			}
		});

		salvaBtn.setIcon(new ImageIcon(getClass().getResource(
				"/Icones/salvar.png")));
		toolBar.add(salvaBtn);

		excluiBtn = new JButton("");
		excluiBtn.setToolTipText("Exclui produto aberto.");
		excluiBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluiClicked();
			}
		});
		excluiBtn.setIcon(new ImageIcon(getClass().getResource(
				"/Icones/delete.png")));
		toolBar.add(excluiBtn);

		JLabel lblNome = new JLabel("Nome:");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.anchor = GridBagConstraints.EAST;
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 1;
		contentPane.add(lblNome, gbc_lblNome);

		nomeTF = new JTextField();
		GridBagConstraints gbc_nomeTF = new GridBagConstraints();
		gbc_nomeTF.insets = new Insets(0, 0, 5, 0);
		gbc_nomeTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_nomeTF.gridx = 1;
		gbc_nomeTF.gridy = 1;
		contentPane.add(nomeTF, gbc_nomeTF);
		nomeTF.setColumns(10);

		JLabel lblQuantidadeNecessria = new JLabel(
				"Quantidade Necess\u00E1ria:");
		GridBagConstraints gbc_lblQuantidadeNecessria = new GridBagConstraints();
		gbc_lblQuantidadeNecessria.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuantidadeNecessria.anchor = GridBagConstraints.EAST;
		gbc_lblQuantidadeNecessria.gridx = 0;
		gbc_lblQuantidadeNecessria.gridy = 2;
		contentPane.add(lblQuantidadeNecessria, gbc_lblQuantidadeNecessria);

		DecimalFormat decimalFormat = new DecimalFormat();
		decimalFormat.applyPattern("#");
		quantidadeTf = new JFormattedTextField(decimalFormat);
		quantidadeTf.setHorizontalAlignment(JTextField.RIGHT);
		GridBagConstraints gbc_quantidadeTf = new GridBagConstraints();
		gbc_quantidadeTf.insets = new Insets(0, 0, 5, 0);
		gbc_quantidadeTf.fill = GridBagConstraints.HORIZONTAL;
		gbc_quantidadeTf.gridx = 1;
		gbc_quantidadeTf.gridy = 2;
		contentPane.add(quantidadeTf, gbc_quantidadeTf);

		JLabel lblPreoDeVenda = new JLabel("Pre\u00E7o de Venda:");
		GridBagConstraints gbc_lblPreoDeVenda = new GridBagConstraints();
		gbc_lblPreoDeVenda.insets = new Insets(0, 0, 5, 5);
		gbc_lblPreoDeVenda.anchor = GridBagConstraints.EAST;
		gbc_lblPreoDeVenda.gridx = 0;
		gbc_lblPreoDeVenda.gridy = 3;
		contentPane.add(lblPreoDeVenda, gbc_lblPreoDeVenda);
//
//		decimalFormat = new DecimalFormat();
//		decimalFormat.applyPattern("#.##");
//		precoTF = new JFormattedTextField(decimalFormat);
		precoTF = new JNumberFormatField(2) {
			private static final long serialVersionUID = 6836701859381295305L;
			{ setLimit(6); }
        };
		precoTF.setHorizontalAlignment(JTextField.RIGHT);
		GridBagConstraints gbc_precoTF = new GridBagConstraints();
		gbc_precoTF.insets = new Insets(0, 0, 5, 0);
		gbc_precoTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_precoTF.gridx = 1;
		gbc_precoTF.gridy = 3;
		contentPane.add(precoTF, gbc_precoTF);
		precoTF.setColumns(10);

		lblTipo = new JLabel("Tipo:");
		GridBagConstraints gbc_lblTipo = new GridBagConstraints();
		gbc_lblTipo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipo.anchor = GridBagConstraints.EAST;
		gbc_lblTipo.gridx = 0;
		gbc_lblTipo.gridy = 4;
		contentPane.add(lblTipo, gbc_lblTipo);

		tipoCb = new JComboBox();
		GridBagConstraints gbc_tipoCb = new GridBagConstraints();
		gbc_tipoCb.insets = new Insets(0, 0, 5, 0);
		gbc_tipoCb.fill = GridBagConstraints.HORIZONTAL;
		gbc_tipoCb.gridx = 1;
		gbc_tipoCb.gridy = 4;
		contentPane.add(tipoCb, gbc_tipoCb);
		preencheTiposProduto();

		root = new DefaultMutableTreeNode("Produtos");
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 5;
		contentPane.add(scrollPane, gbc_scrollPane);
		produtosTree = new JTree(root);
		produtosTree.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int selRow = produtosTree.getRowForLocation(e.getX(), e.getY());
				TreePath selPath = produtosTree.getPathForLocation(e.getX(),
						e.getY());
				if (selRow != -1)
					if (e.getClickCount() == 2)
						produtosTreeDoubleClick(selRow, selPath);
			}

		});
		scrollPane.setViewportView(produtosTree);

		refreshTreeView();
		limpaCampos();
	}

	private void novoClicked() {
		if (possuiAlteracoes())
			return;

		produto = null;
		limpaCampos();
	}

	private void excluiClicked() {

		if(produto == null ){
			JOptionPane.showMessageDialog(null, "Nenhum produto foi selecionado!\nClique duas vezes nele para poder exluí-lo.");
			return;
		}
		
		if (JOptionPane.showConfirmDialog(null,
				"Realmente deseja apagar o item, " + produto.getNome() + "?",
				"Alerta", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
			return;

		myPersistence.removeProduto(produto.getNome());
		limpaCampos();
		refreshTreeView();
	}

	private void salvaClicked() {
		if (produto != null)
			atualizaProduto();
		else
			criaProduto();

		refreshTreeView();
	}

	private void criaProduto() {
		myPersistence.adicionaProduto(nomeTF.getText().trim(), new Integer(
				quantidadeTf.getText()), precoTF.getValue(),
				(TipoProduto) tipoCb.getSelectedItem());
	}

	private void atualizaProduto() {
		String nomeAntigo = produto.getNome();
		produto.setNome(nomeTF.getText());
		produto.setQuantidadeNecessaria(new Integer(quantidadeTf.getText()));
		produto.setPrecoVenda(precoTF.getValue());
		produto.setTipoProduto((TipoProduto) tipoCb.getSelectedItem());

		myPersistence.atualizaProduto(nomeAntigo, produto);
	}

	private void limpaCampos() {
		nomeTF.setText("");
		quantidadeTf.setText("");
		precoTF.setText("");
		tipoCb.setSelectedItem(null);
	}

	private void produtosTreeDoubleClick(int selRow, TreePath selPath) {
		Object userObject = ((DefaultMutableTreeNode) selPath
				.getLastPathComponent()).getUserObject();
		if (userObject instanceof Produto) {
			Produto produtoLocal = (Produto) userObject;
			if (!possuiAlteracoes())
				setProduto(produtoLocal);
		}

	}

	private boolean possuiAlteracoes() {
		boolean modificado = false;
		Produto produtoPreenchido = myPersistence.getProduto(nomeTF.getText());

		if (produtoPreenchido != null) {
			modificado = modificado
					|| !produtoPreenchido.getNome().equals(nomeTF.getText());
			modificado = modificado
					|| !produtoPreenchido.getQuantidadeNecessaria().equals(
							new Integer(quantidadeTf.getText()));
			modificado = modificado
					|| !produtoPreenchido.getPrecoVenda().equals(
							precoTF.getValue());
			modificado = modificado
					|| !produtoPreenchido.getTipoProduto().equals(
							tipoCb.getSelectedItem());
		} else {
			modificado = modificado || !nomeTF.getText().trim().isEmpty();
			modificado = modificado || !quantidadeTf.getText().trim().isEmpty();
			modificado = modificado || !precoTF.getValue().equals(new BigDecimal("0.00"));
			modificado = modificado || tipoCb.getSelectedItem() != null;
		}

		if (modificado)
			modificado = JOptionPane.showConfirmDialog(null,
					"Existem alterações não salvas, deseja continuar?",
					"Alerta", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION;

		return modificado;
	}

	private void setProduto(Produto produtoLocal) {
		produto = produtoLocal;
		nomeTF.setText(produto.getNome());
		quantidadeTf.setValue(produto.getQuantidadeNecessaria());
		precoTF.setText(produto.getPrecoVenda().toString());
		tipoCb.setSelectedItem(produto.getTipoProduto());
	}

	private void refreshTreeView() {
		defaultChildNode();
		preencheProdutos();
		TreeModel arvore = new DefaultTreeModel(root);
		produtosTree.setModel(arvore);
	}

	private void defaultChildNode() {
		root.removeAllChildren();
		for (TipoProduto tipo : TipoProduto.values())
			root.add(new DefaultMutableTreeNode(tipo.getNome()));
	}

	private void preencheProdutos() {
		List<Produto> produtos = myPersistence.getProdutos();
		Collections.sort(produtos);
		for (Produto produto : produtos) {
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
