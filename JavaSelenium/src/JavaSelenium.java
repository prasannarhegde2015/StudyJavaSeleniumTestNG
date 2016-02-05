import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
//import org.openqa.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.By.ByLinkText;
import org.openqa.selenium.firefox.FirefoxDriver;

//Read more: http://mrbool.com/reading-excel-file-with-java/24562#ixzz2FET7Wbwz
public class JavaSelenium {

	/**
	 * @param args
	 */

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        File ff = new File("C:/Users/Sony/Desktop/dat.xls");
         if (!ff.exists())
         {
        	 
        	 JOptionPane.showMessageDialog(null, "File Not found"+ff.toString() , "InfoBox: " + "File Missing", JOptionPane.INFORMATION_MESSAGE);
         
        	 return;
         }
		 addFromexcel("C:/Users/Sony/Desktop/dat.xls");
		// deleteFromExcel("C:/Users/Sony/Desktop/dat.xls");
	}

	public static void writeTextFile(String strtestcase,String strdetails,String strstatus) {
		File file = new File("c:\\Report.CSV");
		// if file doesn't exists, then create it
		if (!file.exists()) {
			try {
				System.out.println("File is  not there Creating File");
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// true = append file
		FileWriter fileWritter = null;
		try {
			System.out.println("Creating File Write Instance");
			fileWritter = new FileWriter(file, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}
         
		BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
		Reader reader=null;
		 try {
			 System.out.println("Creating File Reader instance");
			reader = new FileReader(file);
			System.out.println("Created File Reader instance");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1);
			e1.printStackTrace();
		}
		BufferedReader buffreader = new BufferedReader(reader);
		System.out.println("Created Buffer Reader instance");
		try {
			System.out.println("Buffer ReaderLenght");
			int streeamlength=0;
			try
			{
				streeamlength=buffreader.readLine().toString().length();
			}
			catch (Exception e2)
			{
				streeamlength=0;
			}
			System.out.println("Buffer ReaderLenght"+streeamlength);
			{
			if ( streeamlength == 0) 
			{
				bufferWritter.append( '\u0022' + "test case" + '\u0022' + ","+'\u0022' + "Details" + '\u0022' + ","+'\u0022' + "Status" + '\u0022' + ",");
				try {
					bufferWritter.newLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			bufferWritter.append('\u0022' + strtestcase + '\u0022' + ","+'\u0022' + strdetails + '\u0022' + ","+'\u0022' + strstatus + '\u0022' + ",");
		//	try {
		//		bufferWritter.newLine();
		//	} catch (IOException e) {
				// TODO Auto-generated catch block
		//		e.printStackTrace();
		//	}
		}  
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bufferWritter.newLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bufferWritter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	public static void addFromexcel(String fileName) {
		Connection c = null;
		java.sql.Statement stmnt = null;
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			c = DriverManager
					.getConnection("jdbc:odbc:DRIVER={Microsoft Excel Driver (*.xls, *.xlsx, *.xlsm, *.xlsb)};DBQ="+fileName+";");
			stmnt = c.createStatement();
			String query = "select * from [sheet1$]";
			ResultSet rs = stmnt.executeQuery(query);
			WebDriver driver = new FirefoxDriver();
			driver.get("http://phegde");
			driver.findElement(By.linkText("PHP Samples")).click();
			driver.findElement(By.linkText("Create Web List.")).click();
			int cntr = 0;
			while (rs.next()) {
				String fn = rs.getString(getIndexonName(rs, "FN"));
				String ln = rs.getString(getIndexonName(rs, "LN"));
				String em = rs.getString(getIndexonName(rs, "EM"));
				String ph = rs.getString(getIndexonName(rs, "PH"));
				String ad = rs.getString(getIndexonName(rs, "AD"));

				driver.findElement(By.name("firstname")).sendKeys(fn);
				driver.findElement(By.name("lastname")).sendKeys(ln);
				driver.findElement(By.name("email")).sendKeys(em);
				driver.findElement(By.name("phone")).sendKeys(ph);
				driver.findElement(By.name("address")).sendKeys(ad);
				driver.findElement(By.name("btnsubmit")).click();

				boolean su1 = driver.getPageSource().contains(
						"have been recorded");
				if (su1 == true) {
					writeTextFile("testcase" + (cntr + 1),
							"Data Got inderted for " + fn, "Pass");
				} else

				{ 
					writeTextFile("testcase" + (cntr + 1),
							"Data Got inderted for " + fn, "Fail");
				}
				driver.findElement(By.linkText("Click Here")).click();

				driver.findElement(By.linkText("Index page")).click();

				driver.findElement(By.linkText("Create Web List.")).click();
				cntr++;
			}
			driver.close();
			driver.quit();
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			try {
				stmnt.close();
				c.close();
			} catch (Exception e) {
				System.err.println(e);
			}
		}
	}

	public static void deleteFromExcel(String fileName) {

		Connection c = null;
		java.sql.Statement stmnt = null;

		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

			// using DSN connection. Here qa is the name of DSN
			// c = DriverManager.getConnection( "jdbc:odbc:qa", "", "" );

			// using DSN-less connection
			c = DriverManager
					.getConnection("jdbc:odbc:DRIVER={Microsoft Excel Driver (*.xls, *.xlsx, *.xlsm, *.xlsb)};DBQ="+fileName+";");

			stmnt = c.createStatement();
			String query = "select * from [sheet1$]";
			ResultSet rs = stmnt.executeQuery(query);

			WebDriver driver = new FirefoxDriver();
			driver.get("http://phegde/display.php");

			while (rs.next()) {
				String fn = rs.getString(getIndexonName(rs, "FN"));

				String fnamexpath = "(//input[@name='firstname'])[1]";
				driver.findElement(By.xpath(fnamexpath)).sendKeys(fn);
				driver.findElement(By.name("btndelete")).click();
				driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

			}

			driver.close();
			driver.quit();
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			try {
				stmnt.close();
				c.close();
			} catch (Exception e) {
				System.err.println(e);
			}
		}

	}

	public static int getIndexonName(ResultSet r1, String colName) {
		int Colindex = 0;
		try {
			Colindex = r1.findColumn(colName);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Colindex;
	}

}







