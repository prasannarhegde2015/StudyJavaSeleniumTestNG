package wiki.aiws.net.performance.tests;

import org.openqa.selenium.By;

public class LocatingElementByName extends AbstractLocatingElement {

	private String elementNamePrefix;

	public LocatingElementByName(String url, String elementNamePrefix,
			int elementSuffixStart, int elementSuffixEnd) {
		super(url, elementSuffixStart, elementSuffixEnd);
		this.elementNamePrefix = elementNamePrefix;
	}

	public String getName() {
		return "Locating Element By Name";
	}

	@Override
	public By getLocator(int index) {
		return By.name(elementNamePrefix + index);
	}

}