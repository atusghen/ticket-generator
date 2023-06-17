package com.unibg.ticketgenerator.srv.ope;

import com.unibg.ticketgenerator.dao.TicketsRepository;
import com.unibg.ticketgenerator.dao.QueueRepository;
import com.unibg.ticketgenerator.dao.UtenteRepository;
import com.unibg.ticketgenerator.entities.Ticket;
import com.unibg.ticketgenerator.entities.Queue;
import com.unibg.ticketgenerator.entities.TicketType;
import com.unibg.ticketgenerator.entities.Utente;
import com.unibg.ticketgenerator.srv.dto.IncrementaCb;
import com.unibg.ticketgenerator.srv.library.Autenticatore;
import com.unibg.ticketgenerator.srv.library.BasicOPE;
import com.unibg.ticketgenerator.srv.library.JwtUtils;
import com.unibg.ticketgenerator.srv.ope.exceptions.InvalidPriorityException;
import com.unibg.ticketgenerator.srv.ope.exceptions.TicketAlreadyRegistered;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service(IncrementaOPE.NAME)
@Slf4j
public class IncrementaOPE extends BasicOPE<IncrementaCb.I, IncrementaCb.O> {

    @Autowired
    Autenticatore autenticatore;
    @Autowired
    protected TicketsRepository ticketsRepository;
    @Autowired
    protected QueueRepository queueRepository;
    @Autowired
    protected UtenteRepository utenteRepository;
    @Autowired
    JwtUtils JwtUtils;

    public static final String NAME = "IncrementaOPE";

    public IncrementaCb.O execute(IncrementaCb.I i) throws InvalidPriorityException ,TicketAlreadyRegistered{
        if(autenticatore.autenticazione(i.getToken())) {
            Optional<Utente> utente = utenteRepository.findByUsername(JwtUtils.getUserNameFromJwtTokenNoExpired(i.getToken()));
            log.info("qualcosa: " + utente.get().isAssignedTicket());
            List<Ticket> pila = ticketsRepository.findAll();
            List<Queue> Queue = queueRepository.findAll();
            Queue queue = Queue.iterator().next();
            IncrementaCb.O o = new IncrementaCb.O();
            o.setTotQueueTime(queue.getLTotMeanTime().toString());
            TicketType priority;
            try {
                priority = TicketType.valueOf(i.getPriority());
            } catch (IllegalArgumentException e) {
                //eccezione personalizzata per non sovrapporsi con la stessa eccezione del JwtUtils
                throw new InvalidPriorityException(e.getMessage());
            }
            boolean ass=utente.get().isAssignedTicket();
            log.info(String.valueOf(ass));
            if (!ass) {

//		operazione nel caso la lista sia vuota
                if (queue.getDailyCounter()==0) {
                    queue.IncreaseCounter(priority);
                    //todo: convertire il campo getPriority (che è un singolo char del tipo "A") in un label completo (vedi enum TicketType)
                    Ticket toInsert = new Ticket(1, 0, priority, 0);
                    utente.get().AddTicket(toInsert);
                    log.info("Ticket1 " + toInsert + ", associato all'utente: " + utente.get() + "   " + utente.get().isAssignedTicket());
                    log.info("generating ticket ->" + toInsert.toString());
                    o.setBiglietto(toInsert);
                    ticketsRepository.insert(toInsert);
                    utenteRepository.save(utente.get());
                    queueRepository.save(queue);
                    return o;
                }

//			cerco il max n lista, poi cerco il max id. se per quel n liste è arrivato a 100, aumento nlista e riparto da 0

            //    int listaAttuale = IncrementaOPE.listaAttuale(pila.stream().map(n -> n.getNlista()).toList());
                //    List<Ticket> maxLista = pila.stream().filter(n -> n.getNlista() == listaAttuale).toList();

//			questo per Jvm version<11
//			int listaAttuale=IncrementaOPE.listaAttuale(pila.stream().map(n -> n.getNlista()).collect(Collectors.toList()));
//			List<TipoA> maxLista=pila.stream().filter(n -> n.getNlista()==listaAttuale).collect(Collectors.toList());

//			operazione nel caso la lista sia oltre il 100, creo una nuova lista incrementando il counter
             /*   if (Collections.max(maxLista).getTicketNumber() > 99) {
                    queue.IncreaseCounter(priority);
                    Ticket toInsert = new Ticket(1, 0, priority, listaAttuale + 1);
                    log.info("generating ticket ->" + toInsert.toString());
                    utente.get().AddTicket(toInsert);
                    log.info("Ticket2 " + toInsert + ", associato all'utente: " + utente.get() + "   " + utente.get().isAssignedTicket());
                    log.info("generating ticket ->" + toInsert.toString());
                    o.setBiglietto(toInsert);
                    ticketsRepository.insert(toInsert);
                    utenteRepository.save(utente.get());
                    queueRepository.save(queue);
                    return o;
                }*/

//			operaione nel caso la lista non sia oltre il 100, incremento la lista attuale
                queue.IncreaseCounter(priority);
                //long index = Collections.max(maxLista).getTicketNumber();
                long index=queue.getDailyCounter();
                int ListaAttuale=queue.getDailyCounter()+1;
                Ticket toInsert = new Ticket(index + 1, 0, priority, ListaAttuale);
                log.info("generating ticket ->" + toInsert.toString());
                utente.get().AddTicket(toInsert);
                log.info("Ticket3 " + toInsert + ", associato all'utente: " + utente.get().getUsername() + "   " + utente.get().isAssignedTicket());
                log.info("generating ticket ->" + toInsert.toString());
                o.setBiglietto(toInsert);
                ticketsRepository.insert(o.getBiglietto());
                utenteRepository.save(utente.get());
                queueRepository.save(queue);
                return o;
            }else
                throw new TicketAlreadyRegistered("Biglietto già assegnato all'utente: " + JwtUtils.getUserNameFromJwtTokenNoExpired(i.getToken()));

        }else{
            //autenticazione fallita
            //throw new Exception("Autenticazione fallita");
            return null;
        }

    }

    public static int listaAttuale(List<Integer> nListe)
    {
            return Collections.max(nListe);
    }


}