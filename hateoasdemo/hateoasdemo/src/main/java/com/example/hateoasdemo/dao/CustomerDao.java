package com.example.hateoasdemo.dao;


import com.example.hateoasdemo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerDao extends JpaRepository<Customer, Long> {

}

