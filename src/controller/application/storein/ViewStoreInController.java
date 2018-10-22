/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application.storein;

import DAL.Warehouse;
import Getway.WarehouseGetway;
import List.ListWarehouse;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import media.userNameMedia;

/**
 * FXML Controller class
 *
 * @author Dian
 */
public class ViewStoreInController implements Initializable {

    Warehouse warehouse = new Warehouse();

    private static final Long serialVersionUID = 1L;
    userNameMedia nameMedia;

    WarehouseGetway warehouseGetway = new WarehouseGetway();
    
    String userId;
    @FXML
    private TextField tfSearch;
    @FXML
    private Button btnRefresh;
    @FXML
    private Button btnStoreIn;
    @FXML
    private TableColumn<Object, Object> tblClmProductId;
    @FXML
    private TableColumn<Object, Object> tblClmQuantity;
    @FXML
    private TableColumn<Object, Object> tblClmTotalPrice;
    @FXML
    private TableColumn<Object, Object> tblClmSupplierName;
    @FXML
    private TableColumn<Object, Object> tblClmStoreDate;
    @FXML
    private TableView<ListWarehouse> tblWarehouseView;
    @FXML
    private TableColumn<Object, Object> tblClmStoreInBy;
    @FXML
    private TableColumn<Object, Object> tblClmStoreInId;

    public void setNameMedia(userNameMedia nameMedia) {
        userId = nameMedia.getId();
        this.nameMedia = nameMedia;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblWarehouseView.setOnMouseClicked((MouseEvent event) -> {
            System.out.println("Clicked");
        });
    }

    @FXML
    private void btnStoreInOnClick(ActionEvent event) {
        System.out.println(userId);
        AddStoreInController addStore = new AddStoreInController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(getClass().getResource("/view/application/storein/AddStoreIn.fxml"));

        try {
            fXMLLoader.load();
            Parent parent = fXMLLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            AddStoreInController addStoreInController = fXMLLoader.getController();
            media.setId(userId);
            addStoreInController.setNameMedia(nameMedia);
//            addStoreInController.generateStoreInId();
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
        } catch (IOException e) {
            Logger.getLogger(ViewStoreInController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void viewDetails() {
        tblWarehouseView.setItems(warehouse.listWarehouse);
        tblClmStoreInId.setCellValueFactory(new PropertyValueFactory<>("storeInId"));
        tblClmProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        tblClmQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tblClmStoreInBy.setCellValueFactory(new PropertyValueFactory<>("userName"));
        tblClmStoreDate.setCellValueFactory(new PropertyValueFactory<>("storeDate"));
        tblClmTotalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        //WarehouseGetway
        warehouseGetway.firstTenView(warehouse);
    }

    @FXML
    private void btnRefreshOnAction(ActionEvent event) {
        tblWarehouseView.getItems().clear();
        tfSearch.clear();
        tblWarehouseView.setItems(warehouse.listWarehouse);
        tblClmStoreInId.setCellValueFactory(new PropertyValueFactory<>("storeInId"));
        tblClmProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        tblClmQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tblClmStoreInBy.setCellValueFactory(new PropertyValueFactory<>("userId"));
        tblClmStoreDate.setCellValueFactory(new PropertyValueFactory<>("storeDate"));
        tblClmTotalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        
        //warehousegetway
        warehouseGetway.firstTenView(warehouse);
    }

}
