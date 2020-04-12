package org.sid.cinema.form;

import java.util.ArrayList;
import java.util.List;

import org.sid.cinema.entities.Ticket;

import lombok.Data;

@Data
public class TicketForm {
	private String nomClient;
	private int codePayement;
	private List<Long> tickets = new ArrayList<>();
}
