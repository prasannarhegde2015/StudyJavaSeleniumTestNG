package com.nor.testsuite;



import org.junit.Test;
import com.nor.testfrmk.Context;
import com.nor.testfrmk.Global;
import com.nor.testfrmk.Local;
import com.norwklfws.IncidentCreation;

public class Release1 {
	
	
	
	@Test
	public void test() throws InstantiationException, IllegalAccessException, InterruptedException
	{
	 Global.getdriverutils().InitializeDriver();
	 Local.workflows().getworkflow(IncidentCreation.class).performLogin("admin","ServiceNow97bd916$");
	 Local.workflows().getworkflow(IncidentCreation.class).clickCreateNewIncident();
	 Context.getDriver().quit();
	}

}
