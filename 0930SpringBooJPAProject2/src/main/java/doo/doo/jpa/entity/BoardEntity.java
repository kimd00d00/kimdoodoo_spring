package doo.doo.jpa.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="board")
public class BoardEntity {
	@Id
	private int no;
	private String name, subject, content, pwd;
	private LocalDateTime regdate;
	private int hit;
	
	@PrePersist
	public void regdate() {//값이 입력되지 않았을 때 기본값을 지정
		this.regdate = LocalDateTime.now();
	}
//	@PrePersist
//	public void hit() {
//		hit = 0;
//	}
}
