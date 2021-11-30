package jpashop.domain.item;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import jpashop.domain.Category;
import jpashop.domain.OrderItem;
import jpashop.exception.NotEnoughStockException;
import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
@Data
public abstract class Item {

	@Id @GeneratedValue
	@Column(name = "ITEM_ID")
	private Long id;
	
	private String name;
	private int price;
	private int stockQuantity;
	
	@OneToMany(mappedBy = "item")
	private List<OrderItem> orderItems = new ArrayList<>();
	
	@ManyToMany(mappedBy = "items")
	private List<Category> categories = new ArrayList<>();

	/**
	 * 재고 증가
	 * @param quantity
	 */
	public void addStock(int quantity) {
		this.stockQuantity += quantity;
	}
	
	public void removeStock(int quantity) {
		int restStock = this.stockQuantity - quantity;
		if(restStock < 0) {
			throw new NotEnoughStockException("재고가 0개 미만입니다.");
		}
		this.stockQuantity = restStock;
	}
}
