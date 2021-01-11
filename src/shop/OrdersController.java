package shop;


import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


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
    private TableView<?> completeOrdersPane;

    @FXML
    private TableColumn<?, ?> completeIdColumn;

    @FXML
    private TableColumn<?, ?> completeFromColumn;

    @FXML
    private TableColumn<?, ?> completeToColumn;

    @FXML
    private TableColumn<?, ?> completeDescriptionColumn;

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
    void addNewOrder(ActionEvent event) {
    	try {
    	String to_location = newOrderTo.getText();
    	String from_location = newOrderFrom.getText();
    	String message = newOrderMessage.getText();
    	
    	Order order = new Order();
    	order.settoLocation(to_location);
    	order.setfromLocation(from_location);
    	order.setMessage(message);
    	
    	model.addOrder(order);

    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	}
    
    
    /**
     * 
     */
    void refreshPendingTable() {
    	pendingIdColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("Id"));
    	pendingFromColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("FromLocation"));
    	pendingToColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("ToLocation"));
    	pendingMessageColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("Message"));
    	pendingOrdersTable.getItems().setAll(model.getOrders());
    }

    @FXML
    void initialize() {
        assert TabPane != null : "fx:id=\"TabPane\" was not injected: check your FXML file 'ordersView.fxml'.";
        assert completeOrdersTab != null : "fx:id=\"completeOrdersTab\" was not injected: check your FXML file 'ordersView.fxml'.";
        assert completeOrdersPane != null : "fx:id=\"completeOrdersPane\" was not injected: check your FXML file 'ordersView.fxml'.";
        assert completeIdColumn != null : "fx:id=\"completeIdColumn\" was not injected: check your FXML file 'ordersView.fxml'.";
        assert completeFromColumn != null : "fx:id=\"completeFromColumn\" was not injected: check your FXML file 'ordersView.fxml'.";
        assert completeToColumn != null : "fx:id=\"completeToColumn\" was not injected: check your FXML file 'ordersView.fxml'.";
        assert completeDescriptionColumn != null : "fx:id=\"completeDescriptionColumn\" was not injected: check your FXML file 'ordersView.fxml'.";
        assert pendingOrdersTab != null : "fx:id=\"pendingOrdersTab\" was not injected: check your FXML file 'ordersView.fxml'.";
        assert pendingOrdersTable != null : "fx:id=\"pendingOrdersTable\" was not injected: check your FXML file 'ordersView.fxml'.";
        assert pendingIdColumn != null : "fx:id=\"pendingIdColumn\" was not injected: check your FXML file 'ordersView.fxml'.";
        assert pendingFromColumn != null : "fx:id=\"pendingFromColumn\" was not injected: check your FXML file 'ordersView.fxml'.";
        assert pendingToColumn != null : "fx:id=\"pendingToColumn\" was not injected: check your FXML file 'ordersView.fxml'.";
        assert pendingMessageColumn != null : "fx:id=\"pendingMessageColumn\" was not injected: check your FXML file 'ordersView.fxml'.";
        assert createNewTab != null : "fx:id=\"createNewTab\" was not injected: check your FXML file 'ordersView.fxml'.";
    	model = new Model();
    	refreshPendingTable();
    }
}
