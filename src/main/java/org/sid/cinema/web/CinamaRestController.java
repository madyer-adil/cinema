package org.sid.cinema.web;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.sid.cinema.dao.FilmRepository;
import org.sid.cinema.dao.TicketRepository;
import org.sid.cinema.entities.Film;
import org.sid.cinema.entities.Ticket;
import org.sid.cinema.form.TicketForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CinamaRestController {
	@Autowired
	FilmRepository filmRepository;
	@Autowired
	TicketRepository ticketRepository;
	
	@GetMapping(path = "/imageFilm/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] image(@PathVariable (name="id")Long id) throws Exception {
		Film film = filmRepository.findById(id).get();
		String photoFilm = film.getPhoto();
		File file = new File(System.getProperty("user.home")+"/cinema/images/"+photoFilm);
		Path path = Paths.get(file.toURI()); 
		
		return Files.readAllBytes(path);
	}
	
	@PostMapping("/payerTickets")
	@CrossOrigin("*")
	public List<Ticket> payerTickets(@RequestBody TicketForm ticketForm){
		List<Ticket> listTickets = new ArrayList<>();
		ticketForm.getTickets().forEach(idTicket->{
			Ticket ticket = ticketRepository.findById(idTicket).get();
			ticket.setNomClient(ticketForm.getNomClient());
			ticket.setCodePayement(ticketForm.getCodePayement());
			ticket.setReserve(true);
			ticketRepository.save(ticket);
			listTickets.add(ticket);
		});
		return listTickets;
	}
}
