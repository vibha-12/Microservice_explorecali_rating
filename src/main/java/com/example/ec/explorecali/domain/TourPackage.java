package com.example.ec.explorecali.domain;


import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class TourPackage implements Serializable  {

	 private static final Logger logger = LoggerFactory.getLogger(TourPackage.class);

	
	
@Id
private String code;
@Column
private String name;
public TourPackage(){
	 logger.debug("Log message from TourPacakge constructor");
	  
	 
	
}
public TourPackage(String code, String name) {
	super();
	this.code = code;
	this.name = name;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
@Override
public String toString()
{
	return "TourPackage("+"code='"+code+'\''+",name='"+name+'\''+')';
}

}
