package com.unibg.ticketgenerator.srv.dto;

import lombok.Data;
import java.io.Serializable;

public class LoginCb extends BasicCB<LoginCb.I, LoginCb.O> implements Serializable {
    public LoginCb() {
        i = new I();
        o = new O();
    }

    @Data
    public static class I implements Serializable{
        String username;
        String password;
    }

    @Data
    public static class O implements Serializable{
        String jwt;
    }


}
