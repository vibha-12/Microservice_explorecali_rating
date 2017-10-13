package com.example.ec.explorecali.tests;


import org.apache.catalina.filters.CorsFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import com.example.ec.explorecali.ExplorecaliApplicationTests;
import com.example.ec.explorecali.domain.Difficulty;
import com.example.ec.explorecali.domain.Region;
import com.example.ec.explorecali.domain.Tour;
import com.example.ec.explorecali.domain.TourPackage;
import com.example.ec.explorecali.domain.TourRating;
import com.example.ec.explorecali.domain.TourRatingPk;
import com.example.ec.explorecali.repo.TourRatingRepository;
import com.example.ec.explorecali.web.TourRatingController;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.verify;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TourPackageContTest extends ExplorecaliApplicationTests {
	
	@Autowired
	@Mock
    private TourRatingRepository todoServiceMock;

    @InjectMocks
    private TourRatingController userController;

	private MockMvc mockMvc;
	 
	@Autowired
    private WebApplicationContext wac;
   
   @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        
     //   mockMvc = MockMvcBuilders.standaloneSetup(userController).addFilters(new CorsFilter()).build();
      this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        }
    @Test
    public void test_get_by_id_success() throws Exception {
    TourPackage tourPackage=new TourPackage("BP","backpack"); 
    Tour tour=new Tour(1,"ccc","ccccc", "cccc", 5, "cccccc", "cccccc", "ccccccc", tourPackage, Difficulty.Easy, Region.Central_Coast);
    	
  	TourRatingPk tourRatingPk=new TourRatingPk(tour,1);
    List< TourRating> users = Arrays.asList(new  TourRating(tourRatingPk, 5, "It was Ok"),new  TourRating(tourRatingPk, 4, "Nice"));
    	  
        when(todoServiceMock.findByPkTourId(1)).thenReturn(users);
        
        this.mockMvc.perform(get("/tours/{tourId}/ratings", 1)).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andDo(print());
        mockMvc.perform(get("/tours/{tourId}/ratings", 1))
              .andExpect(status().isOk())
              .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
              .andExpect(jsonPath("$[0].score").value(5))
              .andExpect(jsonPath("$[0].customerId").value(5))
              .andExpect(jsonPath("$[0].comment").value("good"))
              .andExpect(jsonPath("$[1].customerId").value(4))     //way of comparison i.e. tourRatingPk.getCustomerId()
              .andExpect(jsonPath("$[1].score").value(5))
              .andExpect(jsonPath("$[1].comment").value("good"));
               //verify(todoServiceMock,times(1)).findByPkTourId(1);
              verifyNoMoreInteractions(todoServiceMock);
    }
    }


