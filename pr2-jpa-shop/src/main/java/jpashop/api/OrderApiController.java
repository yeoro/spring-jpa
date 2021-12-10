package jpashop.api;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jpashop.domain.Address;
import jpashop.domain.Order;
import jpashop.domain.OrderItem;
import jpashop.domain.OrderStatus;
import jpashop.repository.OrderRepository;
import jpashop.repository.OrderSearch;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

	private final OrderRepository orderRepository;
	
	@GetMapping("/api/v1/orders")
	public List<Order> ordersV1() {
		List<Order> all = orderRepository.findAllByString(new OrderSearch());
		for(Order order : all) {
			order.getMember().getName();
			order.getDelivery().getAddress();
			List<OrderItem> orderItems = order.getOrderItems();
			orderItems.stream().forEach(o -> o.getItem().getName());
		}
		
		return all;
	}
	
	@GetMapping("/api/v2/orders")
	public List<OrderDto> ordersV2() {
		List<Order> orders = orderRepository.findAllByString(new OrderSearch());
		List<OrderDto> collect = orders.stream()
			.map(o -> new OrderDto(o))
			.collect(Collectors.toList());
		
		return collect;
	}
	
	@GetMapping("/api/v3/orders")
	public List<OrderDto> ordersV3() {
		List<Order> orders = orderRepository.findAllWithItem();
		List<OrderDto> collect = orders.stream()
			.map(o -> new OrderDto(o))
			.collect(Collectors.toList());
		
		return collect;
	}
	
	@Getter
	static class OrderDto {
		private Long orderId;
		private String name;
		private LocalDateTime orderDate;
		private OrderStatus orderStatus;
		private Address address;
		private List<OrderItemDto> orderItems;
		
		public OrderDto(Order order) {
			this.orderId = order.getId();
			this.name = order.getMember().getName();
			this.orderDate = order.getOrderDate();
			this.orderStatus = order.getStatus();
			this.address = order.getDelivery().getAddress();
			this.orderItems = order.getOrderItems().stream()
					.map(orderItem -> new OrderItemDto(orderItem))
					.collect(Collectors.toList());
		}
	}

	@Getter
	static class OrderItemDto {
		private String itemName;
		private int orderPrice;
		private int count;
		
		public OrderItemDto(OrderItem orderItem) {
			itemName = orderItem.getItem().getName();
			orderPrice = orderItem.getOrderPrice();
			count = orderItem.getCount();
		}
	}
}
