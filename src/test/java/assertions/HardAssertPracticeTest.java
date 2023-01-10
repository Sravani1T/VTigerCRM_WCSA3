package assertions;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HardAssertPracticeTest 
{
	@Test
	public void demo()
	{
		String s1 = "Hello";
		String s2 = "Hi";
		
	
		Assert.assertTrue(s1.equals(s2));
		
		System.out.println("Hello");
		System.out.println("Hi");
		
	}
	
	@Test
	public void demo2()
	{
		Reporter.log("In demo2", true);
	}

}
