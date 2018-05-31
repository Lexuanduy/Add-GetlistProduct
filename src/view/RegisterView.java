/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entity.Product;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.ProductModel;

/**
 *
 * @author duy
 */
public class RegisterView extends Application {

    private ProductModel productModel = new ProductModel();

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        Label lblName = new Label("Name");
        Label lblImage = new Label("Image");
        Label lblPrice = new Label("Price");
        Label lblMessage = new Label();
        TextField txtName = new TextField();
        TextField txtImage = new TextField();
        TextField txtPrice = new TextField();
        Button btnSubmit = new Button("Submit");
        Button btnReset = new Button("Reset");
        btnSubmit.setOnAction((event) -> {
            String name = txtName.getText();
            String image = txtImage.getText();
            String price = txtPrice.getText();
            if (name == null || name.length() == 0) {
                lblMessage.setText("Vui lòng sửa các thông tin bên dưới và thử lại.");
                lblMessage.setTextFill(Color.web("red"));
                return;
            } else if (productModel.checkExistName(name)) {
                lblMessage.setText("Tên sản phẩm đã tồn tại, vui lòng chọn sản phẩm khác.");
                lblMessage.setTextFill(Color.web("red"));
            }
            if (image == null || image.length() == 0) {
                lblMessage.setText("Vui lòng sửa các thông tin bên dưới và thử lại.");
                lblMessage.setTextFill(Color.web("red"));
            }
            if (price == null || price.length() == 0) {
                lblMessage.setText("Vui lòng sửa các thông tin bên dưới và thử lại.");
                lblMessage.setTextFill(Color.web("red"));
            }
            Product product = new Product(name, image, price);
            if (productModel.register(product)) {
                lblMessage.setText("Đăng ký thành công.");
                lblMessage.setTextFill(Color.web("green"));
                txtName.clear();
                txtImage.clear();
                txtPrice.clear();
            } else {
                lblMessage.setText("Đăng ký thất bại, vui lòng thử lại.");
                lblMessage.setTextFill(Color.web("red"));
            }
        });

        gridPane.add(lblMessage, 0, 0, 1, 1);
        gridPane.add(lblName, 0, 1, 1, 1);
        gridPane.add(lblImage, 0, 2, 1, 1);
        gridPane.add(lblPrice, 0, 3, 1, 1);
        gridPane.add(txtName, 1, 1, 1, 1);
        gridPane.add(txtImage, 1, 2, 1, 1);
        gridPane.add(txtPrice, 1, 3, 1, 1);

        HBox hbox = new HBox();
        hbox.setSpacing(10);
        hbox.getChildren().addAll(btnSubmit, btnReset);

        gridPane.add(hbox, 1, 4, 1, 1);
        Scene scene = new Scene(gridPane, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
