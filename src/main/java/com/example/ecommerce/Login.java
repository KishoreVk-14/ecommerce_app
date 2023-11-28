package com.example.ecommerce;

import java.sql.ResultSet;

public class Login {

    public Customer customerLogin(String useName,String password){
        String query="SELECT * FROM customer WHERE email= + '"+useName+"'  AND password= '"+password+"'";
        DbConnection connection=new DbConnection();
        try {
            ResultSet result=connection.getQuery(query);
            if(result.next()){
                return new Customer(result.getInt("id"),result.getString("name"),result.getString("email"),result.getString("mobile"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Login login=new Login();
        //System.out.println(login.customerLogin("Kishore","kis"));
        Customer customer=login.customerLogin("Kishore","kis");
        System.out.println(customer.getName());
    }
}
