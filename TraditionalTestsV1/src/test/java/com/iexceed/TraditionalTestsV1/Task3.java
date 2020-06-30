package com.iexceed.TraditionalTestsV1;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Task3 extends BaseClass {
	public WebDriver driver;
	public WebDriverWait wait;
	public static String URL = "https://demo.applitools.com/gridHackathonV1.html";
	
	//DEVICE-Laptop & Desktop  VIEWPORT-1200x700
	@Test(dataProvider = "BrowserViewPort")
	public void Laptop_Desktop(String DeviceName, String BrowserName, int viewPortWidth, int viewPortHeight)
			throws InterruptedException {
		browser = BrowserName;
		driver = GetDriver(BrowserName);
		wait = new WebDriverWait(driver, 5);
		driver.get(URL);
		driver.manage().window().setSize(new Dimension(viewPortWidth, viewPortHeight));
		driver.manage().window().setSize(new Dimension(viewPortWidth, viewPortHeight));
		viewport = Integer.toString(viewPortWidth) + "x" + Integer.toString(viewPortHeight);
		device = DeviceName;
		TestName="Product Details Test";
		SoftAssert assertSoft = new SoftAssert();
		wait = new WebDriverWait(driver, 5);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("product_1")));
		driver.findElement(By.id("product_1")).click();
		Thread.sleep(3000);
		
		// Asserting Shoe Image
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("shoe_img")));
		assertSoft.assertTrue(
				BaseClass.WriteResult(3, TestName, "Black Shoe Details screen", "Blackshoe-Image Section",
						"shoe_img", !(driver.findElement(By.id("shoe_img")).getAttribute("style").isEmpty())));

		// Asserting ProductCode
		String colour = driver.findElement(By.id("SMALL____84")).getCssValue("color");
		boolean AssertColourval = false;
		if (colour.equalsIgnoreCase("rgba(68, 68, 68, 1)") || (colour.equals("rgb(68, 68, 68)"))) {
			AssertColourval = true;
		}
		assertSoft.assertTrue(BaseClass.WriteResult(3, TestName, "Black Shoe Details screen",
				"Blackshoe-Productcode", "SMALL____84", AssertColourval));
		
		// Asserting Sizevalue
		String Size_Value = driver.findElement(By.className("current")).getText();
		assertSoft.assertTrue(BaseClass.WriteResult(3, TestName, "Black Shoe Details screen",
				"Blackshoe-Productcode", "Classname='current'", Size_Value.equals("Small (S)")));
		
		// Asserting Amount Format
		String NewPrice = driver.findElement(By.id("new_price")).getText();
		assertSoft.assertTrue(BaseClass.WriteResult(3, TestName, "Black Shoe Details screen",
				"Blackshoe-Productcode", "new_price", NewPrice.equals("$33.00")));
		
		assertSoft.assertAll();

	}

	//DEVICE-Tab  VIEWPORT-780x700
	@Test(dataProvider = "BrowserViewPort")
	public void Tab(String DeviceName, String BrowserName, int viewPortWidth, int viewPortHeight)
			throws InterruptedException {
		browser = BrowserName;
		driver = GetDriver(BrowserName);
		wait = new WebDriverWait(driver,30);
		driver.get(URL);
		Thread.sleep(5000);
		driver.manage().window().setSize(new Dimension(viewPortWidth, viewPortHeight));
		Thread.sleep(5000);
		driver.manage().window().setSize(new Dimension(viewPortWidth, viewPortHeight));
		viewport = Integer.toString(viewPortWidth) + "x" + Integer.toString(viewPortHeight);
		device = DeviceName;
		TestName="Product Details Test";
		SoftAssert assertSoft = new SoftAssert();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("product_1")));
		driver.findElement(By.id("product_1")).click();
		Thread.sleep(5000);
		
		// Asserting Shoe Image
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("shoe_img")));
		assertSoft.assertTrue(
				BaseClass.WriteResult(3, TestName, "Black Shoe Details screen", "Blackshoe-Image Section",
						"shoe_img", !(driver.findElement(By.id("shoe_img")).getAttribute("style").isEmpty())));
		
		// Asserting Position of Profile Picture Icon
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("A__accesslink__56")));
		assertSoft.assertTrue(BaseClass.WriteResult(3, TestName, "Black Shoe Details screen",
				"Header Section-ProfileIcon Position", "A__accesslink__56",
				CheckPosition("(624, 51)", "(635, 51)", GetPosition("A__accesslink__56"))));
		
		// Asserting Position of Favourite Icon
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("A__wishlist__52")));
		assertSoft.assertTrue(BaseClass.WriteResult(3, TestName, "Black Shoe Details screen",
				"Header Section-Favourites Position", "A__wishlist__52",
				CheckPosition("(670, 51)", "(681, 51)", GetPosition("A__wishlist__52"))));
		
		// Asserting Position of My cart Icon
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("A__cartbt__49")));
		assertSoft.assertTrue(BaseClass.WriteResult(3, TestName, "Black Shoe Details screen",
				"Header Section-Add to cart Position", "A__cartbt__49",
				CheckPosition("(716, 51)", "(727, 51)", GetPosition("A__cartbt__49"))));
		
		// Asserting Position of Add to cart Button
		Thread.sleep(7000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("A__btn__114")));
		assertSoft.assertTrue(BaseClass.WriteResult(3, TestName, "Black Shoe Details screen",
				"Product Section-Add to cart button Position", "A__btn__114",
				CheckPosition("(620, 884)", "(631, 884)", GetPosition("A__btn__114"))));
		
		// Asserting Stars and reviews alignment
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SPAN__rating__76")));
		assertSoft.assertTrue(BaseClass.WriteResult(3, TestName, "Black Shoe Details screen",
				"Product Section-Rating Star and reviews", "EM__ratingcoun__82",
				driver.findElement(By.id("SPAN__rating__76")).findElement(By.tagName("em")).getAttribute("class")
						.isEmpty()));
		
		// Asserting ProductCode
		String colour = driver.findElement(By.id("SMALL____84")).getCssValue("color");
		boolean AssertColourval = false;
		if (colour.equalsIgnoreCase("rgba(68, 68, 68, 1)") || (colour.equals("rgb(68, 68, 68)"))) {
			AssertColourval = true;
		}
		assertSoft.assertTrue(BaseClass.WriteResult(3, TestName, "Black Shoe Details screen",
				"Blackshoe-Productcode", "SMALL____84", AssertColourval));
		
		// Asserting Sizevalue
		String Size_Value = driver.findElement(By.className("current")).getText();
		assertSoft.assertTrue(BaseClass.WriteResult(3, TestName, "Black Shoe Details screen",
				"Blackshoe-Size value", "Classname='current'", Size_Value.equals("Small (S)")));
		
		// Asserting Amount Format
		String NewPrice = driver.findElement(By.id("new_price")).getText();
		assertSoft.assertTrue(BaseClass.WriteResult(3, TestName, "Black Shoe Details screen",
				"Blackshoe-Amount Format", "new_price", NewPrice.equals("$33.00")));
		
		assertSoft.assertAll();
	}

	//DEVICE-Mobile  VIEWPORT-450x700
	@Test(dataProvider = "BrowserViewPort")
	public void Mobile(String DeviceName, String BrowserName, int viewPortWidth, int viewPortHeight)
			throws InterruptedException {
		browser = BrowserName;
		driver = GetDriver(BrowserName);
		wait = new WebDriverWait(driver, 10);
		driver.get(URL);
		Thread.sleep(5000);
		driver.manage().window().setSize(new Dimension(viewPortWidth, viewPortHeight));
		driver.manage().window().setSize(new Dimension(viewPortWidth, viewPortHeight));
		viewport = Integer.toString(viewPortWidth) + "x" + Integer.toString(viewPortHeight);
		device = DeviceName;
		TestName="Product Details Test";
		SoftAssert assertSoft = new SoftAssert();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("product_1")));
		driver.findElement(By.id("product_1")).click();
		Thread.sleep(5000);

		// Asserting Stars and reviews alignment
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SPAN__rating__76")));
		assertSoft.assertTrue(BaseClass.WriteResult(3, TestName, "Black Shoe Details screen",
				"Product Section-Rating Star and reviews", "EM__ratingcoun__82",
				driver.findElement(By.id("SPAN__rating__76")).findElement(By.tagName("em")).getAttribute("class")
						.isEmpty()));
		
		// Asserting ProductCode
		String colour = driver.findElement(By.id("SMALL____84")).getCssValue("color");
		boolean AssertColourval = false;
		if (colour.equalsIgnoreCase("rgba(68, 68, 68, 1)") || (colour.equals("rgb(68, 68, 68)"))) {
			AssertColourval = true;
		}
		assertSoft.assertTrue(BaseClass.WriteResult(3, TestName, "Black Shoe Details screen",
				"Blackshoe-Productcode", "SMALL____84", AssertColourval));
		
		// Asserting Sizevalue
		String Size_Value = driver.findElement(By.className("current")).getText();
		assertSoft.assertTrue(BaseClass.WriteResult(3, TestName, "Black Shoe Details screen",
				"Blackshoe-Size value", "Classname='current'", Size_Value.equals("Small (S)")));
		
		// Asserting Amount Format
		String NewPrice = driver.findElement(By.id("new_price")).getText();
		assertSoft.assertTrue(BaseClass.WriteResult(3, TestName, "Black Shoe Details screen",
				"Blackshoe-Amount Format", "new_price", NewPrice.equals("$33.00")));
		
		//Asserting Old Price color
		String OPcolour = driver.findElement(By.id("old_price")).getCssValue("color");
		String OPStyle = driver.findElement(By.id("old_price")).getCssValue("text-decoration");
		boolean AssertOldPriceStyleColour = false;
		if (OPcolour.equalsIgnoreCase("rgba(153, 153, 153, 1)")&&(OPStyle.equals("line-through solid rgb(153, 153, 153)"))) {
			AssertOldPriceStyleColour = true;    
	
		}
		assertSoft.assertTrue(BaseClass.WriteResult(3, TestName, "Black Shoe Details screen",
				"Blackshoe-Old Price Colour & Style", "old_price", AssertOldPriceStyleColour));
		
		assertSoft.assertAll();
	}
	
	
	//CLOSE DRIVER
	@AfterMethod
	public void CloseDriver() {
		driver.quit();
	}

	/*-------COMMON METHODS-------*/

	// To Get Position of element
	public String GetPosition(String Locator) {
		return driver.findElement(By.id(Locator)).getLocation().toString();
	}

	// To Check the position of Element
	public boolean CheckPosition(String ExpectedPos_Chrome, String ExpectedPos_Firefox, String ActualPosition) {
		boolean checkFlag = false;
		if (ActualPosition.equalsIgnoreCase(ExpectedPos_Chrome)
				|| (ActualPosition.equalsIgnoreCase(ExpectedPos_Firefox))) {
			checkFlag = true;
		}
		return checkFlag;
	}

}
