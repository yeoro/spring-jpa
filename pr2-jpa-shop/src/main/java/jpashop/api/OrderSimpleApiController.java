package jpashop.api;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jpashop.domain.Address;
import jpashop.domain.Order;
import jpashop.domain.OrderStatus;
import jpashop.repository.OrderRepository;
import jpashop.repository.OrderSearch;
import jpashop.repository.order.simplequery.OrderSimpleQueryDto;
import jpashop.repository.order.simplequery.OrderSimpleQueryRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
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
	private final OrderSimpleQueryRepository orderSimpleQueryRepository;
	
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
	
	@GetMapping("/api/v2/simple-orders")
	public List<SimpleOrderDto> ordersV2() {
		List<Order> orders = orderRepository.findAllByString(new OrderSearch());
		List<SimpleOrderDto> result = orders.stream()
			.map(o -> new SimpleOrderDto(o))
			.collect(Collectors.toList());
		
		return result;
	}
	
	@GetMapping("/api/v3/simple-orders")
	public List<SimpleOrderDto> ordersV3() {
		List<Order> orders = orderRepository.findAllWithMemberDelivery();
		List<SimpleOrderDto> result = orders.stream()
			.map(SimpleOrderDto::new)
			.collect(Collectors.toList());
		
		return result;
	}
	
	@GetMapping("/api/v4/simple-orders")
	public List<OrderSimpleQueryDto> ordersV4() {
		return orderSimpleQueryRepository.findOrderDtos();
	}
	
	@Data
	@AllArgsConstructor
	static class SimpleOrderDto {
		private Long orderId;
		private String name;
		private LocalDateTime orderDate;
		private OrderStatus orderStatus;
		private Address address;
		
		public SimpleOrderDto(Order o) {
			orderId = o.getId();
			name = o.getMember().getName(); // Lazy 강제 초기화
			orderDate = o.getOrderDate();
			orderStatus = o.getStatus();
			address = o.getDelivery().getAddress(); // Lazy 강제 초기화
		}
	}
}
