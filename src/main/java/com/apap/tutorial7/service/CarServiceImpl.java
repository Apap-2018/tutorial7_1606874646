package com.apap.tutorial7.service;

import java.util.List;
import java.util.Optional;

import com.apap.tutorial7.model.CarModel;
import com.apap.tutorial7.model.DealerModel;
import com.apap.tutorial7.repository.CarDb;
import com.apap.tutorial7.repository.DealerDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class CarServiceImpl implements CarService {
	@Autowired
	private CarDb carDb;
	
	@Override
	public CarModel addCar(CarModel car) {
		return carDb.save(car);
	}
	@Override
	public void deleteCar(CarModel car) {
		carDb.delete(car);
}
	@Override
	public CarModel getCar(Long id) {
		return carDb.findById(id).get();
		
		
}
	@Override
	public List<CarModel> sortByPriceDesc(Long dealer_id) {
		return carDb.findByDealerIdOrderByPriceDesc(dealer_id);
}
	@Override
	public void updateCar(long id, CarModel car) {
		CarModel update = carDb.getOne(id);
		update.setBrand(car.getBrand());
		update.setType(car.getType());
		update.setPrice(car.getPrice());
		update.setAmount(car.getAmount());
		carDb.save(update);
}
	@Override
	public Optional<CarModel> getCarDetailById(long id) {
		// TODO Auto-generated method stub
		return carDb.findById(id);
	}
	@Override
	public List<CarModel> getAllCar() {
		// TODO Auto-generated method stub
		return carDb.findAll();
	}
}
