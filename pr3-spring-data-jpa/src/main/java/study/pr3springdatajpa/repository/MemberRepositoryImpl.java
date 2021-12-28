package study.pr3springdatajpa.repository;

import java.util.List;

import javax.persistence.EntityManager;

import lombok.RequiredArgsConstructor;
import study.pr3springdatajpa.entity.Member;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom{

	private final EntityManager em;
	
	@Override
	public List<Member> findMemberCustom() {
		return em.createQuery("select m from Member m")
				.getResultList();
	}
	
}
