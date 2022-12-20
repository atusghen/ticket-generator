package com.unibg.ticketgenerator.srv.library;

import com.unibg.ticketgenerator.srv.dto.BasicCB;
import com.unibg.ticketgenerator.srv.ope.exceptions.UsernameNotFoundException;
import com.unibg.ticketgenerator.srv.ope.exceptions.WrongPasswordException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public abstract class BasicSRV {

    private static final Logger logger = LoggerFactory.getLogger(BasicSRV.class);

    @Autowired
    protected OPEesecutore oe;

    //qui gestiamo tutte le eccezioni che possono accadere durante la generazione della risposta
    //per adesso si gestiscono solo gli errori di autorizzazione del token
    protected ResponseEntity<BasicCB> execOPE(String opeId, BasicCB cb) {
        try {
            oe.execOPE(opeId, cb);
            return ResponseEntity.ok(cb);
//      eccezioni di autorizzazione
        } catch (ExpiredJwtException e) {
            cb.setOpeResult("error");
            return ResponseEntity.status(403).body(cb);
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
            return ResponseEntity.status(403).body(cb);
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
            return ResponseEntity.status(403).body(cb);
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
            return ResponseEntity.status(403).body(cb);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
            return ResponseEntity.status(403).body(cb);

//      poi mancano le eccezioni di autenticazione (loginOPE)
        } catch (WrongPasswordException e){
            logger.error("Psw dont match: "+e.getMessage());
            cb.setOpeResult("Psw dont match: "+e.getMessage());
            return ResponseEntity.status(401).body(cb);
        } catch (UsernameNotFoundException e){
            logger.error("Username not found: "+e.getMessage());
            cb.setOpeResult("Username not found: "+e.getMessage());
            return ResponseEntity.status(401).body(cb);
        }
//      volendo altre eccezioni di esecuzione
    }

}
