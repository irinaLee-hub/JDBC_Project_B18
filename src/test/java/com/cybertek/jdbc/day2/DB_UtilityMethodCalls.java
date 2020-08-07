package com.cybertek.jdbc.day2;

import com.cybertek.jdbc.utility.DB_Utility;

public class DB_UtilityMethodCalls {
    public static void main(String[] args) {

        DB_Utility.createConnection();
        DB_Utility.runQuery("select * from jobs");

        //        DB_Utility.displayAllData();



//        System.out.println("Column count is " + DB_Utility.getColumnCNT() );

//        System.out.println("Row count is "    + DB_Utility.getRowCount() );

//

//        System.out.println("Getting 3rd row of Jobs Table \n "

//                                + DB_Utility.getRowDataAsList(3) );

//        System.out.println("Getting 2nd Column as a list \n"

//                                  + DB_Utility.getColumnDataAsList(2) );

//        System.out.println("Getting JOB_TITLE Column as a list \n"

//                                  + DB_Utility.getColumnDataAsList("JOB_TITLE") );

//        System.out.println("Getting 3rd row , 2nd column data \n"

//                                    + DB_Utility.getColumnDataAtRow(3,2) );

//        System.out.println("Getting 3rd row , JOB_TITLE column data \n"

//                                    + DB_Utility.getColumnDataAtRow(3,"JOB_TITLE") );

//        System.out.println("Getter the data at 3rd row as Map : \n"

//                                    + DB_Utility.getRowMap(3) );

        DB_Utility.destroy();




    }
}
