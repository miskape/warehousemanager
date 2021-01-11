package mainWarehouse;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * The controller part of the main view
 * @author maailmanpaskintiimi: Jere Moilanen, Marko Korhonen, Huy Nguyen, Miska Peltoniemi
 */
public class Controller {
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
    
    @FXML // fx:id="fileMenu"
    private Menu fileMenu;

    /**
     * Button in the dropdown menu that launches newItemView
     */
    @FXML // fx:id="addNewItem"
    private MenuItem addNewItem;
    
    /**
     * Button in the dropdown menu that refreshes the table of items
     */
    @FXML // fx:id="refreshItems"
    private MenuItem refreshItems;

    /**
     * Button in the dropdown menu that closes the program
     */
    @FXML // fx:id="exit"
    private MenuItem exit;

    /**
     * Searches the database for the string that the user inputs and refreshes the table with the wanted items
     */
    @FXML // fx:id="searchbar"

    private TextField searchbar;

    /**
     * Sets the search criteria to the item ID
     */

    @FXML
    private RadioButton idRadioButton;
    /**
     * Sets the search criteria to the item category
     */

    @FXML
    private RadioButton categoryRadiooButton;
    /**
     * Group of the radio buttons that set the search criteria
     */
    @FXML
    private ToggleGroup searchButtons;

    /**
     * Sets the search criteria to the item's name
     */
    @FXML
    private RadioButton nameRadioButton;

    /**
     * Sets the search criteria to the item's description
     */
    @FXML
    private MenuItem ordersButton;

    @FXML
    private RadioButton descriptionRadioButton;
    
    /**
     * The table that displays the items
     */
    @FXML // fx:id="main"
    private TableView<WarehouseItem> table;
    
    /**
     * The column that displays the item's id in the table
     */
    @FXML // fx:id="id"
    private TableColumn<WarehouseItem, Integer> id;  

    /**
     * The column that displays the item's name in the table
     */
    @FXML // fx:id="name"
    private TableColumn<WarehouseItem, String> name;  

    /**
     * The column that displays the item's amount in the table
     */
    @FXML // fx:id="amount"
    private TableColumn<WarehouseItem, Integer> amount;  

    /**
     * The column that displays the item's price in the table
     */
    @FXML // fx:id="price"
    private TableColumn<WarehouseItem, Float> price;  
    
    /**
     * The column that displays the item's description in the table
     */
    @FXML // fx:id="description"
    private TableColumn<WarehouseItem, String> description;  
    
    /**
     * The column that displays the item's category in the table
     */
    @FXML // fx:id="category"
    private TableColumn<WarehouseItem, String> category;  
    
    /**
     * Right click menu
     */
    @FXML // fx:id="rightclick"
    private MenuItem rightclick; // Value injected by FXMLLoader
    
    @FXML // fx:id="settings"
    private MenuItem settings;
    
    @FXML // fx:id="editMenu"
    private Menu editMenu;
    
    @FXML // fx:id="deleteButton"
    private MenuItem deleteButton;
    
    @FXML // fx:id="sendItemButton"
    private MenuItem sendItemButton;
    
    @FXML // fx:id="helpMenu"
    private Menu helpMenu;
    
    @FXML // fx:id="aboutButton"
    private MenuItem aboutButton;
    
    @FXML // fx:id="searchLabel"
    private Label searchLabel;
    
    @FXML // fx:id="additionalInfoLabel"
    private Label additionalInfoLabel;
    
    @FXML // fx:id="searchByLabel"
    private Label searchByLabel;
    
    @FXML // fx:id="language"
    private Menu language;
    
    @FXML // fx:id="deleteItem"
    private MenuItem deleteItem;
    
    @FXML // fx:id="sendItem"
    private MenuItem sendItem;
    
    @FXML
    void ordersView(ActionEvent event) {
    	try {
    		URL url = new File("src/mainWarehouse/ordersView.fxml").toURI().toURL();
    		Parent addNewOrder = FXMLLoader.load(url);
    		Stage ordersStage = new Stage();
    		Scene ordersScene = new Scene(addNewOrder);
    		ordersStage.setTitle("Orders");
    		ordersStage.setScene(ordersScene);
    		ordersStage.show();

        	
    	} catch (Exception e) {
    		e.printStackTrace();
    		System.out.println("Can't load new window");
    	}
    }

	/**
	 * Search function of the items in the warehouse
	 * Not implemented yet
	 */
    @FXML
    void searchItem() {
    	RadioButton chk = (RadioButton)searchButtons.getSelectedToggle();
    	String criteria = chk.getText();
    	String query = searchbar.getText();
    	LevenshteinDistance levenshteinDistance = new LevenshteinDistance();
    	List<WarehouseItem> allItems = model.getItems();
    	ArrayList<WarehouseItem> searchedItems = new ArrayList<WarehouseItem>();
    	
    	switch(criteria) {
    	case "Name": for(int i = 0; i < allItems.size(); i++) {
    					if(levenshteinDistance.apply(query, allItems.get(i).getName()) < 8) {
    						searchedItems.add(allItems.get(i));
    					}
    				 }
    			  	 break;
    	case "ID":
    		for(int i = 0; i < allItems.size(); i++) {
    			if(levenshteinDistance.apply(query, String.valueOf(allItems.get(i).getId())) == 0) {
    				searchedItems.add(allItems.get(i));
				}
    		}
    		break;
    	case "Description":
    		for(int i = 0; i < allItems.size(); i++) {
    			if(levenshteinDistance.apply(query, allItems.get(i).getDescription()) < 9) {
    				searchedItems.add(allItems.get(i));
				}
    		}
    		break;
    	}

        id.setCellValueFactory(new PropertyValueFactory<WarehouseItem, Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<WarehouseItem, String>("name"));
        amount.setCellValueFactory(new PropertyValueFactory<WarehouseItem, Integer>("amount"));
        price.setCellValueFactory(new PropertyValueFactory<WarehouseItem, Float>("price"));
        description.setCellValueFactory(new PropertyValueFactory<WarehouseItem, String>("description"));

        category.setCellValueFactory(new PropertyValueFactory<WarehouseItem, String>("category"));
        
        if(query.isEmpty()) {
        	table.getItems().setAll(allItems);
        } else {
            table.getItems().setAll(searchedItems);
        }
        }
    
	/**
	 * Launches the addNewItem view
	 */
    @FXML
    void addNewItemButton(ActionEvent event) {
    	try {
    		//URL url = new File("src/application/addItemView.fxml").toURI().toURL();
    		Parent addNewItem = FXMLLoader.load(getClass().getResource("/mainWarehouse/addItemView.fxml"));
    		Stage newItemStage = new Stage();
    		Scene newItemScene = new Scene(addNewItem);
    		newItemStage.setTitle("Add new item");
    		newItemStage.setScene(newItemScene);
    		newItemStage.show();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }


    
	/**
	 * Deletes a selected item from the tableView
	 */
    @FXML
    void deleteItem(ActionEvent event) {
    	model.deleteItem(table.getSelectionModel().getSelectedItem().getId());
    	refreshTable();
    }
    
	/**
	 * Launches the sendItemToShop view
	 */
    @FXML
    void sendItemToShop(ActionEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/mainWarehouse/sendItemToShop.fxml"));
    		Parent sendItemToShop = (Parent) loader.load();
    		SendItemToShopController controller = loader.getController();
    		controller.setItemId(table.getSelectionModel().getSelectedItem().getId());
    		Stage newItemStage = new Stage();
    		Scene newItemScene = new Scene(sendItemToShop);
    		newItemStage.setTitle("Send item to shop");
    		newItemStage.setScene(newItemScene);
    		newItemStage.show();
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

	/**
	 * Closes the main view
	 */
    @FXML
    void closeStage(ActionEvent event) {

    }
    
	/**
	 * Refreshes the tableView in the main view
	 */
    @FXML
    void refreshTable() {
        id.setCellValueFactory(new PropertyValueFactory<WarehouseItem, Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<WarehouseItem, String>("name"));
        amount.setCellValueFactory(new PropertyValueFactory<WarehouseItem, Integer>("amount"));
        price.setCellValueFactory(new PropertyValueFactory<WarehouseItem, Float>("price"));
        description.setCellValueFactory(new PropertyValueFactory<WarehouseItem, String>("description"));

        category.setCellValueFactory(new PropertyValueFactory<WarehouseItem, String>("category"));
        table.getItems().setAll(model.getItems());
    }
    
    
    
	/**
	 * This method is called by the FXMLLoader when initialization is complete
	 */
    @FXML
    void initialize() {
        assert addNewItem != null : "fx:id=\"addNewItem\" was not injected: check your FXML file 'main.fxml'.";
        assert refreshItems != null : "fx:id=\"refreshItems\" was not injected: check your FXML file 'main.fxml'.";
        assert exit != null : "fx:id=\"exit\" was not injected: check your FXML file 'main.fxml'.";
        assert searchbar != null : "fx:id=\"searchbar\" was not injected: check your FXML file 'main.fxml'.";
        assert idRadioButton != null : "fx:id=\"idRadioButton\" was not injected: check your FXML file 'main.fxml'.";
        assert searchButtons != null : "fx:id=\"searchButtons\" was not injected: check your FXML file 'main.fxml'.";
        assert nameRadioButton != null : "fx:id=\"nameRadioButton\" was not injected: check your FXML file 'main.fxml'.";
        assert descriptionRadioButton != null : "fx:id=\"descriptionRadioButton\" was not injected: check your FXML file 'main.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'main.fxml'.";
        assert id != null : "fx:id=\"id\" was not injected: check your FXML file 'main.fxml'.";
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'main.fxml'.";
        assert amount != null : "fx:id=\"amount\" was not injected: check your FXML file 'main.fxml'.";
        assert price != null : "fx:id=\"price\" was not injected: check your FXML file 'main.fxml'.";
        assert description != null : "fx:id=\"description\" was not injected: check your FXML file 'main.fxml'.";
        assert category != null : "fx:id=\"category\" was not injected: check your FXML file 'main.fxml'.";
        assert rightclick != null : "fx:id=\"rightclick\" was not injected: check your FXML file 'main.fxml'.";
    	model = new Model();
    	refreshTable();
    	
    	
    	 
    }
    /**
	 * Localization, switches language
	 */
    
    // Loads EN
    @FXML
    private void btnEN(ActionEvent event) {
    	loadLang("en");
    }
    
    // Loads FI
    @FXML
    private void btnFI(ActionEvent event) {
    	loadLang("fi");
    }
    
    private void loadLang(String lang) {
    	Locale locale = new Locale(lang);
    	resources = ResourceBundle.getBundle("mainWarehouse.lang", locale);
    	name.setText(resources.getString("name"));
    	amount.setText(resources.getString("amount"));
    	price.setText(resources.getString("price"));
    	description.setText(resources.getString("description"));
    	category.setText(resources.getString("category"));
    	nameRadioButton.setText(resources.getString("nameRadioButton"));
    	descriptionRadioButton.setText(resources.getString("descriptionRadioButton"));
    	fileMenu.setText(resources.getString("fileMenu"));
    	ordersButton.setText(resources.getString("ordersButton"));
    	addNewItem.setText(resources.getString("addNewItem"));
    	refreshItems.setText(resources.getString("refreshItems"));
    	exit.setText(resources.getString("exit"));
    	settings.setText(resources.getString("settings"));
    	editMenu.setText(resources.getString("editMenu"));
    	deleteButton.setText(resources.getString("deleteButton"));
    	helpMenu.setText(resources.getString("helpMenu"));
    	aboutButton.setText(resources.getString("aboutButton"));
    	searchLabel.setText(resources.getString("searchLabel"));
    	additionalInfoLabel.setText(resources.getString("additionalInfoLabel"));
    	searchByLabel.setText(resources.getString("searchByLabel"));
    	language.setText(resources.getString("language"));
    	deleteItem.setText(resources.getString("deleteItem"));
    	sendItem.setText(resources.getString("sendItem"));
    	
    }
}