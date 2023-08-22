package com.example.todo.app.model;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table (name="todo")
public class ToDo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
//	@Nonnull
	private Long id;
	
	@Column
//	@Nonnull
	private String title;
	
	@Column
//	@Nonnull
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	
	@Column
//	@Nonnull
	private String status;
	
	public ToDo() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public  Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
