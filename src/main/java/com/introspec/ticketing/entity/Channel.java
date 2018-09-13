package com.introspec.ticketing.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Channel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
//	private Integer Time;
	@NotNull
    @Lob
	private String type;
	@NotNull
    @Lob
	private String name;
//	private Set<Ticket> ticket = new HashSet<Ticket>();
	
	 @OneToMany(cascade = CascadeType.ALL,
	            fetch = FetchType.LAZY,
	            mappedBy = "post")
	    private Set<Ticket> comments = new HashSet<>();
	
//	@ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "ticketid", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
//    private Ticket ticket;
//	

	public Channel() {}
	
	public Channel(Long id, String type, String name, Long ticketid) {
		super();
		this.id = id;
		this.type = type;
		this.name = name;
//		this.ticket= new Ticket(ticketid);
//		this.ticket=  new Ticket(ticketid, 1l, "", "", "",  new Date(), 0.00, new Date(), new Date());
	} 	
}
//@OneToMany(mappedBy="channel",  targetEntity=Ticket.class)
////cascade=CascadeType.ALL
//private Ticket ticket;
//

////public Ticket ticket = new Ticket();
//public Set<Ticket> getTicket() { return ticket; }
//void setTicket(Set ticket) { this.ticket = ticket; }
//private Set<Ticket> ticket =  new TreeSet<>();



