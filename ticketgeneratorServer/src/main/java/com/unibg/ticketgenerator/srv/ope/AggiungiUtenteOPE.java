package com.unibg.ticketgenerator.srv.ope;

import com.unibg.ticketgenerator.dao.UtenteRepository;
import com.unibg.ticketgenerator.entities.Utente;
import com.unibg.ticketgenerator.srv.dto.SignUpCb;
import com.unibg.ticketgenerator.srv.library.BasicOPE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(AggiungiUtenteOPE.NAME)
public class AggiungiUtenteOPE extends BasicOPE<SignUpCb.I, SignUpCb.O> {

    @Autowired
    protected UtenteRepository utenteRepository;

    public static final String NAME = "AggiungiUtenteOPE";

    public SignUpCb.O execute(SignUpCb.I i) {

        List<Utente> pila=utenteRepository.findAll();
        SignUpCb.O o=new SignUpCb.O();
//		operazione nel caso la lista sia vuota

        o.setUtente(new Utente(pila.size(), i.getNome(),i.getCognome(), i.getCf()));
        utenteRepository.insert(new Utente(pila.size(), i.getNome(),i.getCognome(), i.getCf()));
        return o;

    }

}