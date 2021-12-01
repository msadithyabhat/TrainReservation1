package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

}
