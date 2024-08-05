package com.example.swimming_ticket.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.swimming_ticket.dto.request.CreateTicketRequest;
import com.example.swimming_ticket.dto.response.TicketResponse;
import com.example.swimming_ticket.model.Ticket;
import com.example.swimming_ticket.repository.TicketRepository;

import jakarta.transaction.Transactional;

@Service
public class TicketService {
    
    @Autowired
    private TicketRepository ticketRepository;

    public ResponseEntity<List<TicketResponse>> getAllTickets() {
        try {
            List<Ticket> tickets = ticketRepository.findAll();
            List<TicketResponse> ticketData;
            // if (tickets == null) {
            //     ticketData = new ArrayList<>();
            // } else {
                ticketData = tickets.stream().map(ticket ->
                    new TicketResponse(
                        ticket.getName(),
                        ticket.getTicketDate()
                    )
                ).collect(Collectors.toList());
            // }

            return ResponseEntity
                .ok()
                .body(ticketData);
        } catch (Exception e) {
            return ResponseEntity
                .internalServerError()
                .body(null);
        }
    }

    @Transactional
    public ResponseEntity<?> create(CreateTicketRequest request) {
        LocalDate localDate = LocalDate.parse(request.getTicketDate());
        Date ticketDate = Date.valueOf(localDate);
        Ticket newTicket = Ticket.builder()
            .name(request.getName())
            .ticketDate(ticketDate)
            .build();

        ticketRepository.save(newTicket);

        return ResponseEntity
            .ok()
            .body("Ticket berhasil disimpan");
    }
}
