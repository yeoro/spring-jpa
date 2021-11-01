package hellojpa;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
	
	public static void main(String[] args) {
		// 'persistence.xml'에서의 persistence-unit name
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence-unit");
		
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			// member 생성
//			Member member = new Member();
//			member.setId(2L);
//			member.setName("HelloA");
//			em.persist(member);
//			
//			em.detach(member);
			
			// member 조회
//			Member findMember = em.find(Member.class, 1L);
//			System.out.println("id : " + findMember.getId());
//			System.out.println("name : " + findMember.getName());
			
			// member 수정
//			findMember.setName("HelloB");
//			System.out.println("id : " + findMember.getId());
//			System.out.println("name : " + findMember.getName());
			
			// JPQL
//			List<Member> result = em.createQuery("select m from Member as m", Member.class)
//					// 1번부터 10번 가져오기
//					.setFirstResult(1) 
//					.setMaxResults(10) 
//					.getResultList();
//			
//			for(Member member : result) {
//				System.out.println("member.name = " + member.getName());
//			}
			
//			Team team = new Team();
//			team.setName("TeamA");
//			em.persist(team);
//			
//			Member member = new Member();
//			member.setName("member1");
			
			// 테이블 중심의 모델링의 경우
//			member.setTeamId(team.getId());
			
			// 객체 지향 모델링의 경우
//			member.changeTeam(team);
//			em.persist(member);

			// 쿼리를 확인하기 위해 영속성 컨텍스트 초기화
//			em.flush();
//			em.clear();
			
//			Member findMember = em.find(Member.class, member.getId());
//			Team findTeam = findMember.getTeam();
//			
//			System.out.println("findTeam = " + findTeam.getName());
			
			// em.find
//			Member findMember = em.find(Member.class, member.getId());
//			System.out.println("findMember.id = " + findMember.getId());
//			System.out.println("findMember.name = " + findMember.getName());
			
			// em.getReference
//			Member findMember = em.getReference(Member.class, member.getId());
//			System.out.println("findMember = " + findMember.getClass());
//			System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(findMember));
			
			// 준영속 상태
//			em.detach(findMember);
//			findMember.getName();
			

//			Team team = new Team();
//			team.setName("team1");
//			em.persist(team);
			
			// JPQL
//			List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();

			Member member = new Member();
			member.setName("user1");
			member.setHomeAddress(new Address("new", "street", "zipcode"));
			
			member.getFavoriteFoods().add("치킨");
			member.getFavoriteFoods().add("족발");
			member.getFavoriteFoods().add("피자");
			
			member.getAddressHistory().add(new AddressEntity("old1", "street", "zipcode"));
			member.getAddressHistory().add(new AddressEntity("old2", "street", "zipcode"));
			em.persist(member);
			
			em.flush();
			em.clear();
			
			System.out.println("-----------------");
			Member fm = em.find(Member.class, member.getId());
			
			// 값 타입 변경
			Address address = fm.getHomeAddress();
			fm.setHomeAddress(new Address("new2", address.getStreet(), address.getZipcode()));
			
			// 값 타입 컬렉션 변경 (String)
			fm.getFavoriteFoods().remove("치킨");
			fm.getFavoriteFoods().add("한식");
			
			// 값 타입 컬렉션 변경 (List)
//			fm.getAddressHistory().remove(new Address("old1", "street", "zipcode"));
//			fm.getAddressHistory().add(new Address("new1", "street", "zipcode"));
			
			tx.commit();
			
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		emf.close();
	}
}
