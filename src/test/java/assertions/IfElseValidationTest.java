package assertions;

import org.testng.annotations.Test;

public class IfElseValidationTest 
{
	@Test
	public void demo()
	{
	String s1 = "Hello";
	String s2 = "Hi";
     	
	if(s1.equals(s2))
	{
		System.out.println("pass");
	}
	else
	{
		System.out.println("fail");
	}
	}

}


