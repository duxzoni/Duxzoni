package view;

import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.prevayler.Prevayler;
import org.prevayler.PrevaylerFactory;

import persistencia.Persistence;
import view.produto.EstoqueView;
import view.produto.ProdutoView;

public class Vivencia {

	private JFrame frame;
	private Prevayler<Persistence> prevayler;
	

	public Prevayler<Persistence> getPrevayler() {
		return prevayler;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vivencia window = new Vivencia();
					window.prevayler = PrevaylerFactory.createPrevayler(new Persistence(), "C:\\Ghizoni\\Dropbox\\Vivência\\Duxzoni\\vivencia\\Percistencia");

					
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
	}
	
	private void abreTelaDeProduto() {
		ProdutoView produto = new ProdutoView(prevayler);
		produto.setModal(true);
		produto.setVisible(true);
	}

	private void abreTelaDeEstoque() {
		EstoqueView estoque = new EstoqueView(prevayler);
		estoque.setModal(true);
		estoque.setVisible(true);
	}

}
