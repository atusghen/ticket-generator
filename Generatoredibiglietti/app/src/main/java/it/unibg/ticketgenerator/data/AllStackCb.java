package it.unibg.ticketgenerator.data;

import it.unibg.ticketgenerator.entities.Ticket;

import java.io.Serializable;
import java.util.List;

public class AllStackCb extends BasicCB<AllStackCb.I,AllStackCb.O> implements Serializable{

    public static final String NAME = "AllStackOPE";

    public AllStackCb() {
        i = new I();
        o = new O();
    }
    public static class I implements Serializable {
        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

    public static class O implements Serializable {
        private List<Ticket> output;

        public List<Ticket> getOutput() {
            return output;
        }

        public void setOutput(List<Ticket> output) {
            this.output = output;
        }
    }
}
