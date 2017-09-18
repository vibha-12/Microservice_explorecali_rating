package com.example.ec.explorecali.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.ec.explorecali.domain.TourPackage;
import com.example.ec.explorecali.repo.TourPackageRepository;

@RestController
@RequestMapping(path="/tours/packages")
public class TourPackageController {
	TourPackageRepository tourPackageRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	public TourPackageController(TourPackageRepository tourPackageRepository) {
		super();
		this.tourPackageRepository = tourPackageRepository;
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<String> createTourPackage(@RequestBody @Validated TourPackage tourPkgDto)
	{
		String code,name;
		code=tourPkgDto.getCode();
		name=tourPkgDto.getName();
		String url="http://explorecaliTour/tours/packages/"+code+"/"+name;
	String abc;
	abc= restTemplate.getForObject(url,String.class);
	
	if(abc.equals("saved"))
		{tourPackageRepository.save(new TourPackage(tourPkgDto.getCode(),tourPkgDto.getName()));
		//tourRatingService.createTourPkg(tour,tourId,ratingDto,tourRatingRepository);
	return ResponseEntity.status(HttpStatus.CREATED).build();
		}
	else
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

}
