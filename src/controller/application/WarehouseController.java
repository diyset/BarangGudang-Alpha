/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application;

import controller.application.storein.ViewStoreInController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import media.userNameMedia;

/**
 *
 * @author Dian
 */
public class WarehouseController implements Initializable {

    String userId;
    userNameMedia nameMedia;
    @FXML
    private Label lblPathInfo;
    @FXML
    private ToggleButton tbtnWarehouse;
    @FXML
    private ToggleButton tbtnReports;
    @FXML
    private StackPane spMainContent;
    @FXML
    public AnchorPane acMainWarehouses;

    @FXML
    public void tbtnWarehouseOnAction(ActionEvent event) {
        lblPathInfo.setText("Warehouse");
        ViewStoreInController viewStoreInController = new ViewStoreInController();
        userNameMedia media= new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        try {
            fXMLLoader.load(getClass().getResource("/view/application/storein/ViewStoreIn.fxml").openStream());
            media.setId(userId);
            ViewStoreInController controller = fXMLLoader.getController();
            controller.setNameMedia(nameMedia);
            controller.viewDetails();

            spMainContent.getChildren().clear();
            spMainContent.getChildren().add(fXMLLoader.getRoot());
        } catch (IOException ex) {
            Logger.getLogger(WarehouseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setNameMedia(userNameMedia nameMedia) {
        userId = nameMedia.getId();
        this.nameMedia = nameMedia;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ToggleGroup group = new ToggleGroup();
        tbtnWarehouse.setSelected(true);
        tbtnWarehouse.setToggleGroup(group);
        
    }

}
