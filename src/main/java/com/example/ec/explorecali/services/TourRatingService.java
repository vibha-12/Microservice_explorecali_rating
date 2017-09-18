package com.example.ec.explorecali.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ec.explorecali.domain.Tour;

import com.example.ec.explorecali.domain.TourRating;
import com.example.ec.explorecali.domain.TourRatingPk;
import com.example.ec.explorecali.repo.TourRatingRepository;
import com.example.ec.explorecali.web.RatingDto;

@Service
public class TourRatingService {
	private TourRatingRepository tourRatingRepository;

	@Autowired
	public TourRatingService(TourRatingRepository tourRatingRepository) {
		super();
		this.tourRatingRepository = tourRatingRepository;
	}

    
	public TourRatingService() {
		super();
		// TODO Auto-generated constructor stub
	}


	public void createTourPkg(Tour tour, int tourId, RatingDto ratingDto, TourRatingRepository tourRatingRepository2) {
		// TODO Auto-generated method stub
		tourRatingRepository2.save(new TourRating(new TourRatingPk(tour,ratingDto.getCustomerId()),ratingDto.getScore(),ratingDto.getComment()));

	}
	
	
	
	
}
