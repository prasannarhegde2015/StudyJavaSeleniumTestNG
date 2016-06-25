package wiki.aiws.net.performance;

import wiki.aiws.net.performance.tests.InputToMandatoryFields;
import wiki.aiws.net.performance.tests.LocatingElementByCSS;
import wiki.aiws.net.performance.tests.LocatingElementById;
import wiki.aiws.net.performance.tests.LocatingElementByName;
import wiki.aiws.net.performance.tests.LocatingElementByXPath;
import wiki.aiws.net.performance.tests.SelectFromDropDown;

public class Main {

	public static void main(String[] args) throws Exception {
		String url1 = "http://localhost:8080/SeleniumLocate/LocatingElements.html";
		String url2 = "http://localhost:8080/SeleniumLocate/InputMandatory.html";
		String url3 = "http://localhost:8080/SeleniumLocate/SelectDropDown.html";
		// String url4 = "http://localhost:8080/SeleniumLocate/complex/submit";

		String tag = "input";
		LocatingElementById locatingElementById = new LocatingElementById(url1,
				tag, 1, 100);
		LocatingElementByName locatingElementByName = new LocatingElementByName(
				url1, tag, 1, 100);
		LocatingElementByXPath locatingElementByXPath = new LocatingElementByXPath(
				url1, tag, "value", tag, 1, 100);
		LocatingElementByCSS locatingElementByCSS = new LocatingElementByCSS(
				url1, tag, 1, 100);
		InputToMandatoryFields inputToMandatoryFields = new InputToMandatoryFields(
				url2, tag, 1, 100);
		SelectFromDropDown selectFromDropDown = new SelectFromDropDown(url3,
				"dropdown", 1, 50);
		// ComplexTest complexTest = new ComplexTest(url4);
		TestExecution testExecution = new TestExecution("c:\\temp\\log.xls");
		testExecution.addTest(locatingElementById);
		testExecution.addTest(locatingElementByName);
		testExecution.addTest(locatingElementByXPath);
		testExecution.addTest(locatingElementByCSS);
		testExecution.addTest(inputToMandatoryFields);
		testExecution.addTest(selectFromDropDown);
		// testExecution.addTest(complexTest);
		testExecution.runTests();
	}

}
