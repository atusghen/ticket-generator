package it.unibg.ticketgenerator.data;

import it.unibg.ticketgenerator.entities.Utente;
import java.io.Serializable;

public class SignUpCb extends BasicCB<SignUpCb.I,SignUpCb.O> implements Serializable {

    public SignUpCb() {
        i = new I();
        o = new O();
    }

    public static class I implements Serializable{
        private String nome;

        private String cognome;

        private String username;

        private String password;

        private String cf;
    }

    public static class O implements Serializable{
        private Utente utente;
    }

}
