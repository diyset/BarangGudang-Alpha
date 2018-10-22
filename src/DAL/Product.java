/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import List.ListProduct;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Dian
 */
public class Product {
    public String id;
    public String productId;
    public String productName;
    public String quantity;
    public String description;
    public String supplierId;
    public String brandId;
    public String catagoryId;
    public String unitId;
    public String pursesPrice;
    public String sellPrice;
    public String rmaId;
    public String userId;
    public String date;
    
    public ObservableList<ListProduct> productList = FXCollections.observableArrayList();
}
