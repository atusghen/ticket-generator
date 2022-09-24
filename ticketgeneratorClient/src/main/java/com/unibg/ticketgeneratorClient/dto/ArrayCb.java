package com.unibg.ticketgeneratorClient.dto;

import com.unibg.ticketgeneratorClient.entities.BaseI;
import com.unibg.ticketgeneratorClient.entities.BaseO;
import com.unibg.ticketgeneratorClient.entities.TipoA;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

public class ArrayCb extends BasicCB<ArrayCb.I,ArrayCb.O> implements Serializable {

    public ArrayCb() {
        i = new I();
        o = new O();
    }
    @Data
    public static class I extends BaseI implements Serializable {
        private int operatore;
    }
    @Data
    public static class O extends BaseO implements Serializable {
        private List<TipoA> output;
    }
}
