package jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import jpashop.domain.Member;
import jpashop.repository.MemberRepository;

@SpringBootTest
@ContextConfiguration(classes = MainApplication.class)
public class MemberRepositoryTests {

	@Autowired
	MemberRepository memberRepository;

	@Test
	@Transactional
	@Rollback(false)
	public void testMember() throws Exception {
		// given
		Member member = new Member();
		member.setName("memberA");

		// when

		// then
	}
	
}
