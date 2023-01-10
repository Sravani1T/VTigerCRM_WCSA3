package testNGImplementation;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.BaseClass;
import genericLibraries.ExcelFileUtility;
import genericLibraries.IConstantPath;
import genericLibraries.JavaUtility;
import genericLibraries.PropertyFileUtility;
import genericLibraries.WebDriverUtility;
import pomPages.CreateNewLeadPage;
import pomPages.CreateNewOrganizationPage;
import pomPages.HomePage;
import pomPages.LeadsPage;
import pomPages.LoginPage;
import pomPages.NewLeadInfoPage;
import pomPages.NewOrganizationInfoPage;
import pomPages.OrganizationsPage;
import pomPages.DuplicatingPage;

@Listeners(genericLibraries.ListenerImplemntation.class)
public class CreateAndDuplicateLeadTest extends BaseClass
{
    @Test
	public void createDuplicateLeadTest()
	{

    	SoftAssert softAssert = new SoftAssert();
		home.clickLeads();
		softAssert.assertTrue(leads.getPageHeader().contains("Leads"));
		
		leads.clickPlusButton();
		softAssert.assertTrue(createLead.getPageHeader().contains("Creating New Lead"));

		Map<String,String> map = excel.getDataBasedOnKey("TestData", "Create Lead");
        createLead.selectSalutation(web, map.get("First Name Salutation"));
        
		String leadName = map.get("Last Name")+javaUtil.generateRandomNumber(100);
		
		createLead.setLastName(leadName);
		createLead.setCompanyName(map.get("Company"));
		createLead.clickSave();

		softAssert.assertTrue(newLeadInfo.getPageHeader().contains(leadName));
		
		newLeadInfo.clickDuplicateButton();
		
		String duplicateLeadName = map.get("New Last Name")+javaUtil.generateRandomNumber(100);
		
		duplicating.setLastName(duplicateLeadName);
		duplicating.clickSave();
		
		newLeadInfo.clickLeadsLink();
		softAssert.assertTrue(leads.getNewLead().equals(duplicateLeadName));
		if(leads.getNewLead().equals(duplicateLeadName)) 
			excel.updateTestStatusInExcel("TestData", "Create Lead", "Pass", IConstantPath.EXCEL_FILE_PATH);
		else 
			excel.updateTestStatusInExcel("TestData", "Create Lead", "Fail", IConstantPath.EXCEL_FILE_PATH);
		softAssert.assertAll();
	}
}