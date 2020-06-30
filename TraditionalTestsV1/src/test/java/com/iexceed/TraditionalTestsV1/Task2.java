package com.iexceed.TraditionalTestsV1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Task2 extends BaseClass {
	public WebDriver driver;
	public WebDriverWait wait;
	public static String URL = "https://demo.applitools.com/gridHackathonV1.html";
	int iconLoop = 1;
	
	//DEVICE-Laptop & Desktop  VIEWPORT-1200x700
	@Test(dataProvider = "BrowserViewPort")
	public void Laptop_Desktop(String DeviceName, String BrowserName, int viewPortWidth, int viewPortHeight)
			throws InterruptedException {
		browser = BrowserName;
		driver = GetDriver(BrowserName);
		wait = new WebDriverWait(driver, 15);
		driver.get(URL);
		driver.manage().window().setSize(new Dimension(viewPortWidth, viewPortHeight));
		driver.manage().window().setSize(new Dimension(viewPortWidth, viewPortHeight));
		viewport = Integer.toString(viewPortWidth) + "x" + Integer.toString(viewPortHeight);
		device = DeviceName;
		TestName="Shopping Experience Test";
		SoftAssert assertSoft = new SoftAssert();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("LABEL__containerc__104")));
		driver.findElement(By.id("LABEL__containerc__104")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("filterBtn")));
		driver.findElement(By.id("filterBtn")).click();

		// Filter Icon Assertion
		assertSoft.assertTrue(BaseClass.WriteResult(2, TestName, "Black Shoes filtered screen",
				"Products Section-Filter Icon", "ti-filter", !(driver.findElement(By.id("ti-filter")).isDisplayed())));

		// BlackShoes Filter Assertion
		assertSoft.assertTrue(BaseClass.WriteResult(2, TestName, "Black Shoes Count",
				"Products Section-Filtered Black shoes", "product_grid", BlackShoeCount() == 2));

		// Asserting MouseHover Products Icon (Favorites,Shuffle & Add to CartIcon)
		Actions act = new Actions(driver);
		List<WebElement> ImageCount = driver.findElement(By.id("product_grid")).findElements(By.tagName("img"));
		for (int i = 1; i <= ImageCount.toArray().length; i++) {
			act.moveToElement(driver.findElement(By.id("product_grid"))
					.findElement(By.xpath("(//div[@class='col-6 col-md-4'])" + "[" + i + "]"))).build().perform();
			for (int j = 0; j < 3; j++) {
				if (iconLoop > 6) {
					iconLoop = 1;
					break;
				}
				String Icon_id = driver.findElement(By.xpath("(//a[@class='tooltip-1'])" + "[" + iconLoop + "]"))
						.getAttribute("id");
				assertSoft.assertTrue(BaseClass.WriteResult(2, TestName, "Mouse Hover Products-Icon",
						"Products Section-Favorites,Shuffle & Add to Cart Icons", Icon_id,
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
		wait = new WebDriverWait(driver, 30);
		driver.get(URL);
		Thread.sleep(5000);
		driver.manage().window().setSize(new Dimension(viewPortWidth, viewPortHeight));
		Thread.sleep(5000);
		driver.manage().window().setSize(new Dimension(viewPortWidth, viewPortHeight));
		viewport = Integer.toString(viewPortWidth) + "x" + Integer.toString(viewPortHeight);
		device = DeviceName;
		TestName="Shopping Experience Test";
		SoftAssert assertSoft = new SoftAssert();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ti-filter")));
		driver.findElement(By.id("ti-filter")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("LABEL__containerc__104")));
		driver.findElement(By.id("LABEL__containerc__104")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("filterBtn")));
		driver.findElement(By.id("filterBtn")).click();

		// Header WishList Icon Assertion
		assertSoft.assertTrue(
				BaseClass.WriteResult(2, TestName, "Landing Screen", "Header Section-Favourites Icon",
						"A__wishlist__52", !(driver.findElement(By.id("A__wishlist__52")).isDisplayed())));

		// Asserting MenuIcon-1
		try {
			assertSoft.assertTrue(BaseClass.WriteResult(2, TestName, "Landing Screen",
					"Products Section-MenuIcon1", "A____201", !(driver.findElement(By.id("A____201")).isDisplayed())));
		} catch (NoSuchElementException e) {
			assertSoft.assertTrue(BaseClass.WriteResult(2, TestName, "Landing Screen",
					"Products Section-MenuIcon1", "A____202", !(driver.findElement(By.id("A____202")).isDisplayed())));
		}

		// Asserting MenuIcon-2
		try {
			assertSoft.assertTrue(BaseClass.WriteResult(2, TestName, "Landing Screen",
					"Products Section-MenuIcon2", "A____203", !(driver.findElement(By.id("A____203")).isDisplayed())));
		} catch (NoSuchElementException e) {
			assertSoft.assertTrue(BaseClass.WriteResult(2, TestName, "Landing Screen",
					"Products Section-MenuIcon2", "A____204", !(driver.findElement(By.id("A____204")).isDisplayed())));
		}

		// Asserting Product Icons(Favorites,Shuffle & Add to Cart Icon)
		String[] ProductIcons = { "I__tiheart__225", "I__ticontrols__229", "I__tishopping__233", "I__tiheart__247",
				"I__ticontrols__251", "I__tishopping__255" };
		for (int i = 0; i < ProductIcons.length; i++) {
			try {
				assertSoft.assertTrue(BaseClass.WriteResult(2, TestName, "Filter Black shoes screen",
						"Products Section-Product Details", ProductIcons[i],
						(driver.findElement(By.id(ProductIcons[i])).isDisplayed())));
			} catch (NoSuchElementException e) {

				assertSoft.assertTrue(BaseClass.WriteResult(2, TestName, "Filter Black shoes screen",
						"Products Section-Product Details", ProductIcons[i], false));
			}
		}

		// Asserting Black shoes count
		assertSoft.assertTrue(BaseClass.WriteResult(2, TestName, "Black Shoes Count",
				"Products Section-Filtered Black shoes", "product_grid", BlackShoeCount() == 2));
		assertSoft.assertAll();

	}

	@Test(dataProvider = "BrowserViewPort")
	public void Mobile(String DeviceName, String BrowserName, int viewPortWidth, int viewPortHeight)
			throws InterruptedException {
		browser = BrowserName;
		driver = GetDriver(BrowserName);
		wait = new WebDriverWait(driver, 5);
		driver.get(URL);
		Thread.sleep(5000);
		driver.manage().window().setSize(new Dimension(viewPortWidth, viewPortHeight));
		driver.manage().window().setSize(new Dimension(viewPortWidth, viewPortHeight));
		viewport = Integer.toString(viewPortWidth) + "x" + Integer.toString(viewPortHeight);
		device = DeviceName;
		TestName="Shopping Experience Test";
		SoftAssert assertSoft = new SoftAssert();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ti-filter")));
		driver.findElement(By.id("ti-filter")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("LABEL__containerc__104")));
		driver.findElement(By.id("LABEL__containerc__104")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("filterBtn")));
		driver.findElement(By.id("filterBtn")).click();

		// Asserting Input Field
		assertSoft.assertTrue(BaseClass.WriteResult(2,TestName, "Landing Screen",
				"Header Section-Input Box", "DIV__colxlcollg__40", driver.findElement(By.id("DIV__colxlcollg__40"))
						.getAttribute("class").equals("col-xl-6 col-lg-7 col-md-6 d-none d-md-block")));

		// Asserting Header Icons Alignment
		assertSoft.assertTrue(BaseClass.WriteResult(2, TestName, "Landing Screen",
				"Header Section-Icons Alignment", "DIV__colxlcollg__45",
				driver.findElement(By.id("DIV__colxlcollg__45")).getAttribute("class")
						.equals("col-xl-3 col-lg-2 col-md-3")));

		// Asserting Favorite Icon in Header section
		assertSoft.assertTrue(BaseClass.WriteResult(2, TestName, "Landing Screen",
				"Header Section-Favourites Icon", "A__wishlist__52",
				!(driver.findElement(By.id("A__wishlist__52")).isDisplayed())));

		// Asserting My cart Icon in Header section
		assertSoft.assertTrue(
				BaseClass.WriteResult(2, TestName, "Landing Screen", "Header Section-My Cart Icon",
						"STRONG____50", !(driver.findElement(By.id("STRONG____50")).isDisplayed())));
		
		// Asserting Product Icons(Favorites,Shuffle & Add to Cart Icon)_780x700
		String[] ProductIcons = { "I__tiheart__225", "I__ticontrols__229", "I__tishopping__233", "I__tiheart__247",
				"I__ticontrols__251", "I__tishopping__255" };
		for (int i = 0; i < ProductIcons.length; i++) {
			try {
				assertSoft.assertTrue(BaseClass.WriteResult(2, TestName, "Filter Black shoes screen",
						"Products Section-Product Details", ProductIcons[i],
						(driver.findElement(By.id(ProductIcons[i])).isDisplayed())));
			} catch (NoSuchElementException e) {

				assertSoft.assertTrue(BaseClass.WriteResult(2, TestName, "Filter Black shoes screen",
						"Products Section-Product Details", ProductIcons[i], false));
			}
		}
		
		// Asserting MenuIcon-1
		try {
			assertSoft.assertTrue(BaseClass.WriteResult(2, TestName, "Landing Screen",
					"Products Section-MenuIcon1", "A____201", !(driver.findElement(By.id("A____201")).isDisplayed())));
		} catch (NoSuchElementException e) {
			assertSoft.assertTrue(BaseClass.WriteResult(2, TestName, "Landing Screen",
					"Products Section-MenuIcon1", "A____202", !(driver.findElement(By.id("A____202")).isDisplayed())));
		}

		// Asserting MenuIcon-2
		try {
			assertSoft.assertTrue(BaseClass.WriteResult(2, TestName, "Landing Screen",
					"Products Section-MenuIcon2", "A____203", !(driver.findElement(By.id("A____203")).isDisplayed())));
		} catch (NoSuchElementException e) {
			assertSoft.assertTrue(BaseClass.WriteResult(2, TestName, "Landing Screen",
					"Products Section-MenuIcon2", "A____204", !(driver.findElement(By.id("A____204")).isDisplayed())));
		}
		
		// Asserting Quick link Accordian-Expand/Collapse
		try {
			assertSoft.assertTrue(BaseClass.WriteResult(2,TestName, "Landing Screen",
					"Footer Section-Quick link Accordian", "H3____421",
					new Task1().CheckExpanded("H3____421", driver)));
		} catch (NoSuchElementException e) {
			assertSoft.assertTrue(BaseClass.WriteResult(2, TestName, "Landing Screen",
					"Footer Section-Quick link Accordian", "H3__opened__422",
					new Task1().CheckExpanded("H3__opened__422", driver)));
		}
		
		// Asserting Black shoes count
		assertSoft.assertTrue(BaseClass.WriteResult(2, TestName, "Black Shoes Count",
				"Products Section-Filtered Black shoes", "product_grid", BlackShoeCount() == 2));

		assertSoft.assertAll();
	}
	
	
	//CLOSE DRIVER
	@AfterMethod
	public void CloseDriver() {
		driver.quit();
	}

	
	/*-------COMMON METHODS-------*/

	// BlackShoes Count
	public int BlackShoeCount() {
		List<WebElement> ImageTag = driver.findElement(By.id("product_grid")).findElements(By.tagName("img"));
		int ImageTagCount = ImageTag.toArray().length;
		return ImageTagCount;
	}
}
