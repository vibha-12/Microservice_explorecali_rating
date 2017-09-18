package com.example.ec.explorecali.tests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.example.ec.explorecali.ExplorecaliApplicationTests;
import com.example.ec.explorecali.domain.TourPackage;
import com.example.ec.explorecali.services.TourPackageService;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;



@Transactional
public class TourPackageTest extends ExplorecaliApplicationTests {
	@Autowired
	private TourPackageService tourPackageService;
	
@Before
public void setUp()
{
	//tourPackageService.evictCache();
}
@After
public void tearDown()
{
	
}
	
	@Test
	public void testlookup() throws Exception {

		Iterable<TourPackage> tourPkg=tourPackageService.lookup();
		Assert.assertNotNull(tourPkg);
		
	}
	


}