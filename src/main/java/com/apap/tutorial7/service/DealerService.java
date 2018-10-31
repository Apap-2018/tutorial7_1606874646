package com.apap.tutorial7.service;

import java.util.Optional;
import java.util.List;

import com.apap.tutorial7.model.CarModel;
import com.apap.tutorial7.model.DealerModel;
import com.apap.tutorial7.repository.DealerDb;

public interface DealerService {
	Optional<DealerModel> getDealerDetailById(Long id);
	DealerModel addDealer(DealerModel dealer);
	void deleteDealer(DealerModel dealer);
//	void updateDealer(long id, DealerModel dealer);
	List<DealerModel> viewAllDealer();
	void updateDealer(DealerModel updateDealer, Long dealerId);

}
