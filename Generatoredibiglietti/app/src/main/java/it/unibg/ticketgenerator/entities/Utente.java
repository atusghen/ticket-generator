package it.unibg.ticketgenerator.entities;
public class Utente {

    private String nome;

    private String cognome;

    private String username;

    private String password;

    private String cf;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public Utente() {}
    public Utente(String nome, String cognome, String username, String password, String cf) {
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.password = password;
        this.cf = cf;
    }

}