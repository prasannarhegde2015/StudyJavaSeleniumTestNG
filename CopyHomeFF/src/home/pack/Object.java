package home.pack;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Object {

	public enum controlTypeenum {
		textbox, button, radiobutton, dropdown, link, frame, textarea, checkbox;
	}

	StringBuilder sb = new StringBuilder();
	public int cntr = 1;

	public WebElement getControl(WebDriver drv, controlTypeenum ct,
			String locator, String localtorvalue, String index)
			throws Exception {
		WebElement getControl = null;
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
			if (tempelem != null)
				switch (ct.toString().toLowerCase()) {
				case "textbox":
					if ((tempelem.getTagName().equalsIgnoreCase("input") && tempelem
							.getAttribute("type").equalsIgnoreCase("text"))
							|| (tempelem.getTagName().equalsIgnoreCase("input") && tempelem
									.getAttribute("type").equalsIgnoreCase(
											"password"))) {
						getControl = tempelem;
					}
					break;
				case "button":
					if ((tempelem.getTagName().equalsIgnoreCase("input") && tempelem
							.getAttribute("type").equalsIgnoreCase("button"))
							|| (tempelem.getTagName().equalsIgnoreCase("input") && tempelem
									.getAttribute("type").equalsIgnoreCase(
											"submit"))) {
						getControl = tempelem;
					}
					break;

				default:
					break;
				}

			break;
		}

		case "id": {
			getControl = drv.findElement(By.id(localtorvalue));
			break;
		}
		case "xpath": {
			getControl = drv.findElement(By.xpath(localtorvalue));
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
		 File ff = new File(testDataPath+fileName);
         if (!ff.exists())
         {
        	 JOptionPane.showMessageDialog(null, "File Not found"+ff.toString() , "InfoBox: " + "File Missing", JOptionPane.INFORMATION_MESSAGE);
        	 return;
         }
		List<Map<String, String>> rsstructure = getResultSet(testDataPath+fileName,
				"Structure", "", "");
		List<Map<String, String>> rsData = getResultSet(testDataPath+fileName, "Data",
				"testcase", testcase);
		String controlType, searchBy, searchValue, index, fieldName, controlValue;
		if (rsstructure != null && rsData != null) {
			for (Map<String, String> irow : rsstructure) {
				controlType = irow.get("controlType").toString();
				searchBy = irow.get("searchBy").toString();
				searchValue = irow.get("searchValue").toString();
				index = irow.get("index").toString();
				fieldName = irow.get("fieldName").toString();
				controlValue = rsData.get(0).get(fieldName).toString();

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
					WritetoLog("Inside Button case....."+controlType.toLowerCase());
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
				 default :
				{
					WritetoLog("Invalid Control Type was entered in excel" + cntr);
				    break;
				}
				}
				WritetoLog("End of Row Number" + cntr);
				cntr++;
			}

			
		} else

		{
			WritetoLog("Null Record Set was Obtained!");
		}

	}

	/*
	 * public void AddDataPoi(String fileName,String testcase,WebDriver drv)
	 * throws Exception {
	 * 
	 * HSSFSheet rsstructure = GetSheet(fileName, "structure"); HSSFSheet rsData
	 * = GetSheet(fileName, "data"); for ( int i=1; i <
	 * rsstructure.getLastRowNum();i++ ) { // String parentType =
	 * rs.getString(getIndexonName(rsstructure, "parentType")); // String
	 * parentSaerchby = rs.getString(getIndexonName(rsstructure,
	 * "parentSaerchby")); // String parentSaerchValue =
	 * rs.getString(getIndexonName(rsstructure, "parentSaerchValue")); String
	 * controlType = rsstructure.getString(getIndexonName(rsstructure,
	 * "controlType")); String searchBy =
	 * rsstructure.getString(getIndexonName(rsstructure, "searchBy")); String
	 * searchValue = rsstructure.getString(getIndexonName(rsstructure,
	 * "searchValue")); String index =
	 * rsstructure.getString(getIndexonName(rsstructure, "index")); // String
	 * action = rs.getString(getIndexonName(rsstructure, "action ")); String
	 * fieldName = rsstructure.getString(getIndexonName(rsstructure,
	 * "fieldName")); String controlValue =
	 * rsData.getString(getIndexonName(rsstructure, fieldName)); String cdetails
	 * =
	 * "Control Type : "+controlType+" FieldName: "+fieldName+" ControlLocator: "
	 * +searchBy+" Control Locator Value: "+searchValue+" ControlData : "+
	 * controlValue; switch(controlType.toLowerCase()) { case "textBox" :
	 * 
	 * { if (controlValue.length() > 0 ) { if
	 * (getControl(drv,controlTypeenum.textbox,searchBy,searchValue,index) !=
	 * null){
	 * getControl(drv,controlTypeenum.textbox,searchBy,searchValue,index).
	 * sendKeys(controlValue);
	 * WritetoLog("Succesfully Found and perfoemd action on  UI Control ==> :" +
	 * cdetails); } else {
	 * WritetoLog("Failed to Find and perfoemd action on  UI Control ==> :" +
	 * cdetails); } }
	 * 
	 * } case "button" : { if (controlValue.length() > 0 ) { if
	 * (getControl(drv,controlTypeenum.button,searchBy,searchValue,index) !=
	 * null){
	 * getControl(drv,controlTypeenum.button,searchBy,searchValue,index).click
	 * ();
	 * WritetoLog("Successfully Found and performed action on  UI Control ==> :" +
	 * cdetails); } else {
	 * WritetoLog("Failed to Find and performed action on  UI Control ==> :" +
	 * cdetails); } } break; }
	 * 
	 * case "link" : { break; } case "dropdown" : { break; } case "radiobutton"
	 * : { break; } case "javascriptexecutor" : { break; } } }
	 */

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
			c = DriverManager
					.getConnection("jdbc:odbc:DRIVER={Microsoft Excel Driver (*.xls, *.xlsx, *.xlsm, *.xlsb)};DBQ="
							+ file + ";");
			WritetoLog("file path " + file);
			stmnt = c.createStatement();
			if (colname.length() > 0 && colvalue.length() > 0) {
				query = "select * from [" + sheet + "$] where " + colname
						+ " ='" + colvalue + "'";
				WritetoLog("query " + query);
			} else
			{
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
		cntr++;

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
}
