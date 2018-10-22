/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.application.sell;

import DAL.SellCart;
import Getway.SellCartGerway;
import List.ListSold;
import controller.application.util.Constants;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import media.userNameMedia;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author rifat
 */
public class ViewSellController implements Initializable{
    
    private static final long serialVersionUID = 1L;
    userNameMedia nameMedia;
    // Object Sell Cart
    SellCart sellCart = new SellCart();
    // Gate Way backend SellCart
    SellCartGerway sellCartGerway = new SellCartGerway();
    
    String userId;
    @FXML
    private Button btnSellOrder;
    @FXML
    private TableView<ListSold> tblSellView;
    @FXML
    private TableColumn<Object, Object> tblClmSellId;
    @FXML
    private TableColumn<Object, Object> tblClmProductId;
    @FXML
    private TableColumn<Object, Object> tblClmCustomerName;
    @FXML
    private TableColumn<Object, Object> tblClmSoldDate;
    @FXML
    private TableColumn<Object, Object> tblClmPursrsPrice;
    @FXML
    private TableColumn<Object, Object> tblClmSellPrice;
    @FXML
    private TableColumn<Object, Object> tblClmQuantity;
    @FXML
    private TableColumn<Object, Object> tblClmTotalPrice;
    @FXML
    private TableColumn<Object, Object> tblClmWarrenty;
    @FXML
    private TableColumn<Object, Object> tblClmSoldBy;
    @FXML
    private TextField tfSearch;
    @FXML
    private Button btnRefresh;
    @FXML
    private Button btnShowReport;

    public void setNameMedia(userNameMedia nameMedia) {
        userId = nameMedia.getId();
        this.nameMedia = nameMedia;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblSellView.setOnMouseClicked((MouseEvent event) -> {
            System.out.println("Clicked");
        });
    }

    @FXML
    private void btnSellOrderOnAction(ActionEvent event) {
        System.out.println(userId);
        NewSellController acc = new NewSellController();
        userNameMedia media = new userNameMedia();
        FXMLLoader fXMLLoader = new FXMLLoader();
        fXMLLoader.setLocation(getClass().getResource("/view/application/sell/NewSell.fxml"));
        try {
            fXMLLoader.load();
            Parent parent = fXMLLoader.getRoot();
            Scene scene = new Scene(parent);
            scene.setFill(new Color(0, 0, 0, 0));
            NewSellController newSellController = fXMLLoader.getController();
            media.setId(userId);
            newSellController.setNameMedia(nameMedia);
            newSellController.genarateSellID();
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ViewCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void viewDetails(){
        tblSellView.setItems(sellCart.soldList);
        tblClmCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tblClmSellId.setCellValueFactory(new PropertyValueFactory<>("sellId"));
        tblClmProductId.setCellValueFactory(new PropertyValueFactory<>("productGId"));
        tblClmSoldDate.setCellValueFactory(new PropertyValueFactory<>("sellDate"));
        tblClmPursrsPrice.setCellValueFactory(new PropertyValueFactory<>("pursesPrice"));
        tblClmSellPrice.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));
        tblClmQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tblClmTotalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        tblClmWarrenty.setCellValueFactory(new PropertyValueFactory<>("warrentyVoidDate"));
        tblClmSoldBy.setCellValueFactory(new PropertyValueFactory<>("sellerName"));
        sellCartGerway.firstTenView(sellCart);
    }

    @FXML
    private void tfSearchOnKeyReleased(KeyEvent event) {
        tblSellView.getItems().clear();
        sellCart.sellID = tfSearch.getText();
        sellCartGerway.searchView(sellCart);
    }

    @FXML
    private void btnRefreshOnAction(ActionEvent event) {
        tblSellView.getItems().clear();
        tfSearch.clear();
        sellCart.carts.clear();
        tblSellView.setItems(sellCart.soldList);
        tblClmCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tblClmSellId.setCellValueFactory(new PropertyValueFactory<>("sellId"));
        tblClmProductId.setCellValueFactory(new PropertyValueFactory<>("productGId"));
        tblClmSoldDate.setCellValueFactory(new PropertyValueFactory<>("sellDate"));
        tblClmPursrsPrice.setCellValueFactory(new PropertyValueFactory<>("pursesPrice"));
        tblClmSellPrice.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));
        tblClmQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tblClmTotalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        tblClmWarrenty.setCellValueFactory(new PropertyValueFactory<>("warrentyVoidDate"));
        tblClmSoldBy.setCellValueFactory(new PropertyValueFactory<>("sellerName"));
        sellCartGerway.view(sellCart);
    }
    
    private void showReportOfWeek() {
        String reportFile = "G:/TestJaspert/test.jrxml";
        String outputFilePDF = "G:/TestJaspert/test1.pdf";
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(reportFile);
            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outputFilePDF);
            
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("company","EXAMPLE");
        
            ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
            list.add(param);
            
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(list);
            JasperPrint print = JasperFillManager.fillReport(jasperReport, param,beanCollectionDataSource);
            JRViewer viewer = new JRViewer(print);
            exporter.setParameter(JRExporterParameter.JASPER_PRINT,print);
            exporter.exportReport();
            
            viewer.setOpaque(true);
            viewer.setVisible(true);
            Stage stage = new Stage();
            System.out.println("done");
        } catch (JRException ex) {
            Logger.getLogger(ViewSellController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnShowReportAction(ActionEvent event) {
        try{
            showReportOfWeek();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    
}
