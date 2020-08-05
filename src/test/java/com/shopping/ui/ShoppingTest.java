package com.shopping.ui;

import java.io.IOException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.shopping.DriverFactory;
import com.shopping.ReadPropertyFile;

public class ShoppingTest {
	DriverFactory objDriver = new DriverFactory();
	ReadPropertyFile readPropertyFile = new ReadPropertyFile();
	Shopping shopping;
	String BASEURL = "https://www.reklama.lv/ru/";
	String AD_CATEGORY = "Легковые авто";
	String CAR_TYPE = "Mercedes-Benz";
	String CAR_E200 = "E200";

	@Before
	public void setUp() throws IOException, InterruptedException {
		shopping = new Shopping(objDriver.getDriver());
		shopping.setWindowsSize(ReadPropertyFile.getVallueWithComma("size").get(0),
				ReadPropertyFile.getVallueWithComma("size").get(1));
		objDriver.getDriver().navigate().to(BASEURL);
	}

	@After
	public void tearDown() {
		objDriver.quitDriver();
	}

	@Test
	public void addOneFavoriteAd() throws InterruptedException, IOException {
		shopping.setAdvertisingType(AD_CATEGORY);
		shopping.waitForElement(shopping.submitButton);
		shopping.setCategory(CAR_TYPE);
		shopping.setCategory(CAR_E200);
		shopping.openFirstAdOfCategory();
		shopping.addAdToFavoriteList();
		Assert.assertEquals("Number of favorite:", 1, shopping.getCountOfFavoriteList());
	}

}
