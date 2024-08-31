package shivaniankam.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import shivaniankam.AbstractComponents.AbstractComponent;

import org.openqa.selenium.WebDriver;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver , this);
		
	}
	
	
	
//	WebElement userEmails = driver.findElement(By.id("userEmail"));
	//PageFactory
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement s;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	
	
	public ProductCatalog loginApplication(String email, String password) {
		
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		s.click();
		ProductCatalog productCatalog = new ProductCatalog(driver);
		return productCatalog;
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	}
