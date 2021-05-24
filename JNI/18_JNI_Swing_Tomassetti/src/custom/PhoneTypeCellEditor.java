package custom;
import javax.swing.JComboBox;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import models.*;
public class PhoneTypeCellEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {

	private PhoneType phoneType;

	@Override
	public Object getCellEditorValue() {
		return this.phoneType;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		if (value instanceof PhoneType) {
			this.phoneType = (PhoneType) value;
		}

		JComboBox<PhoneType> comboPhoneType = new JComboBox<>(PhoneType.values());

		comboPhoneType.setSelectedItem(phoneType);
		comboPhoneType.addActionListener(this);
		return comboPhoneType;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent event) {
		JComboBox<PhoneType> comboPhone = (JComboBox<PhoneType>) event.getSource();
		this.phoneType = (PhoneType) comboPhone.getSelectedItem();
	}

}
