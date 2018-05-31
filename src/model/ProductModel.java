/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Product;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author xuanhung
 */
public class ProductModel {

    public boolean register(Product product) {
        try {
            Connection cnn = DBConnection.getInstance().getConnection();
            PreparedStatement ps = cnn.prepareStatement("insert into `product` (`name`, `price`, `image`) values (?,?,?)");
            ps.setString(1, product.getName());
            ps.setString(2, product.getPrice());
            ps.setString(3, product.getImage());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public boolean checkExistName(String name) {
        try {
            Connection cnn = DBConnection.getInstance().getConnection();
            PreparedStatement ps = cnn.prepareStatement("select * from `product` where `username` = ?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public ObservableList<Product> displayProduct() {
        ObservableList<Product> listProduct = FXCollections.observableArrayList();
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/vomat?useUnicode=true&characterEncoding=utf-8", "root", "");
            Statement stt = connection.createStatement();
            ResultSet rs = stt.executeQuery("select * from `product`");
            while (rs.next()) {
                String name = rs.getString("name");
                String image = rs.getString("image");
                String price = rs.getString("price");
                Product product = new Product(name, image, price);
                for (Product product1 : listProduct) {
                    listProduct.add(product);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listProduct;
    }
}
