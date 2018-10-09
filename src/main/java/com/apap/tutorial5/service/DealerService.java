package com.apap.tutorial5.service;

import java.util.Optional;
import java.util.List;
import com.apap.tutorial5.model.DealerModel;
import com.apap.tutorial5.repository.DealerDb;
import com.apap.tutorial5.model.CarModel;

public interface DealerService {
	Optional<DealerModel> getDealerDetailById(Long id);
	void addDealer(DealerModel dealer);
	void deleteDealer(DealerModel dealer);
	void updateDealer(long id, Optional<DealerModel> dealer);
	DealerDb viewAllDealer();

}
