package custom;
import javax.swing.JTable;
import java.awt.Component;
import javax.swing.table.DefaultTableCellRenderer;

import models.*;

public class PhoneTypeCellRenderer extends DefaultTableCellRenderer{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		if (value instanceof BottleType) {
			BottleType typeOfBottle = (BottleType) value;
			setText(typeOfBottle.name());
		}
		return this;
	}

}
