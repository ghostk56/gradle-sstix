package com.sstixbackend.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
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
@NamedEntityGraph(name = "Order.events", attributeNodes = @NamedAttributeNode("events"))
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;

	@Column
	private Integer usersId;

	@Column
	private Integer quantity;

	@Column
	private Integer eventPrice;

	@Column
	private Integer Status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(insertable = false, updatable = false)
	private Date orderDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "events_id", updatable = false)
	private Events events;
}
