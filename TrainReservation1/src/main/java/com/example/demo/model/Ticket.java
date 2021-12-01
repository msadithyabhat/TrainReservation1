package com.example.demo.model;

import java.sql.Date;

//import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="TicketTable")
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	@Column(name="pnr")
	private String pnr;
	@Column(name="trainName")
	private String trainName;
	@Column(name="source")
	private String source;
	@Column(name="destination")
	private String destination;
	@Column(name="name")
	private String name;
	@Column(name="age")
	private int age;
	@Column(name="gender")
	private char gender;
	@Column(name="ticketprice")
	private int ticketprice;
	@Column(name="Traveldate")
	private String travelDate; 
	static private int counter=100;
	
	public Ticket() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPnr() {
		return pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public int getTicketprice() {
		return ticketprice;
	}

	public void setTicketprice(char ticketprice) {
		this.ticketprice = ticketprice;
	}

	public String getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(String travelDate) {
		this.travelDate = travelDate;
	}

	public static int getCounter() {
		return counter;
	}

	public static void setCounter(int counter) {
		Ticket.counter = counter;
	}

	public Ticket(String pnr, String trainName, String source, String destination, String name, int age, char gender,
			int ticketprice, String travelDate) {
		super();
		this.pnr = pnr;
		this.trainName = trainName;
		this.source = source;
		this.destination = destination;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.ticketprice = ticketprice;
		this.travelDate = travelDate;
	}

		
			
	
	
	

	
	
}
