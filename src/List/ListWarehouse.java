/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package List;

/**
 *
 * @author Dian
 */
public class ListWarehouse {
    private String Id;
    private String storeInId;
    private String productId;
    private String quantity;
    private String totalPrice;
    private String supplierName;
    private String userId;
    private String storeDate;

    public ListWarehouse(String Id, String storeInId, String productId, String quantity, String totalPrice, String supplierName, String userId, String storeDate) {
        this.Id = Id;
        this.storeInId = storeInId;
        this.productId = productId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.supplierName = supplierName;
        this.userId = userId;
        this.storeDate = storeDate;
    }

    public String getSoteInId() {
        return storeInId;
    }

    public void setSoteInId(String soteInId) {
        this.storeInId = soteInId;
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

    public String getStoreDate() {
        return storeDate;
    }

    public void setStoreDate(String storeDate) {
        this.storeDate = storeDate;
    }
    
    
    
    
    
}
