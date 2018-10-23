/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 *
 * @author Dian
 */
public class PopupAlert {

    public static void AlertError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Wwarning!");
        alert.setContentText(message);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.showAndWait();
    }

    public static void AlertInformation(String messageContent, String headerText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(headerText);
        alert.setContentText(messageContent);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.showAndWait();
    }

    public static void AlertInformationUpdate() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Warning!");
        alert.setContentText("Pilih Item Terlebih Dahulu!");
        alert.initStyle(StageStyle.UNDECORATED);
        alert.showAndWait();
    }

}
