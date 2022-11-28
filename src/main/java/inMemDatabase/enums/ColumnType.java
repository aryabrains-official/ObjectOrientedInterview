package inMemDatabase.enums;

public enum ColumnType {
    INT(Integer.class),
    STRING(String.class);

    public final Class columnTypeClass;

    ColumnType(Class columnTypeClass) {
        this.columnTypeClass = columnTypeClass;
    }

    public Class getColumnTypeClass() {
        return this.columnTypeClass;
    }
}
