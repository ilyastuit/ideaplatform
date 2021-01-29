package com.ilyastuit.ideaplatform;

import com.ilyastuit.ideaplatform.entities.tickets.Ticket;
import com.ilyastuit.ideaplatform.entities.tickets.TicketList;
import com.ilyastuit.ideaplatform.services.parsers.ParserService;
import com.ilyastuit.ideaplatform.services.parsers.impl.GsonParserService;

import java.io.FileNotFoundException;

public class Main {

    private final static String JSON_FILE = "src/main/resources/json/tickets.json";
    private final static String DATE_FORMAT = "DD.MM.yyyy";

    public static void main(String[] args) {

        ParserService parserService = null;
        try {
            parserService = new GsonParserService(JSON_FILE, DATE_FORMAT);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        TicketList ticketList = (TicketList) parserService.parse();

        for (Ticket ticket: ticketList.getTickets()) {
            System.out.println(ticket);
        }
    }

}
