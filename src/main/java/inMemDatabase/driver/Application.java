package inMemDatabase.driver;

import inMemDatabase.database.IDatabase;
import inMemDatabase.database.SqlLikeDataBase;
import inMemDatabase.ds.Column;
import inMemDatabase.ds.Row;
import inMemDatabase.enums.ColumnType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        //create database
        IDatabase database = new SqlLikeDataBase("PetDatabase");

        //create column schema
        Column name = new Column("name", ColumnType.STRING);
        Column age = new Column("age", ColumnType.INT);
        Column petName = new Column("petName", ColumnType.STRING);
        Column petType = new Column("petType", ColumnType.STRING);
        List<Column> columns = new ArrayList();
        columns.add(name);
        columns.add(age);
        columns.add(petName);
        columns.add(petType);

        //createTable
        database.createTable("PetOwners",columns);

        //insertRow1
        Map<Column,Object> rowData1 = new HashMap();
        rowData1.put(name, "Ankur");
        rowData1.put(age, 35);
        rowData1.put(petName, "Bruno");
        rowData1.put(petType, "Dog");
        database.insertRow("PetOwners", rowData1);

        //insertRow2
        Map<Column,Object> rowData2 = new HashMap();
        rowData2.put(name, "Sneha");
        rowData2.put(age, 32);
        rowData2.put(petName, "Miller");
        rowData2.put(petType, "Dog");
        database.insertRow("PetOwners", rowData2);

        //insertRow3
        Map<Column,Object> rowData3 = new HashMap();
        rowData3.put(name, "Arya");
        rowData3.put(age, 10);
        rowData3.put(petName, "Milky");
        rowData3.put(petType, "Cat");
        database.insertRow("PetOwners", rowData3);

        //get all owners with dog
        List<Row> dogOwners = database.getRowsOnFilterCondition("PetOwners", petType, "Dog");
        dogOwners.forEach(dogOwner -> {
            System.out.println(dogOwner.getColumnToColumnValueMap().get(name));
        });
    }
}
