package wiki.aiws.net.performance.tests;

import org.openqa.selenium.By;

public class LocatingElementById extends AbstractLocatingElement {
	private String elementIdPrefix;

	public LocatingElementById(String url, String elementIdPrefix,
			int elementSuffixStart, int elementSuffixEnd) {
		super(url, elementSuffixStart, elementSuffixEnd);
		this.elementIdPrefix = elementIdPrefix;
	}

	public String getName() {
		return "Locating Element By Id";
	}

	@Override
	public By getLocator(int index) {
		return By.id(elementIdPrefix + index);
	}

}