package com.apap.tutorial7.controller;
import java.util.Arrays;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.apap.tutorial7.rest.Setting;
import com.apap.tutorial7.service.CarService;
import com.apap.tutorial7.service.DealerService;

@RestController
@RequestMapping("/model")
public class ModelController {
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	private DealerService dealerService;
	
	@Autowired
private CarService carService;
	@GetMapping()
	private Object getModel(@RequestParam("factory") String factory) throws Exception{
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:63.0) Gecko/20100101 Firefox/63.0");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        String year = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
        String path = Setting.modelUrl + "/?cmd=getModels&make=" + factory + "&year=" + year;
		Object response = restTemplate.exchange(path, HttpMethod.GET, entity, Object.class);
		return response;
	}
}