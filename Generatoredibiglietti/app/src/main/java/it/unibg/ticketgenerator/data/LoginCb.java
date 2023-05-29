package it.unibg.ticketgenerator.data;

import java.io.Serializable;

public class LoginCb extends BasicCB<LoginCb.I, LoginCb.O> implements Serializable {
    public static final String NAME = "LoginOPE";

    public LoginCb() {
        i = new I();
        o = new O();
    }

    public static class I implements Serializable{
        String username;
        String password;

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class O implements Serializable{
        String jwt;

        public String getJwt() {
            return jwt;
        }
    }


}
