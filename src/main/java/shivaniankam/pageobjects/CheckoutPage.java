package shivaniankam.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import shivaniankam.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(css="[placeholder='Select Country']")
	private WebElement country;
	
	@FindBy(css=".action__submit")
	private WebElement submit;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	private WebElement selectIndia;
	
	By results = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName){
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(results);
		selectIndia.click();
		
	}
	
	public ConfirmationPage submitOrder() {
		
//		submit.click();
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//
//		js.executeScript("arguments[0].click();", submit);
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click()", submit);
		
		
		return new ConfirmationPage(driver);
		
	}
	
	
	

}
