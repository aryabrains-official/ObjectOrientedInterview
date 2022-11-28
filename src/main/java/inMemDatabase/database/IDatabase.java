package inMemDatabase.database;

import inMemDatabase.ds.Column;
import inMemDatabase.ds.Row;

import java.util.List;
import java.util.Map;

public interface IDatabase {
    void createTable(String tableName, List<Column> columnSchemaList);
    void dropTable(String tableName);
    void insertRow(String tableName, Map<Column, Object> row);
    void insertMultipleRows(String tableName, List<Map<Column, Object>> rows);

    List<Row> getRowsOnFilterCondition(String tableName, Column column, Object value);
}
