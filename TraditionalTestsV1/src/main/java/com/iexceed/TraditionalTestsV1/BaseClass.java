package com.iexceed.TraditionalTestsV1;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;

public class BaseClass {
	public static String browser;
	public static String viewport;
	public static String device;
	public static String TestName;

	// For Writing Results

	public static boolean WriteResult(int task, String testName, String ScreenName, String Section, String domId,
			boolean comparisonResult) {

		String content = "Task: " + task + ", Test Name: " + testName + " , Screen Name :" + ScreenName + " , Section :"
				+ Section + " , DOM Id: " + domId + ", Browser: " + browser + ", Viewport: " + viewport + ", Device: "
				+ device + ", Status: " + (comparisonResult ? "Pass" : "Fail") + ", Date & Time: "
				+ new Date().toString() + "\n";
		try {
			Files.write(Paths.get("./TestResults/TraditionalTestResultsV1.txt"), content.getBytes(),
					StandardOpenOption.APPEND);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error writing to report file");
			e.printStackTrace();
		}
		return comparisonResult;
	}

	// For Providing Browser and ViewPorts

	@DataProvider(name = "BrowserViewPort")
	public static Object[][] getDataFromDataprovider(Method Method_Name) {
		Object[][] Browser_ViewPort = null;
		if (Method_Name.getName().toString().equalsIgnoreCase("Laptop_Desktop")) {
			Browser_ViewPort = new Object[][] { { "Laptop/Desktop", "Chrome", 1200, 700 }, { "Laptop/Desktop", "Firefox", 1200, 700 }
					 ,{"Laptop/Desktop","Edge",1200,700}
			};
		} else if (Method_Name.getName().toString().equalsIgnoreCase("Tab")) {
			Browser_ViewPort = new Object[][] { { "Tab", "Chrome", 780, 700 }, { "Tab", "Firefox", 780, 700 }
					 ,{"Tab","Edge",780,700}
			};
		} else if (Method_Name.getName().toString().equalsIgnoreCase("Mobile")) {
			Browser_ViewPort = new Object[][] { { "Mobile", "Chrome", 450, 700 }, };
		}
		return Browser_ViewPort;
	}

	// For Invoking the driver of the Corresponding Browser

	public static WebDriver GetDriver(String BrowserName) {
		WebDriver d = null;
		if (BrowserName.equalsIgnoreCase("Chrome")) {
			d = new ChromeDriver();
		} else if (BrowserName.equalsIgnoreCase("Firefox")) {
			d = new FirefoxDriver();
		} else if (BrowserName.equals("Edge")) {
			d = new EdgeDriver();
		}
		return d;
	}

}
