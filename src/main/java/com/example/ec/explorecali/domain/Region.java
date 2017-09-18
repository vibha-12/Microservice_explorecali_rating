package com.example.ec.explorecali.domain;

public enum Region {
Central_Coast("Central Coast"), Southern_California("Sothern California"), Nothern_California("Northern California") , Varies("Varies") ;
	private String Label;
	private Region (String label){
		this.Label=label;
	}
	public static Region findByLabel(String by_Label)
	{
		for(Region r:Region.values())
		{
			if(r.Label.equalsIgnoreCase(by_Label))
				return r;
		}
		return null;
	}
}
