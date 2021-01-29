package com.ilyastuit.ideaplatform.services.timing.impl;

import com.ilyastuit.ideaplatform.entities.tickets.Ticket;
import com.ilyastuit.ideaplatform.entities.tickets.TicketList;
import com.ilyastuit.ideaplatform.services.timing.TimingService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class SimpleFlightTimingService implements TimingService {

    private TicketList tickets;

    public SimpleFlightTimingService(TicketList tickets) {
        this.tickets = tickets;
    }

    @Override
    public void showAverageFlightTime() {
        long sum = 0;
        for (Ticket ticket : tickets.getTickets()) {
            sum += getTimeDifference(ticket);
        }
        sum /= tickets.getTickets().size();

        int hours = (int) sum / 1000 / 3600;
        int minutes = (int) sum / 1000 / 60 % 60;

        System.err.format("The average flight time between Vladivostok and Tel-Aviv is: %d h. and %d min.%n",
                hours, minutes);
    }

    @Override
    public void showPercentile(double procentile) {
        List<Long> differences = new ArrayList<>();

        for (Ticket ticket : tickets.getTickets()) {
            differences.add(getTimeDifference(ticket));
        }

        long calculatedProcentile = getProcentile(differences, procentile);
        int hours = (int) calculatedProcentile / 1000 / 3600;
        int minutes = (int) calculatedProcentile / 1000 / 60 % 60;

        System.err.format("The 90th percentile of flight time between Vladivostok and Tel-Aviv is: %d h. and %d min.%n",
                hours, minutes);
    }

    private long getTimeDifference(Ticket ticket) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy HH:mm");

        Date arrivalTime = null;
        Date departureTime = null;
        try {
            arrivalTime = dateFormat.parse(ticket.getArrivalDate() + " " + ticket.getArrivalTime());
            departureTime = dateFormat.parse(ticket.getDepartureDate().toString() + " " + ticket.getDepartureTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return arrivalTime.getTime() - departureTime.getTime();
    }

    private Long getProcentile(List<Long> list, double proc) {
        Collections.sort(list);
        int index = (int) (list.size() * proc / 100);
        if (index < list.size() - 1) index++;
        return list.get(index) + 60 * 1000;
    }

    public TicketList getTickets() {
        return tickets;
    }

    public void setTickets(TicketList tickets) {
        this.tickets = tickets;
    }
}
