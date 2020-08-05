package com.shopping.ui;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.shopping.BasePageObject;

public class Shopping extends BasePageObject {

	public Shopping(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[@id=\"buttons-form\"]/input[3]")
	WebElement cleanFavoritesButton;

	@FindBy(xpath = "//*[@id=\"filterBigtext\"]")
	WebElement submitButton;

	@FindBy(xpath = "//*[@id=\"favs-link\"]")
	WebElement addToFavButton;

	@FindBy(xpath = "//*[@id=\"favorites-link\"]")
	WebElement favoritesLink;

	@FindBy(xpath = "//*[@id=\"favorites_count\"]")
	WebElement favoritesCount;

	public void setElementText(WebElement element, String text) throws IOException, InterruptedException {
		waitUntilElementIsLoaded(element);
		element.sendKeys(text);
	}

	public void waitForElement(WebElement element) throws IOException, InterruptedException {
		waitUntilElementIsLoaded(element);
	}

	public void setAdvertisingType(String categoryName) throws IOException, InterruptedException {
		WebElement adCategoryName = driver.findElement((By.xpath("//*[contains(text(), \"" + categoryName + "\")]")));
		waitUntilElementIsLoaded(adCategoryName);
		adCategoryName.click();
	}

	public void setCategory(String name) throws IOException, InterruptedException {
		List<WebElement> adName = driver.findElements((By.xpath("//*[contains(text(), \"" + name + "\")]")));
		waitUntilElementIsLoaded(adName.get(1));
		adName.get(1).click();
	}

	public void openFirstAdOfCategory() throws IOException, InterruptedException {
		WebElement adName = driver.findElement((By.xpath("//*[@id=\"posts\"]/tbody/tr")));
		waitUntilElementIsLoaded(adName);
		adName.click();
	}

	public void addAdToFavoriteList() throws IOException, InterruptedException {
		swithOnNewTab();
		waitUntilElementIsLoaded(addToFavButton);
		addToFavButton.click();
	}

	public int getCountOfFavoriteList() throws IOException, InterruptedException {
		waitUntilElementIsLoaded(favoritesCount);
		String number = favoritesCount.getText();
		int numberOfAd = Integer.parseInt(number);
		return numberOfAd;
	}
}
