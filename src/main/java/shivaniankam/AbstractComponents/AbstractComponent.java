package shivaniankam.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import shivaniankam.pageobjects.CartPage;
import shivaniankam.pageobjects.OrderPage;

public class AbstractComponent {
	
	WebDriver driver;
	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver= driver;
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartPage;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderPage;

	public void waitForElementToAppear(By findBy) {
		
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement findBy) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		wait.until(ExpectedConditions.visibilityOf(findBy));
		}
	
	public CartPage goToCartPage() {
		cartPage.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;

	}
	
	public OrderPage goToOrdersPage() {
		orderPage.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
		
	}
	public void waitForElementToDisappear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}

}
