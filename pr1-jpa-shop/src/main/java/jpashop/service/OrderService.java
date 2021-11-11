package jpashop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpashop.domain.Delivery;
import jpashop.domain.Member;
import jpashop.domain.Order;
import jpashop.domain.OrderItem;
import jpashop.domain.item.Item;
import jpashop.repository.ItemRepository;
import jpashop.repository.MemberRepository;
import jpashop.repository.OrderRepository;
import jpashop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

	private final OrderRepository orderRepository;
	private final MemberRepository memberRepository;
	private final ItemRepository itemRepository;
	
	/**
	 * 주문
	 */
	@Transactional
	public Long Order(Long memberId, Long itemId, int count) {
		
		// 엔티티 조회
		Member member = memberRepository.findOne(memberId);
		Item item = itemRepository.findOne(itemId);
		
		// 배송정보 생성
		Delivery delivery = new Delivery();
		delivery.setAddress(member.getAddress());
		
		// 주문상품 생성
		OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
		
		// 주문 생성
		Order order = Order.createOrder(member, delivery, orderItem);
		
		// 주문 저장 - cascade이기 때문에 order만 persist하면 연관관계를 가진 엔티티도 모두 persist 된다.
		orderRepository.save(order);
		
		return order.getId();
	}
	
	/**
	 * 주문 취소
	 */
	@Transactional
	public void cancelOrder(Long orderId) {
		
		// 주문 엔티티 조회
		Order order = orderRepository.findOne(orderId);
		
		// 주문 취소
		order.cancel();
	}
	
	/**
	 * 주문 검색
	 */
	public List<Order> findOrders(OrderSearch orderSearch) {
		return orderRepository.findAllByString(orderSearch);
	}
}
