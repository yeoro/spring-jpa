package jpashop.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jpashop.domain.Order;
import jpashop.repository.OrderRepository;
import jpashop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;

/**
 * xToOne 관계
 * Order -> Member
 * Order -> Delivery
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {
	
	private final OrderRepository orderRepository;
	
	@GetMapping("/api/v1/simple-orders")
	public List<Order> ordersV1() {
		List<Order> all = orderRepository.findAllByString(new OrderSearch());
		for(Order order : all) {
			// Lazy 강제 초기화
			order.getMember().getName();
			order.getDelivery().getAddress();
		}
		return all;
	}
}
