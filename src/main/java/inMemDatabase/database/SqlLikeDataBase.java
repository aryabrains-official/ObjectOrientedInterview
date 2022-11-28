package inMemDatabase.database;

import inMemDatabase.ds.Column;
import inMemDatabase.ds.Row;
import inMemDatabase.ds.Table;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

////collection of tables
public class SqlLikeDataBase implements IDatabase {
    private String databaseName;
    private Map<String, Table> tableNameToTableMap;

    public SqlLikeDataBase(String databaseName) {
        this.tableNameToTableMap = new HashMap<>();
        this.databaseName = databaseName;
    }

    @Override
    public void createTable(String tableName, List<Column> columnSchemaList) {
        if(tableNameToTableMap.containsKey(tableName)) {
            throw new IllegalArgumentException("TableAlreadyExists");
        }
        Table table = new Table(tableName, columnSchemaList);
        tableNameToTableMap.put(tableName, table);
    }


    @Override
    public void dropTable(String tableName) {
        if(!tableNameToTableMap.containsKey(tableName)){
            throw new IllegalArgumentException("TableNotExist");
        }
        tableNameToTableMap.remove(tableName);
    }

    @Override
    public void insertRow(String tableName, Map<Column, Object> row) {
        if(!tableNameToTableMap.containsKey(tableName)){
            throw new IllegalArgumentException("TableNotExist");
        }
        Table table = tableNameToTableMap.get(tableName);
        table.insertRow(row);
    }

    @Override
    public void insertMultipleRows(String tableName, List<Map<Column, Object>> rows) {
        if(!tableNameToTableMap.containsKey(tableName)){
            throw new IllegalArgumentException("TableNotExist");
        }
        Table table = tableNameToTableMap.get(tableName);
        rows.forEach(row -> {
            table.insertRow(row);
        });
    }

    @Override
    public List<Row> getRowsOnFilterCondition(String tableName, Column column, Object value) {
        if(!tableNameToTableMap.containsKey(tableName)){
            throw new IllegalArgumentException("TableNotExist");
        }
        Table table = tableNameToTableMap.get(tableName);
        return table.getRecords(column, value);
    }
}
