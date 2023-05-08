package com.unibg.ticketgenerator.srv.ope;

import com.unibg.ticketgenerator.dao.TicketsRepository;
import com.unibg.ticketgenerator.entities.Ticket;
import com.unibg.ticketgenerator.srv.dto.AllStackCb;
import com.unibg.ticketgenerator.srv.library.BasicOPE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(AllStackOPE.NAME)
public class AllStackOPE extends BasicOPE<AllStackCb.I, AllStackCb.O> {

//    @Autowired
//    Autenticatore autenticatore;
    @Autowired
    protected TicketsRepository ticketsRepository;

    public static final String NAME = "AllStackOPE";
    public AllStackCb.O execute(AllStackCb.I i) //throws ExpiredJwtException
    {
//        if(autenticatore.autenticazione(i.getToken())) {
            List<Ticket> pila = ticketsRepository.findAll();
            AllStackCb.O o = new AllStackCb.O();
            o.setOutput(pila);
            return o;
//        }else{
//            //errore di auth
//            return null;
//        }
    }
}

