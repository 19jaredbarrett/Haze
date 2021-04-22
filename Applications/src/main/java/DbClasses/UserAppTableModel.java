package DbClasses;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class UserAppTableModel extends AbstractTableModel {
    private final ArrayList<UserApp> userAppList;

    private final String[] columnNames = new String[] {
            "appName", "Comment"
    };
    private final Class[] columnClass = new Class[] {
            String.class, String.class
    };

    public UserAppTableModel(ArrayList<UserApp> userAppList)
    {
        this.userAppList = userAppList;
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
        return userAppList.size();
    }
    public UserApp getUserApp(int rowIndex) {
        return userAppList.get(rowIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        UserApp row = userAppList.get(rowIndex);
        if(0 == columnIndex) {
            return row.getAppName();
        }
        else if(1 == columnIndex) {
            return row.getComment();
        }

        return null;
    }
}
