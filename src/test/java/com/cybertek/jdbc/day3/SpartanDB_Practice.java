package com.cybertek.jdbc.day3;

import com.cybertek.jdbc.utility.ConfigurationReader;
import com.cybertek.jdbc.utility.DB_Utility;
import static com.cybertek.jdbc.utility.DB_Utility.*;
//using static import and dont need class name to call the methods from there

public class SpartanDB_Practice {
    public static void main(String[] args) {

        //String url = "jdbc:oracle:thin:@100.26.246.143:1521:XE";
        //DB_Utility.createConnection(url, "SP", "SP");
       DB_Utility.createConnection();
       // DB_Utility.createConnection("dev");
    //Run query "SELECT * FROM SPARTANS"
      DB_Utility.runQuery("select * from spartans");
//    1. Display all data in console
      //  DB_Utility.displayAllData();
//    2. Print column count
       // System.out.println("COLUMN COUNT IS "+ DB_Utility.getColumnCnt());
//    3. Print row count
       // System.out.println("ROW COUNT "+DB_Utility.getRowCount());
//    4. Print out 3rd row data as a list
        //System.out.println("3rd ROW DATA AS LIST :\n"+ DB_Utility.getRowDataAsList(3));
//    5. Print out 2nd column data as a list
       // System.out.println("2nd COLUMN AS LIST: \n"+DB_Utility.getColumnDataAsList(2));
//    6, Print out Name column data as a list
        //System.out.println("Name column as list \n"+ DB_Utility.getColumnDataAsList("Name"));
//    7, Print out 4th row as a Map
      //  System.out.println("4th row as a Map \n"+DB_Utility.getRowDataAsMap(4));
//    8, Print out the data at row 5, column 1
  //      System.out.println("Data at row 5 column 1 \n"+ DB_Utility.getColumnDataAtRow(5,1));
//    9, Print out the data at row 53, phone column
      //  System.out.println("Data at Row 53 phone column \n"+DB_Utility.getColumnDataAtRow(53,"Phone"));
//    10. Print out all the data as List of Map
        //we are storing each row as Map object and we have 100 row,so we can store these 100 map objects into one Collection
        //=>List of Map objects
        System.out.println("Data as List of Map \n"+DB_Utility.getAllDataAsListOfMap());


        DB_Utility.destroy();
    }
}
