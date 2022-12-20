package com.unibg.ticketgenerator.srv.ope;

import com.unibg.ticketgenerator.dao.TicketsRepository;
import com.unibg.ticketgenerator.entities.Ticket;
import com.unibg.ticketgenerator.srv.dto.ServeNextCb;
import com.unibg.ticketgenerator.srv.library.Autenticatore;
import com.unibg.ticketgenerator.srv.library.BasicOPE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service(ServeNextOPE.NAME)
public class ServeNextOPE extends BasicOPE<ServeNextCb.I, ServeNextCb.O> {

    @Autowired
    Autenticatore autenticatore;
    @Autowired
    protected TicketsRepository ticketsRepository;
    public static final String NAME = "ServeNextOPE";

    public ServeNextCb.O execute(ServeNextCb.I i) {
        if (autenticatore.autenticazione(i.getToken())) {
            ServeNextCb.O out = new ServeNextCb.O();
            long op = i.getNumero();
            List<Ticket> pila = ticketsRepository.findAll();

//      qui cerco l'oggetto/oggetti da cancellare. da sostituire con un'operazione di reduce/filter
            List<Ticket> toDelete = new ArrayList<>();
            for (Ticket temp : pila) {
                if (temp.getOperatore() == op) {
                    toDelete.add(temp);
                }
            }

//		se trovo un biglietto con un operatore già assegnato, lo rimuovo
            if (pila.stream().anyMatch(n -> n.getOperatore() == op)) {
                pila.removeIf(n -> n.getOperatore() == op);
                ticketsRepository.deleteAll(toDelete);
            }
//		assegno il primo biglietto all'operatore disponibile
            if (!pila.isEmpty()) {
                Iterator<Ticket> it = pila.iterator();
                while (it.hasNext()) {
                    Ticket temp = it.next();
                    if (temp.getOperatore() == 0) {
                        temp.assegnaOp((int) op);
//		restituisco il biglietto trovato libero
                        out.setBiglietto(temp);
                        ticketsRepository.saveAll(pila);
                        return out;
                    }
                }
                ticketsRepository.saveAll(pila);
//		non avendo trovato nessun biglietto libero, restituisco null
//		pila.forEach(System.out::println);
                return null;
            }

            return null;


    }else{
        return null;
    }
        }

}
