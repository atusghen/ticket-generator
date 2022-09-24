package com.unibg.ticketgeneratorClient.gui;

import com.unibg.ticketgeneratorClient.dto.ObjectCb;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestOperations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utilità {

    public static void invioRichiesta()
    {
        RestTemplateBuilder builder=new RestTemplateBuilder();
        RestOperations restTemplate = builder.build();

        ObjectCb.I in=new ObjectCb.I();
//        in.setNumero((long) id);
        ObjectCb cb=new ObjectCb();
        cb.setI(in);

        ObjectCb r = restTemplate.postForObject("http://localhost:8080/IncrementaOPE",cb,ObjectCb.class);

        if(r!=null)
            System.out.println("hai ricevuto il biglietto->"+r.getO().getBiglietto().toString());
        else System.out.println("errore");
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
}
