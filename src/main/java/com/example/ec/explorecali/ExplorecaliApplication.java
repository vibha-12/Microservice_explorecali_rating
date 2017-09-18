package com.example.ec.explorecali;

import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.example.ec.explorecali.domain.Difficulty;
import com.example.ec.explorecali.domain.Region;
import com.example.ec.explorecali.domain.TourPackage;
//import com.example.ec.explorecali.domain.TourPackage;
import com.example.ec.explorecali.services.TourPackageService;
import com.example.ec.explorecali.services.TourService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


@EnableCircuitBreaker
@SpringBootApplication
@EnableDiscoveryClient
public class ExplorecaliApplication implements CommandLineRunner {
	@Autowired
	private TourPackageService tourPackageService;
	@Autowired
	private TourService tourService;

	private static final Logger logger = LoggerFactory.getLogger(ExplorecaliApplication.class);
	TourPackage model = new TourPackage();
	static TourPackageService service = new TourPackageService();
	
	
	public static void main(String[] args) {
		SpringApplication.run(ExplorecaliApplication.class, args);
		logger.debug("Message logged at main method");
	     service.service();
	}
	
static class TourFromFile{
		private String packageType , title,description,blurb,price,length,bullets,keywords,difficulty,region;

	static Iterable<TourFromFile> importTours() throws IOException {
		// TODO Auto-generated method stub

		return new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY).readValue(TourFromFile.class.getResourceAsStream("/ExploreCalifornia"), new TypeReference<List<TourFromFile>>(){});
	}
	
}    



	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
	/*	tourPackageService.createTourPackage("BP","backpack");
		tourPackageService.createTourPackage("a","aaaaa");
		tourPackageService.createTourPackage("b","bbbbb");
		tourPackageService.createTourPackage("c","ccccc");
		
		
		//tourService.createTour("ccc","ccccc", "cccc", 5, "cccccc", "cccccc", "ccccccc", "backpack", Difficulty.Easy, Region.Central_Coast);
		tourService.createTour("bbb","bbbbb", "bbbbb", 4, "bbbbbbbbbbbbbbb", "bbbbbbbbb", "bbbbbbbbbbbbbbbb", "aaaaa", Difficulty.Easy, Region.Central_Coast);
		tourService.createTour("bbb","bbbbb", "bbbbb", 8, "bbbbbbbbbbbbbbb", "bbbbbbbbb", "bbbbbbbbbbbbbbbb", "bbbbb", Difficulty.Easy, Region.Central_Coast);
		tourService.createTour("rrr","rrrrr", "rrrrr", 9, "rrrrrrrrrrrrrrr", "rrrrrrrrr", "rrrrrrrrrrrrrrrr", "backpack", Difficulty.Medium, Region.Nothern_California);

		tourService.createTour("bbb","bbbbb", "bbbbb", 6, "bbbbbbbbbbbbbbb", "bbbbbbbbb", "bbbbbbbbbbbbbbbb", "ccccc", Difficulty.Easy, Region.Central_Coast);
		tourService.lookup().forEach(tour->System.out.println(tour.getId()));
*/
	//TourFromFile.importTours().forEach(t->tourService.createTour(t.title,t.description,t.blurb,Integer.parseInt(t.price),"bbbbbbbbbbbbbbbb", "bbbbbbbbbbbbbbbb", "bbbbbbbbbbbbbbbb", t.packageType, Difficulty.Easy, Region.findByLabel(t.region)));
		tourPackageService.lookup().forEach(tourPackage->System.out.println(tourPackage));
		System.out.println("Number of Tours"+tourService.total());
		
		
		
	}
	@Configuration
    class Config{
    	@LoadBalanced
    	@Bean
    	public RestTemplate restTemplate(){
    		return new RestTemplate();
    	}
	
	}
}
