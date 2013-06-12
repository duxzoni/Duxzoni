package view;

import java.awt.EventQueue;
import java.awt.GridBagLayout;

import javax.swing.JFrame;

import org.prevayler.Prevayler;
import org.prevayler.PrevaylerFactory;

import persistencia.Persistence;
import view.produto.Produto;

import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		
		JButton btnProduto = new JButton("Produto");
		btnProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = new Produto(prevayler);
//				Produto produto = new Produto();
//				produto.setPrevayler(prevayler);
				produto.setModal(true);
				produto.setVisible(true);
			}
		});
		GridBagConstraints gbc_btnProduto = new GridBagConstraints();
		gbc_btnProduto.insets = new Insets(0, 0, 5, 0);
		gbc_btnProduto.gridx = 0;
		gbc_btnProduto.gridy = 0;
		frame.getContentPane().add(btnProduto, gbc_btnProduto);
	}

}
