package com.unibg.ticketgeneratorClientOperator.dto;

import java.io.Serializable;

public abstract class BasicCB<I,O> implements Serializable {
    private String reqId;
    public String getReqId() {
        return reqId;
    }
    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

//    questo è per indicare l'esito dell'esecuzione dell'OPE che per ora non ci serve
//    private OPEResult r = new OPEResult();
//    public OPEResult getR() {
//        return r;
//    }
//    public void setR(OPEResult r) {
//        this.r = r;
//    }
//
    protected O o;
    public O getO (){
        return o;
    }
    public void setO (O o){this.o = o;}

    protected I i;
    public I getI () {return i;}
    public void setI (I i){
        this.i = i;
    }
}
