package jpashop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import jpashop.domain.Address;
import jpashop.domain.Member;
import jpashop.domain.Order;
import jpashop.domain.OrderStatus;
import jpashop.domain.item.Book;
import jpashop.repository.OrderRepository;

@SpringBootTest(properties = {"spring.config.location=classpath:application-test.yml"})
@Transactional
public class OrderServiceTest {
	
	@Autowired EntityManager em;
	@Autowired OrderService orderService;
	@Autowired OrderRepository orderRepository;
	
	@Test
	public void 상품주문() throws Exception {
		// given
		Member member = createMember();
		
		Book book = createBook("JPA", 10000, 10);
		
		int orderCount = 2;
		
		// when
		Long orderId = orderService.Order(member.getId(), book.getId(), orderCount);
		
		// then
		Order getOrder = orderRepository.findOne(orderId);
		
		assertEquals(OrderStatus.ORDER, getOrder.getStatus()); // 상품 주문 상태
		assertEquals(1, getOrder.getOrderItems().size()); // 주문한 상품 종류 수
		assertEquals(10000 * orderCount, getOrder.getTotalPrice()); // 주문 가격
		assertEquals(8, book.getStockQuantity()); // 재고 수
	}
	
	@Test
	public void 상품주문_재고수량초과() throws Exception {
		// given
		Member member = createMember();
		Book book = createBook("JPA", 10000, 10);
		
		int orderCount = 11;
		
		// when
//		orderService.Order(member.getId(), book.getId(), orderCount);
		
		// then
//		fail("재고 수량 예외 발생");
	}
	
	@Test
	public void 주문취소() throws Exception {
		// given
		Member member = createMember();
		Book book = createBook("JPA", 10000, 10);
		
		int orderCount = 2;
		
		Long orderId = orderService.Order(member.getId(), book.getId(), orderCount);

		// when
		orderService.cancelOrder(orderId);
		
		// then
		Order getOrder = orderRepository.findOne(orderId);
		
		assertEquals(OrderStatus.CANCEL, getOrder.getStatus()); // 취소 상태가 되어야 함
		assertEquals(10, book.getStockQuantity()); // 재고 원상복구
	}
	
	private Member createMember() {
		Member member = new Member();
		member.setName("회원1");
		member.setAddress(new Address("서울", "경기", "123-123"));
		em.persist(member);
		
		return member;
	}
	
	private Book createBook(String name, int price, int stockQuantity) {
		Book book = new Book();
		book.setName(name);
		book.setPrice(price);
		book.setStockQuantity(stockQuantity);
		em.persist(book);
		
		return book;
	}
}