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

//
//Questo codice controlla se nella pila "pila" esiste almeno un oggetto che ha un operatore uguale a op
// usando la funzione anyMatch() della classe Stream di Java. Se c'è, allora viene rimossa dalla pila
// l'intera lista di oggetti che soddisfano questa condizione e vengono cancellati dal repository tramite
// la funzione deleteAll(). In altre parole, questo codice rimuove dalla pila e dal repository tutti i biglietti
// associati all'operatore specificato op.
            if (pila.stream().anyMatch(n -> n.getOperatore() == op)) {
                pila.removeIf(n -> n.getOperatore() == op);
                ticketsRepository.deleteAll(toDelete);
            }
//		assegno il primo biglietto all'operatore disponibile
            if (!pila.isEmpty()) {
                Iterator<Ticket> it = pila.iterator();
                Ticket insert = null;
                while (it.hasNext()) {
                    Ticket temp = it.next();
                    if (temp.getOperatore() == 0 & insert==null) {
//                        temp.assegnaOp((int) op);
//		restituisco il biglietto trovato libero
                        insert=temp;
   //                     out.setBiglietto(temp);
 //                       ticketsRepository.saveAll(pila);
   //                     return out;
                    }else if(temp.getOperatore() == 0 ){
                        if(temp.comparePriority(insert)) {
                            insert = temp;
                        }
                    }
                }
                insert.assegnaOp((int) op);
                if (insert==null) {
                    out.setBiglietto(insert);
                    ticketsRepository.saveAll(pila);
                    return out;
                }

//		non avendo trovato nessun biglietto libero, restituisco null
//		pila.forEach(System.out::println);
            	ticketsRepository.saveAll(pila);
                return null;
            }

            return null;


    }else{
            //autenticazione fallita
        return null;
    }
        }

}
