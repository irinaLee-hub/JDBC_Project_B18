package com.cybertek.jdbc.day1;

import java.sql.*;

public class JDBC_Practice {
    public static void main(String[] args)throws SQLException {
        String connectionStr="jdbc:oracle:thin:@100.26.246.143:1521:XE";
        String username="hr";
        String password="hr";
        Connection connection= DriverManager.getConnection(connectionStr,username,password);
        Statement statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet rs=statement.executeQuery("select * from jobs");

       // rs.next();
        while(rs.next()) {
            System.out.println(rs.getString(2));
        }


        rs.close();
        statement.close();
        connection.close();




    }
}
