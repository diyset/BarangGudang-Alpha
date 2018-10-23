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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStoreInId() {
        return storeInId;
    }

    public void setStoreInId(String storeInId) {
        this.storeInId = storeInId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStoreInDate() {
        return storeInDate;
    }

    public void setStoreInDate(String storeInDate) {
        this.storeInDate = storeInDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public ObservableList<ListWarehouse> getListWarehouse() {
        return listWarehouse;
    }

    public void setListWarehouse(ObservableList<ListWarehouse> listWarehouse) {
        this.listWarehouse = listWarehouse;
    }

    @Override
    public String toString() {
        return "Warehouse{" + "id=" + id + ", storeInId=" + storeInId + ", productId=" + productId + ", quantity=" + quantity + ", totalPrice=" + totalPrice + ", supplierName=" + supplierName + ", userId=" + userId + ", storeInDate=" + storeInDate + ", userName=" + userName + ", supplierId=" + supplierId + ", listWarehouse=" + listWarehouse + '}';
    }
    
    
    
}
