package com.example.ec.explorecali.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ec.explorecali.domain.Difficulty;
import com.example.ec.explorecali.domain.Region;
import com.example.ec.explorecali.domain.Tour;
import com.example.ec.explorecali.domain.TourPackage;
import com.example.ec.explorecali.repo.TourPackageRepository;
import com.example.ec.explorecali.repo.TourRepository;


@Service
public class TourService {
	private TourPackageRepository tourPackageRepository;
	private TourRepository tourRepository;
	 @Autowired
	public TourService(TourPackageRepository tourPackageRepository, TourRepository tourRepository) {
		super();
		this.tourPackageRepository = tourPackageRepository;
		this.tourRepository = tourRepository;
	}
	
	 public Tour createTour(String title,String description,String blurb,Integer price,String bullets,String duration,String keywords,String tourPackageName,Difficulty difficulty,Region region)
	 {
	 	
		TourPackage tourPackage = tourPackageRepository.findByName(tourPackageName);
		if(tourPackage == null)
		{
			throw new RuntimeException(("Tour Package does not exists"+tourPackageName));
		}
	 	return tourRepository.save(new Tour(price, title,description,blurb,price,duration,bullets,keywords,tourPackage,difficulty,region));
	 }

	 public Iterable<Tour> lookup()
	 {
		 return tourRepository.findAll();
	 }
	 public long total()
	 {
		 return tourRepository.count();
	 }
}
