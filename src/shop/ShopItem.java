package shop;

import javax.persistence.*;
import org.hibernate.search.annotations.*;

/**
 * The item class for the items in the warehouse
 * @author maailmanpaskintiimi: Jere Moilanen, Marko Korhonen, Huy Nguyen, Miska Peltoniemi
 *
 */
@Entity
@Indexed
@Table(name = "items")
public class ShopItem {
	
	/**
	 * Unique ID of the item
	 */
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "itemID")
	private int id;
	
	/**
	 * Name of the item
	 */
	@Field(termVector = TermVector.YES)
	@Column(name = "name")
	private String name;
	
	/**
	 * Price of the item in Euro
	 */
	@Field(termVector = TermVector.YES)
	@Column(name = "price")
	private float price;
	
	/**
	 * The number of these items in the warehouse
	 */
	@Field(termVector = TermVector.YES)
	@Column(name = "amount")
	private int amount;
	
	/**
	 * Short description of the item
	 */
	@Field(termVector = TermVector.YES)
	@Column(name = "description")
	private String description;
	
	/**
	 * Short description of the item
	 */
	@Field(termVector = TermVector.YES)
	@Column(name = "category")
	private String category;
	
	
	/**
	 * Location of the item. 0 if at warehouse and 1 if at shop
	 */
	@Field(termVector = TermVector.YES)
	@Column(name = "location")
	private int location;

	/**
	 * Empty constructor required by Hibernate
	 */
	public ShopItem() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	
	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

}
