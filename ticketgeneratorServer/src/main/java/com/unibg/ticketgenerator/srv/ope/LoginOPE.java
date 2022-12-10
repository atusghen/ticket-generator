package com.unibg.ticketgenerator.srv.ope;

import com.unibg.ticketgenerator.dao.UtenteRepository;
import com.unibg.ticketgenerator.entities.Utente;
import com.unibg.ticketgenerator.srv.dto.LoginCb;
import com.unibg.ticketgenerator.srv.dto.ObjectCb;
import com.unibg.ticketgenerator.srv.library.BasicOPE;
import com.unibg.ticketgenerator.srv.library.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service(LoginOPE.NAME)
public class LoginOPE extends BasicOPE<LoginCb.I, LoginCb.O> {

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    protected UtenteRepository utenteRepository;

    public static final String NAME = "LoginOPE";

    public LoginCb.O execute(LoginCb.I i){
        LoginCb.O out= new LoginCb.O();
        Optional<Utente> utente = utenteRepository.findByUsername(i.getUsername());
        if(utente.isEmpty()){
            out= new LoginCb.O();
            out.setJwt("Username not found");
            return out;
        }
        if(i.getPassword().equals(utente.get().getPassword())) {
            out.setJwt(jwtUtils.generateJwtToken(utente.get()));
        }

        return out;
    }
}


