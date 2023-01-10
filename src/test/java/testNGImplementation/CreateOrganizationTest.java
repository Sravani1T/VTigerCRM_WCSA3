package testNGImplementation;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.BaseClass;
import genericLibraries.ExcelFileUtility;
import genericLibraries.IConstantPath;
import genericLibraries.JavaUtility;
import genericLibraries.PropertyFileUtility;
import genericLibraries.WebDriverUtility;
import pomPages.CreateNewOrganizationPage;
import pomPages.HomePage;
import pomPages.LoginPage;
import pomPages.NewOrganizationInfoPage;
import pomPages.OrganizationsPage;

@Listeners(genericLibraries.ListenerImplemntation.class)
public class CreateOrganizationTest extends BaseClass
{
    @Test
	public void createOrganizationTest()  
	{
	
		SoftAssert softAssert = new SoftAssert();
		
		home.clickOrganizations();
		softAssert.assertTrue(organization.getPageHeader().contains("Organization"));
		
		organization.clickPlusButton();
		softAssert.assertTrue(createOrganization.getPageHeader().contains("Creating  Organization"));
		
		
		Map<String,String> map = excel.getDataBasedOnKey("TestData", "Create Organization");
		
		String organizationName = map.get("Organization Name") + javaUtil.generateRandomNumber(100);
		createOrganization.setOrganizationName(organizationName);
		
		createOrganization.selectIndustry(web, map.get("Industry"));
		createOrganization.clickSaveButton();
		softAssert.assertTrue(newOrganization.getPageHeader().contains(organizationName));
		
	
		newOrganization.clickOrganizationsLink();
		softAssert.assertTrue(organization.getNewOrganization().equals(organizationName));
		if(organization.getNewOrganization().equals(organizationName)) 
		{
			excel.updateTestStatusInExcel("TestData", "Create Organization", "Pass", IConstantPath.EXCEL_FILE_PATH);
		}
		else 
		{
			excel.updateTestStatusInExcel("TestData", "Create Organization", "Fail", IConstantPath.EXCEL_FILE_PATH);
		}			
		
		
		softAssert.assertAll();
	}

}