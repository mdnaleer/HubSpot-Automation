package com.qa.naleer.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage extends BasePage{
  
	@FindBy(css = "button.uiButton.private-button.private-button--primary")
	WebElement createContactBtn;
	
	@FindBy(css = "input[data-field='email']")
	WebElement email;
	
	@FindBy(css = "input[data-field='firstname']")
	WebElement firstName;
	
	@FindBy(css = "input[data-field='lastname']")
	WebElement lastName;
	
	@FindBy(css = "input[data-field='jobtitle']")
	WebElement jobTitle;
	
	@FindBy(css = "button.uiButton.private-button.private-button--primary.private-button--default.private-loading-button")
	WebElement createContactSecondBtn;
	
	
	public ContactsPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void createNewContact(String emailVal, String firstname, String lastname, String jobtitle ){
		pause(3);
		createContactBtn.click();
		pause(3);
		email.sendKeys(emailVal);
		firstName.sendKeys(firstname);
		lastName.sendKeys(lastname);
		jobTitle.sendKeys(jobtitle);
		createContactSecondBtn.click();
		
	}
	

	
	
	

}
