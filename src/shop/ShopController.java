package shop;

import java.awt.Label;
import java.io.File;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import com.sun.glass.ui.Menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The controller part of the main view
 * @author maailmanpaskintiimi: Jere Moilanen, Marko Korhonen, Huy Nguyen, Miska Peltoniemi
 */
public class ShopController {
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
    
    @FXML // fx:id="fileMenu"
    private MenuItem fileMenu;
    
    @FXML // fx:id="editMenu"
    private MenuItem editMenu;
    
    @FXML // fx:id="helpMenu"
    private MenuItem helpMenu;
    
    @FXML // fx:id="languageMenu"
    private MenuItem languageMenu;
    
    @FXML // fx:id="undo"
    private MenuItem undo;
    
    @FXML // fx:id="delete"
    private MenuItem delete;
    
    @FXML // fx:id="about"
    private MenuItem about;
    
    @FXML // fx:id="categories"
    private Text categories;
    
    @FXML // fx:id="search"
    private Text search;


    /**
     * Searches the database for the string that the user inputs and refreshes the table with the wanted items
     */
    @FXML // fx:id="searchbar"
    private TextField searchbar;

    @FXML
    private RadioButton idRadioButton;

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
     * Sets the search criteria to the item's name
     */
    @FXML
    private RadioButton categoryRadioButton;
    
    /**
     * Sets the search criteria to the item's description
     */


    @FXML
    private RadioButton descriptionRadioButton;
    
    @FXML
    private MenuItem ordersButton;    

    /**
     * The table that displays the items
     */
    @FXML // fx:id="main"
    private TableView<ShopItem> table;
    
    /**
     * The column that displays the item's id in the table
     */
    @FXML // fx:id="id"
    private TableColumn<ShopItem, Integer> id;  

    /**
     * The column that displays the item's name in the table
     */
    @FXML // fx:id="name"
    private TableColumn<ShopItem, String> name;  

    /**
     * The column that displays the item's amount in the table
     */
    @FXML // fx:id="amount"
    private TableColumn<ShopItem, Integer> amount;  

    /**
     * The column that displays the item's price in the table
     */
    @FXML // fx:id="price"
    private TableColumn<ShopItem, Float> price;  
    
    /**
     * The column that displays the item's description in the table
     */
    @FXML // fx:id="description"
    private TableColumn<ShopItem, String> description; 
    
    /**
     * The column that displays the item's category in the table
     */
    @FXML // fx:id="category"
    private TableColumn<ShopItem, String> category; 
    /**
     * Right click menu
     */
    @FXML // fx:id="rightclick"
    private MenuItem rightclick;  

	/**
	 * Search function of the items in the warehouse
	 * Not implemented yet
	 */
    @FXML
    void searchItem( ) {

    }
    
	/**
	 * Launches the addNewItem view
	 */
    @FXML
    void addNewItemButton(ActionEvent event) {
    	try {
    		URL url = new File("src/shop/addItemView.fxml").toURI().toURL();
    		Parent addNewItem = FXMLLoader.load(url);
    		Stage newItemStage = new Stage();
    		Scene newItemScene = new Scene(addNewItem);
    		newItemStage.setTitle("Add new item");
    		newItemStage.setScene(newItemScene);
    		newItemStage.show();
    			
    	
        	
    	} catch (Exception e) {
    		e.printStackTrace();
    		System.out.println("Can't load new window");
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
        id.setCellValueFactory(new PropertyValueFactory<ShopItem, Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<ShopItem, String>("name"));
        amount.setCellValueFactory(new PropertyValueFactory<ShopItem, Integer>("amount"));
        price.setCellValueFactory(new PropertyValueFactory<ShopItem, Float>("price"));
        description.setCellValueFactory(new PropertyValueFactory<ShopItem, String>("description"));
        category.setCellValueFactory(new PropertyValueFactory<ShopItem, String>("category"));
        table.getItems().setAll(model.getItems());
    }
    
    
    
	/**
	 * This method is called by the FXMLLoader when initialization is complete
	 */
    @FXML
    void initialize() {
        assert addNewItem != null : "fx:id=\"addNewItem\" was not injected: check your FXML file 'shop.fxml'.";
        assert refreshItems != null : "fx:id=\"refreshItems\" was not injected: check your FXML file 'shop.fxml'.";
        assert exit != null : "fx:id=\"exit\" was not injected: check your FXML file 'shop.fxml'.";
        assert searchbar != null : "fx:id=\"searchbar\" was not injected: check your FXML file 'shop.fxml'.";
        assert category != null : "fx:id=\"category\" was not injected: check your FXML file 'shop.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'shop.fxml'.";
        assert id != null : "fx:id=\"id\" was not injected: check your FXML file 'shop.fxml'.";
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'shop.fxml'.";
        assert amount != null : "fx:id=\"amount\" was not injected: check your FXML file 'shop.fxml'.";
        assert price != null : "fx:id=\"price\" was not injected: check your FXML file 'shop.fxml'.";
        assert description != null : "fx:id=\"description\" was not injected: check your FXML file 'shop.fxml'.";
        assert rightclick != null : "fx:id=\"rightclick\" was not injected: check your FXML file 'shop.fxml'.";
    	model = new Model();
    	refreshTable();
    }
    
    // Loads EN
    @FXML
    private void itemEN(ActionEvent event) {
    	loadLang("en");
    }
    
    // Loads FI
    @FXML
    private void itemFI(ActionEvent event) {
    	loadLang("fi");
    }
    
    private void loadLang(String lang) {
    	Locale locale = new Locale(lang);
    	resources = ResourceBundle.getBundle("shop.lang", locale);
    	refreshItems.setText(resources.getString("refreshItems"));
    	addNewItem.setText(resources.getString("addNewItem"));
    	exit.setText(resources.getString("exit"));
    	name.setText(resources.getString("name"));
    	amount.setText(resources.getString("amount"));
    	price.setText(resources.getString("price"));
    	description.setText(resources.getString("description"));
    	undo.setText(resources.getString("undo"));
    	delete.setText(resources.getString("delete"));
    	about.setText(resources.getString("about"));
    	fileMenu.setText(resources.getString("fileMenu"));
    	editMenu.setText(resources.getString("editMenu"));
    	helpMenu.setText(resources.getString("helpMenu"));
    	languageMenu.setText(resources.getString("languageMenu"));
    	idRadioButton.setText(resources.getString("idRadioButton"));
    	nameRadioButton.setText(resources.getString("nameRadioButton"));
    	descriptionRadioButton.setText(resources.getString("descriptionRadioButton"));
    	categoryRadioButton.setText(resources.getString("categoryRadioButton"));
    	search.setText(resources.getString("search"));
    	category.setText(resources.getString("category"));
    	rightclick.setText(resources.getString("rightclick"));
    }
}