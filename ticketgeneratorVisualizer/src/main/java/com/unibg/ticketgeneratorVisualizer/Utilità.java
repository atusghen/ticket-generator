package com.unibg.ticketgeneratorVisualizer;

import org.json.JSONArray;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestOperations;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;

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

    public static void visualizzazione2()
    {
        RestTemplateBuilder builder=new RestTemplateBuilder();
        RestOperations restTemplate = builder.build();
        TipoA[] r = restTemplate.getForObject(
                "http://localhost:8080/allStack",
                TipoA[].class);
        Arrays.stream(r).forEach(n -> System.out.println(n.toString2()));

    }
}
