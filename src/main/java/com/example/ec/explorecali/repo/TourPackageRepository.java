package com.example.ec.explorecali.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.ec.explorecali.domain.TourPackage;


public interface TourPackageRepository extends CrudRepository<TourPackage,String> {
	TourPackage findByName(@Param("name")String name);
	TourPackage findByCode(@Param("code")String code);
}
