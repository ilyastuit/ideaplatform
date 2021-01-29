package com.ilyastuit.ideaplatform.entities.tickets;

import java.util.List;
import java.util.Objects;

public class TicketList {
    private List<Ticket> tickets;

    public TicketList(List<Ticket> ticketList) {
        this.tickets = ticketList;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketList that = (TicketList) o;
        return Objects.equals(tickets, that.tickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tickets);
    }

    @Override
    public String toString() {
        return "TicketList{" +
                "ticketList=" + tickets +
                '}';
    }
}
