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
import custom.RandomIdGenarator;
import dataBase.DBConnection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import media.userNameMedia;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRResultSetDataSource;
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
public class ViewSellController implements Initializable {

    private static final long serialVersionUID = 1L;
    userNameMedia nameMedia;
    // Object Sell Cart
    SellCart sellCart = new SellCart();
    // Gate Way backend SellCart
    SellCartGerway sellCartGerway = new SellCartGerway();
    private File fileReport;
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

    public void viewDetails() {
        tblSellView.setItems(sellCart.soldList);
        tblClmCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tblClmSellId.setCellValueFactory(new PropertyValueFactory<>("sellId"));
        tblClmProductId.setCellValueFactory(new PropertyValueFactory<>("productGId"));
        tblClmSoldDate.setCellValueFactory(new PropertyValueFactory<>("sellDate"));
        tblClmPursrsPrice.setCellValueFactory(new PropertyValueFactory<>("pursesPrice"));
        tblClmSellPrice.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));
        tblClmQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tblClmTotalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
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
        tblClmSoldBy.setCellValueFactory(new PropertyValueFactory<>("sellerName"));
        sellCartGerway.view(sellCart);
    }

    private void showReportOfWeek(String locationDir) {
        String reportFile = "G:/TestJaspert/Invoice.jrxml";
        System.out.println();
        String tempInvoiceId = generateInvoiceReport();
        String outputTemp = locationDir.replaceAll("\\\\", "/");
        String outputFilePDF = "G:/TestJaspert/" + tempInvoiceId + ".pdf";
        System.out.println(outputTemp);
        System.out.println(File.separator);
        try {

            DBConnection dbCon = new DBConnection();
            Connection con = dbCon.geConnection();
            Statement stm = con.createStatement();
            String query = "select S.Id as Id, S.SellId as `SellId`,C.CustomerName as `CustomerId`, P.ProductName as `ProductId`, S.PursesPrice as `PursesPrice`,S.SellPrice\n"
                    + "  as `SellPrice`,S.Quantity as `Quantity`, S.TotalPrice as `TotalPrice`, U.UsrName as `SellerId`, S.SellDate as `SellDate` , S.WarrentyVoidDate as `WarrentyVoidDate`\n"
                    + "from pembukuan.sell as S\n"
                    + "  join pembukuan.customer C on S.CustomerId = C.Id\n"
                    + "  join pembukuan.user U on S.SellerId = U.Id\n"
                    + "  join pembukuan.products P on S.ProductId = P.id\n"
                    + "WHERE S.SellDate >= curdate() - INTERVAL DAYOFWEEK(curdate())+6 DAY\n"
                    + "AND S.SellDate < curdate() - INTERVAL DAYOFWEEK(curdate())-1 DAY";
            ResultSet rs = stm.executeQuery(query);
            JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);
            JasperReport jasperReport = JasperCompileManager.compileReport(reportFile);
            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outputTemp);

            HashMap<String, Object> param = new HashMap<String, Object>();
//            param.put("company", "EXAMPLE");
            param.put("imagedir", "G:/TestJaspert/");
            param.put("invoice", tempInvoiceId);
            param.put("datenow", Constants.sdf.format(new Date()));
            ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
            list.add(param);

            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(list);
            JasperPrint print = JasperFillManager.fillReport(jasperReport, param, jrRS);
            JRViewer viewer = new JRViewer(print);
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
            exporter.exportReport();

            viewer.setOpaque(true);
            viewer.setVisible(true);
//            Stage stage = new Stage();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Print");
            alert.setContentText("Created File Success : " + outputTemp);
            alert.initStyle(StageStyle.UNDECORATED);
            alert.showAndWait();
            System.out.println("done");
            con.close();
            stm.close();
            rs.close();
        } catch (JRException |SQLException ex) {
            Logger.getLogger(ViewSellController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String generateInvoiceReport() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int nowMonth = calendar.get(Calendar.MONTH);
        int nowDayInMonth = calendar.get(Calendar.DAY_OF_MONTH);
        String id = String.valueOf(nowMonth) + String.valueOf(nowDayInMonth) + RandomIdGenarator.randomstring();
        return id;

    }

    @FXML
    private void btnShowReportAction(ActionEvent event) {
        try {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("pdf files (*.pdf)", "*.pdf");
            fileChooser.getExtensionFilters().add(extensionFilter);
            fileChooser.setInitialFileName(generateInvoiceReport());
            fileReport = fileChooser.showSaveDialog(null);
            System.out.println(fileReport.getAbsoluteFile()); 
            System.out.println(fileReport.getAbsolutePath());
            showReportOfWeek(fileReport.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
            
        }
    }

}
