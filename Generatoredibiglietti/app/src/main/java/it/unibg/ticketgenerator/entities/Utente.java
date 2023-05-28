package it.unibg.ticketgenerator.entities;

import org.bson.types.ObjectId;

//@Document(collection = "user")
public class Utente {

//    @Id
    private ObjectId id;

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
    public Utente(long id, String nome, String cognome, String username, String password, String cf) {
        this.id = new ObjectId();
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.password = password;
        this.cf = cf;
    }

    public long getId() {
        return id.getTimestamp();
    }

}