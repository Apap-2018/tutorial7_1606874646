package com.apap.tutorial5.service;

import java.util.List;
import java.util.Optional;

import com.apap.tutorial5.model.CarModel;
import com.apap.tutorial5.model.DealerModel;
import com.apap.tutorial5.repository.CarDb;
import com.apap.tutorial5.repository.DealerDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class CarServiceImpl implements CarService {
	@Autowired
	private CarDb carDb;
	
	@Override
	public void addCar(CarModel car) {
		carDb.save(car);
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
}
