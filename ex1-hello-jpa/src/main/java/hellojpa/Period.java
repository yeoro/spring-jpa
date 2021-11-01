package hellojpa;

import java.time.LocalDateTime;

import javax.persistence.Embeddable;

@Embeddable
public class Period {

	private LocalDateTime startDate;
	private LocalDateTime endDate;
	
	public Period() {}
	
	public Period(LocalDateTime startDate, LocalDateTime endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public boolean isWork() {
		// 현재 시간과 근무 시간 비교 메소드
		return false;
	}
	
	public LocalDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	public LocalDateTime getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
}
