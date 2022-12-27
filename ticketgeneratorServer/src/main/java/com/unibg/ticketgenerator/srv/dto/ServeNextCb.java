package com.unibg.ticketgenerator.srv.dto;

import com.unibg.ticketgenerator.entities.Ticket;
import lombok.Data;

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

    @Data
    public static class I implements Serializable {
        //        @NotNull
        private Long numero;

        private String token;

        public void setNumero(Long numero) {
            this.numero = numero;
        }

    }
    @Data
    public static class O implements Serializable {
        private Ticket biglietto;

    }

}
