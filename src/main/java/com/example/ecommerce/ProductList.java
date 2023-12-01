package com.example.ecommerce;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class ProductList {

    private TableView<Product>productTable;

    public VBox createTable(ObservableList<Product>data){
        TableColumn id=new TableColumn<>("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name=new TableColumn<>("NAME");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price=new TableColumn<>("PRICE");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));



        productTable=new TableView<>();
        productTable.setItems(data);
        productTable.getColumns().addAll(id,name,price);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        VBox vBox=new VBox();
        vBox.setPadding(new Insets(10));
        vBox.getChildren().addAll(productTable);
        return vBox;


    }

    public VBox getDummyData(){
        ObservableList<Product>data = FXCollections.observableArrayList();
        data.add(new Product(2,"IPhone",750000.0));
        data.add(new Product(5,"Samsung",1750000.0));
        return createTable(data);
    }

    public VBox getAllProduct(){
        ObservableList<Product> data=Product.getAllProduct();
        return  createTable(data);
    }

    public Product selectedProduvt(){
        return productTable.getSelectionModel().getSelectedItem();
    }

    public  VBox getProductInCart(ObservableList<Product>data){
        return createTable(data);

    }
}
