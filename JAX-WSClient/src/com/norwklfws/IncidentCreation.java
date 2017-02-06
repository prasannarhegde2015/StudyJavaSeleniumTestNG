package com.norwklfws;

import com.nor.pages.HomePage;
import com.nor.pages.LoginPage;
import com.nor.testfrmk.Local;

public class IncidentCreation {
	 public void OpenBrowser()
	 {
		
		//Global.getdriverutils().InitializeDriver();
	 }
	 
	 public void performLogin(String username, String password) throws InstantiationException, IllegalAccessException, InterruptedException 
	 {
		 
		/* LoginPage lpg = new LoginPage(dr);
		 lpg.swithchtoNavFrame();
		 lpg.enterusername(username);
		 lpg.enterpassword(password);
		 lpg.clickLogin();*/
		 
		 Local.pages().getpage(LoginPage.class).swithchtoNavFrame();
		 Local.pages().getpage(LoginPage.class).enterusername(username);
		 Local.pages().getpage(LoginPage.class).enterpassword(password);
		 Local.pages().getpage(LoginPage.class).clickLogin();
		 /*
		context.local().pages().getpage(LoginPage.class).swithchtoNavFrame();
		 context.local().pages().getpage(LoginPage.class).enterusername(username);
		 context.local().pages().getpage(LoginPage.class).enterpassword(password);
		 context.local().pages().getpage(LoginPage.class).clickLogin();*/
		 
		 
	 }

	 public void clickCreateNewIncident() throws InterruptedException, InstantiationException, IllegalAccessException
	 {
		
		 Local.pages().getpage(HomePage.class).swithchtoDefault();
		 Local.pages().getpage(HomePage.class).swithchtoMainrame();
		 Local.pages().getpage(HomePage.class).waitfor10thingslink();
		 Local.pages().getpage(HomePage.class).swithchtoDefault();
		 Local.pages().getpage(HomePage.class).swithchtoNavFrame();
		 Local.pages().getpage(HomePage.class).clickIncidentLink();
		 Local.pages().getpage(HomePage.class).swithchtoDefault();
		 Local.pages().getpage(HomePage.class).swithchtoMainrame();
		 Local.pages().getpage(HomePage.class).clickCreateNew();
		 
		 
	 }
}

