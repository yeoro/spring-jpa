package hellojpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Team extends BaseEntity {

	@Id @GeneratedValue
	@Column(name = "TEAM_ID")
	private Long id;
	
	private String name;

	// 나의 반대편에 있는 연관관계의 변수명
	@OneToMany(mappedBy = "team")
	private List<Member> members = new ArrayList<Member>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}
}
