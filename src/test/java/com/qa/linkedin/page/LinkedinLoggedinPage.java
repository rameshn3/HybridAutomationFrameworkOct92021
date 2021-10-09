package com.qa.linkedin.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.log4testng.Logger;

public class LinkedinLoggedinPage extends BasePageWeb{
	private static Logger log=Logger.getLogger(LinkedinLoggedinPage.class);
	//create a constructor
	public LinkedinLoggedinPage() {
		PageFactory.initElements(driver, this);
	}

	//identify the elements
	@FindBy(xpath="//div[contains(@class,'profile-rail-card')]")
	private WebElement profilerailCard;

	@FindBy(xpath="//img[@class='global-nav__me-photo ember-view']")
	private WebElement profileImageIcon;

	@FindBy(xpath="//a[contains(.,'Sign Out')]")
	private WebElement signOutLink;

	@FindBy(xpath="//input[contains(@class,'search-global-typeahead')]")
	private WebElement searchEditbox;

	
	public void verifyLinkedinLoggedinProfileRailCard() {
		log.debug("Verify linkedinLoggedinPage profile rail card ");
		wait.until(ExpectedConditions.visibilityOf(profilerailCard));
		Assert.assertTrue(profilerailCard.isDisplayed());
	}
	
		
	public void doLogout() throws InterruptedException {
		log.debug("Started executing the doLogou() for :");
		
		log.debug("wait for profile image picture element");
		wait.until(ExpectedConditions.visibilityOf(profileImageIcon));
		log.debug("Click on profile me image icon");
		clickUsingJsExecutor(profileImageIcon);
		log.debug("wait for the Signout link");
		
		wait.until(ExpectedConditions.visibilityOf(signOutLink));
		log.debug("Click on Singout link");
		click(signOutLink);
		
		log.debug("###############End of doLogin() ######################");

	}
	
	public SearchResultsPage doPeopleSearch(String keyword) throws InterruptedException {
		log.debug("started executing the doPeopleSearch() for the people keyword:  "+keyword);
		log.debug("clear the content in searc hedibtox");
		clearText(searchEditbox);
		log.debug("type the keyword:"+keyword+" in search editbox");
		sendKey(searchEditbox,keyword);
		log.debug("Press the ENTER key from keyword");
		searchEditbox.sendKeys(Keys.ENTER);
		log.debug("wait for the results page");
		Thread.sleep(2000);
		
		return new SearchResultsPage();
	}
	
	
	
}
