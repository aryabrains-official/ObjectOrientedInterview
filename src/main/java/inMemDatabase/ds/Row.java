package inMemDatabase.ds;

import java.util.Map;

//collection of columns
public class Row {
    private int rowNumber;
    //input should be able to take multiple types of datatype like String,Int etc
    private Map<Column, Object> columnToColumnValueMap;

    public Row(int rowNumber, Map<Column, Object>columnToColumnValueMap) {
        this.rowNumber = rowNumber;
        this.columnToColumnValueMap = columnToColumnValueMap;
    }

    public int getRowNumber() {
        return this.rowNumber;
    }

    public Map<Column, Object> getColumnToColumnValueMap() {
        return this.columnToColumnValueMap;
    }
}
