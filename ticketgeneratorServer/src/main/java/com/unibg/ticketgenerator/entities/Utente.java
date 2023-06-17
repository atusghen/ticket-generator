package com.unibg.ticketgenerator.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class Utente {
    @Id
    private ObjectId _id;
    private String nome;

    private String cognome;

    private String username;

    private String password;

    private String cf;

    private Ticket ticket;
    private boolean AssignedTicket;

    public Utente(String nome, String cognome, String username, String password, String cf) {
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.password = password;
        this.cf = cf;
    }


    public String getUsername() {
        return username;
    }

    public Ticket getTicket(){
        return ticket;
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



    public void AddTicket(Ticket t){
        if(!this.username.equalsIgnoreCase("visualizzatore")) {
            this.AssignedTicket = true;
            this.ticket=t;
        }
    }

    public void DeleteTicket(){
        this.ticket=null;
        this.AssignedTicket=false;
    }

    public boolean isAssignedTicket(){
        return AssignedTicket;
    }

}