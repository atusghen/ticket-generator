package com.unibg.ticketgeneratorVisualizer.gui;

import com.unibg.ticketgeneratorVisualizer.dto.AllStackCb;
import com.unibg.ticketgeneratorVisualizer.entities.Ticket;
import org.json.JSONArray;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestOperations;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

public class Utilità {
    public static void visualizzazione()
    {
        try{
            URL url=new URL("http://localhost:8080/allStack");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            bufferedReader.close();
            String s = sb.toString();
            //System.out.println(s);
            // 2. Parsing della stringa come oggetto JSON, e output dei contenuti
            JSONArray o = new JSONArray(s);
//            o.forEach(System.out::println);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void visualizzazione2(Interfaccia ui)
    {
        int i = 0;
        RestTemplateBuilder builder=new RestTemplateBuilder();
        RestOperations restTemplate = builder.build();

        AllStackCb.I in=new AllStackCb.I();
        in.setType("dashboard");
        AllStackCb cb=new AllStackCb();
        cb.setI(in);

        AllStackCb r = restTemplate.postForObject(
                "http://localhost:8080/AllStackOPE", cb, AllStackCb.class);
        List<Ticket> temp=r.getO().getOutput();
        System.out.println("Tabellone ->");
        temp.forEach(System.out::println);

        for(Ticket t : temp){
            ui.setData(i++, t.toString());
        }

    }
}
