package jpashop.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Member {

	@Id @GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;
	private String username;
	
	@OneToMany(mappedBy = "member")
	private List<Order> orders = new ArrayList<>();
	
	@Embedded
	private Address address;
}
