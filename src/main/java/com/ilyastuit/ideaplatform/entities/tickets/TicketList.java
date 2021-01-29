package com.ilyastuit.ideaplatform.entities.tickets;

import java.util.List;
import java.util.Objects;

public class TicketList {
    private List<Ticket> ticketList;

    public TicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketList that = (TicketList) o;
        return Objects.equals(ticketList, that.ticketList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketList);
    }

    @Override
    public String toString() {
        return "TicketList{" +
                "ticketList=" + ticketList +
                '}';
    }
}
