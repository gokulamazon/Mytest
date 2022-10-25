package com.testpom.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.testpom.design.MyProjectMethods;

public class Informationpage extends MyProjectMethods{
	@FindBy(id="first-name")
	WebElement firstname;
	
	@FindBy(id="last-name")
	WebElement lastname;
	
	@FindBy(id="postal-code")
	WebElement zipcode;
	
	@FindBy(id="continue")
	WebElement continuebtn;
	
	public Informationpage()
	{
		PageFactory.initElements(driver, this);
	}
	public Informationpage firstnameenter(String data)
	{
		clearAndType(firstname, data);
		return this;
	}
	public Informationpage lastnameenter(String data)
	{
		clearAndType(lastname, data);
		return this;
	}
	public Informationpage zipcodeenter(String data)
	{
		clearAndType(zipcode, data);
		return this;
	}
	public Overviewpage continuebtn()
	{
		click(continuebtn);
		return new Overviewpage();
	}
	
	
}
