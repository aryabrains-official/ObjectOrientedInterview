package inMemDatabase.ds;

import inMemDatabase.enums.ColumnType;

//basic unit of the system to verify schema
public class Column {
    private String columnName;
    private ColumnType columnType;

    public Column(String columnName, ColumnType columnType) {
        this.columnName = columnName;
        this.columnType = columnType;
    }

    public String getColumnName() {
        return this.columnName;
    }

    public ColumnType getColumnType() {
        return this.columnType;
    }
}
