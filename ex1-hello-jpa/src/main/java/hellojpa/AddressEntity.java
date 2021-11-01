package hellojpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ADDRESS_HISTORY")
public class AddressEntity {

	@Id @GeneratedValue
	private Long id;
	
	private Address address;

	public AddressEntity() {
	}
	
	public AddressEntity(String city, String street, String zipcode) {
		this.address = new Address(city, street, zipcode);
	}

	private Long getId() {
		return id;
	}

	private void setId(Long id) {
		this.id = id;
	}

	private Address getAddress() {
		return address;
	}

	private void setAddress(Address address) {
		this.address = address;
	}
}
