package jpashop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import jpashop.domain.Member;
import jpashop.repository.MemberRepository;

@SpringBootTest(properties = {"spring.config.location=classpath:application-test.yml"})
@Transactional
public class MemberServiceTest {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	MemberRepository memberRepository;
	
	@Test
	@Rollback(false)
	public void 회원가입() throws Exception {
		//given
		Member member = new Member();
		member.setName("yeoro");
		
		// when
		Long saveId = memberService.join(member);

		// then
		assertEquals(member, memberRepository.findOne(saveId));
	}
	
	@Test
	public void 중복_회원_검사() throws Exception {
		//given
		Member m1 = new Member();
		m1.setName("A");

		Member m2 = new Member();
		m2.setName("A");
		
		// when
		memberService.join(m1);
		memberService.join(m2);

		// then
//		assertThrows(IllegalStateException.class);
		
		fail("예외 발생");
	}
}