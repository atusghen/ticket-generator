package com.unibg.ticketgeneratorClientOperator.dto;

import com.unibg.ticketgeneratorClientOperator.entities.BaseI;
import com.unibg.ticketgeneratorClientOperator.entities.BaseO;
import com.unibg.ticketgeneratorClientOperator.entities.TipoA;
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
