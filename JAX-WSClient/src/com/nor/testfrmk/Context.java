package com.nor.testfrmk;

import org.openqa.selenium.WebDriver;

public  class Context {
	
	private static Global gbl;
	private static Local lcl;
	private static WebDriver globalDriver;
	
	public static Global global()
	{
		gbl = new Global();
		return gbl;
	}
	
	public static Local local()
	{
		System.out.println("Inside Context");
		lcl =new Local();
		return lcl;
	}
	
	
	public static WebDriver getDriver() { return globalDriver; }
	public static void setDriver(WebDriver drv) { globalDriver = drv; } 
	    
	   /* public Global  getglobal() { return this.gbl; }
	    public Local   getlocal() { return this.lcl; } */
	    

}
