package com.introspec.ticketing.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.introspec.ticketing.entity.Channel;
import com.introspec.ticketing.entity.Ticket;
import com.introspec.ticketing.service.ChannelService;

@RestController
@RequestMapping("/tickets/{ticketid}/channels")
@CrossOrigin(origins = "http://localhost:3000")
public class ChannelController {
	
	private final ChannelService channelService;
	@Autowired
	public ChannelController(final ChannelService channelService) {
		this.channelService = channelService;
	}
	
	@GetMapping("")
    public Page<Channel> getAllChannelsByTicketId(@PathVariable (value = "ticketid") Long ticketid,
                                                Pageable pageable) {
		return channelService.getAllChannelsByTicketId(ticketid, pageable);
       
    }

    @PostMapping("")
    public Channel createChannel(@PathVariable (value = "ticketId") Long ticketid,
                                 @Valid @RequestBody Channel channel) {
    	return channelService.createChannel(ticketid, channel);
         }

    @PutMapping("/{id}")
    public Channel updateChannel(@PathVariable (value = "ticketid") Long ticketid,
                                 @PathVariable (value = "id") Long id,
                                 @Valid @RequestBody Channel updatedChannel) {
    	return channelService.updateChannel(ticketid, id, updatedChannel);
        }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteChannel(@PathVariable (value = "ticketid") Long ticketid,
                              @PathVariable (value = "id") Long id) {
    	return channelService.deleteChannel(ticketid, id);
    }
       
}
	
	
	
////	@GetMapping("/all")
////	public List<Channel> getAllChannels(){
////		return channelService.getAllChannels();
////	}
//	
//	@GetMapping("")
//	public List<Channel> getAllChannel(@PathVariable Long ticketid){
//		return channelService.getAllChannel(ticketid);
//	}
//	
//	@GetMapping("/{id}")
//	public Channel getChannel(@PathVariable Long ticketid, @PathVariable Long id ){	
//		return channelService.getChannel(id).get();
//	}
//	
//	@PostMapping("")
//	public Channel addChannel(@RequestBody Channel channel, @PathVariable Long ticketid){
//		channel.setTicket( new Ticket(ticketid, 1l, "", "", "",  new Date(), 0.00, new Date(), new Date()));
//		return channelService.addChannel(channel);
//	}

	
	


