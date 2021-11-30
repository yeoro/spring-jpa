package jpashop.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ORDERS")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

	@Id @GeneratedValue
	@Column(name = "ORDER_ID")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMBER_ID")
	private Member member;
	
	private LocalDateTime orderDate;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "DELIVERY_ID")
	private Delivery delivery;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> orderItems = new ArrayList<>();
	
	public void setMember(Member member) {
		this.member = member;
		member.getOrders().add(this);
	}
	
	public void addOrderItem(OrderItem orderItem) {
		this.orderItems.add(orderItem);
		orderItem.setOrder(this);
	}
	
	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
		delivery.setOrder(this);
	}
	
	/**
	 * 생성 메서드
	 */
	public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
		Order order = new Order();
		order.setMember(member);
		order.setDelivery(delivery);
		for(OrderItem oi : orderItems) {
			order.addOrderItem(oi);
		}
		order.setOrderDate(LocalDateTime.now());
		order.setStatus(OrderStatus.ORDER);
		return order;
	}
	
	/**
	 * 주문 취소
	 */
	public void cancel() {
		if(delivery.getStatus() == DeliveryStatus.COMP) {
			throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
		}
		
		this.setStatus(OrderStatus.CANCEL);
		for(OrderItem oi : orderItems) {
			oi.cancel();
		}
	}
	
	/**
	 * 전체 주문 가격 조회
	 */
	public int getTotalPrice() {
//		int totalPrice = 0;
//		for(OrderItem oi : orderItems) {
//			totalPrice += oi.getTotalPrice();
//		}
//		return totalPrice;
		
		// java 1.8
		return orderItems.stream()
				.mapToInt(OrderItem::getTotalPrice)
				.sum();
	}
}
