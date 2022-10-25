package com.testpom.design;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.testpom.actiondriver.CommonMethods;

import utils.DataInputProvider;

public class MyProjectMethods extends CommonMethods {
	public String testCaseName, testDescription, testNodes, author, category, dataSheetName;

	@BeforeSuite
	public void beforeSuite() {
		startReport();
	}

	@AfterSuite
	public void afterSuite() {
		endReport();
	}

	@BeforeClass
	public void beforeClass() {
		startTestModule(testCaseName, testDescription);
	}

	@BeforeMethod
	public void beforeMethod() {
		test = startTestCase(testNodes);
		test.assignAuthor(author);
		test.assignCategory(category);
		startApp("chrome", "https://www.saucedemo.com/");

	}

	@AfterMethod
	public void closeApp() {
		close();
	}

	@DataProvider(name = "fetchData")
	public Object[][] getData() {
		return DataInputProvider.readData(dataSheetName);
	}


}
