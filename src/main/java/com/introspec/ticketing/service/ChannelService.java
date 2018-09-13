package com.introspec.ticketing.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.introspec.ticketing.entity.Channel;
import com.introspec.ticketing.entity.Ticket;
import com.introspec.ticketing.exception.ResourceNotFound;
import com.introspec.ticketing.repo.ChannelRepo;
import com.introspec.ticketing.repo.TicketRepo;

@Service
public class ChannelService {
	
	private final ChannelRepo channelRepo;
	private TicketRepo ticketRepo;
	
	@Autowired
	public ChannelService(final ChannelRepo channelRepo,final TicketRepo ticketRepo) { 
		this.channelRepo = channelRepo;
		this.ticketRepo = ticketRepo;
	}


	
	//to get all tickets generated from  a particular channel
	 public Page<Channel> getAllChannelsByTicketId(Long ticketid, Pageable pageable) {
		 return channelRepo.findByTicketId(ticketid, pageable);
			}
	 
	 
	 public Optional<Channel> getChannel(Long id){
			return channelRepo.findById(id);
		}
	 
	 
	 public Channel createChannel(Long ticketid, Channel channel) {
		 return ticketRepo.findById(ticketid).map(ticket -> {
	            channel.setTicket(ticket);
	            return channelRepo.save(channel);
	        }).orElseThrow(() -> new ResourceNotFound("TicketId " + ticketid + " not found"));	
		}
	 
	 
	 public Channel updateChannel(Long ticketid, Long id, Channel updatedChannel){
		 
		  if(!ticketRepo.existsById(ticketid)) {
	            throw new ResourceNotFound("TicketId " +ticketid + " not found");
	        }

	        return channelRepo.findById(id).map(channel -> {
	            channel.setName(updatedChannel.getName());
	            channel.setType(updatedChannel.getType());
	            return channelRepo.save(channel);
	        }).orElseThrow(() -> new ResourceNotFound("ChannelId " + id + "not found"));
	  
	 }

	 
	 public ResponseEntity<?> deleteChannel(Long ticketid, Long id){
		 if(!ticketRepo.existsById(ticketid)) {
	            throw new ResourceNotFound("TicketId " + ticketid + " not found");
	        }

	        return channelRepo.findById(id).map(channel -> {
	             channelRepo.delete(channel);
	             return ResponseEntity.ok().build();
	        }).orElseThrow(() -> new ResourceNotFound("ChannelId " + id + " not found"));
	    }
		 
} 



	
	 
	
	
	

	
	

	

	

