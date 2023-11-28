package com.example.ecommerce;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class UserInterface {

    GridPane loginPage;

    HBox headerBar;

    Customer loggedIn;

    ProductList productList=new ProductList();

    VBox productPage;



    public BorderPane createContent(){
        BorderPane root =new BorderPane();
        root.setPrefSize(800,600);
        root.setTop(headerBar);
        //root.setCenter(loginPage);
        productPage=productList.createTable();
        root.setCenter(productPage);
        return root;
    }
    public UserInterface(){
        createLogin();
        createHeaderBar();
    }

    private void createLogin(){
        Text userName=new Text("UserName: ");
        Text passwordText=new Text("Password: ");

        Label messageLabel = new Label();

        TextField userNameField=new TextField();
        userNameField.setPromptText("Username...");
        PasswordField passwordField =new PasswordField();
        passwordField.setPromptText("Password...");

        Button loginButton =new Button("Login");

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String userName=userNameField.getText();
                String password=passwordField.getText();
                Login login=new Login();
                loggedIn=login.customerLogin(userName,password);

                if(loggedIn!=null){
                    messageLabel.setText("Log In Success");
                }else{
                    messageLabel.setText("Log In Failed");
                }

            }
        });

        loginPage=new GridPane();
        loginPage.setAlignment(Pos.CENTER);
        loginPage.setVgap(10);
        loginPage.setHgap(10);
        loginPage.add(userName,0,0);
        loginPage.add(userNameField,1,0);
        loginPage.add(passwordText,0,1);
        loginPage.add(passwordField,1,1);
        loginPage.add(loginButton,1,2);
    }

    private void createHeaderBar(){
        TextField searchBar=new TextField();
        searchBar.setPrefWidth(280);
        searchBar.setPromptText("Search here");
        Button searchbutton=new Button("Search");
        headerBar=new HBox();
        headerBar.setSpacing(10);
        headerBar.setPadding(new Insets(10));
        headerBar.setAlignment(Pos.CENTER);
        headerBar.getChildren().addAll(searchBar,searchbutton);
    }

}
