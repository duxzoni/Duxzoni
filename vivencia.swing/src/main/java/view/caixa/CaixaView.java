package view.caixa;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import util.JNumberFormatField;
import vivencia.core.produto.caixa.Caixa;
import vivencia.persistencia.MyPrevaylerPersistence;
import javax.swing.JCheckBox;

public class CaixaView extends JDialog {

	private static final long serialVersionUID = 645007339784147902L;
	private MyPrevaylerPersistence myPersistence;
	private JPanel contentPane;
	private JToolBar toolBar;
	private JButton salvaBtn;
	private JButton historicoBtn;
	private JLabel lblNewLabel;
	private JNumberFormatField valorTF;
	private Caixa caixa;
	private JLabel valorLb;
	private JLabel lblDiferena;
	private JCheckBox isNegativo;

	public CaixaView(MyPrevaylerPersistence myPersistence) {
		setTitle("Caixa");
		this.myPersistence = myPersistence;
		caixa = myPersistence.getCaixa();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 204, 130);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		GridBagConstraints gbc_toolBar = new GridBagConstraints();
		gbc_toolBar.anchor = GridBagConstraints.NORTH;
		gbc_toolBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_toolBar.gridwidth = 5;
		gbc_toolBar.insets = new Insets(0, 0, 5, 0);
		gbc_toolBar.gridx = 0;
		gbc_toolBar.gridy = 0;
		contentPane.add(toolBar, gbc_toolBar);

		salvaBtn = new JButton("");
		salvaBtn.setToolTipText("Adiciona Difenrença ao Valor!");
		salvaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvaClicked();
			}
		});
		salvaBtn.setIcon(new ImageIcon(getClass().getResource(
				"/Icones/salvar.png")));
		toolBar.add(salvaBtn);
		
		historicoBtn = new JButton("");
		historicoBtn.setToolTipText("Histórico de caixa!");
		historicoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				historicoClicked();
			}
		});
		historicoBtn.setIcon(new ImageIcon(getClass().getResource(
				"/Icones/historico.png")));
		toolBar.add(historicoBtn);
		
		lblNewLabel = new JLabel("Valor Atual:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		valorLb = new JLabel("0");
		GridBagConstraints gbc_valorLb = new GridBagConstraints();
		gbc_valorLb.gridwidth = 3;
		gbc_valorLb.fill = GridBagConstraints.HORIZONTAL;
		gbc_valorLb.insets = new Insets(0, 0, 5, 0);
		gbc_valorLb.gridx = 2;
		gbc_valorLb.gridy = 1;
		contentPane.add(valorLb, gbc_valorLb);
		
		salvaBtn.setVisible(true);
		
		lblDiferena = new JLabel("Diferença:");
		GridBagConstraints gbc_lblDiferena = new GridBagConstraints();
		gbc_lblDiferena.insets = new Insets(0, 0, 5, 5);
		gbc_lblDiferena.anchor = GridBagConstraints.WEST;
		gbc_lblDiferena.gridx = 0;
		gbc_lblDiferena.gridy = 2;
		contentPane.add(lblDiferena, gbc_lblDiferena);
		
		isNegativo = new JCheckBox("-");
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNewCheckBox.gridx = 1;
		gbc_chckbxNewCheckBox.gridy = 2;
		contentPane.add(isNegativo, gbc_chckbxNewCheckBox);
		
		
		valorTF = new JNumberFormatField(2) {
			private static final long serialVersionUID = 6836701859381295305L;
			{ setLimit(6); }
        };
		valorTF.setHorizontalAlignment(JTextField.RIGHT);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.weightx = 3.0;
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridwidth = 3;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 2;
		contentPane.add(valorTF, gbc_textField);
		valorTF.setColumns(10);
		valorTF.setEditable(true);
		preencheCampo();
	}

	private void preencheCampo() {
		valorLb.setText(caixa.getValor().toString());
		valorTF.setText("0");
		isNegativo.setSelected(false);
	}

	private void salvaClicked() {
		myPersistence.atualizaCaixa(caixa.getValor().add(new BigDecimal((isNegativo.isSelected() ? "-" : "") + "1").multiply(valorTF.getValue())));
		preencheCampo();
	}

	private void historicoClicked() {
		// TODO Auto-generated method stub
		
	}
}
