package it.unibg.ticketgenerator.data;

import org.bson.types.ObjectId;

import it.unibg.ticketgenerator.entities.Utente;
import java.io.Serializable;

public class SignUpCb extends BasicCB<SignUpCb.I,SignUpCb.O> implements Serializable {


    public static final String NAME = "AggiungiUtenteOPE";

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

        public void setNome(String nome) {
            this.nome = nome;
        }

        public void setCognome(String cognome) {
            this.cognome = cognome;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setCf(String cf) {
            this.cf = cf;
        }
    }

    public static class O implements Serializable{
        private Utente utente;
    }

}
