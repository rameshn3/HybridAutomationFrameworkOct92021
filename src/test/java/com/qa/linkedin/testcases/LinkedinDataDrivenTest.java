package com.qa.linkedin.testcases;

import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.qa.linkedin.base.TestBase;
import com.qa.linkedin.page.LinkedinHomePage;
import com.qa.linkedin.page.LinkedinLoggedinPage;
import com.qa.linkedin.page.SearchResultsPage;
import com.qa.linkedin.util.ExcelUtils;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.AfterClass;
/**
* This is Testcase
*/
public class LinkedinDataDrivenTest extends TestBase{
	private Logger log=Logger.getLogger(LinkedinDataDrivenTest.class);
	
	//declare all page objects
	private String fpath=System.getProperty("user.dir")+"\\src\\test\\java\\com\\qa\\linkedin\\data\\searchData.xlsx";
	LinkedinHomePage lHomePage;
	LinkedinLoggedinPage lloginPage;
	SearchResultsPage srPage;
	
	@BeforeClass
	  public void beforeClass() {
		log.debug("Initilize the page objects");
		lHomePage=new LinkedinHomePage();
		lloginPage=new LinkedinLoggedinPage();
		srPage=new SearchResultsPage();
	  }
	
	@Test(description="login to linkedin")
	public void doLoginTest() throws InterruptedException, IOException {
		log.debug("started executing the  doLoginTest(String uname,String pwd)...");
		log.debug("initial validations of linkedin pages");
		log.debug("verify the linkedin home page title");
		lHomePage.verifyLinkedinHomePageTitle();
		lHomePage.verifyLinkedinLogo();
		log.debug("click on sign in link in linkedin home page");
		lHomePage.clickSigninLink();
		log.debug("navigating to linkedin login page");
		lHomePage.verifyLinkedinLoginPageTitle();
		lHomePage.verifyLinkedinLoginSignHeaderTex();
		lloginPage = lHomePage.doLogin(readPropertyValue("username"), readPropertyValue("pwd"));
	}
	
	
  @Test(dataProvider = "dp",dependsOnMethods= {"doLoginTest"})
  public void doPeopeSearchTest(String s) throws Exception {
	  log.debug("started executing the doPeopeSearchTest(String s) for :"+s);
	  lloginPage.verifyLinkedinLoggedinProfileRailCard();
	  log.debug("performing people search for :"+s);
	  srPage= lloginPage.doPeopleSearch(s);
	  long count=srPage.getResultCount();
	  log.debug("search results count for "+s+" is: "+count);
	  
	  Thread.sleep(1000);
	  log.info("navigating back to home page for next iteration...");
	  srPage.clickHomeTab();
	  Thread.sleep(1000);
  }

  @DataProvider
  public Object[][] dp() throws InvalidFormatException, IOException {
    Object[][] data=new ExcelUtils().getTestData(fpath, "Sheet1");
  	 return data;
    
  }
  /**
*I am loging out from application
*/

  @AfterClass
  public void afterClass() throws InterruptedException {
	  log.debug("perform the logout from application");
	  Thread.sleep(2000);
	  lloginPage.doLogout();
  }

}
