package com.testpom.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.testpom.design.MyProjectMethods;

public class productpage extends MyProjectMethods {
	@FindBy(id="add-to-cart-sauce-labs-backpack")
	WebElement addcart;
	
	@FindBy(id="shopping_cart_container")
	WebElement cartcheck;
	
	public productpage()
	{
		PageFactory.initElements(driver, this);
	}
	public productpage clickprod()
	{
		click(addcart);
		return this;
	}
	public Checkoutpage clickcart()
	{
		click(cartcheck);
		return new Checkoutpage();
	}
	
}
