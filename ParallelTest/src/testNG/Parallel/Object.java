package TESTNG.Parallel;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Object {

	public enum controlTypeenum {
		textbox, button, radiobutton, dropdown, link, frame, textarea, checkbox , webelem;
	}

	StringBuilder sb = new StringBuilder();

	public int cntr = 1;
	public int rptcntr = 1;

	public WebElement getControl(WebDriver drv, controlTypeenum ct,
			String locator, String localtorvalue, String index)
			throws Exception {
		WebElement getControl = null;
		List<WebElement> allweblemscollection = null;
		WebElement tempelem = null;
		switch (locator.toLowerCase()) {
		case "name": {
			try {
				tempelem = drv.findElement(By.name(localtorvalue));
			} catch (StaleElementReferenceException e) {
				tempelem = null;
			} catch (NoSuchElementException e) {
				tempelem = null;
			} catch (Exception e) {
				tempelem = null;
			}
			// Switch for individual control Types .............
			getControl = CheckForControlType(tempelem, ct);

			break;
		}

		case "id": {
			try {
				tempelem = drv.findElement(By.id(localtorvalue));
			} catch (StaleElementReferenceException e) {
				tempelem = null;
			} catch (NoSuchElementException e) {
				tempelem = null;
			} catch (Exception e) {
				tempelem = null;
			}
			getControl = CheckForControlType(tempelem, ct);
			break;
		}
		case "xpath": {
			WritetoLog("Trying to Find by Xpath..");
			try 
			{
				tempelem = drv.findElement(By.xpath(localtorvalue));
		    } catch (StaleElementReferenceException e) {
		    	WritetoLog("Got Stale Eleemnt , elem not attached to page Exception");
			tempelem = null;
		    } catch (NoSuchElementException e) {
			tempelem = null;
			WritetoLog("Got No Such Element  Exception");
		    } catch (Exception e) {
		    	WritetoLog("Got Some other ... Exception");
			tempelem = null;
		    }
			getControl = CheckForControlType(tempelem, ct);
			break;
		}
		case "classname": {
			getControl = drv.findElement(By.className(localtorvalue));
			break;
		}
		case "cssselector": {
			getControl = drv.findElement(By.cssSelector(localtorvalue));
			break;
		}
		case "linktext": {
			getControl = drv.findElement(By.linkText(localtorvalue));
			break;
		}
		case "partiallinktext": {
			getControl = drv.findElement(By.partialLinkText(localtorvalue));
			break;
		}
		case "value": {
			WritetoLog("Finding contol by new locator Value");
			
			allweblemscollection = drv.findElements(By.tagName("Input"));
			WritetoLog("input tag colection count: "+allweblemscollection.size());
			for ( WebElement indcntl:allweblemscollection)
			{
				 if (indcntl.getAttribute("value")!= null)
                 {
					 WritetoLog("Foudn elem with tagane input and attribute value ="+indcntl.getAttribute("value"));
				if (indcntl.getAttribute("value").equalsIgnoreCase(localtorvalue) )
				{
					tempelem  = indcntl ;
				}
                 }
				
			}
			
			getControl = CheckForControlType(tempelem, ct);
			break;
		}
		
        default : 
        {
        WritetoLog("Invalid Locator Value Passed Vlaid are name,id ,xpath classname, cssselector, linktext partiallinktext...");
           break;
        }
		}

		return getControl;
	}

	public void AddData(String fileName, String testcase, WebDriver drv)
			throws Exception {
		WritetoLog("Inside Add Data");
		String projectPath = System.getProperty("user.dir");
		WritetoLog("projectPath: " + projectPath);
		// String configPath = projectPath + "\\CommonResources\\Config.xls";
		String testDataPath = projectPath + "\\TestData\\";
		WritetoLog("testdata path: " + testDataPath);
		File ff = new File(testDataPath + fileName);
		if (!ff.exists()) {
			JOptionPane.showMessageDialog(null,
					"File Not found" + ff.toString(), "InfoBox: "
							+ "File Missing", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		List<Map<String, String>> rsstructure = getResultSetPOI(testDataPath
				+ fileName, "Structure", "", "");

		List<Map<String, String>> rsData = getResultSetPOI(testDataPath
				+ fileName, "Data", "testcase", testcase);
		WritetoLog("Number of rows in structure sheet: " + rsstructure.size());
		WritetoLog("Number of rows in data sheet: " + rsData.size());
		String controlType, searchBy, searchValue, fieldName, controlValue;
		String index = null;
		if (rsstructure != null && rsData != null) {
			WritetoLog("starting Iteration.... ");
			int ircount = 0;
			int iracount = 1;
			for (Map<String, String> irow : rsstructure) {
				WritetoLog("Inside Iteration.... ");
				WritetoLog("Iterating  row number dummy .... " + ircount);
				if (ircount == 0) {
					ircount++;
					continue;
				}
				WritetoLog("Iterating  row number  Real.... " + iracount);
				controlType = irow.get("controlType").toString();
				WritetoLog("controltype value.... " + controlType);
				searchBy = irow.get("searchBy").toString();
				searchValue = irow.get("searchValue").toString();
				WritetoLog("Getting Index..");
				if (irow.get("index") != null) {
					index = irow.get("index").toString();
				}
				WritetoLog("Got Index..");
				fieldName = irow.get("fieldName").toString();
				WritetoLog("Gettting control name... ");
				controlValue = rsData.get(0).get(fieldName).toString();
				WritetoLog("Gettting control name... " + controlValue);
				String cdetails = "Control Type : " + controlType
						+ " FieldName: " + fieldName + " ControlLocator: "
						+ searchBy + " Control Locator Value: " + searchValue
						+ " ControlData : " + controlValue;
				WritetoLog("Prparing for " + cdetails);
				switch (controlType.toLowerCase()) {
				case "textbox":

				{
					WritetoLog("Inside Text Box case");
					if (controlValue.length() > 0) {
						WritetoLog("Trying to Find Control TextBox...");
						if (getControl(drv, controlTypeenum.textbox, searchBy,
								searchValue, index) != null) {
							getControl(drv, controlTypeenum.textbox, searchBy,
									searchValue, index).sendKeys(controlValue);
							WritetoLog("Succesfully Found and performed action on  UI Control ==> :"
									+ cdetails);
						} else {
							WritetoLog("Failed to Find and perform action on  UI Control ==> :"
									+ cdetails);
						}
					}
					break;
				}
				case "button": {
					WritetoLog("Inside Button case....."
							+ controlType.toLowerCase());
					if (controlValue.length() > 0) {
						WritetoLog("Trying to Find Control Button...");
						if (getControl(drv, controlTypeenum.button, searchBy,
								searchValue, index) != null) {
							getControl(drv, controlTypeenum.button, searchBy,
									searchValue, index).click();
							WritetoLog("Succesfully Found and perfoemd action on  UI Control ==> :"
									+ cdetails);
						} else {
							WritetoLog("Failed to Find and perfoemd action on  UI Control ==> :"
									+ cdetails);
						}
					}
					break;
				}

				case "link": {
					WritetoLog("Inside Link case....."
							+ controlType.toLowerCase());
					if (controlValue.length() > 0) {
						WritetoLog("Trying to Find Control Link...");
						if (getControl(drv, controlTypeenum.link, searchBy,
								searchValue, index) != null) {
							getControl(drv, controlTypeenum.link, searchBy,
									searchValue, index).click();
							WritetoLog("Succesfully Found and perfoemd action on  UI Control ==> :"
									+ cdetails);
						} else {
							WritetoLog("Failed to Find and perfoemd action on  UI Control ==> :"
									+ cdetails);
						}
					}
					break;
				}
				case "dropdown": {
					break;
				}
				case "radiobutton": {
					break;
				}
				case "javascriptexecutor": {
					break;
				}
				case "webelem" :
				{
					WritetoLog("Inside Generic Web elem....."
							+ controlType.toLowerCase());
					if (controlValue.length() > 0) {
						WritetoLog("Trying to Find Web elem...");
						if (getControl(drv, controlTypeenum.webelem, searchBy,
								searchValue, index) != null) {
							getControl(drv, controlTypeenum.webelem, searchBy,
									searchValue, index).click();
							WritetoLog("Succesfully Found and perfoemd action on  UI Control ==> :"
									+ cdetails);
						} else {
							WritetoLog("Failed to Find and perfoemd action on  UI Control ==> :"
									+ cdetails);
						}
					}
					break;
				}
				default: {
					WritetoLog("Invalid Control Type was entered in excel: "
							+ controlType);
					break;
				}
				}
				WritetoLog("End of Row Number" + cntr);
				cntr++;
				iracount++;

			}

		} else

		{
			WritetoLog("Null Record Set was Obtained!");
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

	public List<Map<String, String>> getResultSet(String file, String sheet,
			String colname, String colvalue) throws Exception

	{
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
		WritetoLog("Inside Add Data");
		ResultSet rs = null;
		Connection c = null;
		String query = "";
		java.sql.Statement stmnt = null;
		try {
			WritetoLog("Inside Try ");
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			WritetoLog("found class ");
			WritetoLog("file path " + file);
			c = DriverManager
					.getConnection("jdbc:odbc:DRIVER={Microsoft Excel Driver (*.xls, *.xlsx, *.xlsm, *.xlsb)};DBQ="
							+ file + ";");
			WritetoLog("file path " + file);
			stmnt = c.createStatement();
			if (colname.length() > 0 && colvalue.length() > 0) {
				query = "select * from [" + sheet + "$] where " + colname
						+ " ='" + colvalue + "'";
				WritetoLog("query " + query);
			} else {
				query = "select * from [" + sheet + "$]";
				WritetoLog("query " + query);
			}
			rs = stmnt.executeQuery(query);
			Map<String, String> row = null;
			ResultSetMetaData metaData = rs.getMetaData();
			Integer columnCount = metaData.getColumnCount();
			WritetoLog("outsude record set");
			while (rs.next()) {
				row = new HashMap<String, String>();
				for (int i = 1; i <= columnCount; i++) {
					WritetoLog("Inside REcord Set loop");

					String strcolname = metaData.getColumnName(i).toString();
					String strcolvalue = rs.getString(i);
					WritetoLog(" Column name is " + strcolname);
					WritetoLog(" Value of Column " + i + " == " + strcolvalue);

					if (strcolvalue == null) {
						strcolvalue = "";
					}
					row.put(strcolname, strcolvalue);
					WritetoLog("Inserted value" + strcolvalue + "for column is"
							+ strcolname);

				}
				resultList.add(row);

			}
			WritetoLog("retuned record set in form of List ");
			return resultList;
		}

		catch (Exception e) {
			System.err.println(e);
			return resultList;
		} finally {
			try {
				stmnt.close();
				c.close();
				return resultList;
			} catch (Exception e) {
				System.err.println(e);
			}

		}
	}

	public List<Map<String, String>> getResultSetPOI(String file, String sheet,
			String colname, String colvalue) throws Exception

	{
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
	//	WritetoLog("Inside getResultSetPOI:");
		FileInputStream filexls = null;
		HSSFWorkbook workbook = null;
		try {
		//	WritetoLog("Inside Try ");
		//	WritetoLog("File path: " + file);
		//	// ..
			filexls = new FileInputStream(new File(file));
			// Get the workbook instance for XLS file
			workbook = new HSSFWorkbook(filexls);

			// Get first sheet from the workbook
			HSSFSheet retsheet = workbook.getSheet(sheet);
			// Get iterator to all the rows in current sheet
			Iterator<Row> rowIterator = retsheet.iterator();
			Integer rownum = 0;
			if (colname.length() > 0 && colvalue.length() > 0) {
				//WritetoLog("Inside Filter Case for Colname" + colname
				//		+ " and clovalue " + colvalue);
				String columnName = "";
				Map<String, String> row = null;
				ArrayList<String> colnamelist = new ArrayList<String>();
				while (rowIterator.hasNext()) {
					WritetoLog("Row number: " + rownum);
					Row indrow = rowIterator.next();
					row = new HashMap<String, String>();
					// For each row, iterate through each columns
					Iterator<Cell> cellIterator = indrow.cellIterator();
					Integer colnum = 0;
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						// row = new HashMap<String, String>();
						if (cell.getCellType() == Cell.CELL_TYPE_STRING) {

							String value = cell.getStringCellValue();
							//WritetoLog("got cell value as " + value);
							if (rownum == 0) {
								columnName = value;
								colnamelist.add(colnum, columnName);
							} else {

								row.put(colnamelist.get(colnum), value);

							}
						}
						colnum++;
					}
				//	WritetoLog("Checking if Row to be added or not ");
				//	WritetoLog("Column name " + colname + " column value "
				//			+ colvalue);
					// if (row.containsKey(colname)
					// && row.get(colname) == colvalue) {
				//	WritetoLog("Value from Dictionany or hashmap was"
				//			+ row.get(colname));
					if (row.get(colname) != null) {
						if (row.get(colname).equalsIgnoreCase(colvalue)) {
							//WritetoLog("Adding ..row with Column name "
							//		+ colname + " column value " + colvalue);
							resultList.add(row);
						} else {
							/*WritetoLog("Nothing was added as row containes  "
									+ colname + " is  "
									+ row.containsKey(colname)
									+ "and row cotnain colval " + colvalue
									+ " is " + (row.get(colname) == colvalue)); */
						}
					}
					rownum++;
				}
			} else {
				//WritetoLog("Inside  Case for Full record set records unfiltered ");
				String columnName = "";
				ArrayList<String> colnamelist = new ArrayList<String>();
				Map<String, String> row = null;
				while (rowIterator.hasNext()) {
					//WritetoLog("Row number: " + rownum);
					Row indrow = rowIterator.next();
					row = new HashMap<String, String>();
					// For each row, iterate through each columns
					Iterator<Cell> cellIterator = indrow.cellIterator();
					Integer colnum = 0;

					while (cellIterator.hasNext()) {
						WritetoLog("Column number: " + colnum);
						Cell cell = cellIterator.next();
						// row = new HashMap<String, String>();
						if (cell.getCellType() == Cell.CELL_TYPE_STRING) {

							String value = cell.getStringCellValue();
							//WritetoLog("got cell value as " + value);
							if (rownum == 0) {
								columnName = value;
								colnamelist.add(colnum, columnName);
								row.put(colnamelist.get(colnum), value);
							} else {

								row.put(colnamelist.get(colnum), value);

							}
						}
						colnum++;
					}
					resultList.add(row);
					rownum++;
				}
			}
			// Get iterator to all cells of current row

		//	WritetoLog("outsude record set");

			WritetoLog("returned record set in form of List.. ");
			return resultList;
		}

		catch (Exception e) {
			System.err.println(e);
			return resultList;
		} finally {
			try {
				filexls.close();
			//	workbook.close();
				return resultList;
			} catch (Exception e) {
				System.err.println(e);
			}

		}
	}

	public String getCellValue(String file, String colname, int rownumber) {
		String getCellValue = "";

		return getCellValue;
	}

	public void WritetoLog(String strlogmsg) throws IOException {
		File resultcsvfile = new File("c:\\temp\\results.txt");
		if (!resultcsvfile.exists()) {
			try {
				System.out.println("File is  not there Creating File");
				resultcsvfile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileWriter writer = null;
		writer = new FileWriter(resultcsvfile, true);
		BufferedWriter bwriter = new BufferedWriter(writer);
		bwriter.append(strlogmsg);
		bwriter.newLine();
		bwriter.close();
	}

	public void WritetoLog(String FileNametoWriote, String strlogmsg)
			throws IOException {
		File resultcsvfile = new File(FileNametoWriote);
		if (!resultcsvfile.exists()) {
			try {
				System.out.println("File is  not there Creating File");
				resultcsvfile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileWriter writer = null;
		writer = new FileWriter(resultcsvfile, true);
		BufferedWriter bwriter = new BufferedWriter(writer);
		bwriter.append(strlogmsg);
		bwriter.newLine();
		bwriter.close();
	}

	public void WritetHTMLRow(String srnum, String tcid, String tcstep,
			String field, String exp, String act) throws IOException {
		String now = new SimpleDateFormat("dd-MMM.yyyy HH:mm:ss")
				.format(new Date());
		String strnow = now.toString();
		sb.append("<tr>");
		sb.append("<td>" + srnum + "</td>");
		sb.append("<td>" + strnow + "</td>");
		sb.append("<td>" + tcid + "</td>");
		sb.append("<td>" + tcstep + "</td>");
		sb.append("<td>" + field + "</td>");
		sb.append("<td>" + exp + "</td>");
		sb.append("<td>" + act + "</td>");
		if (exp == act) {
			sb.append("<td> <font color='green' >Pass</td>");
		} else {
			sb.append("<td><font color='red' >Fail</td>");
		}
		sb.append("</tr>");
		rptcntr++;

	}

	public void writeTextFile(String strtestcase, String strdetails,
			String strstatus) throws Exception

	{
		File file = new File("c:\\temp\\Report.CSV");
		if (!file.exists()) {
			file.createNewFile();

		}
		FileWriter fileWritter = null;
		fileWritter = new FileWriter(file, true);
		BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
		FileReader reader = null;
		reader = new FileReader(file);
		BufferedReader buffreader = new BufferedReader(reader);

		if (getstreamlength(buffreader) == 0) {
			bufferWritter.append('\u0022' + "test case" + '\u0022' + ","
					+ '\u0022' + "Details" + '\u0022' + "," + '\u0022'
					+ "Status" + '\u0022' + ",");
		}
		bufferWritter.append('\u0022' + strtestcase + '\u0022' + "," + '\u0022'
				+ strdetails + '\u0022' + "," + '\u0022' + strstatus + '\u0022'
				+ ",");
		bufferWritter.newLine();
		bufferWritter.close();

	}

	public void genHTMLTABLE() throws IOException {
		StringBuilder sbh = new StringBuilder();

		sbh.append("<html>");
		sbh.append("<head>");
		sbh.append("</head>");
		sbh.append("<body>");
		sbh.append("<b> Overall Summary <b> ");
		sbh.append("<br>");
		sbh.append("<table  border='1' >");
		sbh.append("<tr>");
		sbh.append("<td><b> Sr No </b> </td>");
		sbh.append("<td><b> TimeStamp </b> </td>");
		sbh.append("<td><b> Testcase ID </b> </td>");
		sbh.append("<td><b> Test case Step </td>");
		sbh.append("<td><b> Field </b> </td>");
		sbh.append("<td><b> Expected </b> </td>");
		sbh.append("<td><b> Actual </b></td>");
		sbh.append("<td><b> Result</b> </td>");
		sbh.append("</tr>");
		sbh.append(sb.toString());
		sbh.append("</table>");
		sbh.append("</body>");
		sbh.append("</html>");
		WritetoLog("c:\\temp\\results.html", sbh.toString());

	}

	public int getstreamlength(BufferedReader rdr) throws Exception {
		int streeamlength = 0;
		try {
			streeamlength = rdr.readLine().toString().length();
		} catch (Exception e2) {
			streeamlength = 0;
		} finally {
			// return streeamlength;
		}
		return streeamlength;
	}
	
	
	public void captureScreenShot(WebDriver drv,String strscreenname) throws IOException
	{
		 File scrFile = ((TakesScreenshot)drv).getScreenshotAs(OutputType.FILE);
	        // Now you can do whatever you need to do with it, for example copy somewhere
	        File resimfile = new File("c:\\tmp\\"+strscreenname+getCurrentTimeStamp()+".png");
	        if( resimfile.exists() == true)
	        {
	        	WritetoLog("Deleting img file..whose time stamp is "+ new SimpleDateFormat("dd-MMM-yyyy HH-mm-ss").format(new Date(resimfile.lastModified())) );
	        	resimfile.delete();
	        	WritetoLog("Deleted  old copy of img file..");
	        }
	        else
	        	
	        {
	        	WritetoLog("File was not exisitng before ");
	        }
	        WritetoLog("File still is there ? before copy Expected [False]: "+resimfile.exists());
	        FileUtils.copyFile(scrFile, resimfile);
	        WritetoLog(String.format("File still is there ? : after copy Expected [True] %s and Modfuied Date %s: ",resimfile.exists(),new SimpleDateFormat("dd-MMM-yyyy HH-mm-ss").format(new Date(resimfile.lastModified()))));
	}
	
	public void VerifyTextPresentonPage(WebDriver drv , String testcaseid , String testcasestep, String fieldNametocheck ,String texttoVrify) throws IOException
	{
		  if (drv.getPageSource().contains(texttoVrify))
          {
       	     
       	   WritetoLog("test has passed");
       	   WritetHTMLRow( Integer.toString(cntr), testcaseid, testcasestep,fieldNametocheck,texttoVrify,texttoVrify);
       	}
          else
          {
       	   WritetHTMLRow( Integer.toString(cntr), testcaseid ,testcasestep ,fieldNametocheck,texttoVrify," Expected Text not found");
       	   WritetoLog("test has failed");
          }
	}

	public String getCurrentTimeStamp() {
	    SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MMM-yyyy HH_mm_ss");
	    Date now = new Date();
	    String strDate = sdfDate.format(now);
	    return strDate;
	}

	
	public WebElement CheckForControlType(WebElement elem , controlTypeenum ct)
	{
		WebElement CheckForControlType = null;
			if (elem != null)
			{
		
					switch (ct.toString().toLowerCase()) 
		{
					case "textbox":
						if ((elem.getTagName().equalsIgnoreCase("input") && elem
								.getAttribute("type").equalsIgnoreCase("text"))
								|| (elem.getTagName().equalsIgnoreCase("input") && elem
										.getAttribute("type").equalsIgnoreCase(
												"password"))) {
							CheckForControlType = elem;
						}
						break;
					case "button":
						if ((elem.getTagName().equalsIgnoreCase("input") && elem
								.getAttribute("type").equalsIgnoreCase("button"))
								|| (elem.getTagName().equalsIgnoreCase("input") && elem
										.getAttribute("type").equalsIgnoreCase(
												"submit"))) {
							CheckForControlType = elem;
						}
						break;
					case "webelem" :
					{
						CheckForControlType = elem;
						break;
					}
					default:
						break;
					}
		}
			return CheckForControlType ;
	}
}