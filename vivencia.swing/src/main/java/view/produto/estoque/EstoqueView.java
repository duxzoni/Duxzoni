package view.produto.estoque;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import vivencia.core.produto.Estoque;
import vivencia.core.produto.Produto;
import vivencia.persistencia.MyPrevaylerPersistence;

public class EstoqueView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;
	private JPanel contentPane;
	private MyPrevaylerPersistence myPersistence;
	private Estoque estoque;
	private JTable estoqueProdutos;
	private JToolBar toolBar;
	private JButton salvaBtn;
	private JButton listaBtn;
	private JButton historicoBtn;

	public EstoqueView(MyPrevaylerPersistence myPersistence) {
		this(myPersistence, myPersistence.getEstoque(), true, "");

	}

	public EstoqueView(MyPrevaylerPersistence myPersistence, Estoque estoque,
			String data) {
		this(myPersistence, myPersistence.getEstoque(), false, data);
	}

	private EstoqueView(MyPrevaylerPersistence myPersistence, Estoque estoque,
			boolean editable, String data) {
		this.estoque = estoque.copy();

		setTitle("Estoque " + data);
		this.myPersistence = myPersistence;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		MeuModel model = new MeuModel(new Object[][] {}, new String[] {
				"Produto", "Quantidade" }, editable);

		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		GridBagConstraints gbc_toolBar = new GridBagConstraints();
		gbc_toolBar.anchor = GridBagConstraints.NORTH;
		gbc_toolBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_toolBar.gridwidth = 1;
		gbc_toolBar.insets = new Insets(0, 0, 5, 0);
		gbc_toolBar.gridx = 0;
		gbc_toolBar.gridy = 0;
		contentPane.add(toolBar, gbc_toolBar);

		salvaBtn = new JButton("");
		salvaBtn.setToolTipText("Salva alterações de estoque!");
		salvaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvaClicked();
			}
		});
		salvaBtn.setIcon(new ImageIcon(getClass().getResource(
				"/Icones/salvar.png")));
		salvaBtn.setVisible(editable);
		toolBar.add(salvaBtn);

		historicoBtn = new JButton("");
		historicoBtn.setToolTipText("Histórico de estoque!");
		historicoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				historicoClicked();
			}
		});
		historicoBtn.setIcon(new ImageIcon(getClass().getResource(
				"/Icones/historico.png")));
		historicoBtn.setVisible(editable);
		toolBar.add(historicoBtn);

		listaBtn = new JButton("");
		listaBtn.setToolTipText("Gera lista de compras");
		listaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listaComprasClicked();
			}
		});
		listaBtn.setIcon(new ImageIcon(getClass().getResource(
				"/Icones/lista.png")));
		toolBar.add(listaBtn);

		estoqueProdutos = new JTable(model);
		estoqueProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 1;
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(estoqueProdutos);
		contentPane.add(scroll, gbc_table);

		preencheEstoque();

	}

	private void preencheEstoque() {
		List<Produto> produtos = myPersistence.getProdutos();
		Collections.sort(produtos, new Comparator<Produto>() {

			public int compare(Produto o1, Produto o2) {
				int compareTo = o1.getTipoProduto().getNome()
						.compareTo(o1.getTipoProduto().getNome());
				if (compareTo == 0)
					compareTo = o1.getNome().compareTo(o1.getNome());
				return compareTo;
			}
		});

		MeuModel model = (MeuModel) estoqueProdutos.getModel();
		for (Produto produto : produtos) {
			model.addRow(new Object[] { produto, estoque.getEstoque(produto) });
		}
	}

	private void salvaClicked() {
		for (int i = 0; i < estoqueProdutos.getRowCount(); i++) {
			Produto produto = (Produto) estoqueProdutos.getModel().getValueAt(i, 0);
			Integer quantidadeEstoque = Integer.parseInt((String) estoqueProdutos.getModel().getValueAt(i,1));
			estoque.atualizaEstoque(produto, quantidadeEstoque);
		}

		myPersistence.atualizaEstoque(estoque);
	}

	private void historicoClicked() {
		HistoricosDeEstoqueView historicoEstoque = new HistoricosDeEstoqueView(
				myPersistence);
		historicoEstoque.setModal(true);
		historicoEstoque.setVisible(true);
	}

	private void listaComprasClicked() {

		JFileChooser chooser = new JFileChooser();

		int returnVal = chooser.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = new File(chooser.getSelectedFile() + ".txt");
			myPersistence.escreveArquivoListaDeCompras(file);
		}
	}

	class MeuModel extends DefaultTableModel {
		private static final long serialVersionUID = 1L;
		private boolean editable;

		public MeuModel(Object[][] objects, String[] strings, boolean editable) {
			super(objects, strings);
			this.editable = editable;
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return editable && (column == 0 ? false : true);
		}
	}

}
