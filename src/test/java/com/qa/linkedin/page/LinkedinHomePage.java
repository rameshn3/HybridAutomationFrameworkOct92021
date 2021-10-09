package com.qa.linkedin.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.log4testng.Logger;

public class LinkedinHomePage extends BasePageWeb{
private static Logger log=Logger.getLogger(LinkedinHomePage.class);
//create a constructor
public LinkedinHomePage() {
	PageFactory.initElements(driver, this);
}

//identify the elements
@FindBy(css="a.nav__logo-link")
private WebElement linkedinLogo;

@FindBy(linkText="Sign in")
private WebElement signInLink;

@FindBy(css="h1.header__content__heading")
private WebElement signinHeaderText;

@FindBy(id="username")
private WebElement emailEditbox;

@FindBy(name="session_password")
private WebElement paswordEditbox;

@FindBy(xpath="//button[@type='submit' and @aria-label='Sign in']")
private WebElement signinBtn;

String linkedinHomePageTitle="LinkedIn: Log In or Sign Up";
String linkedinLoginPageTitle="LinkedIn Login, Sign in | LinkedIn";

public void verifyLinkedinHomePageTitle() {
	log.debug("Verifying the Linkedin Home page title...");
	wait.until(ExpectedConditions.titleContains(linkedinHomePageTitle));
	Assert.assertEquals(driver.getTitle(), linkedinHomePageTitle);
}

public void verifyLinkedinLogo() {
	log.debug("Verify linkedin logo is present in the homepage ");
	wait.until(ExpectedConditions.visibilityOf(linkedinLogo));
	Assert.assertTrue(linkedinLogo.isDisplayed());
}

public void clickSigninLink() throws InterruptedException {
	log.debug("click on signin link in linkedin home page");
	click(signInLink);
}

public void verifyLinkedinLoginPageTitle() {
	log.debug("Verifying the Linkedin Login page title...");
	wait.until(ExpectedConditions.titleContains(linkedinLoginPageTitle));
	Assert.assertEquals(driver.getTitle(), linkedinLoginPageTitle);
}

public void verifyLinkedinLoginSignHeaderTex() {
	log.debug("Verify linkedin login signin header text");
	wait.until(ExpectedConditions.visibilityOf(signinHeaderText));
	Assert.assertTrue(signinHeaderText.isDisplayed());
}

public void clickSigninBtn() throws InterruptedException {
	log.debug("click on signin Buton in linkedinlogin page");
	click(signinBtn);
}

public LinkedinLoggedinPage doLogin(String uname,String pwd) throws InterruptedException {
	log.debug("started executing the doLogin() ....");
	log.debug("clear the content in emai leditbox and type the value:"+uname);
	clearText(emailEditbox);
	sendKey(emailEditbox,uname);
	log.debug("clear the content in  password editbox and type the value:");
	clearText(paswordEditbox);
	sendKey(paswordEditbox,pwd);
	clickSigninBtn();
	return new LinkedinLoggedinPage();
}


}
