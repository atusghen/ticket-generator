package com.unibg.ticketgenerator.srv.dto;

import com.unibg.ticketgenerator.entities.Utente;
import lombok.Data;

import java.io.Serializable;

public class SignUpCb extends BasicCB<SignUpCb.I,SignUpCb.O> implements Serializable {

    public SignUpCb() {
        i = new I();
        o = new O();
    }

    @Data
    public static class I implements Serializable{
        private String nome;

        private String cognome;

        private String username;

        private String password;

        private String cf;
    }

    @Data
    public static class O implements Serializable{
        private Utente utente;
    }

}
