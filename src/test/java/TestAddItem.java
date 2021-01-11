//package test.java;
//
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.stage.Stage;
//import mainWarehouse.Main;
//
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.loadui.testfx.GuiTest;
//
//import java.io.IOException;
//
//public class TestAddItem extends GuiTest {
//	Main instance;
//    
//    public TestAddItem() {}
//    
//    public void testStart() throws Exception {
//        System.out.println("start");
//        Stage stage = null;
//        instance = new Main();
//        instance.start(stage);
//        fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void addAndDeleteItem() {      
//    	click("#fileMenu");
//    	click("#addNewItem");
//    	click("#nameInput").type("testItem");
//    	click("#amountInput").type("11");
//    	click("#priceInput").type("970");
//    	click("#descriptionInput").type("testItem description");
//    	click("#addButtons");
//    }
//
//	@Override
//	protected Parent getRootNode() {
//		Parent parent = null;
//		try {
//			parent = FXMLLoader.load(getClass().getResource("/application/main.fxml"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return parent;
//	}
//}