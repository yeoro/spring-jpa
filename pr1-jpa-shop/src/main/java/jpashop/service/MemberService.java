package jpashop.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpashop.domain.Member;
import jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	
	/**
	 * 중복 회원 검증
	 * @param member
	 */
	public void validateDuplicateMember(Member member) {
		List<Member> findMembers = memberRepository.findByName(member.getName());
		
		if(!findMembers.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		}
	}
	
	/**
	 * 회원 가입
	 * @param member
	 * @return
	 */
	@Transactional
	public Long join(Member member) {
		validateDuplicateMember(member);
		memberRepository.save(member);
		return member.getId();
	}
	
	/**
	 * 회원 조회
	 * @param memberId
	 * @return
	 */
	public Member findOne(Long memberId) {
		return memberRepository.findOne(memberId);
	}
	
	/**
	 * 전체 회원 조회
	 * @return
	 */
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}
}
