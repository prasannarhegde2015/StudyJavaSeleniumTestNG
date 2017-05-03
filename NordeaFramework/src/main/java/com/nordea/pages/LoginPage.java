package com.nordea.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nordea.framework.Context;
import com.nordea.framework.Global;

public class LoginPage {

	private WebDriver driver;

	public WebDriver getDriver() {
		return this.driver;
	}

	public void setDriver(WebDriver drv) {
		this.driver = drv;
	}

	@FindBy(id = "user_name")
	private WebElement txtUsername;

	@FindBy(id = "user_password")
	private WebElement txtPassword;

	@FindBy(id = "sysverb_login")
	private WebElement btnLogin;
	@FindBy(name = "gsft_main")
	private WebElement frmleftNav;

	// private Context context = new Context();

	public LoginPage() throws InterruptedException {
		this.driver = Context.getDriver();
		PageFactory.initElements(driver, this);
		Global.getSeleniumUtils().waitforpgload(this.driver, "Login Page");
		Global.getSeleniumUtils().setSelDriver(this.driver);
	}

	public void swithchtoNavFrame() {
		Global.getSeleniumUtils().Switchtoframe(this.driver, this.frmleftNav, "LoginFrame");

	}

	public void swithchtoDefault() {
		Global.getSeleniumUtils().SwitchtoDelfault(this.driver);
	}

	public void clickLogin() throws InterruptedException {

		Global.getSeleniumUtils().clickElem(this.btnLogin, "Login button");

	}

	public void enterusername(String UserName) {

		Global.getSeleniumUtils().enterValue(this.txtUsername, "UserName", UserName);
	}

	public void enterpassword(String Password) {
		Global.getSeleniumUtils().enterValue(this.txtPassword, "Password", Password);

	}

}
