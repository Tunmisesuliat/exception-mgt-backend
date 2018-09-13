package com.introspec.ticketing;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.introspec.ticketing.entity.Channel;
import com.introspec.ticketing.entity.Ticket;
import com.introspec.ticketing.repo.ChannelRepo;
import com.introspec.ticketing.repo.TicketRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketingAppApplicationTests {
	@Autowired
	private TicketRepo ticketRepo;
	@Autowired
	private ChannelRepo channelRepo;
	
//	private final TicketRepo ticketRepo;
//	
//	@Autowired
//	public TicketService(final TicketRepo ticketRepo) { 
//		this.ticketRepo = ticketRepo;
//	}
	 
	
	@Test
	public void CreateTicket() {
		Ticket ticket = new Ticket();
		ticket.setAccountNo("2058737518");
		ticket.setPan("1157xxxx34");
		ticket.setPhoneNumber("08135997541");
		ticket.setAmount(120.90);
		ticket.setTransactionDate(new Date());
//		ticket.setChannel("NTA");
		ticket.setTerminalId(12346l);
//		ticket.setCreatedAt(new Date());
//		ticket.setUpdatedAt(new Date());
		
		ticketRepo.save(ticket);
	}
	@Test
	public void CreateChannel() {
		Channel channel = new Channel();
		channel.setName("CORE");
		channel.setType("Dispense error");
		
		channelRepo.save(channel);
		
	}

}
