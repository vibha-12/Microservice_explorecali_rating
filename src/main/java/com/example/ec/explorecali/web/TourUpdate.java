package com.example.ec.explorecali.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ec.explorecali.domain.Difficulty;
import com.example.ec.explorecali.domain.Region;
import com.example.ec.explorecali.domain.Tour;
import com.example.ec.explorecali.domain.TourPackage;
import com.example.ec.explorecali.repo.TourPackageRepository;
import com.example.ec.explorecali.repo.TourRepository;

@RestController
@RequestMapping(path="/tourAdd/{tourPkgCode}/{tourPkgName}/{Id}/{Title}/{Description}/{Blurb}/{Price}/{Bullets}/{Duration}/{Keywords}/{Difficulty}/{Region}")
public class TourUpdate {

	@Autowired
	private TourPackageRepository tourPackageRepository;
	
	@Autowired
	private TourRepository tourRepository;
	
	@GetMapping
	public String chkTour(@PathVariable(value="tourPkgCode") String tourPkgCode,@PathVariable(value="tourPkgName") String tourPkgName,@PathVariable(value="Id") int Id,@PathVariable(value="Title") String Title,@PathVariable(value="Description") String Description,@PathVariable(value="Blurb") String Blurb,@PathVariable(value="Price") int Price,@PathVariable(value="Bullets") String Bullets,@PathVariable(value="Duration") String Duration,@PathVariable(value="Keywords") String Keywords,@PathVariable(value="Difficulty") Difficulty Difficulty,@PathVariable(value="Region") Region Region)
	{
		TourPackage tourPk=new TourPackage(tourPkgCode,tourPkgName);
		tourRepository.save(new Tour(Id,Title,Description,Blurb,Price,Bullets,Duration,Keywords,tourPk,Difficulty,Region));
		
		return "saved";
	}
	
}
