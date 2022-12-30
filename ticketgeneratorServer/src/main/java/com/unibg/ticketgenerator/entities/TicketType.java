package com.unibg.ticketgenerator.entities;

import java.util.HashMap;
import java.util.Map;

public enum TicketType {
    A("TypeA"),
    X("TypeX"),
    B("TypeB"),
    C1("TypeC1"),
    C2("TypeC2");

    private static final Map<String, TicketType> BY_LABEL = new HashMap<>();

    static {
        for (TicketType ticketType : values())
        {
            BY_LABEL.put(ticketType.label, ticketType);
        }
    }

//    private static final Map<String, TicketType> BY_VAL = new HashMap<>();
//
//    static {
//        for (TicketType ticketType : values())
//        {
//            BY_VAL.put(ticketType.val, ticketType);
//        }
//    }

    private final String label;
//    private final String val;
    TicketType(String label) {
        this.label=label;
//        this.val=val;
    }

    public String getLabel(){return label;}

//    public String getVal(){return val;}

    public static TicketType valueOfLabel(String label) { return BY_LABEL.get(label);}

//    public static TicketType valueOfVal(String val) { return BY_VAL.get(val);}
}
