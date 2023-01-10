package testNG;

import org.testng.annotations.Test;

import genericUtility.BaseClass;

public class FirstTest extends BaseClass
{
	@Test
	public void first1Test()
	{
		System.out.println("first1Test");
	}
	
	@Test
	public void first2Test()
	{
		System.out.println("first2Test");
	}

}
