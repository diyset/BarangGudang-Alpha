/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Getway;

import DAL.Warehouse;
import List.ListWarehouse;
import dataBase.DBConnection;
import dataBase.DBProperties;
import dataBase.SQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.logging.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Dian
 */
public class WarehouseGetway {

    DBConnection dbCon = new DBConnection();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();

    public void save(Warehouse warehouse) {
        con = dbCon.geConnection();

        try {
            pst = con.prepareStatement("insert into " + db + ".StoreIn values(?,?,?,?,?,?,?)");
            pst.setString(1, null);
            pst.setString(2, warehouse.getStoreInId());
            pst.setString(3, warehouse.getProductId());
            pst.setString(4, warehouse.getQuantity());
            pst.setString(5, warehouse.getTotalPrice());
            pst.setString(6, warehouse.getSupplierId());
            pst.setString(7, warehouse.getUserId());
            pst.setString(8, LocalDateTime.now().toString());
            pst.executeUpdate();
            pst.close();
            rs.close();
            con.close();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(SellCartGerway.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);

        }
    }

    public void view(Warehouse warehouse) {
        con = dbCon.geConnection();
        SQL sql = new SQL();
        try {
            pst = con.prepareStatement("select * from " + db + ".StoreIn");
            rs = pst.executeQuery();
            while (rs.next()) {
                warehouse.id = rs.getString(1);
                warehouse.storeInId = rs.getString(2);
                warehouse.productId = rs.getString(3);
                warehouse.quantity = rs.getString(4);
                warehouse.totalPrice = rs.getString(5);
                warehouse.supplierName = sql.getNameSupplier(rs.getString(6), warehouse.supplierName, "Supplyer");
                warehouse.userId = rs.getString(7);
                warehouse.userName = sql.getName(warehouse.userId, warehouse.userName, "User");
                warehouse.storeInDate = rs.getString(8);
                warehouse.listWarehouse.addAll(new ListWarehouse(warehouse.id,
                        warehouse.storeInId, warehouse.productId, warehouse.quantity,
                        warehouse.totalPrice, warehouse.supplierName, warehouse.userId,
                        warehouse.storeInDate));
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(SellCartGerway.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public void firstTenView(Warehouse warehouse) {
        con = dbCon.geConnection();
        SQL sql = new SQL();
        try {
            pst = con.prepareStatement("select * from " + db + ".StoreIn limit 0,15");
            rs = pst.executeQuery();
            while (rs.next()) {
                warehouse.id = rs.getString(1);
                warehouse.storeInId = rs.getString(2);
                warehouse.productId = rs.getString(3);
                warehouse.quantity = rs.getString(4);
                warehouse.totalPrice = rs.getString(5);
                warehouse.supplierName = sql.getNameSupplier(rs.getString(6),
                        warehouse.supplierName, "Supplyer");
                warehouse.userId = rs.getString(7);
                warehouse.userName = sql.getName(warehouse.userId, warehouse.userName, "User");
                warehouse.storeInDate = rs.getString(8);
                warehouse.listWarehouse.addAll(new ListWarehouse(warehouse.id,
                        warehouse.storeInId, warehouse.productId, warehouse.quantity,
                        warehouse.totalPrice, warehouse.supplierName, warehouse.userId,
                        warehouse.storeInDate));
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(SellCartGerway.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public void searchView(Warehouse warehouse) {
        con = dbCon.geConnection();
        warehouse.listWarehouse.clear();
        SQL sql = new SQL();

        try {
            pst = con.prepareStatement("select * from " + db + ".StoreIn where StoreInId like ?");
            pst.setString(1, "%" + warehouse.storeInId + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                warehouse.id = rs.getString(1);
                warehouse.storeInId = rs.getString(2);
                warehouse.productId = rs.getString(3);
                warehouse.quantity = rs.getString(4);
                warehouse.totalPrice = rs.getString(5);
                warehouse.supplierName = sql.getNameSupplier(rs.getString(6), warehouse.supplierName, "Supplyer");
                warehouse.userId = rs.getString(7);
                warehouse.userName = sql.getName(warehouse.userId, warehouse.userName, "User");
                warehouse.storeInDate = rs.getString(8);
                warehouse.listWarehouse.addAll(new ListWarehouse(warehouse.id,
                        warehouse.storeInId, warehouse.productId, warehouse.quantity,
                        warehouse.totalPrice, warehouse.supplierName, warehouse.userId,
                        warehouse.storeInDate));
            }
            pst.close();
            con.close();
            rs.close();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(SellCartGerway.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);

        }
    }

}
