package wiki.aiws.net.performance;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class TestExecution {
	private static final String[] HEADERS = { "No", "Test", "Time" };
	private List<Test> testList;
	private Class<?>[] webDrivers;
	private Map<String, Map<String, Long>> executionLog;
	private String outputFile;

	public TestExecution(String outputFile) {
		testList = new ArrayList<Test>();
		webDrivers = new Class<?>[] { ChromeDriver.class,
				InternetExplorerDriver.class, HtmlUnitDriver.class };
		executionLog = new HashMap<String, Map<String, Long>>();
		this.outputFile = outputFile;
	}

	public void addTest(Test test) {
		testList.add(test);
	}

	public void runTests() {
		List<WebDriver> destroyDrivers = new ArrayList<WebDriver>();
		try {
			for (Class<?> driverClass : webDrivers) {
				HashMap<String, Long> driverExecutionLog = new HashMap<String, Long>();
				System.out.println("Test with Browser: .....> "
						+ driverClass.getName());
				WebDriver driver;
				if (driverClass.equals(HtmlUnitDriver.class)) {
					driver = new HtmlUnitDriver(true);
				} else {
					driver = (WebDriver) driverClass.newInstance();
				}
				destroyDrivers.add(driver);
				for (Test test : testList) {
					System.out.println("\tStart executing.... "
							+ test.getName() + "...");
					long startTime = new Date().getTime();
					test.execute(driver);
					long endTime = new Date().getTime();
					System.out.println("\tFinished in " + (endTime - startTime)
							+ " ms.");
					driverExecutionLog.put(test.getName(),
							(endTime - startTime));
				}
				driver.quit();
				executionLog.put(driverClass.getSimpleName(),
						driverExecutionLog);
				destroyDrivers.remove(driver);
				Thread.sleep(1000);
			}
			writeLog();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			for (WebDriver driver : destroyDrivers) {
				driver.quit();
			}
		}
	}

	private void writeLog() throws IOException {
		HSSFWorkbook wb = new HSSFWorkbook();
		for (Entry<String, Map<String, Long>> entry : executionLog.entrySet()) {
			HSSFSheet sheet = wb.createSheet(entry.getKey());
			createHeader(sheet);
			writeValue(sheet, entry.getValue());
		}
		FileOutputStream fileOut = new FileOutputStream(outputFile);
		wb.write(fileOut);
		fileOut.close();
		wb.close();
	}

	private void writeValue(HSSFSheet sheet, Map<String, Long> value) {
		int rowIndex = 1;
		for (Entry<String, Long> entry : value.entrySet()) {
			Row row = ((org.apache.poi.ss.usermodel.Sheet) sheet)
					.createRow(rowIndex);
			row.createCell(0).setCellValue(rowIndex);
			row.createCell(1).setCellValue(entry.getKey());
			row.createCell(2).setCellValue(entry.getValue());
			rowIndex++;
		}
	}

	private void createHeader(HSSFSheet sheet) {
		Row header = ((org.apache.poi.ss.usermodel.Sheet) sheet).createRow(0);
		for (int i = 0; i < HEADERS.length; i++) {
			header.createCell(i).setCellValue(HEADERS[i]);
		}
	}
}