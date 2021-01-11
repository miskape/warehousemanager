package test.java;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import mainWarehouse.*;
public class TestDatabase {
	
	// Fetch an item from the database and check if the object properties
	// are correct
	@Test
	public void fetchItemFromDatabase() {
		Model model = new Model();
		WarehouseItem item = model.getItem(36);
		assertEquals(36, item.getId());
		assertEquals("Samsung WR52001", item.getName());
		assertEquals("Samsung 52\" 4K TV", item.getDescription());
	}
}
