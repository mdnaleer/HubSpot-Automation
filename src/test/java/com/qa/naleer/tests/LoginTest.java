package com.qa.naleer.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.naleer.pages.BasePage;
import com.qa.naleer.pages.LoginPage;
import com.qa.naleer.util.Constants;


public class LoginTest {
	
	public WebDriver driver;
	public BasePage basePage;
	public Properties prop;
	public LoginPage loginPage;
	
	@BeforeMethod
	public void setUp(){
		basePage = new BasePage();
		prop = basePage.initProperties();
		driver = basePage.init(prop);
		loginPage = new LoginPage(driver);
	}
	
	
	@Test(priority=1)
	public void verifyLoginPageTitle(){
		String title = loginPage.getTitle();
		System.out.println("login page title is: "+ title);
		Assert.assertEquals(title.trim(), Constants.LOGIN_PAGE_TITLE.trim());
	}
	
	
	@Test(priority=2,enabled=true)
	public void verifyForgotPwdLinkPresentTest(){
		Assert.assertTrue(loginPage.verifyForgotPwdLink());
	}
	
	@Test(priority=3,enabled=true)
	public void loginTest(){
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(loginPage.getTitle(), Constants.HOME_PAGE_TITLE, "Login Success");
	}
	
	
	@AfterMethod(enabled=true)
	public void tearDown(){
		driver.quit();
	}
	
	
	

}
