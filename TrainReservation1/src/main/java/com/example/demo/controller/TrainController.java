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

import com.example.demo.model.Train;
import com.example.demo.model.Train;
import com.example.demo.repository.TrainRepository;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api")
public class TrainController {
	@Autowired
	TrainRepository trainRepository;
	@PostMapping("/Trains")
	public ResponseEntity<Train> createTrain(@RequestBody Train train){		
	try {
		Train _Train	= trainRepository.save(new Train(train.getTrainNo(),train.getTrainName(),train.getDestination(),train.getSource(),train.getTicketprice()));
	    return new ResponseEntity<>(_Train,HttpStatus.CREATED);
	}catch(Exception ex) {		
		return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	}
	
	@GetMapping("/Trains")
	public ResponseEntity<List<Train>> getAllTrains(){
		try {
			List<Train> Trains = new ArrayList<Train>();
			trainRepository.findAll().forEach(Trains::add);
			return new ResponseEntity<>(Trains,HttpStatus.OK);
		}catch(Exception ex) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/Trains/{id}")
	public ResponseEntity<Train> getTrainById(@PathVariable("id") int id){
		Optional<Train> TrainData = trainRepository.findById(id);
		
		if(TrainData.isPresent()) {
			return new ResponseEntity<>(TrainData.get(),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
	}
	
	@PutMapping("/updateTrains/{id}")
	public ResponseEntity<Train> updateTrain(@PathVariable("id") int id,@RequestBody Train Train){
        Optional<Train> TrainData = trainRepository.findById(id);
		
		if(TrainData.isPresent()) {
			Train _Train = TrainData.get();
			_Train.setTrainNo(Train.getTrainNo());
			_Train.setTrainName(Train.getTrainName());
			_Train.setDestination(Train.getDestination());
			_Train.setSource(Train.getSource());
			_Train.setTicketprice(Train.getTicketprice());
			return new ResponseEntity<>(trainRepository.save(_Train),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}	
	}
	
	@DeleteMapping("/Trains/{id}")
	public ResponseEntity<HttpStatus> deleteTrain(@PathVariable("id") int id){
		try {
			trainRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	
	
	
	
	
	
}
