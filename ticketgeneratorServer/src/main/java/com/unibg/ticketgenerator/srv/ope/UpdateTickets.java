package com.unibg.ticketgenerator.srv.ope;

import com.unibg.ticketgenerator.dao.TicketsRepository;
import com.unibg.ticketgenerator.entities.Ticket;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UpdateTickets {
    @Autowired
    private TicketsRepository ticketsRepository;

    @SneakyThrows
    @Scheduled(fixedDelay = 20000) // esegui ogni 20 secondi
    public void updateTicketPriorities() {
        List<Ticket> tickets = ticketsRepository.findAll();
        for (Ticket ticket : tickets) {
            // Aggiorna la priorità del biglietto
            ticket.isExpired();
            // Salva il biglietto nel repository
            ticketsRepository.save(ticket);
        }
    }

    @SneakyThrows
    @Scheduled(cron="0 0 13 * * ?")
    public void emptyRepository(){
        List<Ticket> pila = ticketsRepository.findAll();
        List<Ticket> toDelete = new ArrayList<>();
        for (Ticket temp : pila) {
            toDelete.add(temp);

        }

            ticketsRepository.deleteAll(toDelete);

    }

}
