package DbClasses;

import DbClasses.App;

import javax.swing.table.AbstractTableModel;

import java.util.ArrayList;
//https://www.codejava.net/java-se/swing/editable-jtable-example
//https://www.codejava.net/java-se/swing/editable-jtable-example
//https://www.codejava.net/java-se/swing/editable-jtable-example
public class ApplicationsTableModel extends AbstractTableModel {
    private final ArrayList<App> appsTable;
    private final String[] columnNames = new String[] {
            "Name", "Price", "Downloads", "Rating"
    };
    private final Class[] columnClass = new Class[] {
            String.class, Double.class, Integer.class, Integer.class
    };
    // keeps track if we are ascending right now or descending
    private boolean isAsc;

    public boolean isAsc() {
        return isAsc;
    }

    public void setAsc(boolean asc) {
        isAsc = asc;
    }

    public ApplicationsTableModel(ArrayList<App> appsTable) {
        this.appsTable = appsTable;
        this.isAsc = true;
    }
    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }


    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return columnClass[columnIndex];
    }
    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }

    @Override
    public int getRowCount()
    {
        return appsTable.size();
    }
    // this gets the app in this row
    public App getApp(int rowIndex) {
        return appsTable.get(rowIndex);
    }



    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        App row = appsTable.get(rowIndex);
        if(0 == columnIndex) {
            return row.getAppName();
        }
        else if(1 == columnIndex) {
            return row.getPrice();
        }
        else if(2 == columnIndex) {
            return row.getNumDownloads();
        } else if (3 == columnIndex) {

        }
        return null;
    }

}
