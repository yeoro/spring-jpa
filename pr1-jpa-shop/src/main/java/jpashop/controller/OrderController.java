package jpashop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jpashop.domain.Member;
import jpashop.domain.item.Item;
import jpashop.service.ItemService;
import jpashop.service.MemberService;
import jpashop.service.OrderService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class OrderController {
	
	private final MemberService memberService;
	private final OrderService orderService;
	private final ItemService itemService;
	
	@GetMapping("/order")
	public String createForm(Model model) {
		
		List<Member> members = memberService.findMembers();
		List<Item> items = itemService.findAll();
		
		model.addAttribute("members", members);
		model.addAttribute("items", items);
		
		return "order/orderForm";
	}
	
	@PostMapping("/order")
	public String order(@RequestParam("memberId") Long memberid, 
						@RequestParam("itemId") Long itemId,
						@RequestParam("count") int count) {
		orderService.Order(memberid, itemId, count);
		return "redirect:/orders";
	}
}
