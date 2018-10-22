/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Getway;

import DAL.Product;
import List.ListProduct;
import dataBase.DBConnection;
import dataBase.DBProperties;
import dataBase.SQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 *
 * @author Dian
 */
public class ProductGetway {

    Logger log;
    SQL sql = new SQL();
    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();

    public void view(Product product) {
        con = dbCon.geConnection();
        try {
            pst = con.prepareStatement("select * from " + db + ".Product");
            rs = pst.executeQuery();
            while (rs.next()) {
                product.id = rs.getString(1);
                product.productId = rs.getString(2);
                product.productName = rs.getString(3);
                product.quantity = rs.getString(4);
                product.description = rs.getString(5);
                product.supplierId = rs.getString(6);
                product.brandId = rs.getString(7);
                product.catagoryId = rs.getString(8);
                product.unitId = rs.getString(9);
                product.pursesPrice = rs.getString(10);
                product.userId = rs.getString(13);
                product.date = rs.getString(14);
                product.productList.addAll(new ListProduct(product.id, product.productId,
                        product.productName, product.quantity, product.description,
                        product.supplierId, product.brandId, product.catagoryId,
                        product.unitId, product.pursesPrice, product.sellPrice,
                        product.rmaId, product.userId, product.date));
            }
            rs.close();
            pst.close();
            con.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void searchView(Product product) {
        con = dbCon.geConnection();
        product.productList.clear();
        try {
            pst = con.prepareStatement("select * from " + db + ".Products where ProductName like ? or ProductId like ? ");
            pst.setString(1, "%" + product.productName + "%");
            pst.setString(2, "%" + product.productId + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                product.id = rs.getString(1);
                product.productId = rs.getString(2);
                product.productName = rs.getString(3);
                product.quantity = rs.getString(4);
                product.description = rs.getString(5);
                product.supplierId = rs.getString(6);
                product.brandId = rs.getString(7);
                product.catagoryId = rs.getString(8);
                product.unitId = rs.getString(9);
                product.pursesPrice = rs.getString(10);
                product.userId = rs.getString(13);
                product.date = rs.getString(14);
                product.productList.addAll(new ListProduct(product.id, product.productId,
                        product.productName, product.quantity, product.description,
                        product.supplierId, product.brandId, product.catagoryId,
                        product.unitId, product.pursesPrice, product.sellPrice,
                        product.rmaId, product.userId, product.date));
            }
            rs.close();
            pst.close();
            con.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
