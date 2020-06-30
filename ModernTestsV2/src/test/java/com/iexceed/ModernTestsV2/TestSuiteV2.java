package com.iexceed.ModernTestsV2;

import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.fluent.Target;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.BrowserType;
import com.applitools.eyes.visualgrid.model.DeviceName;
import com.applitools.eyes.visualgrid.model.ScreenOrientation;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;

public class TestSuiteV2 {

	final static String URL = "https://demo.applitools.com/gridHackathonV2.html";
	final static String AppName = "Hackathon Application";
	private WebDriver driver;
	private Eyes eyes;
	private VisualGridRunner runner;
	final int concurrency = 10;
	private static BatchInfo batch;

	// Setting New Batch
	@BeforeClass
	public static void SetBatchName() {
		batch = new BatchInfo("UFG Hackathon");
	}

	// Configurations Setup
	@Before
	public void setUp() {
		driver = new ChromeDriver();
		runner = new VisualGridRunner(concurrency);
		eyes = new Eyes(runner);
		Configuration config = eyes.getConfiguration();
		config.setApiKey("dC8DAsRd5MZKcwz3SU1AAvW44D3uTt2dyc101XR6pAx108M110");
		config.addBrowser(1200, 700, BrowserType.CHROME);
		config.addBrowser(768, 700, BrowserType.CHROME);
		config.addBrowser(1200, 700, BrowserType.EDGE_CHROMIUM);
		config.addBrowser(768, 700, BrowserType.EDGE_CHROMIUM);
		config.addBrowser(1200, 700, BrowserType.FIREFOX);
		config.addBrowser(768, 700, BrowserType.FIREFOX);
		config.addDeviceEmulation(DeviceName.iPhone_X, ScreenOrientation.PORTRAIT);
		eyes.setConfiguration(config);
		eyes.setBatch(batch);
	}

	@After
	public void tearDown() {
		driver.quit();
		System.out.println(runner.getAllTestResults());
		eyes.abortIfNotClosed();
	}

	@Test
	public void Task1() {
		eyes.open(driver, AppName, "Task 1", new RectangleSize(800, 600));
		driver.get(URL);
		// Checking the Landing screen of the application using UFG
		eyes.check(Target.window().fully().withName("Cross-Device Elements Test"));
	}

	@Test
	public void Task2() {
		eyes.open(driver, AppName, "Task 2", new RectangleSize(800, 600));
		driver.get(URL);
		{
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ti-filter")));
			
		}
		driver.findElement(By.id("ti-filter")).click();
		{
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("LABEL__containerc__104")));
		}
		driver.findElement(By.id("LABEL__containerc__104")).click();
		{
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("filterBtn")));
		}
		driver.findElement(By.id("filterBtn")).click();
		// Checking the particular region using UFG
		eyes.checkRegion(By.id("product_grid"), "Product grid");
		// Checking the screen after filtering using UFG
		eyes.check(Target.window().fully().withName("Filter Results"));
	}

	@Test
	public void Task3() {
		eyes.open(driver, AppName, "Task 3", new RectangleSize(800, 600));
		driver.get(URL);
		{
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("product_1")));
		}
		driver.findElement(By.id("product_1")).click();
		// Checking the product details using UFG
		eyes.check(Target.window().fully().withName("Product Details Test"));
	}
}
