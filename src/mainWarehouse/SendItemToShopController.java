package mainWarehouse;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for the newItemView
 * @author maailmanpaskintiimi: Jere Moilanen, Marko Korhonen, Huy Nguyen, Miska Peltoniemi
 *
 */
public class SendItemToShopController {
	
	private int itemID;
	
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
     * Input field where the user specifies the amount of items to be sent to shop
     */
    @FXML // fx:id="amountInput"
    private TextField amountInput;
    
    @FXML // fx:id="cancelButton"
    private Button cancelButton;
    
    /**
     * Exits the sendItemToShopView
     */
    @FXML // fx:id="exitButton"
    private Button exitButton;
    
    @FXML
    private Button sendButton;

    @FXML // fx:id="error"
    private TextArea error;
    
    @FXML
    void sendItem(ActionEvent event) {
    	model.sendItemToShop(itemID, Integer.parseInt(amountInput.getText()));
    }
    
    /**
     * Opens a popup window notifying the user that the operation was unsuccessful
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
     * Closes the newItemView
     */
    @FXML
    void closeStage(ActionEvent event) {
    	   Stage stage = (Stage) cancelButton.getScene().getWindow();
    	   stage.close();
    }
    
    public void setItemId(int itemID) {
    	this.itemID = itemID;
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
        assert amountInput != null : "fx:id=\"amountInput\" was not injected: check your FXML file 'sendItemToShop.FXML'.";
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'sendItemToShop.FXML";
        assert sendButton != null : "fx:id=\"sendButton\" was not injected: check your FXML file 'sendItemToShop.FXML";
    	model = new Model();
    }
}
