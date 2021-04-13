package DbClasses;

import DbClasses.App;

import javax.swing.table.AbstractTableModel;

import java.util.ArrayList;
//https://www.codejava.net/java-se/swing/editable-jtable-example
//https://www.codejava.net/java-se/swing/editable-jtable-example
//https://www.codejava.net/java-se/swing/editable-jtable-example
public class ApplicationsTableModel extends AbstractTableModel {
    private final ArrayList<App> appsTable;
    // keeps track if we are ascending right now or descending
    private boolean isAsc;
    private int currentApp;

    private final String[] columnNames = new String[] {
            // extra character to
            "Name ↓", "Price  ", "Downloads  ", "Rating  "
    };
    private final Class[] columnClass = new Class[] {
            String.class, Double.class, Integer.class, Integer.class
    };


    /**
     * Method that updates the current ascending or descending value
     * current app is 1-4, name: 1, price: 2, downloads: 3, rating 4
     * the user just clicked a table header
     * @param appPressed the app the user just pressed
     * @return
     */
    public int getIsAsc(int appPressed) {
        appPressed -=1;
        String extraChar;
        int returnVal;
        if(isAsc) {
            extraChar = "↑";
            returnVal = 1;
            isAsc = false;

        } else {
            returnVal = 0;
            extraChar = "↓";
            isAsc = true;
        }
        columnNames[appPressed] = columnNames[appPressed].substring(0, columnNames[appPressed].length()-1) + extraChar;


        return returnVal;
    }



    public boolean isAsc() {
        return isAsc;
    }

    public void setAsc(boolean asc) {
        isAsc = asc;
    }

    public ApplicationsTableModel(ArrayList<App> appsTable) {
        this.appsTable = appsTable;
        this.isAsc = true;
        currentApp = 1;
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
            return row.getRating();
        }
        return null;
    }

}
