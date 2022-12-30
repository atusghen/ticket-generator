package com.unibg.ticketgenerator.srv.ope;

import com.unibg.ticketgenerator.dao.TicketsRepository;
import com.unibg.ticketgenerator.entities.Ticket;
import com.unibg.ticketgenerator.entities.TicketType;
import com.unibg.ticketgenerator.srv.dto.IncrementaCb;
import com.unibg.ticketgenerator.srv.library.Autenticatore;
import com.unibg.ticketgenerator.srv.library.BasicOPE;
import com.unibg.ticketgenerator.srv.ope.exceptions.InvalidPriorityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service(IncrementaOPE.NAME)
@Slf4j
public class IncrementaOPE extends BasicOPE<IncrementaCb.I, IncrementaCb.O> {

	@Autowired
	Autenticatore autenticatore;
	@Autowired
	protected TicketsRepository ticketsRepository;

	public static final String NAME = "IncrementaOPE";

	public IncrementaCb.O execute(IncrementaCb.I i) throws InvalidPriorityException {
		if(autenticatore.autenticazione(i.getToken())) {
			List<Ticket> pila = ticketsRepository.findAll();
			IncrementaCb.O o = new IncrementaCb.O();
			TicketType priority;
			try {
				priority = TicketType.valueOf(i.getPriority());
			}catch(IllegalArgumentException e){
				//eccezione personalizzata per non sovrapporsi con la stessa eccezione del JwtUtils
				throw new InvalidPriorityException(e.getMessage());
			}
//		operazione nel caso la lista sia vuota
			if (pila.isEmpty()) {
				//todo: convertire il campo getPriority (che è un singolo char del tipo "A") in un label completo (vedi enum TicketType)
				Ticket toInsert = new Ticket(1, 0, priority, 0);
				log.info("generating ticket ->"+toInsert.toString());
				o.setBiglietto(toInsert);
				ticketsRepository.insert(toInsert);
				return o;
			}

//		cerco il max n lista, poi cerco il max id. se per quel n liste è arrivato a 100, aumento nlista e riparto da 0
			int listaAttuale = IncrementaOPE.listaAttuale(pila.stream().map(n -> n.getNlista()).toList());
			List<Ticket> maxLista = pila.stream().filter(n -> n.getNlista() == listaAttuale).toList();

//		questo per Jvm version<11
//		int listaAttuale=IncrementaOPE.listaAttuale(pila.stream().map(n -> n.getNlista()).collect(Collectors.toList()));
//		List<TipoA> maxLista=pila.stream().filter(n -> n.getNlista()==listaAttuale).collect(Collectors.toList());

//		operazione nel caso la lista sia oltre il 100, creo una nuova lista incrementando il counter
			if (Collections.max(maxLista).getId() > 99) {
				Ticket toInsert = new Ticket(1, 0,priority, listaAttuale + 1);
				log.info("generating ticket ->"+toInsert.toString());
				o.setBiglietto(toInsert);
				ticketsRepository.insert(toInsert);
				return o;
			}

//		operaione nel caso la lista non sia oltre il 100, incremento la lista attuale
			long index = Collections.max(maxLista).getId();
			Ticket toInsert = new Ticket(index + 1, 0,priority,listaAttuale);
			log.info("generating ticket ->"+toInsert.toString());
			o.setBiglietto(toInsert);
			ticketsRepository.insert(o.getBiglietto());
			return o;
		}else {
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