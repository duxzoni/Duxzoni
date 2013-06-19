package view.produto.estoque;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import util.JCalendar;
import vivencia.core.produto.HistoricoEstoque;
import vivencia.persistencia.MyPrevaylerPersistence;
import vivencia.util.DateManager;

public class HistoricosDeEstoqueView extends JDialog {

	private static final long serialVersionUID = -2804608527404735837L;
	private MyPrevaylerPersistence myPersistence;
	private final JButton filtrarBtn;
	private JCalendar deCalendar;
	private JCalendar ateCalendar;
	private JList historicos;

	public HistoricosDeEstoqueView(MyPrevaylerPersistence myPersistence) {
		setTitle("Históricos de Estoque");
		this.myPersistence = myPersistence;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 123, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 20, 65, 20, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0,
				Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblDe = new JLabel("De:");
		GridBagConstraints gbc_lblDe = new GridBagConstraints();
		gbc_lblDe.insets = new Insets(0, 0, 5, 5);
		gbc_lblDe.anchor = GridBagConstraints.EAST;
		gbc_lblDe.gridx = 0;
		gbc_lblDe.gridy = 0;
		getContentPane().add(lblDe, gbc_lblDe);

		deCalendar = new JCalendar(false);
		GridBagConstraints gbc_calendar = new GridBagConstraints();
		gbc_calendar.anchor = GridBagConstraints.WEST;
		gbc_calendar.fill = GridBagConstraints.VERTICAL;
		gbc_calendar.insets = new Insets(0, 0, 5, 5);
		gbc_calendar.gridx = 1;
		gbc_calendar.gridy = 0;
		getContentPane().add(deCalendar, gbc_calendar);

		JLabel lblAt = new JLabel("Até:");
		GridBagConstraints gbc_lblAt = new GridBagConstraints();
		gbc_lblAt.insets = new Insets(0, 0, 5, 5);
		gbc_lblAt.anchor = GridBagConstraints.EAST;
		gbc_lblAt.gridx = 2;
		gbc_lblAt.gridy = 0;
		getContentPane().add(lblAt, gbc_lblAt);

		ateCalendar = new JCalendar(false);
		GridBagConstraints gbc_calendar_1 = new GridBagConstraints();
		gbc_calendar_1.insets = new Insets(0, 0, 5, 5);
		gbc_calendar_1.anchor = GridBagConstraints.WEST;
		gbc_calendar_1.fill = GridBagConstraints.VERTICAL;
		gbc_calendar_1.gridx = 3;
		gbc_calendar_1.gridy = 0;
		getContentPane().add(ateCalendar, gbc_calendar_1);
		
		filtrarBtn = new JButton("Filtrar");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 4;
		gbc_btnNewButton.gridy = 0;
		getContentPane().add(filtrarBtn, gbc_btnNewButton);
		filtrarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				preencheLista();
			}
		});

		historicos = new JList();
		JScrollPane histScroll = new JScrollPane(historicos);
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.gridheight = 4;
		gbc_list.gridwidth = 5;
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 1;
		getContentPane().add(histScroll, gbc_list);
		
		historicos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {  
			         if (evt.getClickCount() == 2)  
			            abreTelaEvento();  
			}
		}); 

		preencheLista();
	}

	private void preencheLista() {
		Date de = DateManager.ddmmaaaaSeparated(deCalendar.getText());
		Date ate = DateManager.ddmmaaaaSeparated(ateCalendar.getText());

		List<HistoricoEstoque> historicoEstoques = myPersistence
				.getHistoricoEstoques(de, ate);
		Collections.sort(historicoEstoques);

		DefaultListModel listModel = new DefaultListModel();
		for (HistoricoEstoque historicoEstoque : historicoEstoques)
			listModel.addElement(historicoEstoque);

		historicos.setModel(listModel);

	}

	private void abreTelaEvento() {
		HistoricoEstoque historicoEstoque = (HistoricoEstoque)historicos.getSelectedValue();
		EstoqueView estoque = new EstoqueView(myPersistence, historicoEstoque.getEstoque(), DateManager.ddmmaaaahhMMssSeparated(historicoEstoque.getData()));
		estoque.setModal(true);
		estoque.setVisible(true);
	}

}
