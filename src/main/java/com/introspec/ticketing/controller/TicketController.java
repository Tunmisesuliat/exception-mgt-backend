package com.introspec.ticketing.controller;

import java.util.List;

import com.introspec.ticketing.entity.Ticket;
import com.introspec.ticketing.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//import org.apache.camel.CamelContext;
//import org.apache.camel.ProducerTemplate;

@RestController
@RequestMapping("/tickets")
@CrossOrigin(origins = "http://localhost:3000")
public class TicketController {
	
	private final TicketService ticketService;
	@Autowired
	public TicketController(final TicketService ticketService) {
		this.ticketService = ticketService;
	}
	
	@GetMapping("")
	@CrossOrigin(origins = "localhost:3000")
	public Page<Ticket> getAllTicket(Pageable pageable){
//		producerTemplate.sendBody("direct:firstRoute", "Calling via Spring Boot Rest Controller");
		return ticketService.getAllTicket(pageable);
	}
	

	@GetMapping("/{id}")
	@CrossOrigin(origins = "localhost:3000")
	public Ticket getTicket(@PathVariable Long id){	
		return ticketService.getTicket(id).get();
	}
	
	@PostMapping("")
	@CrossOrigin(origins = "localhost:3000")
	public Ticket addTicket(@RequestBody Ticket ticket ){
		return ticketService.addTicket(ticket);
	}
	
	//we won't be using the next two, but keeping this as references for such when needed.
	@PutMapping("/{id}")
	public Ticket updateTicket(@PathVariable Long id, @RequestBody Ticket updatedTicket){
		return ticketService.updateTicket(id, updatedTicket);
	}
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public void deleteTicket(@PathVariable Long id){
		ticketService.deleteTicket(id);
	}
}
