package BLL;

import DAL.SellCart;
import Getway.SellCartGerway;
import dataBase.DBConnection;
import dataBase.DBProperties;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SellCartBLL {

    SellCartGerway sellCartGerway = new SellCartGerway();

    DBConnection dbCon = new DBConnection();
    Connection con = dbCon.geConnection();
    PreparedStatement pst;
    ResultSet rs;

    DBProperties dBProperties = new DBProperties();
    String db = dBProperties.loadPropertiesFile();

    public void sell(SellCart sellCart) {

        updateCurrentQuentity(sellCart);
        updateTotalBuyCustomer(sellCart);
        sellCartGerway.save(sellCart);

    }

    public void updateTotalBuyCustomer(SellCart sellCart) {

        try {
            double oldTotalBuy = 0.0;
            double newTotalBuy = 0.0;
            System.out.println("Update total buy customer");
            pst = con.prepareStatement("select * from " + db + ".Customer where id = ?");
            pst.setString(1, sellCart.customerID);
            rs = pst.executeQuery();
            while (rs.next()) {
                String oldTotalBuyStr = rs.getString(5)!=null ? rs.getString(5):"0";
                System.out.println(oldTotalBuyStr);
                oldTotalBuy = Double.parseDouble(rs.getString("TotalBuy")!=null ? rs.getString("TotalBuy"):"0");
                newTotalBuy = Double.parseDouble(sellCart.totalPrice) + oldTotalBuy;
            }

            pst = null;
            rs = null;
            pst = con.prepareStatement("update " + db + ".Customer set TotalBuy = ? where id = ?");
            pst.setString(1, String.valueOf(newTotalBuy));
            pst.setString(2, sellCart.customerID);
            pst.executeUpdate();
//            pst.close();
//            rs.close();
//            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(SellCartBLL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updateCurrentQuentity(SellCart sellCart) {
        System.out.println("In Update");
        int oQ = Integer.parseInt(sellCart.oldQuentity);
        int nQ = Integer.parseInt(sellCart.quantity);
        int uQ = (oQ - nQ);
        System.out.println("NOW QUENTITY IS: " + uQ);
        String updatedQuentity = String.valueOf(uQ);
        try {
            System.out.println("In Processing Update");
            pst = con.prepareStatement("update " + db + ".Products set Quantity=? where Id=?");
            pst.setString(1, updatedQuentity);
            pst.setString(2, sellCart.Id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SellCartBLL.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Update Complate");
    }

}
