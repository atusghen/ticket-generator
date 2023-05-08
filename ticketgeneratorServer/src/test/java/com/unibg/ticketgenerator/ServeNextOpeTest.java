package com.unibg.ticketgenerator;

import com.unibg.ticketgenerator.dao.TicketsRepository;
import com.unibg.ticketgenerator.entities.Ticket;
import com.unibg.ticketgenerator.entities.TicketType;
import com.unibg.ticketgenerator.srv.dto.ServeNextCb;
import com.unibg.ticketgenerator.srv.ope.ServeNextOPE;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@DataMongoTest
@RunWith(SpringRunner.class)
public class ServeNextOpeTest {

    @Autowired
    private TicketsRepository ticketsRepository;
    @TestConfiguration
    static class serveNextOpeTest {
        @Bean
        public ServeNextOPE serveNextOPE() {
            return new ServeNextOPE();
        }
    }

    @Autowired
    private ServeNextOPE serveNextOPE;

    @Test
    public void checkServeNext()
    {
        Ticket priorityA = new Ticket(1,0, TicketType.A, 1);
        Ticket priorityB = new Ticket(2,0, TicketType.B, 1);
        Ticket priorityC1 = new Ticket(3,0, TicketType.C1, 1);
        Ticket priorityC2 = new Ticket(4,0, TicketType.C2, 1);
        Ticket priorityBsecondo = new Ticket(5,0, TicketType.B, 1);
        Ticket priorityAsecondo = new Ticket(6,0, TicketType.A, 1);

        ticketsRepository.save(priorityA);
        ticketsRepository.save(priorityB);
        ticketsRepository.save(priorityC1);
        ticketsRepository.save(priorityC2);
        ticketsRepository.save(priorityBsecondo);
        ticketsRepository.save(priorityAsecondo);

        ServeNextCb.I in = new ServeNextCb.I();
        in.setNumero(1L);
        ServeNextCb.O out = serveNextOPE.execute(in);

        assert out.getBiglietto().getPriority().equals(TicketType.A);

        out = serveNextOPE.execute(in);

        assert out.getBiglietto().getPriority().equals(TicketType.A);

        out = serveNextOPE.execute(in);

        assert out.getBiglietto().getPriority().equals(TicketType.B);

        out = serveNextOPE.execute(in);

        assert out.getBiglietto().getPriority().equals(TicketType.B);

        out = serveNextOPE.execute(in);

        assert out.getBiglietto().getPriority().equals(TicketType.C1) || out.getBiglietto().getPriority().equals(TicketType.C2);

        out = serveNextOPE.execute(in);

        assert out.getBiglietto().getPriority().equals(TicketType.C1) || out.getBiglietto().getPriority().equals(TicketType.C2);

    }
}
