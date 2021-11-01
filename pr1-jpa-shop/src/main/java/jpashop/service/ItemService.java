package jpashop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpashop.domain.item.Item;
import jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

	private final ItemRepository itemRepository;
	
	/**
	 * 상품 저장
	 * @param item
	 */
	@Transactional
	public void save(Item item) {
		itemRepository.save(item);
	}
	
	/**
	 * 상품 조회
	 * @param id
	 * @return
	 */
	public Item findOne(Long id) {
		return itemRepository.findOne(id);
	}
	
	/**
	 * 상품 전체 조회
	 * @return
	 */
	public List<Item> findAll() {
		return itemRepository.findAll();
	}
}
