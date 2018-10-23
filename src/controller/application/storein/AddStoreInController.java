/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application.storein;

import BLL.WarehouseBLL;
import DAL.CurrentProduct;
import DAL.Product;
import DAL.Supplyer;
import DAL.Warehouse;
import Getway.CurrentProductGetway;
import Getway.ProductGetway;
import Getway.SupplyerGetway;
import List.ListProduct;
import List.ListSupplyer;
import custom.RandomIdGenarator;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import media.userNameMedia;

/**
 * FXML Controller class
 *
 * @author Dian
 */
public class AddStoreInController implements Initializable {

    userNameMedia nameMedia;
    String userId;
    String productId;
    String supplierId;
    int quantity;
    @FXML
    private MenuButton mbtnProduct;
    @FXML
    private TextField tfProductId;
    @FXML
    private TextField tfQuantity;
    @FXML
    private Label lblCurrentQuantity;
    @FXML
    private TextField tfSellPrice;
    @FXML
    private Label lblPursesPrice;
    @FXML
    private Label lblTotal;
    @FXML
    private TextField tfSupplierName;
    @FXML
    private TextField tfSupplierPhone;
    @FXML
    private Button btnAddToStore;
    @FXML
    private Button btnAddProduct;
    @FXML
    private TextField tfProductName;
    @FXML
    private MenuButton mbtnSupplier;
    @FXML
    private TableView<ListProduct> tblProductSortView;
    @FXML
    private Button btnAddSupplier;
    @FXML
    private Label lblSellId;
    @FXML
    private Button btnClose;
    @FXML
    private TextField tfProductSearch;
    @FXML
    private TextField tfSupplierSearch;
    @FXML
    private TableView<ListSupplyer> tblSupplierSortView;
    @FXML
    private TableColumn<Object, Object> tblClmProductName;
    @FXML
    private TableColumn<Object, Object> tblClmProductStock;
    @FXML
    private TableColumn<Object, Object> tblClmSupplierName;
    @FXML
    private TableColumn<Object, Object> tblClmSupplierPhoneNo;
    @FXML
    private Button btnResetStoreIn;

    public void setNameMedia(userNameMedia nameMedia) {
        userId = nameMedia.getId();
        this.nameMedia = nameMedia;
    }

    CurrentProduct currentProduct = new CurrentProduct();
    CurrentProductGetway currentProductGetway = new CurrentProductGetway();
    Product product = new Product();
    ProductGetway productGetway = new ProductGetway();
    Supplyer supplyer = new Supplyer();
    SupplyerGetway supplyerGetway = new SupplyerGetway();
    WarehouseBLL warehouseBLL = new WarehouseBLL();
    Warehouse warehouse = new Warehouse();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        clearAll();
    }

    @FXML
    private void mbtnProductOnClicked(MouseEvent event) {
        product.productName = tfProductSearch.getText().trim();
        tblProductSortView.setItems(product.productList);
        System.out.println(product.productList);
        tblClmProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        tblClmProductStock.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        productGetway.searchView(product);
    }

    @FXML
    private void tfProductIdOnAction(ActionEvent event) {
        if (tfProductId.getText().isEmpty()) {
            clearAll();
        } else {
            currentProduct.productId = tfProductId.getText().trim();
            currentProductGetway.sView(currentProduct);
            lblCurrentQuantity.setText(currentProduct.quantity);
            tfProductName.setText(currentProduct.productName);
            tfSellPrice.setText(currentProduct.sellPrice);
            generateStoreInId(currentProduct.productId.toUpperCase());
        }
    }

    @FXML
    private void tfQuantityOnAction(KeyEvent event) {
    }

    @FXML
    private void tfSellPriceOnAction(KeyEvent event) {
    }

    @FXML
    private void btnAddToStoreAction(ActionEvent event) {
        //NEXT FOR BUILD
        // UPDATE STOCK ON THE TBL_PRODUCT
        // MENYIMMPAN HISTORY BARANG MASUK KE TBL_STOREIN
        Warehouse warehouseAdd = new Warehouse();
        warehouseAdd.setId(null);
        warehouseAdd.setProductId(product.getProductId());
        warehouseAdd.setQuantity(tfQuantity.getText());
        warehouseAdd.setSupplierId(supplierId);
        warehouseAdd.setStoreInId(lblSellId.getText());
        warehouseAdd.setUserId(userId);
        System.out.println(product.toString());
        System.out.println(supplyer.toString());
        System.out.println(warehouseAdd.toString());
        System.out.println(System.getProperty("user.dir"));
//        warehouseBLL.productInWarehouse(warehouseAdd);
    }

    @FXML
    private void btnAddProductAction(ActionEvent event) {
    }

    @FXML
    private void tblProductOnClick(MouseEvent event) {
        try {
            mbtnProduct.setText(tblProductSortView.getSelectionModel().getSelectedItem().getProductName());
            productId = tblProductSortView.getSelectionModel().getSelectedItem().getProductId();
            System.out.println(productId);
            if (productId == null) {
                clearAll();
            } else {
                currentProduct.productId = productId;
                currentProductGetway.sView(currentProduct);
                /*
                   SET TO COMPONENT
                 */
                lblCurrentQuantity.setText(currentProduct.quantity);
                tfProductName.setText(currentProduct.productName);
                tfSellPrice.setText(currentProduct.sellPrice);
                tfProductId.setEditable(false);
                lblPursesPrice.setText(currentProduct.pursesPrice);
                tfProductId.setText(currentProduct.productId);
                generateStoreInId(productId);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        }
    }

    @FXML
    private void mbtnSupplierOnClicked(MouseEvent event) {
        supplyer.supplyerName = tfSupplierSearch.getText().trim();
        tblSupplierSortView.setItems(supplyer.supplyerDetails);
        System.out.println(supplyer.supplyerDetails);
        tblClmSupplierName.setCellValueFactory(new PropertyValueFactory<>("supplyerName"));
        tblClmSupplierPhoneNo.setCellValueFactory(new PropertyValueFactory<>("supplyerPhoneNumber"));
        supplyerGetway.searchView(supplyer);
    }

    @FXML
    private void btnAddSupplierAction(ActionEvent event) {
    }

    @FXML
    private void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    private void generateStoreInId(String productId) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int nowYear = calendar.get(Calendar.YEAR);
        int nowMonth = calendar.get(Calendar.MONTH);
        int nowDayInMonth = calendar.get(Calendar.DAY_OF_MONTH);
        String id = String.valueOf(nowMonth) + String.valueOf(nowDayInMonth) + productId + RandomIdGenarator.randomstring();
        if (id.matches("001215")) {
            String nId = nowYear + tfProductId.getText() + RandomIdGenarator.randomstring();
            lblSellId.setText(nId);
        } else {
            lblSellId.setText(id);
        }

    }

    public boolean inNotNull() {
        boolean isNotNull = false;

        if (mbtnProduct.getText().matches("Select Product") || tfSellPrice.getText() == null || tfQuantity.getText().trim().matches("")) {
//            Dialogs.create().title("").masthead("ERROR").message("Please fill requere field").styleClass(org.controlsfx.dialog.Dialog.STYLE_CLASS_UNDECORATED).showError();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("ERROR");
            alert.setContentText("Please fill all require field");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
            return isNotNull;
        } else {
            isNotNull = true;
        }
        return isNotNull;
    }

    public void clearAll() {
        tfProductName.clear();
        tfProductId.clear();
        tfQuantity.clear();
        tfProductSearch.clear();
        lblPursesPrice.setText(null);
        tfSellPrice.clear();
        tfSupplierName.clear();
        tfSupplierPhone.clear();
        tfSupplierSearch.clear();
        tfProductId.setEditable(true);
        tfProductId.clear();
        lblCurrentQuantity.setText("");
        mbtnProduct.setText("Select Product");
        mbtnSupplier.setText("Select Vendor");
        lblSellId.setText("");
    }

    @FXML
    private void tfProductSearchOnKeyReleased(KeyEvent event) {
        product.productName = tfProductSearch.getText().trim();
        tblProductSortView.setItems(product.productList);
        System.out.println(product.productList);
        tblClmProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        tblClmProductStock.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        productGetway.searchView(product);
    }

    @FXML
    private void tfSupplierSearchOnKeyReleased(KeyEvent event) {
        supplyer.supplyerName = tfSupplierName.getText().trim();
        tblSupplierSortView.setItems(supplyer.supplyerDetails);
        tblClmSupplierName.setCellValueFactory(new PropertyValueFactory<>("supplyerName"));
        tblClmSupplierPhoneNo.setCellValueFactory(new PropertyValueFactory<>("supplyerPhoneNumber"));
    }

    @FXML
    private void tblSupplierOnClick(MouseEvent event) {
        try {
            mbtnSupplier.setText(tblSupplierSortView.getSelectionModel().getSelectedItem().getSupplyerName());
            supplierId = tblSupplierSortView.getSelectionModel().getSelectedItem().getSupplyerId();
            if(supplierId==null && supplierId.equals("")){
                tfSupplierName.setText("");
                tfSupplierSearch.setText("Select Vendor");
                tfSupplierPhone.setText("");
            }
            supplyer.id = supplierId;
            supplyerGetway.searchView(supplyer);
            
            /*
            Set COMPONENT
            */
            tfSupplierSearch.setText(supplyer.supplyerName);
            tfSupplierName.setText(supplyer.supplyerName);
            tfSupplierPhone.setText(supplyer.supplyerContactNumber);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println(e.getMessage());
        }

    }

    @FXML
    private void btnResetStoreInAction(ActionEvent event) {
        clearAll();
    }

}
