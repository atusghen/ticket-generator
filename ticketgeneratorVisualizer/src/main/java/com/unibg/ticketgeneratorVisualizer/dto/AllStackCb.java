package com.unibg.ticketgeneratorVisualizer.dto;

import com.unibg.ticketgeneratorVisualizer.entities.BaseI;
import com.unibg.ticketgeneratorVisualizer.entities.BaseO;
import com.unibg.ticketgeneratorVisualizer.entities.Ticket;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

public class AllStackCb extends BasicCB<AllStackCb.I,AllStackCb.O> implements Serializable {

    public AllStackCb() {
        i = new I();
        o = new O();
    }
    @Data
    public static class I extends BaseI implements Serializable {
        private String type;
    }
    @Data
    public static class O extends BaseO implements Serializable {
        private List<Ticket> output;
    }
}
