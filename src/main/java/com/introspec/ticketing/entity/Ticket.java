package com.introspec.ticketing.entity;

import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
	value = {"createdAt", "updatedAt"},
	allowGetters = true
)
public class Ticket {
	@NotNull
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Long terminalId;
	private String accountNo;
	private String pan;
	private String phoneNumber;
	private Date transactionDate;
	private Double amount;	
//	private String channel;
	@DateTimeFormat(pattern="yyy-MM-dd'T'HH:mm:ss")
	@Column(nullable=false, updatable=false)
	@CreatedDate
	private Date createdAt;
	@Column(nullable=false)
	@LastModifiedDate
	private Date updatedAt;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "channelid", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Channel channel;
	
	
//	 @ManyToOne
//	 @JoinColumn(name="channel")
//	 private Channel channel;
	
	public Ticket() {};
	public Ticket(Long id, Long terminalId, String accountNo, String pan, String phoneNumber, Date transactionDate,
			Double amount, Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.terminalId = terminalId;
		this.accountNo = accountNo;
		this.pan = pan;
		this.phoneNumber = phoneNumber;
		this.transactionDate = transactionDate;
		this.amount = amount;
//		this.channel = channel;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	public Ticket(Long id) {
		this.id = id;
	}

//	public Ticket(Long ticketid, long l, String string, String string2, String string3, Date date, String string4,
//			Date date2, Date date3) {
//		// TODO Auto-generated constructor stub
//	}
//	
	
	
	
//	@Override
//	public String toString() {
//		return "Ticket [id=" + id + ", terminalId=" + terminalId + ", accountNo=" + accountNo + ", pan=" + pan
//				+ ", phoneNumber=" + phoneNumber + ", transactionDate=" + transactionDate + ", amount=" + amount
//				+ ", channel=" + channel + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", getClass()="
//				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
//	}
	
	
}
