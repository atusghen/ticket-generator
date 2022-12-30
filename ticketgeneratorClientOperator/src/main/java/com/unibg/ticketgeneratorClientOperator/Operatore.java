package com.unibg.ticketgeneratorClientOperator;

import com.unibg.ticketgeneratorClientOperator.dto.CheckOperatorCb;
import com.unibg.ticketgeneratorClientOperator.dto.ObjectCb;
import com.unibg.ticketgeneratorClientOperator.dto.SignUpCb;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestOperations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Operatore {

    public static void serviSuccessivo()
    {
        RestTemplateBuilder builder=new RestTemplateBuilder();
        RestOperations restTemplate = builder.build();

        int id=Operatore.getIdOperatore();

        ObjectCb.I in=new ObjectCb.I();
        in.setNumero((long) id);
        ObjectCb cb=new ObjectCb();
        cb.setI(in);

        ObjectCb r = restTemplate.postForObject("http://localhost:8080/ServeNextOPE",cb,ObjectCb.class);

        if(r!=null)
            System.out.println("l'operatore "+r.getI().getNumero()+" sta servendo il biglietto "+r.getO().getBiglietto().toString());
        else System.out.println("nessuno in coda");
    }

    public static void aggiungiUtente() throws IOException {
        RestTemplateBuilder builder=new RestTemplateBuilder();
        RestOperations restTemplate = builder.build();

        //int id=Operatore.getIdOperatore();
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        System.out.println("inserisci il nome: ");
        String nome = br.readLine();

        System.out.println("inserisci il cognome: ");
        String cognome = br.readLine();

        System.out.println("inserisci lo username: ");
        String username = br.readLine();

        System.out.println("inserisci la password: ");
        String password = br.readLine();

        System.out.println("inserisci il codice fiscale: ");
        String cf = br.readLine();

        SignUpCb.I in=new SignUpCb.I();
        in.setNome(nome);
        in.setCognome(cognome);
        in.setUsername(username);
        in.setPassword(password);
        in.setCf(cf);
        SignUpCb cb=new SignUpCb();
        cb.setI(in);

        SignUpCb r = restTemplate.postForObject("http://localhost:8080/AggiungiUtenteOPE",cb,SignUpCb.class);

        if(r != null)
            System.out.println("Registrazione avvenuta");
        else System.out.println("Registrazione fallita");
    }

    private static int getIdOperatore() {
        String input = null;
        do {

            try {
                InputStreamReader isr = new InputStreamReader(System.in);
                BufferedReader br = new BufferedReader(isr);
                System.out.println("inserisci un numero ->");
                input = br.readLine();
            } catch (IOException e) {
                System.out.println("non hai inserito niente");
            }
        } while (Integer.parseInt(input) <0 && Integer.parseInt(input)>10);
        return Integer.parseInt(input);
    }

    public static void interfaccia()
    {
        String input = null;
        do {

            try {
                InputStreamReader isr = new InputStreamReader(System.in);
                BufferedReader br = new BufferedReader(isr);
                System.out.println("vuoi passare al successivo? 0-si 1-no");
                input = br.readLine();
            } catch (IOException e) {
                System.out.println("non hai inserito niente");
            }
            if(Integer.parseInt(input)==0) return;
        } while (Integer.parseInt(input) != 0);
    }

    public static CheckOperatorCb login() {
        String username = "admin";
        String password = "admin";
        RestTemplateBuilder builder=new RestTemplateBuilder();
        RestOperations restTemplate = builder.build();

        CheckOperatorCb.I in=new CheckOperatorCb.I();
        in.setUsername(username);
        in.setPassword(password);
        CheckOperatorCb cb=new CheckOperatorCb();
        cb.setI(in);

        CheckOperatorCb r = restTemplate.postForObject("http://localhost:8080/CheckOperatorOPE",cb,CheckOperatorCb.class);

        if(r != null)
            System.out.println("Operatore riconosciuto");
        else System.out.println("Operatore sconosciuto");

        return r;
    }

//    protected ResponseEntity<ObjectCb> execOPE() {
//        RestTemplateBuilder builder=new RestTemplateBuilder();
//        RestOperations restTemplate = builder.build();
//
//        ObjectCb.I in=new ObjectCb.I();
//        in.setNumero((long) id);
//        ObjectCb cb=new ObjectCb();
//        cb.setI(in);
//
//        return restTemplate.postForEntity("http://localhost:8080/serveNext",cb,ObjectCb.class);
////        return restTemplate.getForEntity(
////                "http://localhost:8080/serveNext?op="+Integer.toString(id),
////                TipoA.class);
//    }


}
