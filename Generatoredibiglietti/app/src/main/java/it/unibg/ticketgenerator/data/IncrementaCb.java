package it.unibg.ticketgenerator.data;

import it.unibg.ticketgenerator.entities.Ticket;
import java.io.Serializable;

public class IncrementaCb extends BasicCB<IncrementaCb.I,IncrementaCb.O> implements Serializable {

    public IncrementaCb() {
       i = new I();
       o = new O();
    }

    public String toString()
    {
        return "input ->"+i.toString()+" output->"+o.toString();
    }


    public static class I implements Serializable {
        //        @NotNull


        private String priority;

        private String token;
        public String getPriority() {
            return  priority;
        }


    }

    public static class O implements Serializable {
        private Ticket biglietto;

    }

}
