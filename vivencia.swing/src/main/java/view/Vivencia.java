package view;

import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import view.caixa.CaixaView;
import view.produto.ProdutoView;
import view.produto.estoque.EstoqueView;
import view.produto.lista.ListasDeComprasView;
import vivencia.persistencia.MyPrevaylerPersistence;

public class Vivencia {

	private JFrame frame;
	private MyPrevaylerPersistence myPersistence;
	

	public MyPrevaylerPersistence getMyPersistence() {
		return myPersistence;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vivencia window = new Vivencia();
					window.myPersistence = new MyPrevaylerPersistence("C:/Ghizoni/Dropbox/Vivência/Duxzoni/Percistencia"); 
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Vivencia() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Modulos");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmProduto = new JMenuItem("Produto");
		mnNewMenu.add(mntmProduto);
		mntmProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abreTelaDeProduto();
			}
		});
		
		JMenuItem mntmEstoque = new JMenuItem("Estoque");
		mnNewMenu.add(mntmEstoque);
		mntmEstoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abreTelaDeEstoque();
			}
		});

		JMenuItem mntmListaCompras = new JMenuItem("Listas de Compras");
		mnNewMenu.add(mntmListaCompras);
		mntmListaCompras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abreTelaDeListasDeCompras();
			}
		});
		
		JMenuItem mntmCaixa = new JMenuItem("Caixa");
		mnNewMenu.add(mntmCaixa);
		mntmCaixa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abreTelaDeCaixa();
			}
		});
		
//		JMenuItem mntmHistoricoEstoque = new JMenuItem("Histórico de Estoque");
//		mnNewMenu.add(mntmHistoricoEstoque);
//		mntmHistoricoEstoque.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				abreTelaDeHistoricoDeEstoque();
//			}
//		});
	}
	
	private void abreTelaDeProduto() {
		ProdutoView produto = new ProdutoView(myPersistence);
		produto.setModal(true);
		produto.setVisible(true);
	}

	private void abreTelaDeEstoque() {
		EstoqueView estoque = new EstoqueView(myPersistence);
		estoque.setModal(true);
		estoque.setVisible(true);
	}

	private void abreTelaDeListasDeCompras() {
		ListasDeComprasView listasDeCompras = new ListasDeComprasView(myPersistence);
		listasDeCompras.setModal(true);
		listasDeCompras.setVisible(true);
	}

	private void abreTelaDeCaixa() {
		CaixaView caixa = new CaixaView(myPersistence);
		caixa.setModal(true);
		caixa.setVisible(true);
	}

//	private void abreTelaDeHistoricoDeEstoque() {
//		HistoricosDeEstoqueView historicoEstoque = new HistoricosDeEstoqueView(myPersistence);
//		historicoEstoque.setModal(true);
//		historicoEstoque.setVisible(true);
//	}

}
