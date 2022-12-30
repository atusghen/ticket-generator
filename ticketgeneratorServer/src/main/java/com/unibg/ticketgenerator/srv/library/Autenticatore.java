package com.unibg.ticketgenerator.srv.library;

import com.unibg.ticketgenerator.dao.UtenteRepository;
import com.unibg.ticketgenerator.entities.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class Autenticatore {
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UtenteRepository utenteRepository;

    public boolean autenticazione(String token)// throws ExpiredJwtException
    {
        //se il token c'è, corrisponde ad un nome utente nel db e la sua exp date è > del momento attuale allora autorizza
        if(token!=null){
            String username = jwtUtils.getUserNameFromJwtToken(token);
            Optional<Utente> utente = utenteRepository.findByUsername(username);
            if(!utente.isEmpty()){
                Date attuale = new Date();
                Date exp = jwtUtils.getExpirationFromJwtToken(token);
                if(attuale.before(exp))
                    {
                        return true;
                        //autorize
                    }else{
                    return false;
                    //token expired
                }
            }else{
                return false;
                //username not found, token invalid, is the secret code leaked?
            }
        }else{
            return false;
            //token null, then not auth or bad request
        }
    }
}
