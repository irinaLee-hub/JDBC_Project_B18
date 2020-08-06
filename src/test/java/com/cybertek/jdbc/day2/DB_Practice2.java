package com.cybertek.jdbc.day2;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DB_Practice2 {
    public static void main(String[] args) throws SQLException {
        // print out all data from Jobs Table
        DB_Utility.createConnection();
       // ResultSet rs = DB_Utility.runQuery("SELECT * FROM regions");

        System.out.println("=====================================================================");
     /*   // get the first row data  | without knowing the column names
        int colCount = DB_Utility.getColumnCnt();
        //to get all rows and all columns
        while(rs.next()){//row iteration
            for (int i = 1; i <= colCount; i++) {//column iteration
                System.out.print(rs.getString(i) + "\t");
            }
            System.out.println();//adding a blank line
        }

      */
        ResultSet rs = DB_Utility.runQuery("SELECT * FROM employees");
        DB_Utility.displayAllData();
       // rs.absolute(2);
       // DB_Utility.runQuery("Select * from employees where salary > 19000");
        DB_Utility.displayAllData();

        System.out.println("===========================================================");
        //GETTING A SINGLE DATA IN A SPECIFIC ROW AND SPECIFIC COLUMN
      //  System.out.println(DB_Utility.getColumnDataAtRow(3, 2));
      //  System.out.println(DB_Utility.getColumnDataAtRow(3,"Region_name"));
       // System.out.println(DB_Utility.getRowDataAsList1(4));

        System.out.println(DB_Utility.getColumnDataAsList1(4));

     DB_Utility.destroy();



    }
}
