package com.unibg.ticketgenerator.srv.dto;

import com.unibg.ticketgenerator.entities.Ticket;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

public class AllStackCb extends BasicCB<AllStackCb.I,AllStackCb.O> implements Serializable {

    public AllStackCb() {
        i = new I();
        o = new O();
    }
    @Data
    public static class I implements Serializable {

        private String type;
        private String token;
    }
    @Data
    public static class O implements Serializable {
        private List<Ticket> output;
    }
}
