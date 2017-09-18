package com.example.ec.explorecali.web;

import java.util.AbstractMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.ec.explorecali.domain.Tour;
import com.example.ec.explorecali.domain.TourRating;
import com.example.ec.explorecali.domain.TourRatingPk;
import com.example.ec.explorecali.repo.TourRatingRepository;
import com.example.ec.explorecali.repo.TourRepository;
import com.example.ec.explorecali.services.TourRatingService;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping(path="/tours/{tourId}/ratings")
public class TourRatingController  {
TourRatingRepository tourRatingRepository;
TourRepository tourRepository;
TourRatingService tourRatingService;
@Autowired
public TourRatingController(TourRatingRepository tourRatingRepository, TourRepository tourRepository) {
	super();
	this.tourRatingRepository = tourRatingRepository;
	this.tourRepository = tourRepository;
}
public TourRatingController(){
	
}

@RequestMapping(method=RequestMethod.POST)
@ResponseStatus(HttpStatus.CREATED)
public void createTourRating(@PathVariable(value="tourId") int tourId,@RequestBody @Validated RatingDto ratingDto)
{
	
	Tour tour=verifyTour(tourId);
	//tourRatingService.createTourPkg(tour,tourId,ratingDto,tourRatingRepository);
     tourRatingRepository.save(new TourRating(new TourRatingPk(tour,ratingDto.getCustomerId()),ratingDto.getScore(),ratingDto.getComment()));
	
}
private Tour verifyTour(int tourId) throws NoSuchElementException

{
Tour tour=tourRepository.findOne(tourId);
if(tour==null){
	throw new NoSuchElementException("Tour does not exists"+tourId);
}
return tour;

}

@RequestMapping(method=RequestMethod.GET)
public List<RatingDto> getAllRatingsForTour(@PathVariable(value="tourId")int tourId)
{
	verifyTour(tourId);
	return tourRatingRepository.findByPkTourId(tourId).stream().map(tourRating->toDto(tourRating)).collect(Collectors.toList());
}




@RequestMapping(method=RequestMethod.GET,path="/average")
public AbstractMap.SimpleEntry<String, Double> getAverage(@PathVariable(value="tourId")int tourId)

{
	verifyTour(tourId);
	List<TourRating> tourRatings=tourRatingRepository.findByPkTourId(tourId);
	OptionalDouble average=tourRatings.stream().mapToInt(TourRating::getScore).average();
	return new AbstractMap.SimpleEntry<String,Double>("average",average.isPresent()?average.getAsDouble():null);
}

@RequestMapping(method=RequestMethod.PUT)
public RatingDto updateWithPut(@PathVariable(value="tourId")int tourId,@RequestBody @Validated RatingDto ratingDto)

{    

	TourRating tourRating=verifyTourRating(tourId,ratingDto.getCustomerId());
	tourRating.setScore(ratingDto.getScore());
	tourRating.setComment(ratingDto.getComment());
	return toDto(tourRatingRepository.save(tourRating));
}
@RequestMapping(method=RequestMethod.PATCH)
public RatingDto updateWithPatch(@PathVariable(value="tourId")int tourId,@RequestBody @Validated RatingDto ratingDto)

{
	TourRating tourRating=verifyTourRating(tourId,ratingDto.getCustomerId());
	if(ratingDto.getScore()!=null)
	{
		tourRating.setScore(ratingDto.getScore());

	}
	if(ratingDto.getComment()!=null)
	{
		tourRating.setComment(ratingDto.getComment());

	}
	return toDto(tourRatingRepository.save(tourRating));
}

@RequestMapping(method=RequestMethod.DELETE,path="/{customerId}")

public void delete(@PathVariable(value="tourId")int tourId,@PathVariable(value="customerId")int customerId)

{
	TourRating tourRating=verifyTourRating(tourId,customerId);
	tourRatingRepository.delete(tourRating);
	
}

private TourRating verifyTourRating(int tourId,int customerId) throws NoSuchElementException
{
	TourRating rating=tourRatingRepository.findByPkTourIdAndPkCustomerId(tourId, customerId);
	if(rating==null)
	{
		
	throw new NoSuchElementException("tour  Rating pair for request"+tourId+"for customer"+customerId);
	
	}		

	return rating;
	
}
private RatingDto toDto(TourRating tourRating )
{
	return new RatingDto(tourRating.getScore(),tourRating.getComment(),tourRating.getPk().getCustomerId());
}
@ResponseStatus(HttpStatus.NOT_FOUND)
@ExceptionHandler(NoSuchElementException.class)
public String return400(NoSuchElementException ex)
{
	return ex.getMessage();
}
}
