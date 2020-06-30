package com.iexceed.TraditionalTestsV1;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class Task1 extends BaseClass {
	public WebDriver driver;
	public static String URL = "https://demo.applitools.com/gridHackathonV1.html";
	int iconLoop = 1;
	
	//DEVICE-Laptop & Desktop  VIEWPORT-1200x700
	@Test(dataProvider = "BrowserViewPort")
	public void Laptop_Desktop(String DeviceName, String BrowserName, int viewPortWidth, int viewPortHeight)
			throws InterruptedException {
		browser = BrowserName;
		driver = GetDriver(BrowserName);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		driver.get(URL);
		driver.manage().window().setSize(new Dimension(viewPortWidth, viewPortHeight));
		driver.manage().window().setSize(new Dimension(viewPortWidth, viewPortHeight));
		viewport = Integer.toString(viewPortWidth) + "x" + Integer.toString(viewPortHeight);
		device = DeviceName;
		TestName="Cross-Browser Elements Test";
		SoftAssert assertSoft = new SoftAssert();
		
		// Assert FilterIcon
		assertSoft.assertTrue(BaseClass.WriteResult(1, TestName, "Landing Screen",
				"Products Section-Filter Icon", "ti-filter", !(driver.findElement(By.id("ti-filter")).isDisplayed())));

		// Asserting MouseHover Products Icon (Favorites,Shuffle & Add to CartIcon)
		Actions act = new Actions(driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("product_grid")));
		List<WebElement> ImageCount = driver.findElement(By.id("product_grid")).findElements(By.tagName("img"));
		for (int i = 1; i <= ImageCount.toArray().length; i++) {
			WebElement element = driver.findElement(By.xpath("(//div[@class='col-6 col-md-4'])" + "[" + i + "]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			act.moveToElement(driver.findElement(By.id("product_grid"))
					.findElement(By.xpath("(//div[@class='col-6 col-md-4'])" + "[" + i + "]"))).build().perform();
			Thread.sleep(1000);
			for (int j = 0; j < 3; j++) {
				if (iconLoop > 27) {
					iconLoop = 1;
					break;
				}
				String Icon_id = driver.findElement(By.xpath("(//a[@class='tooltip-1'])" + "[" + iconLoop + "]"))
						.getAttribute("id");
				assertSoft.assertTrue(BaseClass.WriteResult(1, TestName, "Landing Screen",
						"Mouse Hover-Products Section-Favorites,Shuffle & Add to Cart Icons", Icon_id,
						driver.findElement(By.id(Icon_id)).isEnabled()));
				iconLoop++;
			}
		}
		
		assertSoft.assertAll();
	}
	
	//DEVICE-Tab  VIEWPORT-780x700
	@Test(dataProvider = "BrowserViewPort")
	public void Tab(String DeviceName, String BrowserName, int viewPortWidth, int viewPortHeight)
			throws InterruptedException {
		browser = BrowserName;
		driver = GetDriver(BrowserName);
		driver.get(URL);
		Thread.sleep(5000);
		driver.manage().window().setSize(new Dimension(viewPortWidth, viewPortHeight));
		Thread.sleep(5000);
		driver.manage().window().setSize(new Dimension(viewPortWidth, viewPortHeight));
		viewport = Integer.toString(viewPortWidth) + "x" + Integer.toString(viewPortHeight);
		device = DeviceName;
		TestName="Cross-Browser Elements Test";
		SoftAssert assertSoft = new SoftAssert();
		String[] ProductIconElements = { "I__tiheart__225", "I__ticontrols__229", "I__tishopping__233",
				"I__tiheart__250", "I__ticontrols__254", "I__tishopping__258", "I__tiheart__272", "I__ticontrols__276",
				"I__tishopping__280", "I__tiheart__294", "I__ticontrols__298", "I__tishopping__302", "I__tiheart__316",
				"I__ticontrols__320", "I__tishopping__324", "I__tiheart__338", "I__ticontrols__342",
				"I__tishopping__346", "I__tiheart__360", "I__ticontrols__364", "I__tishopping__368", "I__tiheart__385",
				"I__ticontrols__389", "I__tishopping__393", "I__tiheart__407", "I__ticontrols__411",
				"I__tishopping__415" };

		// Asserting Favorites Icon in header section
		assertSoft.assertTrue(BaseClass.WriteResult(1, TestName, "Landing Screen",
				"Header Section-Favourites Icon", "A__wishlist__52",
				!(driver.findElement(By.id("A__wishlist__52")).isDisplayed())));
		
		// Asserting Menu Icon-1 near filter Icon
		try {
			assertSoft.assertTrue(BaseClass.WriteResult(1, TestName, "Landing Screen",
					"Products Section-MenuIcon1", "A____201", !(driver.findElement(By.id("A____201")).isDisplayed())));
		} catch (NoSuchElementException e) {
			assertSoft.assertTrue(BaseClass.WriteResult(1, TestName, "Landing Screen",
					"Products Section-MenuIcon1", "A____202", !(driver.findElement(By.id("A____202")).isDisplayed())));
		}
		
		// Asserting Menu Icon-2 near filter Icon
		try {
			assertSoft.assertTrue(BaseClass.WriteResult(1, TestName, "Landing Screen",
					"Products Section-MenuIcon2", "A____203", !(driver.findElement(By.id("A____203")).isDisplayed())));
		} catch (NoSuchElementException e) {
			assertSoft.assertTrue(BaseClass.WriteResult(1, TestName, "Landing Screen",
					"Products Section-MenuIcon2", "A____204", !(driver.findElement(By.id("A____204")).isDisplayed())));
		}

		// Favourites,Shuffle&AddtoCart Icons Assertion
		this.AssertElements("Product Icons-Favourite,Shuffle&AddtoCart", ProductIconElements, assertSoft, driver);
		
		assertSoft.assertAll();
	}
	
	//DEVICE-Mobile  VIEWPORT-450x700
	@Test(dataProvider = "BrowserViewPort")
	public void Mobile(String DeviceName, String BrowserName, int viewPortWidth, int viewPortHeight)
			throws InterruptedException {
		browser = BrowserName;
		driver = GetDriver(BrowserName);
		driver.get(URL);
		Thread.sleep(5000);
		driver.manage().window().setSize(new Dimension(viewPortWidth, viewPortHeight));
		viewport = Integer.toString(viewPortWidth) + "x" + Integer.toString(viewPortHeight);
		device = DeviceName;
		TestName="Cross-Browser Elements Test";
		SoftAssert assertSoft = new SoftAssert();
		String[] ProductIconElements = { "I__tiheart__225", "I__ticontrols__229", "I__tishopping__233",
				"I__tiheart__250", "I__ticontrols__254", "I__tishopping__258", "I__tiheart__272", "I__ticontrols__276",
				"I__tishopping__280", "I__tiheart__294", "I__ticontrols__298", "I__tishopping__302", "I__tiheart__316",
				"I__ticontrols__320", "I__tishopping__324", "I__tiheart__338", "I__ticontrols__342",
				"I__tishopping__346", "I__tiheart__360", "I__ticontrols__364", "I__tishopping__368", "I__tiheart__385",
				"I__ticontrols__389", "I__tishopping__393", "I__tiheart__407", "I__ticontrols__411",
				"I__tishopping__415" };

		// Asserting Input Field
		assertSoft.assertTrue(BaseClass.WriteResult(1, TestName, "Landing Screen",
				"Header Section-Input Box", "DIV__colxlcollg__40", driver.findElement(By.id("DIV__colxlcollg__40"))
						.getAttribute("class").equals("col-xl-6 col-lg-7 col-md-6 d-none d-md-block")));

		// Asserting Header Icons Alignment
		assertSoft.assertTrue(BaseClass.WriteResult(1, TestName, "Landing Screen",
				"Header Section-Icons Alignment", "DIV__colxlcollg__45",
				driver.findElement(By.id("DIV__colxlcollg__45")).getAttribute("class")
						.equals("col-xl-3 col-lg-2 col-md-3")));

		// Asserting Favorite Icon in Header section
		assertSoft.assertTrue(BaseClass.WriteResult(1, TestName, "Landing Screen",
				"Header Section-Favourites Icon", "A__wishlist__52",
				!(driver.findElement(By.id("A__wishlist__52")).isDisplayed())));

		// Asserting My cart Icon in Header section
		assertSoft.assertTrue(
				BaseClass.WriteResult(1, TestName, "Landing Screen", "Header Section-My Cart Icon",
						"STRONG____50", !(driver.findElement(By.id("STRONG____50")).isDisplayed())));
	
		// Asserting Menu Icon-1 near filter Icon
		try {
			assertSoft.assertTrue(BaseClass.WriteResult(1, TestName, "Landing Screen",
					"Products Section-MenuIcon1", "A____201", !(driver.findElement(By.id("A____201")).isDisplayed())));
		} catch (NoSuchElementException e) {
			assertSoft.assertTrue(BaseClass.WriteResult(1, TestName, "Landing Screen",
					"Products Section-MenuIcon1", "A____202", !(driver.findElement(By.id("A____202")).isDisplayed())));
		}
		
		// Asserting Menu Icon-2 near filter Icon
		try {
			assertSoft.assertTrue(BaseClass.WriteResult(1, TestName, "Landing Screen",
					"Products Section-MenuIcon2", "A____203", !(driver.findElement(By.id("A____203")).isDisplayed())));
		} catch (NoSuchElementException e) {
			assertSoft.assertTrue(BaseClass.WriteResult(1, TestName, "Landing Screen",
					"Products Section-MenuIcon2", "A____204", !(driver.findElement(By.id("A____204")).isDisplayed())));
		}

		// Favourites,Shuffle&AddtoCart Icons Assertion
		this.AssertElements("Product Icons-Favourite,Shuffle&AddtoCart", ProductIconElements, assertSoft, driver);

		// Asserting Quick link Accordian-Expand/Collapse
		try {
			assertSoft.assertTrue(BaseClass.WriteResult(1, TestName, "Landing Screen",
					"Footer Section-Quick link Accordian", "H3____421", CheckExpanded("H3____421",driver)));
		} catch (NoSuchElementException e) {
			assertSoft.assertTrue(BaseClass.WriteResult(1, TestName, "Landing Screen",
					"Footer Section-Quick link Accordian", "H3__opened__422", CheckExpanded("H3__opened__422",driver)));
		}

		assertSoft.assertAll();
	}

	//CLOSE DRIVER
	@AfterMethod
	public void CloseDriver() {
		driver.quit();
	}

	
	/*-------COMMON METHODS-------*/
	
	// For Asserting the Quick Link Accordian
	public boolean CheckExpanded(String Locator,WebDriver driver) {
		boolean checkFlag = false;
		String classValue = driver.findElement(By.id(Locator)).getAttribute("class");
		if (classValue.equals(null) || classValue.equals("collapsed") || classValue.equals("")) {
			checkFlag = true;
		}
		return checkFlag;
	}

	// Asserting all the Product Icons -Favourites,Shuffle&AddtoCart Icons
	public void AssertElements(String Section, String[] Arr, SoftAssert SA, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 2);
		for (int i = 0; i < Arr.length; i++) {
			if (Arr[i] == null || Arr[i] == "") {
				break;
			}
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Arr[i])));
				SA.assertTrue(BaseClass.WriteResult(1, TestName, "Landing Screen", Section, Arr[i],
						driver.findElement(By.id(Arr[i])).isDisplayed()));
			} catch (NoSuchElementException e) {
				SA.assertTrue(BaseClass.WriteResult(1, TestName, "Landing Screen", Section, Arr[i],
						false));
			}
			catch(TimeoutException e) {
				SA.assertTrue(BaseClass.WriteResult(1, TestName, "Landing Screen", Section, Arr[i],
						false));
			}
		}
	}

}
