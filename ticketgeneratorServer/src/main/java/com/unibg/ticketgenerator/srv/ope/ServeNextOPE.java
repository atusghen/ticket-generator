package com.unibg.ticketgenerator.srv.ope;

import com.unibg.ticketgenerator.dao.TicketsRepository;
import com.unibg.ticketgenerator.dao.UtenteRepository;
import com.unibg.ticketgenerator.dao.QueueRepository;
import com.unibg.ticketgenerator.entities.Ticket;
import com.unibg.ticketgenerator.entities.Queue;
import com.unibg.ticketgenerator.entities.Utente;
import com.unibg.ticketgenerator.srv.dto.ServeNextCb;
import com.unibg.ticketgenerator.srv.library.Autenticatore;
import com.unibg.ticketgenerator.srv.library.BasicOPE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service(ServeNextOPE.NAME)
@Slf4j
public class ServeNextOPE extends BasicOPE<ServeNextCb.I, ServeNextCb.O> {

    @Autowired
    protected QueueRepository queueRepository;
    @Autowired
    protected UtenteRepository utenteRepository;
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
            List<Queue> Queue=queueRepository.findAll();
            Queue queue= Queue.iterator().next();

//      qui cerco l'oggetto/oggetti da cancellare. da sostituire con un'operazione di reduce/filter
            List<Ticket> toDelete = new ArrayList<>();
            for (Ticket temp : pila) {
                if (temp.getOperatore() == op) {
                    toDelete.add(temp);
                    Optional<Utente> utente = utenteRepository.findByTicket(temp);
                    if(temp.equals(utenteRepository.findByTicket(temp))){
                        utente.get().DeleteTicket();
                        utenteRepository.save(utente.get());
                    }
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

                if (insert!=null) {
                    insert.setStartService();
                    log.info("Coda creata "+queue.toString()+" "+insert.getPriority()+" "+insert.getQueueTime()+" "+insert.getCreationTime()+" "+insert.getStartService());
                    queue.UpdateMeanTime(insert.getPriority(), insert.getQueueTime());
                    queue.DecreseCounter(insert.getPriority());
                    log.info("Coda  "+queue.getMeanTimeA());
                    queueRepository.save(queue);
                    insert.assegnaOp((int) op);
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