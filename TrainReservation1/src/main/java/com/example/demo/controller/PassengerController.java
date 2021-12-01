package com.example.demo.controller;

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
import com.example.demo.repository.PassengerRepository;



@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api")
public class PassengerController {
	@Autowired
	PassengerRepository passengerRepository;
	
	@PostMapping("/passenger")
	public ResponseEntity<Passenger> createPassenger(@RequestBody Passenger passenger){		
	try {
		Passenger _Passenger	= passengerRepository.save(new Passenger(passenger.getName(), passenger.getAge(),passenger.getGender()));
	    return new ResponseEntity<>(_Passenger,HttpStatus.CREATED);
	}catch(Exception ex) {		
		return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	}
	
	@GetMapping("/passenger")
	public ResponseEntity<List<Passenger>> getAllPassenger(){
		try {
			List<Passenger> Passenger = new ArrayList<Passenger>();
			passengerRepository.findAll().forEach(Passenger::add);
			return new ResponseEntity<>(Passenger,HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/passenger/{id}")
	public ResponseEntity<Passenger> getPassengerById(@PathVariable("id") int id){
		Optional<Passenger> PassengerData = passengerRepository.findById(id);
		
		if(PassengerData.isPresent()) {
			return new ResponseEntity<>(PassengerData.get(),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
	}
	
	@PutMapping("/Updatepassenger/{id}")
	public ResponseEntity<Passenger> updatePassenger(@PathVariable("id") int id,@RequestBody Passenger passenger){
        Optional<Passenger> PassengerData = passengerRepository.findById(id);
		
		if(PassengerData.isPresent()) {
			Passenger _passenger = PassengerData.get();
			_passenger.setName(passenger.getName());
			_passenger.setAge(passenger.getAge());
			_passenger.setGender(passenger.getGender());
			return new ResponseEntity<>(passengerRepository.save(_passenger),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}	
	}
	
	@DeleteMapping("/passenger/{id}")
	public ResponseEntity<HttpStatus> deletePassenger(@PathVariable("id") int id){
		try {
			passengerRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	
	
	
	
	
	
}
