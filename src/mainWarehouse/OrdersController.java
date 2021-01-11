package mainWarehouse;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

public class OrdersController {
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TabPane TabPane;

    @FXML
    private Tab completeOrdersTab;

    @FXML
    private TableView<Order> completeOrdersPane;

    @FXML
    private TableColumn<Order, Integer> completeIdColumn;

    @FXML
    private TableColumn<Order, String> completeFromColumn;

    @FXML
    private TableColumn<Order, String> completeToColumn;

    @FXML
    private TableColumn<Order, String> completeMessageColumn;

    @FXML
    private Tab pendingOrdersTab;

    @FXML
    private TableView<Order> pendingOrdersTable;

    @FXML
    private TableColumn<Order, Integer> pendingIdColumn;

    @FXML
    private TableColumn<Order, String> pendingFromColumn;

    @FXML
    private TableColumn<Order, String> pendingToColumn;

    @FXML
    private TableColumn<Order, String> pendingMessageColumn;

    @FXML
    private Tab createNewTab;
   
    @FXML
    private TextField newOrderTo;

    @FXML
    private TextField newOrderId;

    @FXML
    private TextField newOrderFrom;
    
    @FXML
    private TextArea newOrderMessage;

    @FXML
    private MenuItem completeButton;
    
    @FXML
    private MenuItem deleteOrderButton;
    
    @FXML
    private Button orderButton;
    
    @FXML
    private Button cancelButton;
    
    @FXML
    private Button okButton;

    /**
     * 
     * @param Marks the selected order as "complete"
     */
    @FXML
    void markAsComplete(ActionEvent event) {
    	model.modifyOrder(pendingOrdersTable.getSelectionModel().getSelectedItem().getId());
    	refreshPendingTable();
    	refreshCompleteTable();
    }
    
    /**
     * 
     * @param Creates a new order and pushes it to the database
     */
    @FXML
    void addNewOrder(ActionEvent event) {
    	try {
    	String to_location = newOrderTo.getText();
    	String from_location = newOrderFrom.getText();
    	String message = newOrderMessage.getText();
    	Boolean complete = false;
    	Order order = new Order();
    	order.settoLocation(to_location);
    	order.setfromLocation(from_location);
    	order.setMessage(message);
    	order.setComplete(complete);
    	model.addOrder(order);
    	refreshPendingTable();
    	refreshCompleteTable();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	}
    
    /**
     * 
     * @param Deletes the selected order
     */
    @FXML
    void deleteOrder(ActionEvent event) {
    	model.deleteOrder(pendingOrdersTable.getSelectionModel().getSelectedItem().getId());
    	refreshPendingTable();
    }
    
    /**
     * 
     * @param Refreshes the "pending" table
     */
    void refreshPendingTable() {
    	pendingIdColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("Id"));
    	pendingFromColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("FromLocation"));
    	pendingToColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("ToLocation"));
    	pendingMessageColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("Message"));
    	pendingOrdersTable.getItems().setAll(model.getOrders(false));
    }
    
    /**
     * 
     * @param Refreshes the "complete" table
     */
    void refreshCompleteTable() {
    	completeIdColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("Id"));
    	completeFromColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("FromLocation"));
    	completeToColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("ToLocation"));
    	completeMessageColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("Message"));
    	completeOrdersPane.getItems().setAll(model.getOrders(true));
    }
    
    @FXML
    void initialize() {
        assert TabPane != null : "fx:id=\"TabPane\" was not injected: check your FXML file 'ordersView.fxml'.";
        assert completeOrdersTab != null : "fx:id=\"completeOrdersTab\" was not injected: check your FXML file 'ordersView.fxml'.";
        assert completeOrdersPane != null : "fx:id=\"completeOrdersPane\" was not injected: check your FXML file 'ordersView.fxml'.";
        assert completeIdColumn != null : "fx:id=\"completeIdColumn\" was not injected: check your FXML file 'ordersView.fxml'.";
        assert completeFromColumn != null : "fx:id=\"completeFromColumn\" was not injected: check your FXML file 'ordersView.fxml'.";
        assert completeToColumn != null : "fx:id=\"completeToColumn\" was not injected: check your FXML file 'ordersView.fxml'.";
        assert completeMessageColumn != null : "fx:id=\"completeDescriptionColumn\" was not injected: check your FXML file 'ordersView.fxml'.";
        assert pendingOrdersTab != null : "fx:id=\"pendingOrdersTab\" was not injected: check your FXML file 'ordersView.fxml'.";
        assert pendingOrdersTable != null : "fx:id=\"pendingOrdersTable\" was not injected: check your FXML file 'ordersView.fxml'.";
        assert pendingIdColumn != null : "fx:id=\"pendingIdColumn\" was not injected: check your FXML file 'ordersView.fxml'.";
        assert pendingFromColumn != null : "fx:id=\"pendingFromColumn\" was not injected: check your FXML file 'ordersView.fxml'.";
        assert pendingToColumn != null : "fx:id=\"pendingToColumn\" was not injected: check your FXML file 'ordersView.fxml'.";
        assert pendingMessageColumn != null : "fx:id=\"pendingMessageColumn\" was not injected: check your FXML file 'ordersView.fxml'.";
        assert createNewTab != null : "fx:id=\"createNewTab\" was not injected: check your FXML file 'ordersView.fxml'.";
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'addItemView.FXML";
        model = new Model();
    	refreshPendingTable();
    	refreshCompleteTable();
    }
    
}
