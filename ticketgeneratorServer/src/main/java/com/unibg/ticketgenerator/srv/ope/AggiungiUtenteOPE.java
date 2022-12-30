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
        SignUpCb.O o=new SignUpCb.O();
        List<Utente> pila = utenteRepository.findAll();

        if(utenteRepository.findByCf(i.getCf()).isEmpty()) {
            Utente utente = new Utente(pila.size(), i.getNome(), i.getCognome(), i.getUsername(), i.getPassword(), i.getCf());
            utenteRepository.insert(utente);
            o.setUtente(utente);
        }

        return o;

    }

}