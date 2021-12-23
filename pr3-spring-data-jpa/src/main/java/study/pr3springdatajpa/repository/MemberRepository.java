package study.pr3springdatajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import study.pr3springdatajpa.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
