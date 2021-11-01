import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import domain.Book;

public class JpaMain {
	
	public static void main(String[] args) {
		// 'persistence.xml'에서의 persistence-unit name
		EntityManagerFactory enf = Persistence.createEntityManagerFactory("persistence-unit");
		
		EntityManager em = enf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			
			Book book = new Book();
			book.setName("JPA");
			book.setAuthor("김영한");
			book.setCreatedBy("Seong");
			book.setCreatedTime(LocalDateTime.now());

			em.persist(book);
			
			
			tx.commit();
			
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		
		enf.close();
	}
}
