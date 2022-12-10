package com.unibg.ticketgenerator.srv.dto;

import com.unibg.ticketgenerator.entities.BaseI;
import com.unibg.ticketgenerator.entities.BaseO;
import com.unibg.ticketgenerator.entities.TipoA;
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
    public static class I extends BaseI implements Serializable {
        //        @NotNull
        private Long numero;

        private String token;

        public void setNumero(Long numero) {
            this.numero = numero;
        }

    }
    @Data
    public static class O extends BaseO implements Serializable {
        private TipoA biglietto;

    }

}
