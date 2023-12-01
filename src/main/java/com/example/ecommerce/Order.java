package com.example.ecommerce;

import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class Order {

   public static  boolean placeOrder(Customer customer,Product product){

       String groupId="SELECT MAX(group_order_id)+1 id FROM orders";
       DbConnection dbConnection=new DbConnection();
       try{
           ResultSet resultSet=dbConnection.getQuery(groupId);
           if(resultSet.next()){
               String placeOrder="INSERT INTO orders (group_order_id,customer_id,product_id) VALUES("+resultSet.getInt("id")+"," +customer.getId()+","+product.getId()+")";
               return dbConnection.updateQuery(placeOrder)!=0;
           }
       }catch (Exception e){
           e.printStackTrace();
       }
       return false;

   }

    public static  int placeMultipleOrder(Customer customer, ObservableList<Product> productList){

        String groupId="SELECT MAX(group_order_id)+1 id FROM orders";
        DbConnection dbConnection=new DbConnection();
        try{
            ResultSet resultSet=dbConnection.getQuery(groupId);
            int count=0;
            if(resultSet.next()){
                for(Product product:productList){
                    String placeOrder="INSERT INTO orders (group_order_id,customer_id,product_id) VALUES("+resultSet.getInt("id")+"," +customer.getId()+","+product.getId()+")";
                    count+= dbConnection.updateQuery(placeOrder);
                }
            }
            return count;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;

    }
}
