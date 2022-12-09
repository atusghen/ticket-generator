package com.unibg.ticketgeneratorClientOperator.dto;

import com.unibg.ticketgeneratorClientOperator.entities.BaseI;
import com.unibg.ticketgeneratorClientOperator.entities.BaseO;
import com.unibg.ticketgeneratorClientOperator.entities.TipoA;
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

        private String nome;
        private String cognome;

        public String cf;


        public void setNumero(Long numero) {
            this.numero = numero;
        }

        public void setNome(String nome){
            this.nome = nome;
        }

        public void setCognome(String cognome){
            this.cognome = cognome;
        }

        public void setCF(String cf){
            this.cf = cf;
        }
    }
    @Data
    public static class O extends BaseO implements Serializable {
        private TipoA biglietto;
    }

}
