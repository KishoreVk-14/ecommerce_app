package com.example.ecommerce;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class UserInterface {

    GridPane loginPage;

    HBox headerBar;

    Customer loggedIn;

    ProductList productList=new ProductList();

    VBox productPage;

    VBox body;

    Button signinButton;

    Label welcomeLabel;

    HBox footerBar;

    ObservableList <Product>itemInCart= FXCollections.observableArrayList();

    Button placeOrderButton =new Button("Place Order ");



    public BorderPane createContent(){
        BorderPane root =new BorderPane();
        root.setPrefSize(800,600);
        root.setTop(headerBar);
        //root.setCenter(loginPage);
        body=new VBox();
        body.setPadding(new Insets(10));
        body.setAlignment(Pos.CENTER);
        root.setCenter(body);
        productPage=productList.getAllProduct();
        body.getChildren().add(productPage);
        root.setBottom(footerBar);
        return root;
    }
    public UserInterface(){
        createLogin();
        createHeaderBar();
        createFooterBar();
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
                    messageLabel.setText("Welcome"+ loggedIn.getName());
                    welcomeLabel.setText("Welcome "+ loggedIn.getName());
                    headerBar.getChildren().add(welcomeLabel);
                    body.getChildren().clear();
                    body.getChildren().add(productPage);
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

        Button homeButton=new Button();
        Image image=new Image("C:\\Users\\kisho\\IdeaProjects\\ecommerce\\src\\OIP.jpg");
        ImageView img=new ImageView();
        img.setImage(image);
        img.setFitWidth(80);
        img.setFitHeight(20);
        homeButton.setGraphic(img);

        TextField searchBar=new TextField();
        welcomeLabel=new Label();
        searchBar.setPrefWidth(280);
        searchBar.setPromptText("Search here");
        Button searchbutton=new Button("Search");
        signinButton=new Button("Sign In");
        headerBar=new HBox();
        headerBar.setSpacing(10);
        headerBar.setPadding(new Insets(10));
        headerBar.setAlignment(Pos.CENTER);

        Button cartButton=new Button("cart");

        headerBar.getChildren().addAll(homeButton,searchBar,searchbutton,signinButton,cartButton);



        signinButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                body.getChildren().add(loginPage);
                headerBar.getChildren().remove(signinButton);
            }
        });

        cartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                VBox prodPage=productList.getProductInCart(itemInCart);
                prodPage.getChildren().add(placeOrderButton);
                prodPage.setAlignment(Pos.CENTER);
                prodPage.setSpacing(10);

                body.getChildren().add(prodPage);
                footerBar.setVisible(false);
            }
        });

        placeOrderButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {


                if(itemInCart==null){
                    showDialogue("Add some Product");
                }
                if(loggedIn==null){
                    showDialogue("Login first");
                }
                int count=Order.placeMultipleOrder(loggedIn,itemInCart);
                if(count!=0){
                    showDialogue("Order Success");
                }else {
                    showDialogue("Order Failed");
                }
            }

        });

        homeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                body.getChildren().add(productPage);
                footerBar.setVisible(true);
                if(loggedIn==null){
                    if(headerBar.getChildren().indexOf(signinButton)==-1){
                        headerBar.getChildren().add(signinButton);
                    }


                }
            }
        });

    }

    private void createFooterBar(){

        Button buyNowButton=new Button("Buy Now!!");

        footerBar=new HBox();
        footerBar.setSpacing(10);
        footerBar.setPadding(new Insets(10));
        footerBar.setAlignment(Pos.CENTER);

        Button addtoCart=new Button("Add to Cart");

        footerBar.getChildren().addAll(buyNowButton,addtoCart);

        buyNowButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product=productList.selectedProduvt();

                if(product==null){
                    showDialogue("Select order");
                }
                if(loggedIn==null){
                    showDialogue("Login first");
                }
                boolean status=Order.placeOrder(loggedIn,product);
                if(status){
                    showDialogue("Order Success");
                }else {
                    showDialogue("Order Failed");
                }
            }
        });

        addtoCart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product=productList.selectedProduvt();

                if(product==null){
                    showDialogue("Select product to add  in cart");

                }
                itemInCart.add(product);
                showDialogue("Selected product add in cart");
            }
        });
    }

    private void showDialogue(String message){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.setTitle("Message");
        alert.showAndWait();

    }

}
