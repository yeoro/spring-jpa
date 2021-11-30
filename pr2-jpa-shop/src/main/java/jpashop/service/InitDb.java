package jpashop.service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jpashop.domain.Address;
import jpashop.domain.Delivery;
import jpashop.domain.Member;
import jpashop.domain.Order;
import jpashop.domain.OrderItem;
import jpashop.domain.item.Book;
import lombok.RequiredArgsConstructor;

/**
 * 총 주문 2개
 * userA - JPA1 BOOK
 *		 - JPA2 BOOK
 *
 * userB - SPRING1 BOOK
 * 		 - SPRING2 BOOK
 */
@Component
@RequiredArgsConstructor
public class InitDb {
	
	private final InitService initService;
	
	@PostConstruct
	public void Init() {
		initService.dbInit1();
		initService.dbInit2();
	}

	@Component
	@Transactional
	@RequiredArgsConstructor
	static class InitService {
		private final EntityManager em;
		
		public void dbInit1() {
			Member member = new Member();
			member.setName("userA");
			member.setAddress(new Address("서울", "1", "1111"));
			em.persist(member);
			
			Book book1 = createBook("JPA1 BOOK", 10000, 100);
			em.persist(book1);

			Book book2 = createBook("JPA2 BOOK", 10000, 100);
			em.persist(book2);
			
			OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
			OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);
			
			Delivery delivery = new Delivery();
			delivery.setAddress(member.getAddress());
			Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
			em.persist(order);
		}
		
		public void dbInit2() {
			Member member = new Member();
			member.setName("userB");
			member.setAddress(new Address("부산", "1", "1111"));
			em.persist(member);
			
			Book book1 = createBook("SPRING1 BOOK", 20000, 200);
			em.persist(book1);
			
			Book book2 = createBook("SPRING2 BOOK", 40000, 300);
			em.persist(book2);
			
			OrderItem orderItem1 = OrderItem.createOrderItem(book1, 20000, 3);
			OrderItem orderItem2 = OrderItem.createOrderItem(book2, 40000, 4);
			
			Delivery delivery = createDelivery(member);
			Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
			em.persist(order);
		}
		
		private Book createBook(String name, int price, int stockQuantity) {
			Book book = new Book();
			book.setName(name);
			book.setPrice(price);
			book.setStockQuantity(stockQuantity);
			em.persist(book);
			return book;
		}
		
		private Delivery createDelivery(Member member) {
			Delivery delivery = new Delivery();
			delivery.setAddress(member.getAddress());
			return delivery;
		}
	}
}


