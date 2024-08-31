package shivaniankam.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import shivaniankam.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{
	
	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	

	@FindBy(css = ".hero-primary")
	WebElement confirmMessage;
	
	public String getConfirmMessage() {
		
		CheckoutPage cp = new CheckoutPage(driver);
//		cp.submit.click();
		
		
		return confirmMessage.getText();
	}
	

	
}
 