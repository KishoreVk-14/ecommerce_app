package com.example.ecommerce;
import java.sql.*;

public class DbConnection {

    private final String dburl="jdbc:mysql://localhost:3306/ecommerce";

    private final String userNamw="root";

    private final String password="Kishore@14";


    private Statement getStatement(){
        try {
            Connection connection=DriverManager.getConnection(dburl,userNamw,password);
            return connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getQuery(String query){
        try {
            Statement statement=getStatement();
            return statement.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public int updateQuery(String query){

        try {
            Statement statement=getStatement();
            return statement.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }

        return 0;

    }

    public static void main(String[] args) {
        DbConnection dbConnection=new DbConnection();
        ResultSet rs=dbConnection.getQuery("SELECT * FROM customer");

        if(rs!=null){
            System.out.println("haii");
        }

    }

}
