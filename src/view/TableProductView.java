/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entity.Product;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.ProductModel;

/**
 *
 * @author duy
 */
public class TableProductView extends Application {

    ProductModel productModel = new ProductModel();

    @Override
    public void start(Stage primaryStage) throws Exception {
        TableView<Product> tableView = new TableView<>();

        TableColumn<Product, String> columnName = new TableColumn<>("Product Name");
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnName.setMinWidth(200);

        TableColumn<Product, String> columnImage = new TableColumn<>("Product Image");
        columnImage.setCellValueFactory(new PropertyValueFactory<>("image"));
        columnImage.setMinWidth(170);

        TableColumn<Product, String> columnPrice = new TableColumn<>("Product Price");
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        columnPrice.setMinWidth(130);

        columnImage.setCellFactory(new Callback<TableColumn<Product, String>, TableCell<Product, String>>() {
            public TableCell<Product, String> call(TableColumn<Product, String> param) {
                return new TableCell<Product, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        if (item != null) {
                            Image image = new Image(productModel.displayProduct() + item);
                            ImageView imageView = new ImageView();
                            imageView.setFitHeight(50);
                            imageView.setFitWidth(50);
                            imageView.setImage(image);
                            setGraphic(imageView);
                        }
                    }
                };
            }
        });

        tableView.getColumns().addAll(columnImage, columnName, columnPrice);
        ObservableList<Product> list = productModel.displayProduct();
        tableView.getItems().addAll(list);

        Scene scene = new Scene(tableView, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
