package com.cybertek.jdbc.day2;

import com.cybertek.jdbc.utility.DB_Utility;

public class DBUtility_Practice {
    public static void main(String[] args) {



        DB_Utility.createConnection();
        DB_Utility.runQuery("select * from regions");
        System.out.println(DB_Utility.getRowDataAsList(2));
        System.out.println(DB_Utility.getColumnDataAtRow(2,2));
        System.out.println(DB_Utility.getRowDataAsMap(2));







        DB_Utility.destroy();
    }
}
