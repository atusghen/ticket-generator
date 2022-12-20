package com.unibg.ticketgeneratorClient.dto;

import com.unibg.ticketgeneratorClient.entities.BaseI;
import com.unibg.ticketgeneratorClient.entities.BaseO;
import com.unibg.ticketgeneratorClient.entities.Ticket;
import lombok.Data;

import java.io.Serializable;

public class ObjectCb extends BasicCB<ObjectCb.I,ObjectCb.O> implements Serializable {

    public ObjectCb() {
       i = new I();
       o = new O();
    }

    public String toString()
    {
        return "input ->"+i.toString()+" output->"+o.toString();
    }

    @Data
    public static class I extends BaseI implements Serializable {
        //        @NotNull
        private Long numero;
        public void setNumero(Long numero) {
            this.numero = numero;
        }
    }
    @Data
    public static class O extends BaseO implements Serializable {
        private Ticket biglietto;
    }

}
