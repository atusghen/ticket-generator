package com.unibg.ticketgenerator.srv.dto;

import com.auth0.jwt.JWT;
import com.unibg.ticketgenerator.entities.BaseI;
import com.unibg.ticketgenerator.entities.BaseO;
import com.unibg.ticketgenerator.entities.User;
import lombok.Data;

import java.io.Serializable;

public class UserCb extends BasicCB<UserCb.I, UserCb.O> {

    public UserCb(){
        i = new I();
        o = new O();
    }

    @Data
    public static class I extends BaseI implements Serializable {
        User user;
    }

    @Data
    public static class O extends BaseO implements Serializable{
        JWT token;
    }
}
