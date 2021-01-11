package shop;

import javax.persistence.*;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

/**
 * The order class for the orders in the warehouse
 * @author maailmanpaskintiimi: Jere Moilanen, Marko Korhonen, Huy Nguyen, Miska Peltoniemi
 *
 */
@Entity
@Indexed
@Table(name = "orders")
public class Order {

	/**
	 * Unique ID of the order
	 */
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderID")
	private int orderID;
	
	/**
	 * Name of the location from which the order is from
	 */
	@Field(termVector = TermVector.YES)
	@Column(name = "from_location")
	private String from_location;
	
	/**
	 * Name of the location to which the order is going heading
	 */
	@Field(termVector = TermVector.YES)
	@Column(name = "to_location")
	private String to_location;

	/**
	 * Message regarding the order
	 */
	@Field(termVector = TermVector.YES)
	@Column(name = "message")
	private String message;
	
	/**
	 * Empty constructor required by Hibernate
	 */
	public Order() {}

	public int getId() {
		return orderID;
	}

	public void setId(int orderID) {
		this.orderID = orderID;
	}

	public String getFromLocation() {
		return from_location;
	}

	public void setfromLocation(String from_location) {
		this.from_location = from_location;
	}

	public String getToLocation() {
		return to_location;
	}

	public void settoLocation(String to_location) {
		
		this.to_location = to_location;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
