package com.nor.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nor.testfrmk.Context;
import com.nor.testfrmk.Global;

public class HomePage {
	
	
	private WebDriver driver;
	public WebDriver getDriver() { return this.driver; }
	public void setDriver(WebDriver drv) { this.driver = drv;} 
	
	@FindBy(linkText="Incidents")
	private WebElement lnkIncidents;
	
	@FindBy(id="sysverb_new")
	private WebElement btnNew;
		
	@FindBy(xpath="//iframe[@title='Navigation Content']")
	private WebElement frmMainPane;

	@FindBy(xpath="//iframe[@title='Main menu']")
	private WebElement frmleftNav;
	
	@FindBy(linkText="10 Things")
	private WebElement lnktenthings;
	
	public WebDriverWait wt ;
	public HomePage() throws InterruptedException
	{
		this.driver=Context.getDriver();
		PageFactory.initElements(driver, this);
		wt = new WebDriverWait(this.driver, 500);
		Global.getSeleniumUtils().waitforpgload(this.driver);
		System.out.println("** Out side Constrcutor homepage");
	}


	public void swithchtoNavFrame()
	{
		Global.getSeleniumUtils().Switchtoframe(this.driver, this.frmleftNav);
	}
	public void swithchtoMainrame()
	{
		System.out.println("** Looking for Mainframe");
		Global.getSeleniumUtils().Switchtoframe(this.driver, this.frmMainPane);
		System.out.println("** Switched to Mainframe");
		//context.global().getSeleniumUtils().Switchtoframe(this.frmleftNav);
	}
	
	public void swithchtoDefault()
	{
		System.out.println("Switch to Default Frame: Home Page");
		Global.getSeleniumUtils().SwitchtoDelfault(this.driver);
	}
	
	public void clickIncidentLink(){
		
     Global.getSeleniumUtils().clickElem(this.lnkIncidents, "Incidents Link");

	}
	
	public  void clickCreateNew()
	{
		Global.getSeleniumUtils().clickElem(this.btnNew,"New Button");
	}
	public void waitfor10thingslink(){
		System.out.println("Waiting for 10 thing slink");
		Global.getSeleniumUtils().waitforPresenseofElement(this.driver, this.lnktenthings);

		}
	
	

}
