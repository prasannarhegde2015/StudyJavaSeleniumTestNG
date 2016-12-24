package wiki.aiws.net.performance.tests;

import org.openqa.selenium.By;

public class LocatingElementByXPath extends AbstractLocatingElement {
	private String elementTag;
	private String elementAttribute;
	private String elementAttributeValuePrefix;

	public LocatingElementByXPath(String url, String elementTag,
			String elementAttribute, String elementAttributeValuePrefix,
			int elementSuffixStart, int elementSuffixEnd) {
		super(url, elementSuffixStart, elementSuffixEnd);
		this.elementTag = elementTag;
		this.elementAttribute = elementAttribute;
		this.elementAttributeValuePrefix = elementAttributeValuePrefix;
	}

	public String getName() {
		return "Locating Element By XPath";
	}

	@Override
	public By getLocator(int index) {
		return By.xpath("//" + elementTag + "[@" + elementAttribute + "='"
				+ elementAttributeValuePrefix + index + "']");
	}

}