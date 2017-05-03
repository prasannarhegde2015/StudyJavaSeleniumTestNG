package com.nordea.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nordea.framework.Context;
import com.nordea.framework.Global;

public class IncidentPage {

	private WebDriver driver;
	public WebDriverWait wt;

	public IncidentPage() throws InterruptedException {
		this.driver = Context.getDriver();
		PageFactory.initElements(driver, this);
		wt = new WebDriverWait(this.driver, 500);
		Global.getSeleniumUtils().waitforpgload(this.driver, "Incident Page");
		Global.getSeleniumUtils().setSelDriver(this.driver);

	}

	@FindBy(id = "incident.number")
	private WebElement txtIncNumber;

	@FindBy(id = "incident.short_description")
	private WebElement txtshortdesc;

	@FindBy(id = "incident.comments")
	private WebElement txtcooments;

	@FindBy(id = "incident.impact")
	private WebElement selImpact;

	@FindBy(id = "incident.state")
	private WebElement selState;

	@FindBy(id = "sysverb_insert")
	private WebElement btnSubmit;

	@FindBy(id = "sysverb_delete")
	private WebElement btnDelete;

	@FindBy(id = "ok_button")
	private WebElement btnOKDelete;

	public void EnterIncidentNumber(String incnumber) {
		Global.getSeleniumUtils().enterValue(this.txtIncNumber, "Incident Number", incnumber);
	}

	public void EnterIncDesc(String incdec) {
		Global.getSeleniumUtils().enterValue(this.txtshortdesc, "Incident Description", incdec);
	}

	public void EnterComments(String inccmt) {
		Global.getSeleniumUtils().enterValue(this.txtcooments, "Incident Details", inccmt);
	}

	public void SelectImpact(String selimp) {
		Global.getSeleniumUtils().selectValue(this.selImpact, "Impact", selimp);
	}

	public void SelectState(String selimp1) {
		Global.getSeleniumUtils().selectValue(this.selState, "State", selimp1);
	}

	public void clickSubmit() throws InterruptedException {
		Global.getSeleniumUtils().clickElem(this.btnSubmit, "Submit");
	}

	public void Clickrdynalink(String Dynaval) throws InterruptedException {
		Global.getSeleniumUtils().clickElemByLinkText(this.driver, Dynaval, " Newly Created Dynamic Link");

	}

	public void verifynumber(String Dynaval) {

		String acttext = Global.getSeleniumUtils().getText(this.txtIncNumber, "Incident Number");
		Global.gethelperutils().creatextentReport("TC_01_step_01;Incident Number;" + Dynaval + ";" + acttext + ";");
		// colnamesarr, colvals, screenshot_path);

	}

	public void ClickDeleteButton() throws InterruptedException {
		Global.getSeleniumUtils().clickElem(this.btnDelete, "Delete Button");
	}

	public void ClickConfirmDeleteButton() throws InterruptedException {
		Global.getSeleniumUtils().waitforPresenseofElement(this.driver, this.btnOKDelete, "Confirm Delete Button");
		Global.getSeleniumUtils().clickElem(this.btnOKDelete, "Confirm Delete Button");
	}
}
