package com.qa.naleer.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.naleer.util.Constants;

public class HomePage extends BasePage {
	
	@FindBy(id = "nav-primary-contacts-branch")
	WebElement contactsTabLink;
	
	@FindBy(id = "nav-secondary-contacts")
	WebElement contactsLink;

	By homePageHeader = By.xpath("//h1/i18n-string[text()='Thanks for choosing HubSpot']");
	By accountName = By.xpath("//a[@id='account-menu']");
	By accountName1 = By.cssSelector("a#navAccount-current > div.navAccount-accountName");

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getHomePageTitle() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.titleIs(Constants.HOME_PAGE_TITLE));
		return driver.getTitle();
	}

	public boolean verifyHomePageHeader() {
		pause(5);
		return driver.findElement(homePageHeader).isDisplayed();
	}

	public String getAccountName() {
		pause(5);
		driver.findElement(accountName).click();
		return driver.findElement(accountName1).getText();
	}
	
	public void clickContacts(){
		pause(3);
		contactsTabLink.click();
		
		pause(2);
		contactsLink.click();
	}
	
	public ContactsPage goToContactsPage(){
		clickContacts();
		return new ContactsPage(driver);
	}

}
