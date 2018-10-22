/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import List.ListWarehouse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Dian
 */
public class Warehouse {
    public String id;
    public String storeInId;
    public String productId;
    public String quantity;
    public String totalPrice;
    public String supplierName;
    public String userId;
    public String storeInDate;
    public String userName;
    public String supplierId;
   
    
    public ObservableList<ListWarehouse> listWarehouse = FXCollections.observableArrayList();
    
}
