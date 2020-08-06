package com.cybertek.jdbc.day2;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DB_Practice {
    public static void main(String[] args) throws SQLException {

        DB_Utility.createConnection();
        ResultSet rs=DB_Utility.runQuery("select * from jobs");
      //  rs.next();
      //  System.out.println(rs.getString(1));
        while(rs.next()){
            System.out.println(rs.getString(1));
        }

        //we want to print out everything in resultset without knowing th column name

        System.out.println("Column count: "+ DB_Utility.getColumnCnt());
        //To get the first row data without knowing column name
        //to get entire columns and 1 row of data
        int colCount=DB_Utility.getColumnCnt();
        rs.first();
        for( int i=1;i<=colCount;i++){
            System.out.print(rs.getString(i)+ "\t");
        }

       DB_Utility.destroy();


    }
}
