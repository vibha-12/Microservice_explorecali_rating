package com.example.ec.explorecali;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ec.explorecali.domain.TourPackage;
import com.example.ec.explorecali.repo.TourPackageRepository;
import com.example.ec.explorecali.services.TourPackageService;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping(path="/rest/hello/tourChk/{tourPkgCode}")
public class TourCheck {
	/*@Autowired
	private TourPackageService tourPackageService;
	@Autowired
	private TourService tourService;*/
	@Autowired
	private TourPackageRepository tourPackageRepository;
	
	
	@GetMapping
	public String chkTour(@PathVariable(value="tourPkgCode") String tourId)
	{
		TourPackage tp=tourPackageRepository.findByCode(tourId);
		String namee=tp.getName();
		String codee=tp.getCode();
		System.out.println("nameeeeeee"+namee);
		if(namee.equals(null))
			return null;
		
			return namee;
	}

	
	
	
}
