package OpenDevtoolbar;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.swing.*;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.security.UserAndPassword;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.reporters.jq.Main;

import GmailTest.Test1;
import GmailTest.Test2;

public class OpenBrowser {
	
	private JFrame MainFrame ;
	private JPanel Panel1 ;
	private JPanel updatepanel;
	private JPanel updatepanel2;
	private JRadioButton openbrowser ;
	private JButton open ;
	private JTextField url ;
	private JLabel urllabel;
	public String strurl = "";
	private JLabel lblLogicalName;
	private JLabel lblLocator;
	private JLabel lblLocatorValue;
	private JTextField txtLogicalName;
	private JTextField txtLocator;
	private JTextField txtLocatorValue;
	private JButton update;
	
	public static String CHROMEdriverpath = "D:\\Users\\Prasanna\\Automation\\Selenium\\Selenium Jar and Drivers\\chromedriver_win32\\chromedriver.exe";
	public static WebDriver globalDriver = null;
	public static void main(String[] args)
	{
	  
	  OpenBrowser ob = new OpenBrowser();
	  ob.createUI();
	}

	
	
   private void createUI()
	{
		MainFrame = new JFrame("Update UI");
		MainFrame.setSize(400,400);
		MainFrame.setLayout(new GridLayout(3, 3));
		
		Panel1 = new JPanel();
		updatepanel = new JPanel();
		updatepanel.setLayout(new GridLayout(3, 3));
		updatepanel2 = new JPanel();
		openbrowser = new JRadioButton("Open Browser");
		open = new JButton("Open");
		open.setActionCommand("Open");
		urllabel = new JLabel();
		urllabel.setText("Enter URL to Open");
		url = new JTextField(20);
	    url.setPreferredSize(new Dimension(20, 20));
	    open.addActionListener(new ButtonClickListener());
		 
     //   Panel1.setLayout(new BoxLayout( Panel1, BoxLayout.Y_AXIS));
		Panel1.add(urllabel);
		Panel1.add(url);
		Panel1.add(openbrowser);
		Panel1.add(open);
		lblLogicalName = new JLabel("Logical Name");
		lblLocatorValue = new JLabel("Locator Value");
		lblLocator = new JLabel("Locator");
		txtLogicalName = new JTextField(20);
		txtLogicalName.setPreferredSize(new Dimension(20, 20));
		txtLocator = new JTextField(20);
		txtLocatorValue = new JTextField(20);
        update = new JButton("Update");
        update.addActionListener(new ButtonClickListener());
        update.setActionCommand("Update");
		updatepanel.add(lblLogicalName);
		updatepanel.add(txtLogicalName);
		
		updatepanel.add(lblLocator);
		updatepanel.add(txtLocator);
		
		updatepanel.add(lblLocatorValue);
		updatepanel.add(txtLocatorValue);
		
		updatepanel2.add(update);
		MainFrame.add(Panel1);
		MainFrame.add(updatepanel);
		MainFrame.add(updatepanel2);
		MainFrame.setVisible(true);
		MainFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
		        System.exit(0);
	         }        
	      });    
	}

   public  class ButtonClickListener implements ActionListener{
	      public void actionPerformed(ActionEvent e) { 
	    	  strurl =url.getText();
	         System.out.println("system URL is "+ strurl);
	         if ( e.getActionCommand().equals("Open"))
	         {
	        	 if (strurl.length() == 0)
	        	 {
	        		 ShowPopupMessage("Please Enter Valid URL to open", "Missing URL");
	        		 return;
	        	 }
	        	 if (strurl.contains("http") == false)
	        	 {
	        		 ShowPopupMessage("URL is Invalid Please Enter Correct URL", "Missing URL");
	        		 return;
	        	 }
	        	 setupdriver(strurl);
	        	 swithctowindow(globalDriver, "Atlassian Cloud");
	        	 int[] arrkes = new int[]{ KeyEvent.VK_CONTROL, KeyEvent.VK_SHIFT, KeyEvent.VK_I};
	        	 sendkeysplus(arrkes);
	        	/* sendkeys_press(KeyEvent.VK_CONTROL);
	        	 sendkeys_press(KeyEvent.VK_SHIFT);
	        	 sendkeys_press(KeyEvent.VK_I);
	        	 sendkeys_relase(KeyEvent.VK_I);
	        	 sendkeys_relase(KeyEvent.VK_SHIFT);
	        	 sendkeys_relase(KeyEvent.VK_CONTROL);*/
	         }
	         if ( e.getActionCommand().equals("Update"))
	         {
	        	 if ( (txtLocator.getText().length() == 0) || (txtLocatorValue.getText().length() == 0) || (txtLogicalName.getText().length() == 0) )
	        	 {
	        		 ShowPopupMessage("No Inputs Provided to Update", "Missing inputs");
	        	 }
	         }
	      }		
	   }
 
   public static void  setupdriver(String baseurl)
	{
		
		System.setProperty("webdriver.chrome.driver", CHROMEdriverpath);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("chrome.switches","--disable-extensions");
		globalDriver= new ChromeDriver(options);
		globalDriver.manage().window().maximize();
		globalDriver.get(baseurl);
		
	}

   public void swithctowindow(WebDriver drv , String title)
    
   {
	   Set<String> allwins =  drv.getWindowHandles();
	   for(String wind:allwins)
	   {
		   if (wind.equals(title))
		   {
			   drv.switchTo().window(wind);
		   }
	   }
   }
   

   public void sendkeys_press( int key)
   {
	   try {
	        Robot robot = new Robot();

	        // Simulate a mouse click
	    //    robot.mousePress(InputEvent.BUTTON1_MASK);
	    //    robot.mouseRelease(InputEvent.BUTTON1_MASK);

	        // Simulate a key press
	        robot.keyPress(key);
	     //   robot.keyRelease(KeyEvent.VK_A);

	} catch (AWTException e) {
	        e.printStackTrace();
	}
   }
   
   public void sendkeys_relase( int key)
   {
	   try {
	        Robot robot = new Robot();

	        // Simulate a mouse click
	    //    robot.mousePress(InputEvent.BUTTON1_MASK);
	    //    robot.mouseRelease(InputEvent.BUTTON1_MASK);

	        // Simulate a key press
	     //   robot.keyPress(key);
	        robot.keyRelease(key);

	} catch (AWTException e) {
	        e.printStackTrace();
	}
   }
 
   
   public void sendkey(int key)
   {
	   sendkeys_press(key);
	   sendkeys_relase(key);
   }
   
   public void sendkeysplus(int[] keys)
   {
	   for(int k:keys)
	   {
		   sendkeys_press(k);
	   }
	   for(int k:keys)
	   {
		   sendkeys_relase(k);
	   }
   }
   
   
   public void ShowPopupMessage(String Messsage, String MesssagTitle)
   {
	   JOptionPane.showMessageDialog(MainFrame, Messsage, MesssagTitle,1);
   }
   
   public void enterusernamepassword(WebDriver drv, String username, String password)
   {
	   WebDriverWait wait = new WebDriverWait(drv, 500);
	   Alert alert = wait.until(ExpectedConditions.alertIsPresent());
	   alert.authenticateUsing(new UserAndPassword(username, password));
   }
   
}   


	   
