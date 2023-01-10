package hardcodedTestScripts;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateAndDuplicateLeadTest
{
	
	public static void main(String[] args) throws InterruptedException
	{
		Random random = new Random();
		int randomNum = random.nextInt(100);
		
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver=new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
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
				
				
				driver.findElement(By.xpath("//a[@href=\"index.php?module=Leads&action=index\"]")).click();
				
				String LeadsText=driver.findElement(By.xpath("//a[@class='hdrLink']")).getText();
				if(LeadsText.contains("Leads"))
				{
					System.out.println("Leads page is displayed");
				}
				else
				{
					System.out.println("Leads page is displayed");
				}
				
				
				driver.findElement(By.xpath("//img[@title='Create Lead...']")).click();
				String CreateLeadText = driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
				if(CreateLeadText.contains("Creating New Lead"))
				{
					System.out.println("Create lead page is displayed");
				}
				else
				{
					System.out.println("Create lead page is not displayed");
				}
				
				Select salutationTypeDD=new Select(driver.findElement(By.xpath("//select[@name='salutationtype']")));
				salutationTypeDD.selectByValue("Mrs.");
				
				String leadLastName = "Srav" + randomNum;
				
				driver.findElement(By.name("lastname")).sendKeys(leadLastName);
				
				driver.findElement(By.name("company")).sendKeys("TYSS");
				
				driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
				
				String newLeadInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if(newLeadInfo.contains(leadLastName))
				{
					System.out.println("Lead information page is displayed");
				}
				else
				{
					System.out.println("Lead information page is not displayed");
				}
				
				
				driver.findElement(By.xpath("//input[@name='Duplicate']")).click();
				
				String DupicatePageText = driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
				if(DupicatePageText.contains("Duplicating"))
					 System.out.println("Duplicate page is displayed");
				else
					System.out.println("Duplicate page is not displayed");
		
				
					driver.findElement(By.xpath("//input[@name='lastname']")).clear();
					
					String duplicateLeadName = "Sravani" + randomNum;
					driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(duplicateLeadName);
			
	
				
				driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
				
	           driver.findElement(By.xpath("//a[@class='hdrLink']")).click();
				
	               // to verify modified lead is added or not
				String newLead = driver.findElement(By.xpath("//table[@class='lvt small']/descendant::tr[last()]/td[3]/a")).getText();
				if(newLead.contains(duplicateLeadName))
					System.out.println("Lead is created");
				else
					System.out.println("Lead is not created");
				
				Thread.sleep(5000);
				WebElement administratorIcon = driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
			    Actions a = new Actions(driver);
			    a.moveToElement(administratorIcon).perform();
			    
				driver.findElement(By.xpath("//a[.='Sign Out']")).click();
				
				Thread.sleep(5000);
				
				
				driver.quit();  // it will close all windows opened by selenium				
				
				
	}
	

}
