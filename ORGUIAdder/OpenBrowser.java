package OpenDevtoolbar;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.swing.*;
import javax.xml.bind.JAXBElement.GlobalScope;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
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
	private JComboBox<String> cmblocator;
	public UpdateDataInExcel csvlog ;
    public String logPath = "D:\\Prasanna\\OR.csv" ;
	public static String CHROMEdriverpath = "D:\\Users\\Prasanna\\Automation\\Selenium\\Selenium Jar and Drivers\\chromedriver_win32\\chromedriver.exe";
	public static WebDriver globalDriver = null;
	public static void main(String[] args)
	{

		OpenBrowser ob = new OpenBrowser();
		ob.createUI();
	}



	private void createUI()
	{
		MainFrame = new JFrame("Create OR");
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

		cmblocator = new JComboBox<String>();
		cmblocator.addItem("id");
		cmblocator.addItem("name");
		cmblocator.addItem("xpath");
		cmblocator.addItem("cssselector");
		cmblocator.addItem("classname");
		cmblocator.addItem("linktext");
		cmblocator.addItem("partiallinktext");
		cmblocator.addItem("tagname");

		Object cmboitem = cmblocator.getSelectedItem();

		update = new JButton("Update");
		update.addActionListener(new ButtonClickListener());
		update.setActionCommand("Update");
		updatepanel.add(lblLogicalName);
		updatepanel.add(txtLogicalName);

		updatepanel.add(lblLocator);
		updatepanel.add(cmblocator);

		updatepanel.add(lblLocatorValue);
		updatepanel.add(txtLocatorValue);

		updatepanel2.add(update);
		MainFrame.add(Panel1);
		MainFrame.add(updatepanel);
		MainFrame.add(updatepanel2);
		MainFrame.setVisible(true);
	//	txtLogicalName.addKeyListener(new CustomKeyListener());
	/*	KeyboardFocusManager.getCurrentKeyboardFocusManager()
		  .addKeyEventDispatcher(new KeyEventDispatcher() {
		      @Override
		      public boolean dispatchKeyEvent(KeyEvent e) {
		        System.out.println("Got key event!"+e.getKeyChar());
		        return false;
		      }
		}); */
		
		MainFrame.addKeyListener(new CustomKeyListener()) ;
		MainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}        
		});    
	}

	public  class ButtonClickListener implements ActionListener{
		public void actionPerformed(ActionEvent e) { 
			csvlog = new UpdateDataInExcel();
			strurl =url.getText();
		//	System.out.println("system URL is "+ strurl);
			if ( e.getActionCommand().equals("Open"))
			{
				if (strurl.length() == 0)
				{
					ShowPopupMessage("Please Enter Valid URL to open", "Missing URL");
					return;
				}
				if (strurl.contains("http") == false)
				{
					ShowPopupMessage("URL is Invalid Please Enter Correct URL", "Icorrect URL");
					return;
				}
				try {
					setupdriver(strurl);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				swithctowindow(globalDriver, "ServiceNow");
				WebElement curfrm = globalDriver.switchTo().activeElement();
				String currentFrame = curfrm.getAttribute("id");
				globalDriver.switchTo().defaultContent();
				System.out.println("Current Frame Name: "+currentFrame);
				globalDriver.switchTo().frame(currentFrame);
			//	getCurrentCurosorPosition(globalDriver);
			//	globalDriver.switchTo().frame(0);
				//	 int[] arrkes = new int[]{ KeyEvent.VK_CONTROL, KeyEvent.VK_SHIFT, KeyEvent.VK_I};
				//	 sendkeysplus(arrkes);
				/* sendkeys_press(KeyEvent.VK_CONTROL);
	        	 sendkeys_press(KeyEvent.VK_SHIFT);
	        	 sendkeys_press(KeyEvent.VK_I);
	        	 sendkeys_relase(KeyEvent.VK_I);
	        	 sendkeys_relase(KeyEvent.VK_SHIFT);
	        	 sendkeys_relase(KeyEvent.VK_CONTROL);*/



			}
			if ( e.getActionCommand().equals("Update"))
			{

				System.out.println("Outside Frames Check:");
				getCurrentCurosorPosition(globalDriver);
				globalDriver.switchTo().defaultContent();
				List<WebElement> allframescount = globalDriver.findElements(By.tagName("iframe"));
				System.out.println("Number of Framess:"+allframescount.size());
				if (allframescount.size() > 0 )
				{
				//	JavascriptExecutor jsExecutor = (JavascriptExecutor)globalDriver;
				// currentFrame = (String) jsExecutor.executeScript("return self.name");
					System.out.println("Inside Frames Check:");
					//System.out.println("Current Frame Name: "+currentFrame);
					WebElement curfrm = globalDriver.switchTo().activeElement();
					String currentFrame = curfrm.getAttribute("id");
					globalDriver.switchTo().defaultContent();
					System.out.println("Current Frame Name: "+currentFrame);
					globalDriver.switchTo().frame(currentFrame);
				}
				System.out.println("Swithicng to active element "+ strurl);
				WebElement curleem = globalDriver.switchTo().activeElement();
				System.out.println("Option Selected" +cmblocator.getSelectedItem().toString());
				switch (cmblocator.getSelectedItem().toString()) {
				case "id": {
					String strlocvalue = curleem.getAttribute("id");
					String strfieldName = txtLogicalName.getText();
					txtLocatorValue.setText(strlocvalue);
					try {
						csvlog.createCSVFile(logPath, "FieldName;Locator;LocatorValue", strfieldName+";id;"+strlocvalue);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				}
				case "name": {
					String strlocvalue = curleem.getAttribute("name");
					String strfieldName = txtLogicalName.getText();
					txtLocatorValue.setText(strlocvalue);
					try {
						
						csvlog.createCSVFile(logPath, "FieldName;Locator;LocatorValue", strfieldName+";name;"+strlocvalue);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				}
				case "tagname": {
					String strlocvalue = curleem.getAttribute("tagname");
					txtLocatorValue.setText(strlocvalue);
					try {
						csvlog.createCSVFile(logPath, "Locator;LocatorValue", "tagname;"+strlocvalue);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				}
				case "classname": {
					String strlocvalue = curleem.getAttribute("classname");
					txtLocatorValue.setText(strlocvalue);
					try {
						csvlog.createCSVFile(logPath, "Locator;LocatorValue", "classname;"+strlocvalue);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				}
				case "linktext": {
					String strlocvalue = curleem.getText();
					String strfieldName = txtLogicalName.getText();
					txtLocatorValue.setText(strlocvalue);
					try {
						csvlog.createCSVFile(logPath, "FieldName;Locator;LocatorValue", strfieldName+";Linktext;"+strlocvalue);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				}

				default:
					ShowPopupMessage("Invalid selector", "Missing inputs");
				}
				

			}
		}		
	}

	public static void  setupdriver(String baseurl) throws InterruptedException
	{

		System.setProperty("webdriver.chrome.driver", CHROMEdriverpath);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("chrome.switches","--disable-extensions");
		globalDriver= new ChromeDriver(options);
		globalDriver.manage().window().maximize();
		globalDriver.get(baseurl);
		Thread.sleep(15000);
		System.out.println("Trying to detect mouse event focus");
		getCurrentCurosorPosition(globalDriver);
		
		

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

    public class CustomKeyListener implements KeyListener {
    	public void keyTyped(KeyEvent e) {           
        }
    	/** Handle the key-pressed event from the text field. */
    	public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_SHIFT){
            	System.out.println("Key action was invkoded");
             //  getCurrentCurosorPosition(globalDriver);
            }
         }
		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
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
	
	public static void getCurrentCurosorPosition(WebDriver drv)
	{
		int x = (int) MouseInfo.getPointerInfo().getLocation().getX();
    	int y = (int) MouseInfo.getPointerInfo().getLocation().getY();
    	
    	//org.openqa.selenium.Point point = new Point(x, y);
    	JavascriptExecutor jse = (JavascriptExecutor)drv;
    	String strjsscript = "var elem = document.elementFromPoint("+x+","+y+"); return elem.getAttribute('id');";
    	String elemid = (String)jse.executeScript(strjsscript);
	    System.out.println("From java script mouse position===> "+elemid);
	}
	
	

}   



