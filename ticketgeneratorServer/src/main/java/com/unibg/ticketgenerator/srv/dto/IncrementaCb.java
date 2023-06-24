package com.unibg.ticketgenerator.srv.dto;

import com.unibg.ticketgenerator.entities.Ticket;
import lombok.Data;

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

    @Data
    public static class I implements Serializable {
        //        @NotNull


        private String priority;

        private String token;
        public String getPriority() {
            return  priority;
        }


    }
    @Data
    public static class O implements Serializable {
        private Ticket biglietto;
        private String  UserQueue;

    }

}
