package mainWarehouse;

import java.io.File;
import javafx.collections.*;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Controller for the newItemView
 * @author maailmanpaskintiimi: Jere Moilanen, Marko Korhonen, Huy Nguyen, Miska Peltoniemi
 *
 */
public class NewItemController {

	/**
	 * Database access class
	 */
	private Model model;

	/**
	 * ResourceBundle that was given to the FXMLLoader
	 */
    @FXML
    private ResourceBundle resources;
    
    /**
     * URL location of the FXML file that was given to the FXMLLoader
     */
    @FXML
    private URL location;
    
    /**
     * Input field where the user specifies the item's name
     */
    @FXML // fx:id="nameInput"
    private TextField nameInput;
    
    /**
     * Input field where the user specifies the item's amount
     */
    @FXML // fx:id="amountInput"
    private TextField amountInput;
    
    /**
     * Input field where the user specifies the item's price
     */
    @FXML // fx:id="priceInput"
    private TextField priceInput;
    
    /**
     * Input field where the user specifies the item's description
     */
    @FXML // fx:id="descriptionInput"
    private TextField descriptionInput;
    
    /**
     * When the user clicks this button, the item is added to the database
     */
    @FXML // fx:id="addButton"
    private Button addButton;
    
    /**
     * User can choose which category item is added to the database
     */
    @FXML // fx:id="categoryInput"
    private TextField categoryInput;
    
    /**
     * The item that closes the newItemView
     */
    @FXML // fx:id="cancelButton"
    private Button cancelButton;
        
    /**
     * Exits the newItemView
     */
    @FXML // fx:id="exitButton"
    private Button exitButton;
    
    @FXML // fx:id="nameText"
    private Text nameText;
    
    @FXML // fx:id="amountText"
    private Text amountText;
    
    @FXML // fx:id="priceText"
    private Text priceText;
    
    @FXML // fx:id="descriptionText"
    private Text descriptionText;
    
    /**
     * Opens a popup window notifying the user that adding the new item was unsuccessful
     */
    @FXML
    void error (ActionEvent event){
    	try {
		URL url = new File("src/mainWarehouse/error.fxml").toURI().toURL();
		Parent error = FXMLLoader.load(url);
		Stage newErrorStage = new Stage();
		Scene newErrorScene = new Scene(error);
		newErrorStage.setTitle("Error Message");
		newErrorStage.setScene(newErrorScene);
		newErrorStage.show();
		   Stage stage = (Stage) exitButton.getScene().getWindow();
    	   stage.close();
       	} catch (Exception e) {
    		e.printStackTrace();
    		System.out.println("Error doesn't load a window");
    	}
    }
    
    /**
     * Gets information about the item to be added from the text fields
     * and adds it to the database
     */
    @FXML
    void addItem(ActionEvent event) {
    	try {
    	String name = nameInput.getText();
    	int amount = Integer.parseInt(amountInput.getText());
    	float price = Float.parseFloat(priceInput.getText());
    	String description = descriptionInput.getText();
    	String category = categoryInput.getText();
    	
    	WarehouseItem item = new WarehouseItem();
    	item.setName(name);
    	item.setAmount(amount);
    	item.setPrice(price);
    	item.setDescription(description);
    	item.setCategory(category);    	
    	item.setLocation(0);
    	
    	model.addItem(item);
    	Stage stage = (Stage) cancelButton.getScene().getWindow();
    	stage.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    		
    		error(event);
    	}
    	}

    /**
     * Closes the newItemView
     */
    @FXML
    void closeStage(ActionEvent event) {
    	   Stage stage = (Stage) cancelButton.getScene().getWindow();
    	   stage.close();
    }   
    
    /**
     * Closes the error
     */    
    @FXML
    void exitStage(ActionEvent event) {
    	   Stage stage = (Stage) exitButton.getScene().getWindow();
    	   stage.close();
    }   
    
	/**
	 * This method is called by the FXMLLoader when initialization is complete
	 */
    @FXML
    void initialize() {
        assert nameInput != null : "fx:id=\"nameInput\" was not injected: check your FXML file 'addItemView.FXML'.";
        assert amountInput != null : "fx:id=\"amountInput\" was not injected: check your FXML file 'addItemView.FXML'.";
        assert priceInput != null : "fx:id=\"priceInput\" was not injected: check your FXML file 'addItemView.FXML";
        assert descriptionInput != null : "fx:id=\"descriptionInput\" was not injected: check your FXML file 'addItemView.FXML";
        assert categoryInput != null : "fx:id=\"categoryInput\" was not injected: check your FXML file 'addItemView.FXML";
        assert addButton != null : "fx:id=\"addButton\" was not injected: check your FXML file 'addItemView.FXML";
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'addItemView.FXML";
    	model = new Model();
    }
    
}
