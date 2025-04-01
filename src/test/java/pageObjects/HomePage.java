package pageObjects;

import javax.xml.xpath.XPath;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage 

{

	 public HomePage(WebDriver driver)
	 {
	     super(driver);// TODO Auto-generated constructor stub
	 }
	@FindBy(xpath = "//span[normalize-space()='My Account']") WebElement lnkMyaccount;
	
	@FindBy(xpath = "//a[normalize-space()='Register']") WebElement linkRegister;
	
	@FindBy(linkText = "Login") WebElement LinkLogin;
	
	public void clickMyAccount()
	{
		lnkMyaccount.click();
	}
	
	public void clickRegister()
	{
		linkRegister.click();
		
	}
	
	public void clickLogin()
	{
		LinkLogin.click();
	}
}
