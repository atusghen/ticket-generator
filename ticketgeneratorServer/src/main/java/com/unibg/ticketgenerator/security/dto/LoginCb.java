package com.unibg.ticketgenerator.security.dto;

import com.unibg.ticketgenerator.entities.BaseI;
import com.unibg.ticketgenerator.entities.BaseO;
import com.unibg.ticketgenerator.srv.dto.BasicCB;
import lombok.Data;
import java.io.Serializable;

public class LoginCb extends BasicCB<LoginCb.I, LoginCb.O> implements Serializable {

    public LoginCb() {
        i = new I();
        o = new O();
    }

    @Data
    public static class I extends BaseI implements Serializable {

        private String username;
        private String password;

    }

    @Data
    public static class O extends BaseO implements Serializable {
        JwtResponse out;
    }
}