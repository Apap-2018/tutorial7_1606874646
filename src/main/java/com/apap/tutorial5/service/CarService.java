package com.apap.tutorial5.service;

import java.util.List;

import com.apap.tutorial5.model.CarModel;
public interface CarService {
	void addCar(CarModel car);
	void deleteCar(CarModel car);
	CarModel getCar(Long id);
	void updateCar(long id,CarModel car);
	List<CarModel> sortByPriceDesc(Long dealer_id);
}
