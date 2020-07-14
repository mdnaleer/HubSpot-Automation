package com.qa.naleer.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.naleer.pages.BasePage;
import com.qa.naleer.pages.ContactsPage;
import com.qa.naleer.pages.HomePage;
import com.qa.naleer.pages.LoginPage;
import com.qa.naleer.util.CommonUtil;

public class ContactsPageTest {
	
	public WebDriver driver;
	public BasePage basePage;
	public Properties prop;
	public LoginPage loginPage;
	public HomePage homePage;
	public ContactsPage contactsPage;
	
	@BeforeMethod
	public void setUp(){
		basePage = new BasePage();
		prop = basePage.initProperties();
		driver = basePage.init(prop);
		loginPage = new LoginPage(driver);
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		contactsPage = homePage.goToContactsPage();
	}
	
	
	@DataProvider(name="getContactsTestData")
	public Object[][] getContactsTestData(){
		return CommonUtil.getTestData("contact");
	}

	
	@Test(dataProvider = "getContactsTestData")
	public void createContactTest(String email, String lastName, String firstName, String jobTitle){
		contactsPage.createNewContact(email, lastName, firstName, jobTitle);
	}
	
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}

}
