package study.pr3springdatajpa.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@MappedSuperclass
@Getter
public class JpaBaseEntity {

	@Column(updatable = false)
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	
	@PrePersist
	public void prePersist() {
		LocalDateTime now = LocalDateTime.now();
		
		createdDate = now;
		updatedDate = now;
	}
	
	@PreUpdate
	public void preUpdate() {
		updatedDate = LocalDateTime.now();
	}
}
