package com.apap.tutorial7.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.apap.tutorial7.model.DealerModel;
import com.apap.tutorial7.rest.DealerDetail;
import com.apap.tutorial7.rest.Setting;
import com.apap.tutorial7.service.CarService;
import com.apap.tutorial7.service.DealerService;

@RestController
@RequestMapping("/dealer")
public class DealerController {
	@Autowired
	private DealerService dealerService;
	
	@Autowired
	private CarService carService;
	
	@RequestMapping("/")
	private String home() {
		return "home";
	}
	@RequestMapping(value = "/dealer/add", method = RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("dealer", new DealerModel());
		return "addDealer";
	}
	
	@PostMapping(value = "/add")
	private DealerModel addDealerSubmit(@RequestBody DealerModel dealer) {
		return dealerService.addDealer(dealer);
	}
	@DeleteMapping(value = "/delete")
	private String deleteDealer(@RequestParam("dealerId") long id, Model model) {
		DealerModel dealer = dealerService.getDealerDetailById(id).get();
		dealerService.deleteDealer(dealer);
		return "delete";
}
	@GetMapping(value = "/{dealerId}")
	private DealerModel viewDealer(@PathVariable ("dealerId") long dealerId, Model model) {
		return dealerService.getDealerDetailById(dealerId).get();
}
	@RequestMapping(value = "/dealer/update/{id}", method = RequestMethod.GET)
	private String updateDealer(@PathVariable(value = "id") long id, Model model) {
		DealerModel dealer = dealerService.getDealerDetailById(id).get();
		model.addAttribute("dealer",dealer);
		return "updateDealer";
	}
	
	@PutMapping(value = "/{id}")
	private String updateDealerSubmit(@PathVariable (value = "id") long id, 
			@RequestParam("alamat") String alamat, @RequestParam("telp") String telp) {
		DealerModel dealer = (DealerModel) dealerService.getDealerDetailById(id).get();
		if(dealer.equals(null)) {
			
			return "error";
		}
		dealer.setAlamat(alamat);
		dealer.setNoTelp(telp);
		dealerService.updateDealer(dealer, id);
		return "update";
}
	@GetMapping
	private List<DealerModel> viewAllDealer(Model model) { 

		return dealerService.viewAllDealer();

	}	
	
	@Autowired
	RestTemplate restTemplate;
	
		@Bean
		public RestTemplate rest() {
			return new RestTemplate();
		}
		@GetMapping(value = "/status/{dealerId}")
		private String getStatus(@PathVariable ("dealerId") long dealerId) throws Exception {
			String path = Setting.dealerUrl + "/dealer?id=" + dealerId;
			return restTemplate.getForEntity(path, String.class).getBody();	
			}
		@GetMapping(value = "/full/{dealerId}")
		private DealerDetail postStatus(@PathVariable ("dealerId") long dealerId) throws Exception {
			String path = Setting.dealerUrl + "/dealer";
			DealerModel dealer = dealerService.getDealerDetailById(dealerId).get();
			DealerDetail detail = restTemplate.postForObject(path, dealer, DealerDetail.class);
			return detail;
			}
}
