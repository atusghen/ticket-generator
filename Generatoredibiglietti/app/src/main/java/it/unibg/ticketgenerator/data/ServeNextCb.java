package it.unibg.ticketgenerator.data;

import it.unibg.ticketgenerator.entities.Ticket;
import java.io.Serializable;

public class ServeNextCb extends BasicCB<ServeNextCb.I,ServeNextCb.O> implements Serializable {

    public ServeNextCb() {
        i = new I();
        o = new O();
    }

    public String toString()
    {
        return "input ->"+i.toString()+" output->"+o.toString();
    }

    public static class I implements Serializable {
        //        @NotNull
        private Long numero;

        private String token;

        public void setNumero(Long numero) {
            this.numero = numero;
        }

    }
    public static class O implements Serializable {
        private Ticket biglietto;

    }

}
