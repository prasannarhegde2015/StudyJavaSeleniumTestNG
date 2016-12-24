package TESTNG.Parallel;
 /* Author: Prasanna Hegde
    Date : 31-Mar-2016
 */
import TESTNG.Parallel.Object;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
 
public class loginSquash  extends TestBase{
	Object obj = new Object();	
    @Test
    @Parameters("execremote")
   public void testLgogin(String execremote)throws Exception 
   {
		
    	obj.WritetoLog("************Test Execution Started for  Test case ==Squash  Login:************");
        getDriver(execremote).get("http://meinwessqalw01:8080/squash/login");
        obj.WritetoLog("*****Starting Add Data");
        obj.AddData("Login.xls", "TC_01", getDriver(execremote));
        Thread.sleep(3000);
        obj.WritetoLog("*Completed Add Data************");
        obj.VerifyTextPresentonPage(getDriver(execremote), "Squash Login", "step_1","logout link", "Prasanna");
        obj.captureScreenShot(getDriver(execremote), "LoginHomeSquash");
        obj.genHTMLTABLE();
        obj.WritetoLog("************Test Execution Ended for Test case ==Squash Login:************");
    }
    
   

}