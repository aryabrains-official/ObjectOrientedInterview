package inMemDatabase.ds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//collection of rows
public class Table {
    private List<Row> rowList = new ArrayList<>();
    private Map<String, Column> columnNameToSchemaMap = new HashMap<>();
    private int rowNumber;
    private final String tableName;
    public Table(String tableName, List<Column> columnSchemaList) {
        this.tableName = tableName;
        this.rowNumber = 0;
        columnSchemaList.forEach(columnSchema -> {
            columnNameToSchemaMap.put(columnSchema.getColumnName(), columnSchema);
        });
    }

    public void insertRow(Map<Column, Object> rowData) {
        if(!isRowValid(rowData)) {
            throw new IllegalArgumentException("InvalidRow");
        }
        int newRowNumber = getRowNumber();
        Row row = new Row(newRowNumber, rowData);
        this.rowList.add(row);
    }

    public List<Row> getRecords(Column column, Object value) {
        List<Row> rowsReturned = new ArrayList<>();
        this.rowList.forEach(row -> {
            Object columnValue = row.getColumnToColumnValueMap().get(column);
            if(columnValue.equals(value)) {
                rowsReturned.add(row);
            }
        });
        return rowsReturned;
    }

    private boolean isRowValid(final Map<Column, Object> rowData) {
        for (Map.Entry<Column,Object> column : rowData.entrySet()) {
            //validate the Object datatype
            Class supportedColumnDataType = column.getKey().getColumnType().getColumnTypeClass();
            if(!supportedColumnDataType.isInstance(column.getValue())) {
                return false;
            }
            //validate column names are same as passed during schema creation
            if(!columnNameToSchemaMap.containsKey(column.getKey().getColumnName())) {
                return false;
            }
        }
        return true;
    }

    private synchronized int getRowNumber() {
        this.rowNumber++;
        return this.rowNumber;
    }
}
