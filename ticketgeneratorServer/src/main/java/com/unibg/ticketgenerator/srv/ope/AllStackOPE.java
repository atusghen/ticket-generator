package com.unibg.ticketgenerator.srv.ope;

import com.unibg.ticketgenerator.dao.TipoARepository;
import com.unibg.ticketgenerator.entities.TipoA;
import com.unibg.ticketgenerator.srv.dto.AllStackCb;
import com.unibg.ticketgenerator.srv.library.Autenticatore;
import com.unibg.ticketgenerator.srv.library.BasicOPE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(AllStackOPE.NAME)
public class AllStackOPE extends BasicOPE<AllStackCb.I, AllStackCb.O> {

    @Autowired
    Autenticatore autenticatore;
    @Autowired
    protected TipoARepository tipoARepository;

    public static final String NAME = "AllStackOPE";
    public AllStackCb.O execute(AllStackCb.I i) {

        if(autenticatore.autenticazione(i.getToken())) {
            List<TipoA> pila = tipoARepository.findAll();
            AllStackCb.O o = new AllStackCb.O();
            o.setOutput(pila);
            return o;
        }else{
            //errore di auth
            return null;
        }
    }
}

