package study.pr3springdatajpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import study.pr3springdatajpa.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

	List<Member> findByUsernameAndAgeGreaterThan(String username, int age);
	
	@Query(name = "Member.findByUsername")
	List<Member> findByUsername(@Param("username") String username);
}
