package jpashop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import jpashop.domain.item.Item;
import lombok.Data;

@Entity
@Data
public class OrderItem {

	@Id @GeneratedValue
	@Column(name = "ORDER_ITEM_ID")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDER_ID")
	private Order order;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ITEM_ID")
	private Item item;
	
	private int orderPrice;
	private int count;
	
	/**
	 * 생성 메서드
	 */
	public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
		OrderItem orderItem = new OrderItem();
		orderItem.setItem(item);
		orderItem.setOrderPrice(orderPrice);
		orderItem.setCount(count);
		
		item.removeStock(count);
		return orderItem;
	}
	
	/**
	 * 주문 취소
	 */
	public void cancel() {
		getItem().addStock(count);
	}

	/**
	 * 전체 주문 가격 조회
	 */
	public int getTotalPrice() {
		return getOrderPrice() * getCount();
	}
}
