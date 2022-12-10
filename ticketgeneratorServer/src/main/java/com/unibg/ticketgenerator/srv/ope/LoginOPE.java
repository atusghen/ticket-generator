package com.unibg.ticketgenerator.srv.ope;

import com.auth0.jwt.JWT;
import com.unibg.ticketgenerator.dao.TipoARepository;
import com.unibg.ticketgenerator.dao.UtenteRepository;
import com.unibg.ticketgenerator.entities.Utente;
import com.unibg.ticketgenerator.srv.dto.LoginCb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service(LoginOPE.NAME)
public class LoginOPE {

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
            generateJwt(out);
        }


        return out;
    }

    private void generateJwt(LoginCb.O out){
        JWT jwt = new JWT();
        JWT.create().
    }
}


