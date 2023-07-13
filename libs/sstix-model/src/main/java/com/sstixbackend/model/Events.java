package com.sstixbackend.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Events {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;
	
	@Column
	private String name;
	
	@Column
	private String details;
	
	@Column
	private String location;
	
	@Column
	private String organizer;
	
	@Column
	private Integer status;
	
	@Column
	private Integer price;
	
	@Column
	private Integer qty;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable = false, updatable = false)
	private Date createDate;
	
	@Column
	private String eventDate;
	
	@Column
	private String image1;
	
	@Column
	private String image2;
	
}
