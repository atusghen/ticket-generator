package com.unibg.ticketgenerator.srv.library;

import com.unibg.ticketgenerator.srv.dto.BasicCB;
import com.unibg.ticketgenerator.srv.ope.exceptions.*;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@Slf4j
public abstract class BasicSRV {

    @Autowired
    protected OPEesecutore oe;

    //qui gestiamo tutte le eccezioni che possono accadere durante la generazione della risposta
    //per adesso si gestiscono solo gli errori di autorizzazione del token
    protected ResponseEntity<BasicCB> execOPE(String opeId, BasicCB cb) {
        try {
            oe.execOPE(opeId, cb);
            if(cb.getOpeResult()==null) {
                cb.setOpeResult("OK");
                return ResponseEntity.ok(cb);
            }else
                return ResponseEntity.status(500).body(cb);

//       eccezioni di esecuzione dei service
        } catch (InvalidPriorityException e){
            log.error("Input priority not valid: "+e.getMessage());
            cb.setOpeResult("Input priority not valid: "+e.getMessage());
            return ResponseEntity.status(500).body(cb);
//      eccezioni di autorizzazione
        } catch (ExpiredJwtException e) {
            cb.setOpeResult("error");
            return ResponseEntity.status(403).body(cb);
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
            return ResponseEntity.status(403).body(cb);
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
            return ResponseEntity.status(403).body(cb);
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
            return ResponseEntity.status(403).body(cb);
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
            return ResponseEntity.status(403).body(cb);

//      poi mancano le eccezioni di autenticazione (loginOPE)
        } catch (WrongPasswordException e){
            log.error("Psw dont match: "+e.getMessage());
            cb.setOpeResult("Psw dont match: "+e.getMessage());
            return ResponseEntity.status(401).body(cb);
        } catch (UsernameNotFoundException e) {
            log.error("Username not found: " + e.getMessage());
            cb.setOpeResult("Username not found: " + e.getMessage());
            return ResponseEntity.status(401).body(cb);
        } catch (UsernameAlreadyExistException e) {
            log.error("Username not available: " + e.getMessage());
            cb.setOpeResult("Username not available: " + e.getMessage());
            return ResponseEntity.status(401).body(cb);
        } catch (CfAlreadyRegistered e) {
            log.error("Cf already registered: " + e.getMessage());
            cb.setOpeResult("Cf already registered: " + e.getMessage());
            return ResponseEntity.status(401).body(cb);
        }
    }

}
