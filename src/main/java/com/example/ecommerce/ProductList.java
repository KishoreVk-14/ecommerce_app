package com.example.ecommerce;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class ProductList {

    private TableView<Product>productTable;

    public VBox createTable(){
        TableColumn id=new TableColumn<>("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name=new TableColumn<>("NAME");
        id.setCellValueFactory(new PropertyValueFactory<>("name"));

        ObservableList<Product>data = FXCollections.observableArrayList();
        data.add(new Product(2,"IPhone",750000.0));
        data.add(new Product(5,"Samsung",1750000.0));

        productTable=new TableView<>();
        productTable.setItems(data);
        productTable.getColumns().addAll(id,name);

        VBox vBox=new VBox();
        vBox.getChildren().addAll(productTable);
        return vBox;


    }
}
