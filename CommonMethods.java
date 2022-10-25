package com.testpom.actiondriver;

	import java.io.File;
	import java.io.IOException;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.Set;
	import java.util.concurrent.TimeUnit;

	import org.apache.commons.io.FileUtils;
	import org.openqa.selenium.Alert;
	import org.openqa.selenium.InvalidElementStateException;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.NoAlertPresentException;
	import org.openqa.selenium.NoSuchElementException;
	import org.openqa.selenium.NoSuchFrameException;
	import org.openqa.selenium.NoSuchWindowException;
	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.TimeoutException;
	import org.openqa.selenium.WebDriverException;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.remote.RemoteWebDriver;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.Select;
	import org.openqa.selenium.support.ui.WebDriverWait;

	import utils.Reporter;

	public class CommonMethods extends Reporter {

		public static RemoteWebDriver driver;

		public void startApp(String url) {
			try {
				System.setProperty("webdriver.chrome.driver", "C:\\106 cr-driver\\chromedriver.exe");
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.get(url);
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				logStep("The browser chrome launched successfully", "pass");
			} catch (WebDriverException e) {
				logStep("The browser could not be launched", "fail");
			}
		}

		public void startApp(String browser, String url) {
			try {
				if (browser.equalsIgnoreCase("chrome")) {

					System.setProperty("webdriver.chrome.driver", "C:\\106 cr-driver\\chromedriver.exe");
					driver = new ChromeDriver();
				} else if (browser.equalsIgnoreCase("firefox")) {
					System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver");
					driver = new FirefoxDriver();

				}
				driver.manage().window().maximize();
				driver.get(url);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				logStep("The browser " + browser + " launched successfully", "pass");
			} catch (WebDriverException e) {
				logStep("The browser " + browser + " could not be launched", "fail");
			}
		}

		public WebElement locateElement(String locatorType, String value) {
			try {
				switch (locatorType) {
				case "id":
					return driver.findElementById(value);
				case "name":
					return driver.findElementByName(value);
				case "class":
					return driver.findElementByClassName(value);
				case "link":
					return driver.findElementByLinkText(value);
				case "tagName":
					return driver.findElementByTagName(value);
				case "xpath":
					return driver.findElementByXPath(value);
				case "css":
					return driver.findElementByCssSelector(value);
				}
			} catch (NoSuchElementException e) {
				logStep("The element with locator " + locatorType + " not found.", "fail");
			} catch (WebDriverException e) {
				logStep("Unknown exception occured while finding " + locatorType + " with the value " + value, "fail");
			}
			return null;
		}

		public WebElement locateElement(String value) {
			try {
				return driver.findElementById(value);
			} catch (NoSuchElementException e) {
				logStep("The element with locator id not found.", "fail");
			} catch (WebDriverException e) {
				logStep("Unknown exception occured while finding id with the value " + value, "fail");
			}
			return null;
		}

		public List<WebElement> locateElements(String type, String value) {
			try {
				switch (type) {
				case "id":
					return driver.findElementsById(value);
				case "name":
					return driver.findElementsByName(value);
				case "class":
					return driver.findElementsByClassName(value);
				case "link":
					return driver.findElementsByLinkText(value);
				case "tagName":
					return driver.findElementsByTagName(value);
				case "xpath":
					return driver.findElementsByXPath(value);
				case "css":
					return driver.findElementsByCssSelector(value);
				}
			} catch (WebDriverException e) {
				logStep("Element not located", "fail");
			}
			return null;
		}

		public void switchToAlert() {
			String text = "";
			try {
				Alert alert = driver.switchTo().alert();
				logStep("The alert " + text + " is switched.", "pass");
			} catch (NoAlertPresentException e) {
				logStep("There is no alert present.", "fail");
			} catch (WebDriverException e) {
				logStep("WebDriverException as occured: In switchToAlert", "fail");
			}

		}

		public void acceptAlert() {
			String text = "";
			try {
				Alert alert = driver.switchTo().alert();
				text = alert.getText();
				alert.accept();
				logStep("The alert " + text + " is accepted.", "pass");
			} catch (NoAlertPresentException e) {
				logStep("There is no alert present.", "fail");
			} catch (WebDriverException e) {
				logStep("WebDriverException as occured: In acceptAlert", "fail");
			}

		}

		public void dismissAlert() {
			String text = "";
			try {
				Alert alert = driver.switchTo().alert();
				text = alert.getText();
				alert.dismiss();
				logStep("The alert " + text + " is dismissed.", "pass");
			} catch (NoAlertPresentException e) {
				logStep("There is no alert present.", "fail");
			} catch (WebDriverException e) {
				logStep("WebDriverException as occured: In dismissAlert", "fail");
			}

		}

		public String getAlertText() {
			String text = "";
			try {
				Alert alert = driver.switchTo().alert();
				text = alert.getText();
			} catch (NoAlertPresentException e) {
				logStep("There is no alert present.", "fail");
			} catch (WebDriverException e) {
				logStep("WebDriverException as occured: In getAlertText", "fail");
			}
			return text;
		}

		public void typeAlert(String data) {
			String text="";
			try {
				Alert alert = driver.switchTo().alert();
				text = alert.getText();
				alert.sendKeys(data);
			} catch (NoAlertPresentException e) {
				logStep("There is no alert present.", "fail");
			} catch (WebDriverException e) {
				logStep("WebDriverException as occured: In getAlertText", "fail");
			}
		}

		public void switchToWindow(int index) {
			try {
				Set<String> allWindowHandles = driver.getWindowHandles();
				List<String> allHandles = new ArrayList<>();
				allHandles.addAll(allWindowHandles);
				driver.switchTo().window(allHandles.get(index));
			} catch (NoSuchWindowException e) {
				logStep("The driver could not move to the given window by index " + index, "pass");
			} catch (WebDriverException e) {
				logStep("WebDriverException as occured: In switchToWindow", "fail");
			}

		}

		public void switchToWindow(String title) {
			try {
				Set<String> allWindows = driver.getWindowHandles();
				for (String eachWindow : allWindows) {
					driver.switchTo().window(eachWindow);
					if (driver.getTitle().equals(title)) {
						break;
					}
				}
				logStep("The Window With Title: " + title + " is switched ", "pass");
			} catch (NoSuchWindowException e) {
				logStep("The Window With Title: " + title + " not found", "fail");
			}

		}

		public void switchToFrame(int index) {
			try {
				driver.switchTo().frame(index);
				logStep("switch In to the Frame " + index, "pass");
			} catch (NoSuchFrameException e) {
				logStep("NoSuchFrameException as occured", "fail");
			} catch (WebDriverException e) {
				logStep("WebDriverException as occured: In switchToFrame ", "fail");
			}

		}

		public void switchToFrame(WebElement ele) {
			try {
				driver.switchTo().frame(ele);
				logStep("switch In to the Frame " + ele, "pass");
			} catch (NoSuchFrameException e) {
				logStep("NoSuchFrameException as occured", "fail");
			} catch (WebDriverException e) {
				logStep("WebDriverException as occured: In switchToFrame ", "fail");
			}
		}

		public void switchToFrame(String idOrName) {
			try {
				driver.switchTo().frame(idOrName);
				logStep("switch In to the Frame " + idOrName, "pass");
			} catch (NoSuchFrameException e) {
				logStep("NoSuchFrameException as occured", "fail");
			} catch (WebDriverException e) {
				logStep("WebDriverException as occured: In switchToFrame ", "fail");
			}
		}

		public void defaultContent() {
			try {
				driver.switchTo().defaultContent();
				logStep("switch In to the Main html page ", "pass");
			} catch (NoSuchFrameException e) {
				logStep("NoSuchFrameException as occured", "fail");
			} catch (WebDriverException e) {
				logStep("WebDriverException as occured: In switchToFrame ", "fail");
			}
		}

		public boolean verifyUrl(String url) {
			try {
				if (driver.getCurrentUrl().equals(url)) {
					logStep("The url: " + url + " matched successfully", "pass");
					return true;
				} else {
					logStep("The url: " + url + " not matched", "fail");
				}
			} catch (WebDriverException e) {
				logStep("unknow exception occured while verifying the url", "fail");
			}
			return false;
		}

		public boolean verifyTitle(String title) {
			try {
				if (driver.getTitle().equals(title)) {
					logStep("The title: " + title + " matched successfully", "pass");
					return true;
				} else {
					logStep("The title: " + title + " not matched", "fail");
				}
			} catch (WebDriverException e) {
				logStep("unknow exception occured while verifying the title of the page", "fail");
			}
			return false;
		}

		public long takeSnap() {
			long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L;
			try {
				FileUtils.copyFile(driver.getScreenshotAs(OutputType.FILE),
						new File("./reports/images/" + number + ".jpg"));
			} catch (WebDriverException e) {
				System.out.println("The browser has been closed.");
			} catch (IOException e) {
				System.out.println("The snapshot could not be taken");
			}
			return number;
		}

		public void close() {
			try {
				driver.close();
				logStep("The browser is closed", "pass", false);
			} catch (Exception e) {
				logStep("The browser could not be closed", "fail", false);
			}

		}

		public void quit() {
			try {
				driver.quit();
				logStep("The opened browsers are closed", "pass", false);
			} catch (Exception e) {
				logStep("Unexpected error occured in Browser", "fail", false);
			}

		}

		public void click(WebElement ele) {
			String text = "";
			try {
				WebDriverWait wait = new WebDriverWait(driver, 10);
				wait.until(ExpectedConditions.elementToBeClickable(ele));
				text = ele.getText();
				ele.click();
				logStep("The element " + text + " is clicked", "pass");
			} catch (InvalidElementStateException e) {
				logStep("The element: " + text + " could not be clicked", "fail");
			} catch (WebDriverException e) {
				logStep("Unknown exception occured while clicking in the field :", "fail");
			}

		}

		public void clickWithNoSnap(WebElement ele) {
			String text = "";
			try {
				WebDriverWait wait = new WebDriverWait(driver, 10);
				wait.until(ExpectedConditions.elementToBeClickable(ele));
				text = ele.getText();
				ele.click();
				logStep("The element: " + text + "  is clicked.", "pass", false);
			} catch (InvalidElementStateException e) {
				logStep("The element: " + text + " could not be clicked", "fail", false);
			} catch (WebDriverException e) {
				logStep("Unknown exception occured while clicking in the field :", "fail", false);
			}
		}

		public void append(WebElement ele, String data) {
			try {
				ele.sendKeys(data);
				logStep("The data: " + data + " entered successfully in the field :" + ele, "pass");
			} catch (InvalidElementStateException e) {
				logStep("The data: " + data + " could not be entered in the field :" + ele, "fail");
			} catch (WebDriverException e) {
				logStep("Unknown exception occured while entering " + data + " in the field :" + ele, "fail");
			}

		}

		public void clear(WebElement ele) {
			String text = "";
			try {
				text = ele.getText();
				ele.clear();
				logStep("The element: " + text + " cleared successfully", "pass");
			} catch (InvalidElementStateException e) {
				logStep("The element: " + text + " could not be entered", "fail");
			} catch (WebDriverException e) {
				logStep("Unknown exception occured while clearing the field", "fail");
			}

		}

		public void clearAndType(WebElement ele, String data) {
			try {
				ele.clear();
				ele.sendKeys(data);
				logStep("The data: " + data + " entered successfully in the field :" + ele, "pass");
			} catch (InvalidElementStateException e) {
				logStep("The data: " + data + " could not be entered in the field :" + ele, "fail");
			} catch (WebDriverException e) {
				logStep("Unknown exception occured while entering " + data + " in the field :" + ele, "fail");

			}
		}

		public String getElementText(WebElement ele) {
			String bReturn = "";
			try {
				bReturn = ele.getText();
			} catch (WebDriverException e) {
				logStep("The element: " + ele + " could not be found.", "fail");
			}
			return bReturn;
		}

		public String getBackgroundColor(WebElement ele) {
			String bReturn = "";
			try {
				bReturn = ele.getCssValue("background");
				logStep("The element: " + ele + " background color be found.", "pass");
			} catch (WebDriverException e) {
				logStep("The element: " + ele + " could not be found.", "fail");
			}
			return bReturn;
		}

		public String getTypedText(WebElement ele) {
			String bReturn = "";
			try {
				bReturn = ele.getAttribute("value");
				logStep("The element: " + ele + " attribute value found.", "pass");
			} catch (WebDriverException e) {
				logStep("The element: " + ele + " could not be found.", "fail");
			}
			return bReturn;
		}

		public void selectDropDownUsingText(WebElement ele, String value) {
			try {
				new Select(ele).selectByVisibleText(value);
				logStep("The dropdown is selected with text " + value, "pass");
			} catch (WebDriverException e) {
				logStep("The element: " + ele + " could not be found.", "fail");
			}

		}

		public void selectDropDownUsingIndex(WebElement ele, int index) {
			try {
				new Select(ele).selectByIndex(index);
				logStep("The dropdown is selected with text " + index, "pass");
			} catch (WebDriverException e) {
				logStep("The element: " + ele + " could not be found.", "fail");
			}

		}

		public void selectDropDownUsingValue(WebElement ele, String value) {
			try {
				new Select(ele).selectByValue(value);
				logStep("The dropdown is selected with text " + value, "pass");
			} catch (WebDriverException e) {
				logStep("The element: " + ele + " could not be found.", "fail");
			}

		}

		public boolean verifyExactText(WebElement ele, String expectedText) {
			try {
				if (getElementText(ele).equals(expectedText)) {
					logStep("The text: " + getElementText(ele) + " matches with the value: " + expectedText, "pass");
					return true;
				} else {
					logStep("The text: " + getElementText(ele) + " doesn't matches the actual " + expectedText, "fail");
				}
			} catch (WebDriverException e) {
				logStep("Unknown exception occured while verifying the Text", "fail");
			}
			return false;
		}

		public boolean verifyPartialText(WebElement ele, String expectedText) {
			try {
				if (getElementText(ele).contains(expectedText)) {
					logStep("The expected text contains the actual " + expectedText, "pass");
					return true;
				} else {
					logStep("The expected text doesn't contain the actual " + expectedText, "fail");
				}
			} catch (WebDriverException e) {
				logStep("Unknown exception occured while verifying the Text", "fail");
			}
			return false;
		}

		public boolean verifyExactAttribute(WebElement ele, String attribute, String value) {
			try {
				if (ele.getAttribute(attribute).equals(value)) {
					logStep("The expected attribute :" + attribute + " value matches the actual " + value, "pass");
					return true;
				} else {
					logStep("The expected attribute :" + attribute + " value does not matches the actual " + value, "fail");
				}
			} catch (WebDriverException e) {
				logStep("Unknown exception occured while verifying the Attribute Text", "fail");
			}
			return false;
		}

		public void verifyPartialAttribute(WebElement ele, String attribute, String value) {
			try {
				if (ele.getAttribute(attribute).equals(value)) {
					logStep("The expected attribute :" + attribute + " value contains the partial " + value, "pass");
				} else {
					logStep("The expected attribute :" + attribute + " value does not contains the partial " + value,
							"fail");
				}
			} catch (WebDriverException e) {
				logStep("Unknown exception occured while verifying the Attribute Text", "fail");
			}

		}

		public boolean verifyDisplayed(WebElement ele) {
			try {
				if (ele.isDisplayed()) {
					logStep("The element " + ele + " is visible", "pass");
					return true;
				} else {
					logStep("The element " + ele + " is not visible", "fail");
				}
			} catch (WebDriverException e) {
				logStep("WebDriverException : " + e.getMessage(), "fail");
			}
			return false;
		}

		public boolean verifyDisappeared(WebElement ele) {
			try {
				if (new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOf(ele))) {
					return true;
				} else {
					logStep("TimeoutException occured", "fail");
				}
			} catch (TimeoutException e) {
				logStep("TimeoutException occured", "fail");
			}
			return false;
		}

		public boolean verifyEnabled(WebElement ele) {
			try {
				if (ele.isEnabled()) {
					logStep("The element " + ele + " is enabled", "pass");
					return true;
				} else {
					logStep("The element " + ele + " is not enabled", "fail");
				}
			} catch (WebDriverException e) {
				logStep("WebDriverException : " + e.getMessage(), "fail");
			}
			return false;
		}

		public boolean verifySelected(WebElement ele) {
			try {
				if (ele.isSelected()) {
					logStep("The element " + ele + " is selected", "pass");
					return true;
				} else {
					logStep("The element " + ele + " is not selected", "fail");
				}
			} catch (WebDriverException e) {
				logStep("WebDriverException : " + e.getMessage(), "fail");
			}
			return false;
		}

		public void scrollPage() {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,1000)");
		}

		public void mouseover(WebElement ele1, WebElement ele2) {
			Actions act = new Actions(driver);
			act.moveToElement(ele1).moveToElement(ele2).click().build().perform();
		}
	}

