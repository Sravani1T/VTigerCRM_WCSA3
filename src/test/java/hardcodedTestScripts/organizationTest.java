package hardcodedTestScripts;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class organizationTest
{

	public static void main(String[] args) throws InterruptedException
	{
		
		        // used to create random number for organization name
				Random random = new Random();
				int randomNum = random.nextInt(100);  // means more than 100 we will not create
				
				
				
		WebDriverManager.chromedriver().setup();  // it will download chrome driver
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		
		
		driver.get("http://localhost:8888/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
		
		// to verify login page is displayed or not by using logo
		
		String title = driver.findElement(By.xpath("//a[@href='http://www.vtiger.com']")).getText();
		
		if(title.contains("vtiger"))
		   System.out.println("Login page is displayed");
		else
			System.out.println("login page is not displayed");
		
			
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		// to verify home page is displayed or not by using
		
		String homepageText = driver.findElement(By.xpath("//a[@class='hdrLink']")).getText();
		
		if(homepageText.contains("Home"))		
			System.out.println("Home page is displayed");		
		else		
			System.out.println("Home page is not displayed");
		
		
		
		driver.findElement(By.xpath("//a[.='Organizations']")).click();
		
		// to verify Organizations page is displayed or not by using organization name
		
		String Organizations = driver.findElement(By.xpath("//a[@class='hdrLink']")).getText();
		
		if(Organizations.contains("Organizations"))
		{
			System.out.println("Organization page is displayed");
		}
		else
		{
			System.out.println("Organization page is not displayed");
		}
		
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		// to verify create organization page is displayed by using Creating new organization
		
		String createOrgText = driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
		
		if(createOrgText.contains("Creating New Organization"))
		{
			System.out.println("Create organization page is displayed");
		}
		else
		{
			System.out.println("Create organization page is not displayed");
		}
		
		
		String organizationName = "Qspiders" + randomNum; // becz while executing 1st time it will create org vith Qspiders and 2nd time if we try to execute the script it will not execute becz application will through org name already exists so along with name we will generate random num
		
		driver.findElement(By.name("accountname")).sendKeys(organizationName);
		
		// To get the address of list box
		//WebElement industry=driver.findElement(By.xpath("//select[@name='industry']"));
		
		// To pass address of list box
		//Select sel=new Select(industry);
		
		// To select based on text
		//sel.selectByVisibleText("Telecommunications");
		          // OR
		
		Select industryDD=new Select(driver.findElement(By.xpath("//select[@name='industry']")));
		industryDD.selectByVisibleText("Telecommunications");
		
		driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
		
		
		
		// to verify organization is created or not 
		
		String newOrgInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		if(newOrgInfo.contains(organizationName))
		{
			System.out.println("Organization is created");
		}
		else
		{
			System.out.println("Organization is not created");
		}
		
	
				
		driver.findElement(By.xpath("//a[@class='hdrLink']")).click(); 
		
		
		//validate whether created recored is displayed or not 
		
	    String newOrg =	driver.findElement(By.xpath("//table[@class='lvt small']/descendant::tr[last()]/td[3]")).getText();
	    if(newOrg.contains(organizationName))
	    	System.out.println("newly created organization is displayed");
	    else
	    	System.out.println("newly created organization is not displayed");
	
	    // to perform mouse hover on administrator icon
		WebElement administratorIcon = driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
	    Actions a = new Actions(driver);
	    a.moveToElement(administratorIcon).perform();
	    
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		
		Thread.sleep(5000);
		
		
		driver.quit();  // it will close all windows opened by selenium
		
		
	}

}
