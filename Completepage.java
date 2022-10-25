package com.testpom.pages;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.testpom.design.MyProjectMethods;

public class Completepage extends MyProjectMethods {

	
	public Completepage()
	{
		PageFactory.initElements(driver, this);
	}
	public boolean iscomplted()
	{
		String expected =driver.getCurrentUrl();
		Assert.assertEquals("https://www.saucedemo.com/checkout-complete.html",expected);
		return true;
	}
	
}
