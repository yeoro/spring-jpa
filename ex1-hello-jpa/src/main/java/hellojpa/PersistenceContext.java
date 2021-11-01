package hellojpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class PersistenceContext {
	
	public static void main(String[] args) {
		// 'persistence.xml'에서의 persistence-unit name
		EntityManagerFactory enf = Persistence.createEntityManagerFactory("persistence-unit");
		
		EntityManager em = enf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			// 처음 조회, 1차 캐시 저장
			Member findMember1 = em.find(Member.class, 1L);
			// 두 번째 조회, 캐시에 존재하기 때문에 쿼리문X
			Member findMember2 = em.find(Member.class, 1L);
			
			System.out.println("result = " + (findMember1 == findMember2));
			
			tx.commit();
			
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		
		enf.close();
	}
}
