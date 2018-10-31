package com.apap.tutorial7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tutorial7.model.DealerModel;

import org.springframework.data.jpa.repository.Query;

@Repository
public interface DealerDb extends JpaRepository<DealerModel, Long> {

}
