package shop;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
	private EntityManagerFactory emf;
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
	public boolean addItem(ShopItem item) {
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
	 * @param order the order to be added to the database
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
	 * Deletes an item from the database
	 * @param id ID of the item to be removed from the database
	 * @return true if the deletion was succesful and false otherwise
	 */
	public boolean deleteItem(int id) {
		Session session = sf.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			ShopItem item = session.load(ShopItem.class, id);
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
	public ShopItem getItem(int id) {
		Session session = sf.openSession();
		ShopItem item = null;
		try {
			item = session.get(ShopItem.class, id);
			Hibernate.initialize(item);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return item;
	}
	/**
	 * To be used later
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
	 * Gets all items currently present in the database.
	 * @return List of all items in the database
	 */
	@SuppressWarnings("unchecked")
	public List<ShopItem> getItems() {
		Session session = sf.openSession();
		List<ShopItem> items = null;
		try {
			items = session.createQuery("from ShopItem where location = 1").getResultList();
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
	public List<Order> getOrders() {
		Session session = sf.openSession();
		List<Order> orders = null;
		
		try {
			orders = session.createQuery("from Order").getResultList();
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
	@SuppressWarnings("unchecked")
	public List<ShopItem> searchItems(String query) {
		List<ShopItem> result = null;
		
		FullTextEntityManager fullTextEntityManager 
		= Search.getFullTextEntityManager(em);
		
		try {
			fullTextEntityManager.createIndexer().startAndWait();
			QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
					  .buildQueryBuilder()
					  .forEntity(ShopItem.class)
					  .get();
			
			org.apache.lucene.search.Query queryObject = queryBuilder
					  .keyword()
					  .onField("name")
					  .matching(query)
					  .createQuery();
			org.hibernate.search.jpa.FullTextQuery jpaQuery
			  = fullTextEntityManager.createFullTextQuery(queryObject,ShopItem.class);
			result = jpaQuery.getResultList();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
