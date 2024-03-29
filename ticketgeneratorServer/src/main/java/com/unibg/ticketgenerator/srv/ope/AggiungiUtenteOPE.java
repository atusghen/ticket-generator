package com.unibg.ticketgenerator.srv.ope;

import com.unibg.ticketgenerator.dao.UtenteRepository;
import com.unibg.ticketgenerator.entities.Utente;
import com.unibg.ticketgenerator.srv.dto.SignUpCb;
import com.unibg.ticketgenerator.srv.library.BasicOPE;
import com.unibg.ticketgenerator.srv.ope.exceptions.CfAlreadyRegistered;
import com.unibg.ticketgenerator.srv.ope.exceptions.UsernameAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(AggiungiUtenteOPE.NAME)
public class AggiungiUtenteOPE extends BasicOPE<SignUpCb.I, SignUpCb.O> {

    @Autowired
    protected UtenteRepository utenteRepository;

    public static final String NAME = "AggiungiUtenteOPE";

    public SignUpCb.O execute(SignUpCb.I i) throws UsernameAlreadyExistException, CfAlreadyRegistered {
        SignUpCb.O o=new SignUpCb.O();
        List<Utente> pila = utenteRepository.findAll();

        if(utenteRepository.findByCf(i.getCf()).isEmpty()) {
            if(utenteRepository.findByUsername(i.getUsername()).isEmpty()){
                Utente utente = new Utente(i.getNome(), i.getCognome(), i.getUsername(), i.getPassword(), i.getCf());
                utenteRepository.insert(utente);
                o.setUtente(utente);
            }
            else
                throw new UsernameAlreadyExistException("Username gia in uso.");
        }
        else
            throw new CfAlreadyRegistered("Questo codice fiscale è gia registrato.");

        return o;

    }

}