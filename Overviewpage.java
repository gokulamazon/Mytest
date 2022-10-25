package com.testpom.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.testpom.design.MyProjectMethods;

public class Overviewpage extends MyProjectMethods{
	@FindBy(id="finish")
	WebElement finishbtn;
	
	public Overviewpage()
	{
		PageFactory.initElements(driver, this);
	}
	public Completepage finish()
	{
		click(finishbtn);
		return new Completepage();
	}
}
