package TestBase;


import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class BaseClass 
{
	public WebDriver driver;
	public Logger logger; //Log4j
	public Properties p;
	@BeforeClass(groups = {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os, String br)  throws IOException 
	{
		
		 try (FileReader file = new FileReader("./src/test/resources/config.properties")) {
	            p = new Properties();
	            p.load(file);
	        } catch (IOException e) {
	            System.out.println("Error loading config.properties: " + e.getMessage());
	            throw e; // Re-throw exception
	        }
		
		
		logger = LogManager.getLogger(this.getClass());
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			// os
			
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("linux"))
			{
			//browser
				capabilities.setPlatform(Platform.LINUX);
		}
			
			else if(os.equalsIgnoreCase("mac"))
			{
			//browser
				capabilities.setPlatform(Platform.MAC);
		}
			else
			{
				System.out.println("No MAtching OS");
				return;
			}
			//browser
			switch (br.toLowerCase())
			{
			case"chrome": capabilities.setBrowserName("chrome"); break;
			case"edge": capabilities.setBrowserName("MicroSoftEdge"); break;
			case"firefox": capabilities.setBrowserName("firefox"); break;	
			

			default: System.out.println("No matching browser");
				break;
			}
			
			driver = new RemoteWebDriver(new URL("http://192.168.0.123:4444/wd/hub"),capabilities);
			
		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(br.toLowerCase())
			{
			case"chrome": 
			driver = new ChromeDriver();
			logger.info("Chrome browser launched");
			break;
			case"edge": 
			driver = new EdgeDriver(); 
			logger.info("Edge browser launched");
			break;
			case"firefox": 
			driver = new FirefoxDriver(); 
			logger.info("Firefox browser launched");
			break;
			default : System.out.println("Invalid browser name..");
			return;
			
			}
		}
		
	
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appURL1")); //reading url from properties file
	
		driver.manage().window().maximize();
	}
	
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	
	public String randomeString()
	{
		 
		return RandomStringUtils.randomAlphabetic(5); 
	}
	
	
	public String randomeNumber()
	{
		return RandomStringUtils.randomNumeric(10);
	}
	
	public String randomeAlphaNumberic()
	{
		return RandomStringUtils.randomAlphabetic(3) + "@" + RandomStringUtils.randomNumeric(3);
		
	}


	public String captureScreen(String name) throws IOException
	{
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date(0));
		
		
	       File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        String screenshotPath = System.getProperty("user.dir") + "\\screenshots\\" + name + "_" + timeStamp + ".png";
	        
	        File destFile = new File(screenshotPath);
	        FileUtils.copyFile(srcFile, destFile);
	        
	        return screenshotPath;
	}
	
	
	
	
	
	
}