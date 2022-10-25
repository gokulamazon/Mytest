package com.testpom.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.testpom.design.MyProjectMethods;

public class loginpage extends MyProjectMethods {
	
	@FindBy(id="user-name")
	WebElement usertab;
	
	@FindBy(id="password")
	WebElement passtab;
	
	@FindBy(id="login-button")
	WebElement loginbtn;
	
	public loginpage()
	{
		PageFactory.initElements(driver, this);
	}
	public loginpage enterusername(String data)
	{
		clearAndType(usertab, data);
		return this;
	}
	public loginpage enterpassword(String data)
	{
		clearAndType(passtab, data);
		return this;
	}
	public productpage clickloginbtn()
	{
		click(loginbtn);
		return new productpage();
	}
	
}
