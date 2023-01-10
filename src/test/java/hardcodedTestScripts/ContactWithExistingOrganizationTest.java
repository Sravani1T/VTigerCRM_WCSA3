package hardcodedTestScripts;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ContactWithExistingOrganizationTest 
{
	
	public static void main(String[] args) throws InterruptedException
	{
		
		
		// used to create random number
				Random random = new Random();
				int randomNum = random.nextInt(100);  // means more than 100 we will not create
	
				
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver=new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("http://localhost:8888/");
		
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
				
				driver.findElement(By.xpath("//a[.='Contacts']")).click();
				
				
				// to verify contacts page displayed or not by using contacts text
				String ContactsPageText=driver.findElement(By.xpath("//a[@class='hdrLink']")).getText();
				
				if(ContactsPageText.contains("Contacts"))
			    	System.out.println("Contacts page is displayed");
				else
				    System.out.println("Contacts page is not displayed");
			
					
				driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
				
				// to verify create contact page displayed or not by using create new contact text
				String createNewContactPageText=driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
				
				if(createNewContactPageText.contains("Creating New Contact"))
				{
					System.out.println("Create contact page is dislayed");
				}
				else
				{
					System.out.println("Create contact page is not dislayed");
				}
				
				
				Select salutationTypeDD=new Select(driver.findElement(By.xpath("//select[@name='salutationtype']")));
				salutationTypeDD.selectByValue("Mrs.");
				
				String lastName = "Srav" + randomNum;
				
				driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName);
						
				driver.findElement(By.xpath("//img[contains(@onclick,'Accounts&action')]")).click();
			
				String parentWindow = driver.getWindowHandle();
				Set<String> windows = driver.getWindowHandles();
				for(String wID : windows) {
					driver.switchTo().window(wID);
				}
		    	
		    	
		    	
		    	String organization = "Qspiders";
		    	
				List<WebElement> organizationList = driver.findElements(By.xpath("//div[@id='ListViewContents']/descendant::table[@cellpadding='5']/descendant::tr/td[1]/a"));

				for(WebElement org: organizationList) {
					String name = org.getText();
					if(name.contains("Qspiders")) {
						org.click();
						break;
					}
				}
				
				
				driver.switchTo().window(parentWindow);
					
				
				//driver.findElement(By.name("imagename")).sendKeys("\"C:\\Users\\AMARSRAVANI\\Pictures\\Saved Pictures\\amar.JPEG\"");
	
				
				driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
		
				
					driver.findElement(By.xpath("//a[@class='hdrLink']")).click();
					
					// how to verify created record displayed or not
				
				String newContact = driver.findElement(By.xpath("//table[@class='lvt small']/descendant::tr[last()]/td[4]/a")).getText();
				if(newContact.contains(lastName))
					System.out.println("Contact is created");
				else
					System.out.println("Contact is not created");
				
				Thread.sleep(5000);
				
				WebElement administratorIcon = driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
			    Actions a = new Actions(driver);
			    a.moveToElement(administratorIcon).perform();
			    
				driver.findElement(By.xpath("//a[.='Sign Out']")).click();
				
				Thread.sleep(5000);
				
				
				driver.quit();  // it will close all windows opened by selenium				
				
				
				
				
	}
	
	
	
	
	

}
