package mainWarehouse;

import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

/**
 * Database access class
 * Uses hibernate to access a MariaDB database
 * @author maailmanpaskintiimi: Jere Moilanen, Marko Korhonen, Huy Nguyen, Miska Peltoniemi
 */
public class Model {
	private static SessionFactory sf;
	private static Configuration cf;
	private EntityManager em;
	
	public Model() {
		cf = new Configuration();
		cf.configure("/hibernate.cfg.xml");
		sf = cf.buildSessionFactory();
	}
	
	/**
	 * Adds an item object to the database
	 * @param item the item to be added to the database
	 * @return true if adding was successful and false otherwise
	 */
	public boolean addItem(WarehouseItem item) {
		Session session = sf.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(item);
			tx.commit();
			return true;
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
	}
	
	/**
	 * Adds an order to the database
	 * @param the order to be added to the database
	 * @return true if adding was successful and false otherwise
	 */
	public boolean addOrder(Order order) {
		Session session = sf.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(order);
			tx.commit();
			return true;
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
	}
	
	/**
	 * Modifys an order in the database
	 * @param the order to be modified.
	 * @return true if successful, false otherwise
	 */
	public boolean modifyOrder(int id) {
		Session session = sf.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Order order = session.load(Order.class, id);
			order.setComplete(true);
			session.save(order);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		} finally {
        	session.flush();
        	tx.commit();
            session.close();
		}
	}
	
	/**
	 * Deletes the selected item
	 * @param the id of the order to be deleted
	 * @return true if successful, false otherwise
	 */
	public boolean deleteOrder(int id) {
		Session session = sf.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Order order = session.load(Order.class, id);
			session.delete(order);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		} finally {
        	session.flush();
        	tx.commit();
            session.close();
		}
	}
	
	/**
	 * Deletes an item from the database
	 * @param id ID of the item to be removed from the database
	 * @return true if the deletion was succesful and false otherwise
	 */
	public boolean deleteItem(int id) {
		Session session = sf.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			WarehouseItem item = session.load(WarehouseItem.class, id);
			session.delete(item);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		} finally {
        	session.flush();
        	tx.commit();
            session.close();
		}
	}
	
	/**
	 * Finds an item from the database
	 * @param id ID of the item to be fetched from the database
	 * @return Item object that was found from the database
	 */
	public WarehouseItem getItem(int id) {
		Session session = sf.openSession();
		WarehouseItem item = null;
		try {
			item = session.get(WarehouseItem.class, id);
			Hibernate.initialize(item);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return item;
	}
	/**
	 * To be used if needed
	 * 
	public Order getOrder(int id) {
		Session session = sf.openSession();
		Order order = null;
		try {
			order = session.get(Order.class, id);
			Hibernate.initialize(order);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return order;
	}
	*/
	
	/**
	 * Gets all items currently present in the warehouse.
	 * @return List of all items in the warehouse
	 */
	@SuppressWarnings("unchecked")
	public List<WarehouseItem> getItems() {
		Session session = sf.openSession();
		List<WarehouseItem> items = null;
		try {
			items = session.createQuery("from WarehouseItem where location = 0").getResultList();
		} catch(HibernateException e) {
			e.printStackTrace();
		} finally {
	         session.close(); 
	    }
		return items;
	}
	
	/**
	 * Gets all items currently present in the shop.
	 * @return List of all items in the shop
	 */
	@SuppressWarnings("unchecked")
	public List<WarehouseItem> getItemsInShop() {
		Session session = sf.openSession();
		List<WarehouseItem> items = null;
		try {
			items = session.createQuery("from WarehouseItem where location = 1").getResultList();
		} catch(HibernateException e) {
			e.printStackTrace();
		} finally {
	         session.close(); 
	    }
		return items;
	}
	
	/**
	 * Gets all orders currently present in the database.
	 * @return List of all orders in the database
	 */ 
	@SuppressWarnings("unchecked")
	public List<Order> getOrders(Boolean complete) {
		Session session = sf.openSession();
		List<Order> orders = null;

		try {
			orders = session.createQuery("from Order where complete = "+complete).getResultList();
		} catch(HibernateException e) {
			e.printStackTrace();
		} finally {
	         session.close(); 
	    }

		return orders;
	}
	
	/**
	 * Finds a list of items from the database that matches a defined search criteria.
	 * Not implemented completely yet.
	 * @return List of items that was found from the database
	 */
//	@SuppressWarnings("unchecked")
//	public List<WarehouseItem> searchItems(String query) {
//		List<WarehouseItem> result = null;
//		
//		FullTextEntityManager fullTextEntityManager 
//		= Search.getFullTextEntityManager(em);
//		
//		try {
//			fullTextEntityManager.createIndexer().startAndWait();
//			QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
//					  .buildQueryBuilder()
//					  .forEntity(WarehouseItem.class)
//					  .get();
//			
//			org.apache.lucene.search.Query queryObject = queryBuilder
//					  .keyword()
//					  .onField("name")
//					  .matching(query)
//					  .createQuery();
//			org.hibernate.search.jpa.FullTextQuery jpaQuery
//			  = fullTextEntityManager.createFullTextQuery(queryObject,WarehouseItem.class);
//			result = jpaQuery.getResultList();
//		
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//		return result;
//	}
	
	public boolean sendItemToShop(int itemID, int amount) {
		WarehouseItem itemToBeSent = this.getItem(itemID);
		WarehouseItem shopItem = null;
		List<WarehouseItem> shopItems = this.getItemsInShop();
		boolean itemIsInShop = false;
		
		for (WarehouseItem item : shopItems) {
			if(item.getName().equals(itemToBeSent.getName())) {
				shopItem = item;
				itemIsInShop = true;
				break;
			}
		}
		
		
		Session session = sf.openSession();
		Transaction tx = null;
		tx = session.beginTransaction();
		Boolean successful = false;
		int finalAmount = itemToBeSent.getAmount() - amount;
		
		try {
			if(finalAmount < 0) {
				successful = false;
			}
			if(finalAmount == 0) {// warehouse amount is going to be 0, so we can delete it
				if(itemIsInShop) { // item is already in shop, lets add the amount to it
					shopItem.setAmount(shopItem.getAmount() + amount);
					session.saveOrUpdate(shopItem);
					session.delete(itemToBeSent);
				}
				else {  // item is not in shop but warehouse quantity is also null, so
						// we can just move the item location
					itemToBeSent.setLocation(1);
					session.saveOrUpdate(itemToBeSent);
				}
				successful = true;
			}
			if(finalAmount > 0) {
				if(itemIsInShop) {  // item is in shop, so lets add the amount to it and
									// substract it from the warehouse
					shopItem.setAmount(shopItem.getAmount() + amount);
					itemToBeSent.setAmount(itemToBeSent.getAmount() - amount);
					session.update(itemToBeSent);
					session.update(shopItem);
				}
				else {  // item that we are sending is not in shop, lets create it and substract
						// the amount from the old item in warehouse
					WarehouseItem newItem = new WarehouseItem();
					newItem.setName(itemToBeSent.getName());
					newItem.setPrice(itemToBeSent.getPrice());
					newItem.setAmount(amount);
					newItem.setDescription(itemToBeSent.getDescription());
					newItem.setLocation(1);
					itemToBeSent.setAmount(itemToBeSent.getAmount() - amount);
					session.saveOrUpdate(itemToBeSent);
					session.save(newItem);
					successful = true;
				}
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {// clean and close the session
	        tx.commit();
	        session.close();
		}
		return successful;
	}
}
