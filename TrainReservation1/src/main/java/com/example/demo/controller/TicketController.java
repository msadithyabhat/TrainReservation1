package com.example.demo.controller;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Passenger;
import com.example.demo.model.Ticket;
import com.example.demo.model.Train;
import com.example.demo.repository.PassengerRepository;
import com.example.demo.repository.TicketRepository;


@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api")
public class TicketController {

	@Autowired
	TicketRepository ticketRepository;
	
	@Autowired
	PassengerRepository passenger;
	@PostMapping("/tickets")
	public ResponseEntity<String> createTicket(@RequestBody Ticket ticket){
		System.out.println(ticket.getTrainName());
			try {
				String pnrstr="";
				String src=ticket.getSource();
				String name=ticket.getName();
				String dest=ticket.getDestination();
				String travelDate=ticket.getTravelDate();
				int counter=ticket.getCounter();
				String pattern="yyyy-mm-dd";
				DateFormat df=new SimpleDateFormat(pattern);
				pnrstr=src.charAt(0)+""+dest.charAt(0)+""+travelDate+"_"+counter;
				counter++;
				Ticket _ticket = ticketRepository.save(new Ticket(pnrstr,ticket.getTrainName(),ticket.getSource(),ticket.getDestination(),ticket.getName(),ticket.getAge(),ticket.getGender(),ticket.getTicketprice(),ticket.getTravelDate()));
				passenger.save(new Passenger(ticket.getName(),ticket.getAge(),ticket.getGender()));
				return new ResponseEntity<>("",HttpStatus.CREATED);
			} catch (Exception e) {
				
				return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
			}	
			
	}
	
	@GetMapping("/alltickets")
	public ResponseEntity<List<Ticket>> getTickets(){
		List<Ticket> list = new ArrayList<Ticket>();
		
		try {
			ticketRepository.findAll().forEach(list::add);
			return new ResponseEntity<>(list,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	@GetMapping("/tickets/{pnr}")
	public ResponseEntity<Ticket> getTicketById(@PathVariable("pnr") int pnr){
		Optional<Ticket> Ticketdata = ticketRepository.findById(pnr);
		
		if(Ticketdata.isPresent()){
			return new ResponseEntity<>(Ticketdata.get(),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}	
		
	}
//	@PutMapping("/tickets/{id}")
//	public ResponseEntity<Ticket> updateTicket(@PathVariable("id") int id,@RequestBody Ticket ticket){
//		Optional<Ticket> Ticketdata = ticketRepository.findById(id);
//		
//		if(Ticketdata.isPresent()){
//			Ticket tn = Ticketdata.get();
//			tn.setDate(ticket.getDate());
//			tn.setPid(ticket.getPid());
//			tn.setTrainno(ticket.getTrainno());
//			tn.setPnr(ticket.getPnr());
//			return new ResponseEntity<>(ticketRepository.save(tn),HttpStatus.OK);
//		}
//		else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}	
//		
//	}
	
	@DeleteMapping("/tickets/{id}")
	public ResponseEntity<Ticket> deleteTicket(@PathVariable("id") int id,@RequestBody Ticket Ticket){
		try {
			ticketRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping("/generatTicet")
	public String genarateTicket(@RequestBody Ticket ticket ) 
	{
		String pnrstr="";
		String src=ticket.getSource();
		String name=ticket.getName();
		String dest=ticket.getDestination();
		String travelDate=ticket.getTravelDate();
		int counter=ticket.getCounter();
		String pattern="yyyy-mm-dd";
		DateFormat df=new SimpleDateFormat(pattern);
		pnrstr=src.charAt(0)+""+dest.charAt(0)+""+travelDate+"_"+counter;
		counter++;
		return pnrstr;		
	}
	
	
}