package wiki.aiws.net.performance.tests;

import org.openqa.selenium.By;

public class LocatingElementByCSS extends AbstractLocatingElement {

	private String elementClassPrefix;

	public LocatingElementByCSS(String url, String elementClassPrefix,
			int elementSuffixStart, int elementSuffixEnd) {
		super(url, elementSuffixStart, elementSuffixEnd);
		this.elementClassPrefix = elementClassPrefix;
	}

	public String getName() {
		return "Locating Element By CSS";
	}

	@Override
	public By getLocator(int index) {
		return By.className(elementClassPrefix + index);
	}

}