package study.pr3springdatajpa.controller;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import study.pr3springdatajpa.entity.Member;
import study.pr3springdatajpa.repository.MemberRepository;

@RestController
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberRepository memberRepository;
	
	@GetMapping("/members/{id}")
	public String findMember(@PathVariable("id") Long id) {
		Member member = memberRepository.findById(id).get();
		return member.getUsername();
	}
	
	@GetMapping("/members2/{id}")
	public String findMember2(@PathVariable("id") Member member) {
		return member.getUsername();
	}
	
	@PostConstruct
	public void init() {
		memberRepository.save(new Member("userA"));
	}
}
