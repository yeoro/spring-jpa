package jpamapping;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
	
	public static void main(String[] args) {
		// 'persistence.xml'에서의 persistence-unit name
		EntityManagerFactory enf = Persistence.createEntityManagerFactory("persistence-unit");
		
		EntityManager em = enf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			
			Movie movie = new Movie();
			movie.setDirector("A");
			movie.setActor("B");
			movie.setName("C");
			movie.setPrice(1000);
			em.persist(movie);
			
			em.flush();
			em.clear();
			
			Movie findMovie = em.find(Movie.class, movie.getId());
			System.out.println("findMovie = " + findMovie);
			
			tx.commit();
			
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		
		enf.close();
	}
}
