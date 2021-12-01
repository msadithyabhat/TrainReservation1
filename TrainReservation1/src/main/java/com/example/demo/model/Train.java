package com.example.demo.model;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Train")
public class Train {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name="trainNo")
	private int trainNo;
	@Column(name="trainName")
	private String trainName;
	@Column(name="source")
	private String source;
	@Column(name="destination")
	private String destination;	
	@Column(name="ticketprice")
	private double ticketprice;
	
	public Train() {
		
	}
	public Train(int trainNo, String trainName, String source, String destination, double ticketprice) {
		super();
		this.trainNo = trainNo;
		this.trainName = trainName;
		this.source = source;
		this.destination = destination;
		this.ticketprice = ticketprice;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTrainNo() {
		return trainNo;
	}
	public void setTrainNo(int trainNo) {
		this.trainNo = trainNo;
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
	public double getTicketprice() {
		return ticketprice;
	}
	public void setTicketprice(double ticketprice) {
		this.ticketprice = ticketprice;
	}
	@Override
	public String toString() {
		return "Train [id=" + id + ", trainNo=" + trainNo + ", trainName=" + trainName + ", source=" + source
				+ ", destination=" + destination + ", ticketprice=" + ticketprice + "]";
	}
	
	
}







