package com.unibg.ticketgenerator.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Utente {

    @Id
    private long id;

    private String username;

    private String password;

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

    private String nome;
    private String cognome;
    private String cf;

    private String type ="U";

    public Utente() {}
    public Utente(long id, String nome, String cognome, String cf) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.cf = cf;
    }
    public Utente(long id) {
        this.id=id;
    }
    public long getId() {
        return id;
    }
    public String getNome()
    {
        return nome;
    }
    public String getCognome(){return cognome;}

    public String getCf() {return cf;}

    public String toString() {return type+ ((getId() >10) ? "0" : "00")+ getId();}

}