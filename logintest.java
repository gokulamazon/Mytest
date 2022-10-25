package com.testpom.testcase;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.testpom.design.MyProjectMethods;
import com.testpom.pages.Checkoutpage;
import com.testpom.pages.Completepage;
import com.testpom.pages.Informationpage;
import com.testpom.pages.Overviewpage;
import com.testpom.pages.loginpage;
import com.testpom.pages.productpage;

public class logintest extends MyProjectMethods {

	@BeforeTest
	public void setdata()
	{
		testCaseName="logintest";
		testDescription="login into productpage";
		testNodes="Login";
		author="gokul";
		category="smoke";
		dataSheetName="TC_001";
	}
	@Test(dataProvider = "fetchData")
	public void login(String username, String password, String firstname1, String lastname1, String zipcode)
	{
		new loginpage().enterusername(username).enterpassword(password).clickloginbtn();
		new productpage().clickprod().clickcart();
		new Checkoutpage().checkoutbtn();
		new Informationpage().firstnameenter(firstname1).lastnameenter(lastname1).zipcodeenter(zipcode).continuebtn();
		new Overviewpage().finish();
		new Completepage().iscomplted();
	}
	

}
