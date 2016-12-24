package TESTNG.Parallel;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
 /* Author: Prasanna Hegde
    Date : 31-Mar-2016
 */
public class loginActiTime extends TestBase{
	Object obj = new Object();	
    @Test
    @Parameters("execremote")
    public void testLgogin(String execremote)throws Exception{
    	obj.WritetoLog("************Test Execution Started for Test case :Actititme  Login:************");
        getDriver(execremote).get("http://meinwessqalw:8080/login.do");
        obj.WritetoLog("Starting Add Data");
        obj.AddData("LoginActiTime.xls", "TC_01", getDriver(execremote));
        obj.WritetoLog("*Completed Add Data************");
        Thread.sleep(3000);
        obj.VerifyTextPresentonPage(getDriver(execremote), "ActiTime Login", "step_1","logout link", "Prasanna R. Hegde");
        obj.captureScreenShot(getDriver(execremote), "LoginHomeActiTime");
        Thread.sleep(2000);
        obj.genHTMLTABLE();
        obj.WritetoLog("************Test Execution Ended for Test case : Actititme  Login:************");
    }
 
}