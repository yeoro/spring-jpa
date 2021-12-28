package study.pr3springdatajpa.repository;

import java.util.List;

import study.pr3springdatajpa.entity.Member;

public interface MemberRepositoryCustom {
	List<Member> findMemberCustom();
}
