package util;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerListModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableCellEditor;

public class SpinnerEditor extends AbstractCellEditor implements TableCellEditor {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 77L;
	final JSpinner spinner = new JSpinner();

	  public SpinnerEditor(List<Integer> items) {
	    spinner.setModel(new SpinnerListModel(items));
	    spinner.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
//				spinner.lostFocus(null, what)
			}
		});
	  }

	  public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
	      int row, int column) {
	    spinner.setValue(value);
	    return spinner;
	  }

	  public boolean isCellEditable(EventObject evt) {
		  return true;
	  }

	  public Object getCellEditorValue() {
	    return spinner.getValue();
	  }
	}

//public class SpinnerEditor extends DefaultCellEditor
//{
//	private static final long serialVersionUID = 77L;
//	JSpinner spinner;
//    JSpinner.DefaultEditor editor;
//    JTextField textField;
//    boolean valueSet;
//
//    public SpinnerEditor() {
//        super(new JTextField());
//        spinner = new JSpinner(new SpinnerNumberModel());
//        editor = ((JSpinner.DefaultEditor)spinner.getEditor());
//        textField = editor.getTextField();
//        textField.addFocusListener( new FocusListener() {
//            public void focusGained( FocusEvent fe ) {
//                SwingUtilities.invokeLater( new Runnable() {
//                    public void run() {
//                        if ( valueSet ) {
//                            textField.setCaretPosition(1);
//                        }
//                    }
//                });
//            }
//            public void focusLost( FocusEvent fe ) {
//            }
//        });
//        textField.addActionListener( new ActionListener() {
//            public void actionPerformed( ActionEvent ae ) {
//                stopCellEditing();
//            }
//        });
//    }
//
//    public Component getTableCellEditorComponent(
//        JTable table, Object value, boolean isSelected, int row, int column
//    ) {
//        if ( !valueSet ) {
//            spinner.setValue(value);
//        }
//        SwingUtilities.invokeLater( new Runnable() {
//            public void run() {
//                textField.requestFocus();
//            }
//        });
//        return spinner;
//    }
//
//    public boolean isCellEditable( EventObject eo ) {
////        if ( eo instanceof KeyEvent ) {
////            KeyEvent ke = (KeyEvent)eo;
////            textField.setText(String.valueOf(ke.getKeyChar()));
////            valueSet = true;
////        } else {
////            valueSet = false;
////        }
//        return true;
//    }
//
//    // Returns the spinners current value.
//    public Object getCellEditorValue() {
//        return spinner.getValue();
//    }
//
//    public boolean stopCellEditing() {
//        try {
//            editor.commitEdit();
//            spinner.commitEdit();
//        } catch ( java.text.ParseException e ) {
//            JOptionPane.showMessageDialog(null,
//                "Invalid value, discarding.");
//        }
//        return super.stopCellEditing();
//    }
//}
