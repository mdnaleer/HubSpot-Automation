package com.qa.naleer.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.qa.naleer.listener.WebEventListener;
import com.qa.naleer.util.Constants;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	WebDriver driver;
	Properties prop;
	String browserName;
	EventFiringWebDriver eDriver;
	WebEventListener eventListener;


	public WebDriver init(Properties prop) {
		browserName = prop.getProperty("browser");
		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().version("83.0.4103.39").setup();
			driver = new ChromeDriver();
		}else if(browserName.equals("firefox")){
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIME_OUT, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();

		eDriver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		eDriver.register(eventListener);

		driver = eDriver;
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));

		return driver;
	}

	public Properties initProperties() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/qa/naleer/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			System.out.println("File not found exception has happened");
		} catch (IOException e) {
			System.out.println("IO exception has happened");
		}
		return prop;
	}
	
	public final String getTitle() {
		return driver.getTitle();
	
	}
	
	public final void pause(int i ) {
		try {
			Thread.sleep(i*1000);
		} catch (InterruptedException e) {
			System.out.println("Interrupted exception has happened");
		}
	}

}
