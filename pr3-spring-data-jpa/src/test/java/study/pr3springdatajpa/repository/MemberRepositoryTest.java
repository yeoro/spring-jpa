package study.pr3springdatajpa.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import study.pr3springdatajpa.entity.Member;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberRepositoryTest {

	@Autowired
	MemberRepository memberRepository;
	
	@Test
	public void testMember() {
		System.out.println("memberRepository = " + memberRepository.getClass());
		
		Member member = new Member("memberA");
		Member saveMember = memberRepository.save(member);
		
		Member findMember = memberRepository.findById(saveMember.getId()).get();
		
		assertThat(findMember.getId()).isEqualTo(member.getId());
		assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
		assertThat(findMember).isEqualTo(member);
	}
	
	@Test
	public void basicCRUD() {
		Member member1 = new Member("member1");
		Member member2 = new Member("member2");
		memberRepository.save(member1);
		memberRepository.save(member2);
		
		// 단 건 조회 검증
		Member findMember1 = memberRepository.findById(member1.getId()).get();
		Member findMember2 = memberRepository.findById(member2.getId()).get();
		assertThat(findMember1).isEqualTo(member1);
		assertThat(findMember2).isEqualTo(member2);
		
		// 리스트 조회 검증
		List<Member> all = memberRepository.findAll();
		assertThat(all.size()).isEqualTo(2);
		
		// 카운트 검증
		Long count = memberRepository.count();
		assertThat(count).isEqualTo(2);
		
		// 삭제 검증
		memberRepository.delete(findMember1);
		memberRepository.delete(findMember2);
		
		Long deletedCount = memberRepository.count();
		assertThat(deletedCount).isEqualTo(0);
	}
}
