package com.example.swimming_ticket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.swimming_ticket.dto.request.CreateTicketRequest;
import com.example.swimming_ticket.dto.response.TicketResponse;
import com.example.swimming_ticket.services.TicketService;

@RestController
@RequestMapping("ticket-management")
public class TicketController {
    
    @Autowired
    private TicketService ticketService;

    @GetMapping("/tickets")
    public ResponseEntity<List<TicketResponse>> getTickets() {
        return ticketService.getAllTickets();
    }

    @PostMapping("/tickets")
    public ResponseEntity<?> createTicket(@RequestBody CreateTicketRequest request) {
        return ticketService.create(request);
    }
}
