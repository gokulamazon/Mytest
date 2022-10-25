package com.testpom.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.testpom.design.MyProjectMethods;

public class Checkoutpage extends MyProjectMethods {
	@FindBy(id="checkout")
	WebElement checkbtn;
	
	public Checkoutpage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public Informationpage checkoutbtn()
	{
		click(checkbtn);
		return new Informationpage();
	}
}
