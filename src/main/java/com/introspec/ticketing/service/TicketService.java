package com.introspec.ticketing.service;


import java.util.List;
import java.util.Optional;

import com.introspec.ticketing.entity.Ticket;
import com.introspec.ticketing.exception.ResourceNotFound;
import com.introspec.ticketing.repo.TicketRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

	private final TicketRepo ticketRepo;
	
	@Autowired
	public TicketService(final TicketRepo ticketRepo) { 
		this.ticketRepo = ticketRepo;
	}
	 
	
	 public Page<Ticket> getAllTicket(@PageableDefault(size = 10) Pageable pageable) {
		return ticketRepo.findAll(pageable);
		
		}
	

	
	public Optional<Ticket> getTicket(Long id){
		return ticketRepo.findById(id);
	}

	public Ticket addTicket(Ticket ticket) {
		return ticketRepo.save(ticket);	
	}

	//update and delete won't be used for ticketing, just keeping these here as reference for others
	public Ticket updateTicket(Long id, Ticket updatedTicket){
		return ticketRepo.findById(id).map(
			ticket -> {
				ticket.setAccountNo(updatedTicket.getAccountNo());
				ticket.setAmount(updatedTicket.getAmount());
//				ticket.setChannel(updatedTicket.getChannel());
				ticket.setPan(updatedTicket.getPan());
				ticket.setPhoneNumber(updatedTicket.getPhoneNumber());
				ticket.setTerminalId(updatedTicket.getTerminalId());
				ticket.setTransactionDate(updatedTicket.getTransactionDate());

				return ticketRepo.save(ticket);
			}
		).orElseThrow(()-> new ResourceNotFound(String.format("Post id {0} not found", id)));
	}

	//construct forces me to return something, not great. research possible fix
	public Ticket deleteTicket(Long id){
		return ticketRepo.findById(id).map(
			ticket -> {
				ticketRepo.delete(ticket);
				return ticket;
			}
		).orElseThrow(()-> new ResourceNotFound(String.format("Post id {0} not found", id)));
	}
}
