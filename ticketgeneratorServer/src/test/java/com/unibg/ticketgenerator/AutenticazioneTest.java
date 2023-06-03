package com.unibg.ticketgenerator;

import com.unibg.ticketgenerator.dao.TicketsRepository;
import com.unibg.ticketgenerator.entities.TicketType;
import com.unibg.ticketgenerator.srv.dto.IncrementaCb;
import com.unibg.ticketgenerator.srv.ope.IncrementaOPE;
import com.unibg.ticketgenerator.srv.ope.exceptions.InvalidPriorityException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertThrows;

@DataMongoTest
@RunWith(SpringRunner.class)
public class AutenticazioneTest {

    @Autowired
    private TicketsRepository ticketsRepository;
    @TestConfiguration
    static class incrementaOpeTest {
        @Bean
        public IncrementaOPE incrementaOPE() {
            return new IncrementaOPE();
        }
    }

    @Autowired
    private IncrementaOPE incrementaOPE;

    @Test
    public void checkIncrementa()
    {

        //caso in cui il token JWT è nullo -> il risultato restituito dalla OPE deve essere nullo
        IncrementaCb.I in = new IncrementaCb.I();
        in.setPriority("A");
        in.setToken(null);
        IncrementaCb.O out = incrementaOPE.execute(in);
        assert out == null;


        //caso in cui il token JWT non è valido -> il risultato restituito dalla OPE deve essere nullo
        in = new IncrementaCb.I();
        in.setPriority("A");
        in.setToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ3aXN1YWxpenphdG9yZSIsImlhdCI6MTY3MjQwOTEyMSwiZXhwIjoxNjcyNDA5MTIxfQ.Qq4ZUYDUURnljEoaZeXycNb7wZX9UO2SxmjX7JvU9t3zL7If3bMZ00bVErIX1crGuLqx6wK0srrwJu44xI4bog");
        out = incrementaOPE.execute(in);
        assert out == null;

        //caso in cui il token JWT è valido ma la priorità scelta non esiste -> la OPE lancia l'eccezione InvalidPriorityException
        assertThrows(InvalidPriorityException.class, () -> {
            IncrementaCb.I inL = new IncrementaCb.I();
            inL.setPriority("F1");
            inL.setToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXN1YWxpenphdG9yZSIsImlhdCI6MTY3MjQwOTEyMSwiZXhwIjoxNjcyNDA5MTIxfQ.Qq4ZUYDUURnljEoaZeXycNb7wZX9UO2SxmjX7JvU9t3zL7If3bMZ00bVErIX1crGuLqx6wK0srrwJu44xI4bog");
            incrementaOPE.execute(inL);
        });

        //caso in cui il token JWT è valido, la priorità esiste e la coda è vuota -> il risultato restituito dalla OPE
        // è un biblietto con: id = 1, priority = A, nlista = 0, operatore = 0
        in = new IncrementaCb.I();
        in.setPriority("A");
        in.setToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXN1YWxpenphdG9yZSIsImlhdCI6MTY3MjQwOTEyMSwiZXhwIjoxNjcyNDA5MTIxfQ.Qq4ZUYDUURnljEoaZeXycNb7wZX9UO2SxmjX7JvU9t3zL7If3bMZ00bVErIX1crGuLqx6wK0srrwJu44xI4bog");
        out = incrementaOPE.execute(in);
        assert out.getBiglietto().getTicketNumber() == 1;
        assert out.getBiglietto().getPriority() == TicketType.valueOf("in.getPriority()");
        assert out.getBiglietto().getNlista() == 0;
        assert out.getBiglietto().getOperatore() == 0;

        //caso in cui il token JWT è valido, la priorità esiste e la coda non è vuota -> il risultato restituito dalla OPE
        // è un biblietto con: id = 2, priority = B, nlista = 1, operatore = 0
        in = new IncrementaCb.I();
        in.setPriority("B");
        in.setToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aXN1YWxpenphdG9yZSIsImlhdCI6MTY3MjQwOTEyMSwiZXhwIjoxNjcyNDA5MTIxfQ.Qq4ZUYDUURnljEoaZeXycNb7wZX9UO2SxmjX7JvU9t3zL7If3bMZ00bVErIX1crGuLqx6wK0srrwJu44xI4bog");
        out = incrementaOPE.execute(in);
        assert out.getBiglietto().getTicketNumber() == 2;
        assert out.getBiglietto().getPriority() == TicketType.valueOf("in.getPriority()");
        assert out.getBiglietto().getNlista() == 1;
        assert out.getBiglietto().getOperatore() == 0;
    }
}
