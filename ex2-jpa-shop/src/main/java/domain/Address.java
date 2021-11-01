package domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {

	@Column(length = 10)
	private String city;
	
	@Column(length = 20)
	private String street;
	
	@Column(length = 5)
	private String zipcode;
	
	// 사용자 정의 메소드
	public String fullAddress() {
		return getCity() + " " + getStreet() + " " + getZipcode();
	}
	
	public boolean isValid() {
		return false;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getStreet() {
		return street;
	}
	
	public String getZipcode() {
		return zipcode;
	}
	
	private void setCity(String city) {
		this.city = city;
	}
	
	private void setStreet(String street) {
		this.street = street;
	}
	
	private void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		result = prime * result + ((zipcode == null) ? 0 : zipcode.hashCode());
		return result;
	}

	// 변수를 직접 호출하는 것 보다 getter를 통해 호출하는 것이 안전함
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		if (zipcode == null) {
			if (other.zipcode != null)
				return false;
		} else if (!zipcode.equals(other.zipcode))
			return false;
		return true;
	}
}
