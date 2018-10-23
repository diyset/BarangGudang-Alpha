package DAL;

import List.ListSupplyer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by rifat on 8/1/15.
 */
public class Supplyer {

    public String id;
    public String supplyerName;
    public String supplyerContactNumber;
    public String supplyerAddress;
    public String supplyerDescription;
    public String creatorId;
    public String date;

//    public List<ListSupplyer> rowSupplyer;
    public ObservableList<ListSupplyer> supplyerDetails = FXCollections.observableArrayList();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSupplyerName() {
        return supplyerName;
    }

    public void setSupplyerName(String supplyerName) {
        this.supplyerName = supplyerName;
    }

    public String getSupplyerContactNumber() {
        return supplyerContactNumber;
    }

    public void setSupplyerContactNumber(String supplyerContactNumber) {
        this.supplyerContactNumber = supplyerContactNumber;
    }

    public String getSupplyerAddress() {
        return supplyerAddress;
    }

    public void setSupplyerAddress(String supplyerAddress) {
        this.supplyerAddress = supplyerAddress;
    }

    public String getSupplyerDescription() {
        return supplyerDescription;
    }

    public void setSupplyerDescription(String supplyerDescription) {
        this.supplyerDescription = supplyerDescription;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ObservableList<ListSupplyer> getSupplyerDetails() {
        return supplyerDetails;
    }

    public void setSupplyerDetails(ObservableList<ListSupplyer> supplyerDetails) {
        this.supplyerDetails = supplyerDetails;
    }

    @Override
    public String toString() {
        return "Supplyer{" + "id=" + id + ", supplyerName=" + supplyerName + ", supplyerContactNumber=" + supplyerContactNumber + ", supplyerAddress=" + supplyerAddress + ", supplyerDescription=" + supplyerDescription + ", creatorId=" + creatorId + ", date=" + date + ", supplyerDetails=" + supplyerDetails + '}';
    }

    

    
}
