package com.unibg.ticketgenerator;

public class Incrementa {
    long id;
    Incrementa(long id)
    {
        this.id=id;
    }

    public TipoA getNum()
    {
        return new TipoA(this.id);
    }
}

