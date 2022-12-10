package com.unibg.ticketgenerator.srv.dto;

import com.unibg.ticketgenerator.entities.BaseI;
import com.unibg.ticketgenerator.entities.BaseO;
import lombok.Data;
import java.io.Serializable;

public class LoginCb extends BasicCB<LoginCb.I, LoginCb.O> implements Serializable {
    public LoginCb() {
        i = new I();
        o = new O();
    }

    @Data
    public static class I extends BaseI implements Serializable{
        String username;
        String password;
    }

    @Data
    public static class O extends BaseO implements Serializable{
        String jwt;
    }


}
