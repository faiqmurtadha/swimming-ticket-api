package com.example.swimming_ticket.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.swimming_ticket.model.Ticket;

@Repository
public class TicketRepository {
    
    private List<Ticket> tickets = new ArrayList<>();

    public List<Ticket> findAll() {
        return tickets;
    }

    public Ticket save(Ticket ticket) {
        tickets.add(ticket);
        return ticket;
    }
}
