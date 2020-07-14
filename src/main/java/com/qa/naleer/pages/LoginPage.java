package com.qa.naleer.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage{
	
	
	@FindBy(id = "username")
	WebElement username;
	
	@FindBy(id = "password")
	WebElement password;
	
	@FindBy(id = "loginBtn")
	WebElement loginBtn;
	
	@FindBy(xpath = "//a/i18n-string[text()='Forgot my password']")
	WebElement forgotPwdLink;
	
	public LoginPage(WebDriver driver){
		this.driver = driver;
		pause(5); //wait for the browser check completion  
		PageFactory.initElements(driver, this);
	}
	
	public HomePage login(String userName, String pwd){
		username.sendKeys(userName);
		password.sendKeys(pwd);
		loginBtn.click();
		System.out.println("login is done");
		return new HomePage(driver);
	}
	
	public boolean verifyForgotPwdLink(){
		return forgotPwdLink.isDisplayed();
	}
	

}
