import java.util.List;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ExpenseIncomeTableModel extends AbstractTableModel {
    // The list to store the entries' rows in the table
    private final List<ExpenseIncomeEntry> entries;
    //table column names
    private final String[] columnNames = {"Date","Description","Amount","Type",};

    public ExpenseIncomeTableModel(){
        entries = new ArrayList<>();
    }

    public void addEntry(ExpenseIncomeEntry entry) {
        entries.add(entry);
        //To notify the table of the insertion of a new row
        fireTableRowsInserted(entries.size()-1, entries.size()-1);
    }

    @Override
    public int getRowCount() {
        return entries.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column)  {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ExpenseIncomeEntry entry = entries.get(rowIndex);

        //return cell values depending on the column index
        switch (columnIndex){
            case 0:
                return entry.getDate();
            case 1:
                return entry.getDescription();
            case 2:
                return entry.getAmount();
            case 3:
                return entry.getType();
            default:
                return null;
        }

    }


}
