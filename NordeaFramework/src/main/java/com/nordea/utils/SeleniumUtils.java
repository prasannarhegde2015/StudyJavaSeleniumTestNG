package com.nordea.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumUtils {

	final Logger logger = LogManager.getLogger(SeleniumUtils.class);
	public String ERR_NOELEM = "No Such Elelment exception for: " + "[ElemName]" + "Details ==>" + "[Excepption]";
	public String ERR_STALEELEM = "Stale  Elelment Refernce  exception for: " + "[ElemName]" + "Details ==>"
			+ "[Excepption]";
	public String ERR_N0FRAME = "No Such Frame exception for: " + "[ElemName]" + "Details ==>" + "[Excepption]";
	public String ERR_OTH = "Other exception for: " + "[ElemName]" + "Details ==>" + "[Excepption]";
	private static WebDriver selDriver = null;

	public static WebDriver getSelDriver() {
		return selDriver;
	}

	public static void setSelDriver(WebDriver drv) {
		selDriver = drv;
	}

	public void clickElem(WebElement el, String elname) throws InterruptedException {
		WebElement orig = el;
		try {

			el.click();
			logger.info("Element was clicked: " + elname);
		} catch (NoSuchElementException ex) {
			logger.error(ERR_NOELEM.replace("[ElemName]", elname).replace("[Excepption]", ex.toString()));
		} catch (StaleElementReferenceException ex) {
			logger.error(ERR_STALEELEM.replace("[ElemName]", elname).replace("[Excepption]", ex.toString()));
			while (isStale(orig, elname)) {
				Thread.sleep(1000);
				logger.info("Stable check for Staleness being verified");
			}

		} catch (Exception ex) {
			logger.error(ERR_OTH.replace("[ElemName]", elname).replace("[Excepption]", ex.toString()));
		}
	}

	public void clickElemByLinkText(WebDriver dr, String linktext, String elname) throws InterruptedException {
		WebElement eld = null;
		try {
			By geyby = By.linkText(linktext);
			eld = dr.findElement(By.linkText(linktext));
			waitforPresenseofElementBy(dr, geyby, eld, elname);
			logger.info("Element Located after  wait :  now trying to click " + elname);
			Thread.sleep(2000);
			eld.click();
			logger.info("Element was clicked: " + elname);
		} catch (StaleElementReferenceException ex) {
			logger.error(ERR_STALEELEM.replace("[ElemName]", elname).replace("[Excepption]", ex.toString()));
			while (isStale(eld, elname)) {
				Thread.sleep(1000);
				eld = dr.findElement(By.linkText(linktext)); // By needs to
																// relocate
																// element to
																// remove
																// staleness
				logger.info("Stable check for Staleness being verified");
			}
		} catch (Exception ex) {
			logger.error(ERR_OTH.replace("[ElemName]", elname).replace("[Excepption]", ex.toString()));
		}
	}

	public void enterValue(WebElement el, String elname, String val) {
		try {
			el.clear();
			el.sendKeys(val);
			logger.info("Value [" + val + "]  set in : " + elname);
		} catch (NoSuchElementException ex) {
			logger.error(ERR_NOELEM.replace("[ElemName]", elname).replace("[Excepption]", ex.toString()));
		} catch (StaleElementReferenceException ex) {
			logger.error(ERR_STALEELEM.replace("[ElemName]", elname).replace("[Excepption]", ex.toString()));
		} catch (Exception ex) {
			logger.error(ERR_OTH.replace("[ElemName]", elname).replace("[Excepption]", ex.toString()));
		}
	}

	public void selectValue(WebElement el, String elname, String selval) {

		Select sel = new Select(el);
		try {
			sel.selectByVisibleText(selval);
			logger.info("Selected Value: [" + selval + "] For Elment: " + elname);
		} catch (NoSuchElementException ex) {
			logger.error(ERR_NOELEM.replace("[ElemName]", elname).replace("[Excepption]", ex.toString()));
		} catch (StaleElementReferenceException ex) {
			logger.error(ERR_STALEELEM.replace("[ElemName]", elname).replace("[Excepption]", ex.toString()));
		} catch (Exception ex) {
			logger.error(ERR_OTH.replace("[ElemName]", elname).replace("[Excepption]", ex.toString()));
		}

	}

	public String getText(WebElement el, String elname) {
		try {
			logger.info("Element text retruned is " + el.getAttribute("value"));
			return el.getAttribute("value");
		} catch (NoSuchElementException ex) {
			logger.error(ERR_NOELEM.replace("[ElemName]", elname).replace("[Excepption]", ex.toString()));
			return "";
		} catch (StaleElementReferenceException ex) {
			logger.error(ERR_STALEELEM.replace("[ElemName]", elname).replace("[Excepption]", ex.toString()));
			return "";
		} catch (Exception ex) {
			logger.error(ERR_OTH.replace("[ElemName]", elname).replace("[Excepption]", ex.toString()));
			return "";
		}
	}

	public void Switchtoframe(WebDriver dr, WebElement el, String elname) {
		WebDriverWait wt = new WebDriverWait(dr, 500);
		try {

			wt.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(el));
			logger.info("Sel Utils: Switched to Farme" + elname);
		} catch (NoSuchFrameException ex) {
			logger.error(ERR_N0FRAME.replace("[ElemName]", elname).replace("[Excepption]", ex.toString()));
		}
	}

	public void SwitchtoDelfault(WebDriver dr) {
		try {

			dr.switchTo().defaultContent();
			logger.info("Sel Utils Switch to default frame or parent page");
		} catch (Exception ex) {
			logger.error(ERR_OTH.replace("[Excepption]", ex.toString()));
		}
	}

	public void waitforPresenseofElement(WebDriver drv, WebElement el, String elname) throws InterruptedException

	{
		int timeout = 500;
		logger.debug("Sel Utils: Waiting for Elm to be present Static Elem" + elname);
		for (int i = 0; i < timeout; i++) {
			Thread.sleep(1000);
			logger.debug("Wait attempt [ " + i + " ] Wait Limited" + timeout);
			if (isElemPresent(el)) {
				break;
			}
		}

		// wt.until(new ExpectedCondition<Boolean>() {
		// @Override
		// public Boolean apply(WebDriver drv) {
		// try {
		// el.isDisplayed();
		// return true;
		// } catch (NoSuchElementException e) {
		// return false;
		// } catch (StaleElementReferenceException e) {
		// return false;
		// } catch (Exception e) {
		// logger.info("Fluent Wait Error" + e);
		// return false;
		// }
		// }
		// });
		logger.debug("Sel Utils: Element Displyed post Wait:" + elname);

	}

	public void waitforPresenseofElementBy(WebDriver drv, By bylocator, WebElement el, String elname)
			throws InterruptedException

	{
		int timeout = 500;
		logger.debug("Sel Utils: Waiting for Elm to be present using [Refresh By]" + elname);
		for (int i = 0; i < timeout; i++) {
			Thread.sleep(1000);
			el = drv.findElement(bylocator);
			logger.debug("Wait attempt [ " + i + " ] Wait Limited" + timeout);
			if (isElemPresent(el)) {
				break;
			}
		}

		// wt.until(new ExpectedCondition<Boolean>() {
		// @Override
		// public Boolean apply(WebDriver drv) {
		// try {
		// el.isDisplayed();
		// return true;
		// } catch (NoSuchElementException e) {
		// return false;
		// } catch (StaleElementReferenceException e) {
		// return false;
		// } catch (Exception e) {
		// logger.info("Fluent Wait Error" + e);
		// return false;
		// }
		// }
		// });
		logger.debug("Sel Utils: Element Displyed post Wait:" + elname);

	}

	public void waitforpgload(WebDriver drv, String pgname) throws InterruptedException {
		logger.debug("Waiting for Page to Load.." + pgname);
		String status = (String) ((JavascriptExecutor) drv).executeScript("return document.readyState");

		if (status == null) {
			status = "Blank";
		}
		while (status.equals("complete") == false) {
			status = (String) ((JavascriptExecutor) drv).executeScript("return document.readyState");
			if (status == null) {
				status = "Blank";
			}
			System.out.println("** Page complete  " + status);
			Thread.sleep(1000);
		}
		logger.debug("Page Loaded.." + pgname);
	}

	public void movetoelem(WebElement elm) {
		Actions acn = new Actions(SeleniumUtils.getSelDriver());
		acn.moveToElement(elm).build().perform();
		// acn.moveToElement(el, 50, 50);
	}

	public void mclickelem(WebElement elm) {
		if (SeleniumUtils.getSelDriver() == null) {
			logger.info("Driver not inintiazed");
		}
		Actions acn = new Actions(SeleniumUtils.getSelDriver());

		acn.click(elm).build().perform();
	}

	public boolean isStale(WebElement el2, String elmname) throws InterruptedException {

		if (el2 == null) {
			logger.info("Element not passed from calling fucntion");
			return true;
		}
		try {
			logger.info("Inside Stale Check");
			Thread.sleep(1000);
			logger.info("Trying to click");
			el2.click();
			logger.info("Element was clicked: In Stale Function ");
			return false;
		} catch (StaleElementReferenceException ex) {
			logger.info("Element is Still Stale:");
			return true;
		} catch (NoSuchElementException ex) {
			logger.error(ERR_NOELEM.replace("[ElemName]", elmname).replace("[Excepption]", ex.toString()));
			return false;
		}
	}

	public boolean isElemPresent(WebElement elm) {
		try {
			boolean cond1 = elm.isDisplayed();
			logger.info("Elemnt was disaplyed : " + cond1);
			return cond1;
		} catch (StaleElementReferenceException ex) {
			logger.info("Element is Still Stale: While Checking its Presense");
			return false;
		} catch (Exception ex) {
			return false;
		}
	}
}
