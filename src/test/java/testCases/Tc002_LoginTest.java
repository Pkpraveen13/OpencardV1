package testCases;



import org.testng.Assert;
import org.testng.annotations.Test;

import TestBase.BaseClass;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class Tc002_LoginTest extends BaseClass
{
 @Test(groups = {"Sanity", "Master"})
 public void verify_login()
 {
 logger.info("********Starting TC_002_LoginTest******");
try {
 HomePage hp = new HomePage(driver);
 hp.clickMyAccount();
 hp.clickLogin();
 
 LoginPage lp= new LoginPage(driver);
 lp.setEmail(p.getProperty("email"));
 lp.setPassword(p.getProperty("password"));
 lp.clickLogin();
 
 //My Account Page
 
 MyAccountPage macc = new MyAccountPage(driver);
 
 boolean targetPage = macc.isMyAccountPageExists();
 
 
 //Assert.assertEquals(targetPage, true,"Login Failed");
 
 Assert.assertTrue(targetPage);
 }
 catch(Exception e)
{
	 Assert.fail();
}
 logger.info("********Finished TC_002_LoginTest******");
 
 }
	
	
	
	
	
}
