package hellojpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaCaution {
	
	public static void main(String[] args) {
		// 'persistence.xml'에서의 persistence-unit name
		EntityManagerFactory enf = Persistence.createEntityManagerFactory("persistence-unit");
		
		EntityManager em = enf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			// 주인이 아닌 곳에서 값을 넣을 때 (member1의 TEAM_ID는 null)
//			Member member = new Member();
//			member.setName("member1");
//			em.persist(member);
//
//			Team team = new Team();
//			team.setName("TeamA");
//			team.getMembers().add(member);
//			em.persist(team);
			
			Team team = new Team();
			team.setName("TeamA");
			em.persist(team);
			
			Member member = new Member();
			member.setName("member1");
			em.persist(member);

			member.changeTeam(team);
//			team.getMembers().add(member); 
			
//			em.flush();
//			em.clear();
			 
			Team findTeam = em.find(Team.class, team.getId());
			List<Member> members = findTeam.getMembers();
			
			for(Member m : members) {
				System.out.println("m = " + m.getName());
			}
			
			tx.commit();
			
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		
		enf.close();
	}
}
