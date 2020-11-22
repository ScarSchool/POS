package custom;
import javax.swing.table.*;
import models.Bottle;  
public class NativeTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 1L;

	static {
		    System.loadLibrary("NativeTableModel");
		  }

		  @Override
		  public native int getRowCount();
		  @Override
		  public native int getColumnCount();

		  @Override
		  public native String getColumnName(int columnIndex);

		  @Override
		  public native Class<?> getColumnClass(int columnIndex);

		  @Override
		  public native boolean isCellEditable(int rowIndex, int columnIndex);

		  @Override
		  public native Object getValueAt(int rowIndex, int columnIndex);

		  @Override
		  public native void setValueAt(Object aValue, int rowIndex, int columnIndex);

		  public native void addItem(Bottle phone);
		  public native void removeRow(int index);
		  public native void loadFromFile(String path);
		  public native void saveToFile(String path);
}
