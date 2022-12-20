package com.unibg.ticketgenerator.srv.ope;

import com.unibg.ticketgenerator.dao.TicketsRepository;
import com.unibg.ticketgenerator.entities.Ticket;
import com.unibg.ticketgenerator.srv.dto.IncrementaCb;
import com.unibg.ticketgenerator.srv.library.Autenticatore;
import com.unibg.ticketgenerator.srv.library.BasicOPE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service(IncrementaOPE.NAME)
public class IncrementaOPE extends BasicOPE<IncrementaCb.I, IncrementaCb.O> {

	@Autowired
	Autenticatore autenticatore;
	@Autowired
	protected TicketsRepository ticketsRepository;

	public static final String NAME = "IncrementaOPE";

	public IncrementaCb.O execute(IncrementaCb.I i) {
		if(autenticatore.autenticazione(i.getToken())) {
			List<Ticket> pila = ticketsRepository.findAll();
			IncrementaCb.O o = new IncrementaCb.O();
//		operazione nel caso la lista sia vuota
			if (pila.isEmpty()) {
				o.setBiglietto(new Ticket(1, 0, 0));
				ticketsRepository.insert(new Ticket(1, 0,i.getPriority(), 0));
				return o;
			}

//		cerco il max n lista, poi cerco il max id. se per quel n liste è arrivato a 100, aumento nlista e riparto da 0
			int listaAttuale = IncrementaOPE.listaAttuale(pila.stream().map(n -> n.getNlista()).toList());
			List<Ticket> maxLista = pila.stream().filter(n -> n.getNlista() == listaAttuale).toList();

//		int listaAttuale=IncrementaOPE.listaAttuale(pila.stream().map(n -> n.getNlista()).collect(Collectors.toList()));
//		List<TipoA> maxLista=pila.stream().filter(n -> n.getNlista()==listaAttuale).collect(Collectors.toList());

//		operazione nel caso la lista sia oltre il 100, creo una nuova lista incrementando il counter
			if (Collections.max(maxLista).getId() > 99) {
				o.setBiglietto(new Ticket(1, 0,i.getPriority(), listaAttuale + 1));
				ticketsRepository.insert(new Ticket(1, 0,i.getPriority(),listaAttuale + 1));
				return o;
			}

//		operaione nel caso la lista non sia oltre il 100, incremento la lista attuale
			long index = Collections.max(maxLista).getId();
			o.setBiglietto(new Ticket(index + 1, 0,i.getPriority(),listaAttuale));
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