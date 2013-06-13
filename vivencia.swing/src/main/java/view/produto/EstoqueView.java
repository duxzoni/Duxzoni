package view.produto;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.prevayler.Prevayler;

import persistencia.Persistence;
import persistencia.produto.AtualizaEstoqueProduto;
import util.SpinnerEditor;
import core.produto.Estoque;
import core.produto.Produto;

public class EstoqueView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;
	private JPanel contentPane;
	private Prevayler<Persistence> prevayler;
	private Estoque estoque;
	private JTable estoqueProdutos;
	private JToolBar toolBar;
	private JButton salvaBtn;

	/**
	 * Create the frame.
	 */
	public EstoqueView(Prevayler<Persistence> prevayler) {
		this.prevayler = prevayler;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		MeuModel model = new MeuModel(new Object [][] { },
				new String [] {"Produto", "Quantidade"});
		
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
		salvaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvaClicked();
			}
		});
		salvaBtn.setIcon(new ImageIcon(
				"C:\\Ghizoni\\Dropbox\\Vivência\\Duxzoni\\vivencia.swing\\src\\main\\resources\\Icones\\salvar.png"));
		toolBar.add(salvaBtn);
		
		
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
		
		
		
		estoque = prevayler.prevalentSystem().getEstoque();
		List<Produto> produtos = prevayler.prevalentSystem().getProdutos().getProdutos();
		Collections.sort(produtos, new Comparator<Produto>() {

			public int compare(Produto o1, Produto o2) {
				int compareTo  = o1.getTipoProduto().getNome().compareTo(o1.getTipoProduto().getNome());
				if(compareTo == 0)
					compareTo = o1.getNome().compareTo(o1.getNome());
				return compareTo;
			}
		});
		
		MeuModel model = (MeuModel) estoqueProdutos.getModel();
		for (Produto produto : produtos) {
			model.addRow(new Object[]{ produto, estoque.getEstoque(produto)});
		}
		
		TableColumn column = estoqueProdutos.getColumnModel().getColumn(1);  
		column.setCellRenderer(new DefaultTableCellRenderer() {  
			private static final long serialVersionUID = 55L;

			@Override  
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {  
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);  

                JSpinner spinner = new JSpinner();  
                if (component instanceof JLabel) {  
                    spinner.setValue(Integer.valueOf(((JLabel) component).getText()));  
                      
                } else if (value != null) {  
                    spinner.setValue(Integer.valueOf(value.toString()));  
                      
                } else {  
                    System.out.println("Não foi possivel criar Renderer");  
                }  

                return spinner;  
            }  
        });  
		column.setCellEditor(new SpinnerEditor(estoquePossivel()));
	}

	private List<Integer> estoquePossivel() {
		List<Integer> estoquePossivel = new ArrayList<Integer>();
		for (int i = 0; i < 51; i++) {
			estoquePossivel.add(i);
		}
		return estoquePossivel;
	}

	private void salvaClicked() {
		for (int i = 0; i < estoqueProdutos.getRowCount(); i++) {
			Produto produto = (Produto) estoqueProdutos.getModel().getValueAt(i, 0);
			Integer quantidadeEstoque = (Integer) estoqueProdutos.getModel().getValueAt(i, 1);
			prevayler.execute(new AtualizaEstoqueProduto(produto, quantidadeEstoque));
		}
	}
	
	class MeuModel extends DefaultTableModel{
		private static final long serialVersionUID = 1L;

		public MeuModel(Object[][] objects, String[] strings) {
			super(objects, strings);
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return column == 0 ? false : true;
		}
	}

}
