package DbClasses;


import javax.swing.table.AbstractTableModel;

import java.util.ArrayList;
//https://www.codejava.net/java-se/swing/editable-jtable-example
//https://www.codejava.net/java-se/swing/editable-jtable-example
//https://www.codejava.net/java-se/swing/editable-jtable-example
public class ApplicationsTableModel extends AbstractTableModel {
    private final ArrayList<App> appsTable;
    // keeps track if we are ascending right now or descending
    private boolean isAsc;
    private final int sortedApp;
    public static final int ORDER_BY_NAME = 0;
    public static final int ORDER_BY_PRICE = 1;
    public static final int ORDER_BY_DOWNLOADS = 2;
    public static final int ORDER_BY_RATING = 3;

    private String[] columnNames;
    private final Class[] columnClass = new Class[] {
            String.class, Double.class, Integer.class, Integer.class
    };


    /**
     * Method that updates the current ascending or descending value
     * current app is 1-4, name: 0, price: 1, downloads: 2, rating 3
     * the user just clicked a table header
     * @return opposite of ascending or descending, acts as a switch
     */
    public int getIsAsc() {

        // return the opposite of whatever the current status is
        if(isAsc)
            return 0;
        else return 1;
    }




    public void setAsc(boolean asc) {
        isAsc = asc;
    }

    public ApplicationsTableModel(ArrayList<App> appsTable, int sortedApp, boolean isAsc  ) {
        this.appsTable = appsTable;
        this.sortedApp = sortedApp;
        this.isAsc = isAsc;
        setColumnNames();

    }
    private void setColumnNames() {
         columnNames = new String[] {
                "Name", "Price", "Downloads", "Rating"
        };
         switch (sortedApp) {
             case ORDER_BY_NAME:
                 if(isAsc) columnNames[0] += " ↑";
                 else columnNames[0] += " ↓";
                 break;
             case ORDER_BY_PRICE:
                 if(isAsc)
                     columnNames[1] += " ↑";
                 else columnNames[1] += " ↓";
                 break;
             case ORDER_BY_DOWNLOADS:
                 if(isAsc)
                     columnNames[2] += " ↑";
                 else columnNames[2] += " ↓";
                 break;
             case ORDER_BY_RATING:
                 if(isAsc)
                     columnNames[3] += " ↑";
                 else columnNames[3] += " ↓";
                 break;
         }
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
