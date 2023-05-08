package com.unibg.ticketgenerator.srv.ope;

import com.unibg.ticketgenerator.dao.TicketsRepository;
import com.unibg.ticketgenerator.entities.Ticket;
import com.unibg.ticketgenerator.srv.dto.ServeNextCb;
import com.unibg.ticketgenerator.srv.library.BasicOPE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service(ServeNextOPE.NAME)
public class ServeNextOPE extends BasicOPE<ServeNextCb.I, ServeNextCb.O> {

    @Autowired
    protected TicketsRepository ticketsRepository;
    public static final String NAME = "ServeNextOPE";

    public ServeNextCb.O execute(ServeNextCb.I i) {

            ServeNextCb.O out = new ServeNextCb.O();
            long op = i.getNumero();
            List<Ticket> pila = ticketsRepository.findAll();

//		elimino il biglietto assegnato precedentemente
            if (pila.stream().anyMatch(n -> n.getOperatore() == op)) {
                pila.removeIf(n -> {
                    if(n.getOperatore() == op) {
                        ticketsRepository.delete(n);
                        return true;
                    }
                    else
                        return false;
                });
            }

            AtomicReference<Ticket> temp = new AtomicReference<>();
            if(!pila.isEmpty())
                pila.forEach(n -> {
                    if(n.getOperatore() == 0)
//		assegno il primo biglietto disponibile all'operatore oppure controllo la priorità con il successivo
                        if(temp.get() == null || n.comparePriority(temp.get()))
                            temp.set(n);
                });

//		restituisco il biglietto trovato libero se presente
            Ticket insert = temp.get();
                if (insert!=null) {
                    insert.assegnaOp((int) op);
                    out.setBiglietto(insert);
                    ticketsRepository.saveAll(pila);
                    return out;
                }

//		non avendo trovato nessun biglietto libero, restituisco null
            	ticketsRepository.saveAll(pila);
                return null;
            }

}
