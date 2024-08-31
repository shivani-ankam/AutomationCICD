package shivaniankam.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import shivaniankam.TestComponents.BaseTest;
import shivaniankam.pageobjects.CartPage;
import shivaniankam.pageobjects.CheckoutPage;
import shivaniankam.pageobjects.ConfirmationPage;
import shivaniankam.pageobjects.LandingPage;
import shivaniankam.pageobjects.ProductCatalog;

public class ErrorValidationsTest extends BaseTest {

	/*
	 * We don't require static or main now, We can use TestNG Annotations
	 * 
	 * @Test--Which marks as the test submitOrder is test case
	 */

	@Test(groups= {"ErrorHandlings"}, retryAnalyzer=shivaniankam.TestComponents.Retry.class)
	public void LoginErrorValidation() throws IOException {
		
		landingPage.loginApplication("shvk@gmail.com", "shnk10");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

		

	}
	
	@Test
	public void ProductErrorValidation() throws IOException {
		String productName = "ZARA COAT 3";
		ProductCatalog productCatalog = landingPage.loginApplication("shvank@gmail.com", "shvAnk10");

//		ProductCatalog productCatalog = new ProductCatalog(driver);
		List<WebElement> products = productCatalog.getProductList();

//		productCatalog.getProductByName(productName);
		productCatalog.addProductToCart(productName);
		CartPage cartPage = productCatalog.goToCartPage();
//		CartPage cartPage = new CartPage(driver);
		Boolean match = cartPage.verifyProductDisplay("ZARA COAT 39");
		Assert.assertFalse(match);
		

	}
}
