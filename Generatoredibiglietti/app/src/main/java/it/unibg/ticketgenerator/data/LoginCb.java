package it.unibg.ticketgenerator.data;

import java.io.Serializable;

public class LoginCb extends BasicCB<LoginCb.I, LoginCb.O> implements Serializable {
    public LoginCb() {
        i = new I();
        o = new O();
    }

    public static class I implements Serializable{
        String username;
        String password;
    }

    public static class O implements Serializable{
        String jwt;
    }


}
