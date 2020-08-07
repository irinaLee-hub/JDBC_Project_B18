package com.cybertek.jdbc.utility;

import com.sun.rowset.internal.Row;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DB_Utility {
    private static Connection conn;
    private static ResultSet rs;
    private static Statement statement;

    //Create a connection with valid url and username and password
   /* public static void createConnection() {
        String connectionStr = ConfigurationReader.getProperty("database.url");
        String username = ConfigurationReader.getProperty("database.username");
        String password = ConfigurationReader.getProperty("database.password");

//            try {
//            conn = DriverManager.getConnection(connectionStr, username, password);
//            System.out.println("Connection successful!");
//        } catch (SQLException throwables) {
//            System.out.println("CONNECTION HAS FAILED!");
//            throwables.printStackTrace();
//        }
                 createConnection(connectionStr,username,password);
        }


    }

    */
    public static void createConnection() {


//            try {
//            conn = DriverManager.getConnection(connectionStr, username, password);
//            System.out.println("Connection successful!");
//        } catch (SQLException throwables) {
//            System.out.println("CONNECTION HAS FAILED!");
//            throwables.printStackTrace();
//        }

        if(ConfigurationReader.getProperty("active_env").equalsIgnoreCase("test")){
            String connectionStr = ConfigurationReader.getProperty("test.database.url");
            String username = ConfigurationReader.getProperty("test.database.username");
            String password = ConfigurationReader.getProperty("test.database.password");
            createConnection(connectionStr,username,password);
        }else if(ConfigurationReader.getProperty("active_env").equalsIgnoreCase("dev")){
            String connectionStr = ConfigurationReader.getProperty("dev.database.url");
            String username = ConfigurationReader.getProperty("dev.database.username");
            String password = ConfigurationReader.getProperty("dev.database.password");
            createConnection(connectionStr,username,password);
        }else{
            System.out.println("No environment info provided!");
        }


    }


    //OVERLOAD createConnection() METHOD TO ACCEPT USERNAME,PASSWORD AND CONNECTIONSTR
    //url=ConnectionStr

   public static void createConnection(String url,String username,String password){

        try{
            conn=DriverManager.getConnection(url,username,password);
        }catch(SQLException e){
            System.out.println("ERROR WHILE CONNECTING");
            e.printStackTrace();
        }


    }
    //OVERLOAD CONNECTION METHOD

    public static void createConnection(String env){
        //add validation to avoid invalid input
        //or add some condition to directly get the information
        String connectionStr = ConfigurationReader.getProperty(env+".database.url");
        String username = ConfigurationReader.getProperty(env+".database.username");
        String password = ConfigurationReader.getProperty(env+".database.password");
        createConnection(connectionStr,username,password);
        System.out.println("You are in "+env+" environment");


    }



    //a method to get the resultSet object with valid connection by executing query
    public static ResultSet runQuery(String Query) {

        try {
           statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = statement.executeQuery(Query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    //METHOD TO GET THE COLUMN COUNT OF THE CURRENT RESULTSET
    public static int getColumnCnt() {
        int colCount = 0;
        try {

            ResultSetMetaData rsmd = rs.getMetaData();
            colCount = rsmd.getColumnCount();
        } catch (SQLException e) {
            System.out.println("ERROR WHILE COUNTING THE COLUMN");
            e.printStackTrace();
        }
        return colCount;
    }

    //Method to display all data from resultSet
    public static void displayAllData() {
        // get the first row data  | without knowing the column names

        int colCount = DB_Utility.getColumnCnt();
        //to get all rows and all columns
        try {
            rs.beforeFirst();
            while (rs.next()) {//row iteration
                for (int i = 1; i <= colCount; i++) {//column iteration
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();//adding a blank line
            }
            rs.beforeFirst();
        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING ALL DATA");
            e.printStackTrace();
        }
    }

    //getting single column cell value at certain row
    public static String getColumnDataAtRow (int rowNum , int columnIndex){

        String result = "" ;
        try {
            rs.absolute( rowNum ) ;
            result = rs.getString( columnIndex ) ;


        } catch (SQLException e) {
            System.out.println("ERROR WHILE getColumnDataAtRow ");
            e.printStackTrace();
        }


        return result ;

    }


    public static String getColumnDataAtRow (int rowNum , String columnName){

        String result = "" ;
        try {
            rs.absolute( rowNum ) ;
            result = rs.getString( columnName );

        } catch (SQLException e) {
            System.out.println("ERROR WHILE getColumnDataAtRow ");
            e.printStackTrace();
        }

        return result ;

    }

    //Getting entire row asList of String of all columns
    public static List<String>getRowDataAsList(int rowNum){
        List<String> rowDataList = new ArrayList<>();

        // how to move to that Row with rowNum

        try {
            rs.absolute(rowNum);
            // iterate over each and every column and add the valie to the list
            for (int i = 1; i <=  getColumnCnt() ; i++) {
                rowDataList.add(    rs.getString(i)    );

            }

          rs.beforeFirst();
        } catch (SQLException e) {

            System.out.println("ERROR WHILE getRowDataAsList ");
            e.printStackTrace();

        }

        return rowDataList;

    }

    public static int getRowCount(){
        int rowCount=0;

        try{
            rs.last();
            rowCount=rs.getRow();
            //moving back the cursor to before first location just in case
            rs.beforeFirst();
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("ERROR WHILE getRowCount");
        }
        return rowCount;
    }


    public static List<String>getColumnDataAsList(int columnIndex){
        List<String>columnDataList= new ArrayList<>();
        try {
            rs.beforeFirst();//moving the cursor to before first location
            while (rs.next()) {

                String data =  rs.getString(columnIndex) ;

                // getting the data from that column and adding to the the list
                columnDataList.add( data  );
                rs.beforeFirst();//putting back cursor after we done
            }
        }catch(SQLException e){
            System.out.println("ERROR WHILE getColumnDataAsList");
            e.printStackTrace();
        }



        return columnDataList;
    }

    public static List<String> getColumnDataAsList(String columnName){

        List<String> columnDataLst = new ArrayList<>();
        try {
            rs.beforeFirst();  // moving the cursor to before first location

            while(rs.next() ){

                String data =  rs.getString(columnName) ;
                // getting the data from that column and adding to the the list
                columnDataLst.add( data  );

            }

            rs.beforeFirst();  // moving the cursor to before first location after we are done
        } catch (SQLException throwables) {
            System.out.println("ERROR WHILE getColumnDataAsList ");
            throwables.printStackTrace();
        }

        return columnDataLst;

    }


        //GETTING FIRST ROW DATA AS MAP
    //THE KEY IS COLUMN NAME,VALUE IS COLUMN DATA
        public static  Map<String,String> getRowDataAsMap(int rowNum) {
            Map<String,String> rowMap = new HashMap<>();
            try {
                rs.absolute(rowNum);
                ResultSetMetaData rsmd = rs.getMetaData();
                for (int i = 1; i <= getColumnCnt(); i++) {
                    rowMap.put(rsmd.getColumnName(i), rs.getString(i));
                }
            } catch (SQLException e) {
                System.out.println("ERROR WHILE getRowDataAsList");
                e.printStackTrace();
            }
            return rowMap;
        }

     /**
             *
             * @return The entire resultset as List of Row Map
 */
    public static List<Map<String,String> > getAllDataAsListOfMap(){
        List<Map<String,String> > rowMapList = new ArrayList<>();
        for (int i = 1; i <= getRowCount(); i++) {
            rowMapList.add(   getRowDataAsMap(i) ) ;


        }

        return rowMapList ;
    }


        public static List<String> getColumnNameList() {
            List<String> colLst = new ArrayList<>();
            try {
                ResultSetMetaData rsmd = rs.getMetaData();
                int colCount = rsmd.getColumnCount();
                for (int i = 1; i <= colCount; i++) {
                    colLst.add(rsmd.getColumnName(i));
                }
            } catch (SQLException e) {
                System.out.println("ERROR WHILE GETTING THE COLUMN NAMES");
                e.printStackTrace();
            }
            return colLst;


        }
    public static void destroy() {

        try {
            if (rs != null) {
                rs.close();
            }
            if (statement != null) {
                statement.close();

            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to close database connection!");
        }
    }



}




