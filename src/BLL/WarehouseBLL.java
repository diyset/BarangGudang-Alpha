/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.Warehouse;
import Getway.WarehouseGetway;
import dataBase.DBConnection;
import dataBase.DBProperties;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dian
 */
public class WarehouseBLL {

    WarehouseGetway warehouseGetway = new WarehouseGetway();
    DBConnection dbCon = new DBConnection();
    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;

    DBProperties bProperties = new DBProperties();
    String db = bProperties.loadPropertiesFile();

    public void productInWarehouse(Warehouse warehouse) {
        updateQuantityProduct(warehouse);
        warehouseGetway.save(warehouse);
    }

    private void updateQuantityProduct(Warehouse warehouse) {

        int oldQty = 0;
        int newQty = 0;
        try {
            Logger.getLogger(WarehouseBLL.class.getName()).log(Level.INFO,"UpdateQuantityProduct "+warehouse.productId);
            pst = con.prepareStatement("select * from " + db + ".Product where id = ?");
            pst.setString(1, warehouse.productId);
            rs = pst.executeQuery();
            while(rs.next()){
                oldQty = rs.getString(5)!=null ? Integer.parseInt(rs.getString(5)):0;
                newQty = oldQty + newQty;
            }
            pst = null;
            rs = null;
            pst = con.prepareStatement("update "+db+".Product set Qty = ? where id = ?");
            pst.setInt(1, newQty);
            pst.setString(2, warehouse.productId);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(WarehouseBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

}
