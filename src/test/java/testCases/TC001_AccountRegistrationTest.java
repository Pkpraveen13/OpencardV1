package testCases;




import org.testng.Assert;
import org.testng.annotations.Test;

import TestBase.BaseClass;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class TC001_AccountRegistrationTest  extends BaseClass
{
		
	@Test(groups = {"Regression","Master"})
	public void verify_account_registration()
	{
		logger.info("*** Starting TC001_AccountRegistrationTest ***");
		logger.debug("This is a debug log message");
		try
		{
			
		
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		
		logger.info("Clicked on MyAccount Link");

		hp.clickRegister();
	
		logger.info("Clicked on Register Link");
		
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		
		logger.info("Providing Customer Details...");
		
		regpage.setFirstName(randomeString().toUpperCase());
		regpage.setLastName(randomeString().toUpperCase());
		regpage.setEmail(randomeString()+"@gmail.com");
		regpage.setTelephone(randomeNumber());
		
		String password = randomeAlphaNumberic();
		
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		
		regpage.setPrivacyPolicy();
		regpage.clickcontinue();
		
		
		logger.info("Validating Expected message..");
		
		String confmsg = regpage.getConfirmationMsg();
		if(confmsg.equals("Your Account Has Been Created!!!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test Failed..");// TODO: handle exception
			logger.debug("Debug logs..");
			Assert.assertTrue(false);
		}
		//Assert.assertEquals(confmsg, "Your Account Has Been Created!!!");
	
		
		}
		catch (Exception e) 
		{
			logger.error("Test Failed..");// TODO: handle exception
			logger.debug("Debug logs..");
			Assert.fail();
		}
		logger.info("*** Stating TC001_AccountRegistrationTest***");
	}

	  
	
	
}
