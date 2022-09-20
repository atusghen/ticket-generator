package com.unibg.ticketgeneratorClient;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestOperations;

public class Operatore {
    private static int id=1;

    public static void serviSuccessivo()
    {
        RestTemplateBuilder builder=new RestTemplateBuilder();
        RestOperations restTemplate = builder.build();
        TipoA r = restTemplate.getForObject(
                "http://localhost:8080/serveNext?op="+Integer.toString(id),
                TipoA.class);
        if(r!=null)
            System.out.println("l'operatore "+id+" sta servendo il biglietto "+r.toString());
        else System.out.println("nessuno in coda");
    }
}
