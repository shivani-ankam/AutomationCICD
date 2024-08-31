package shivaniankam.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import shivaniankam.TestComponents.BaseTest;
import shivaniankam.pageobjects.CartPage;
import shivaniankam.pageobjects.CheckoutPage;
import shivaniankam.pageobjects.ConfirmationPage;
import shivaniankam.pageobjects.LandingPage;
import shivaniankam.pageobjects.OrderPage;
import shivaniankam.pageobjects.ProductCatalog;

public class SubmitOrderTest extends BaseTest {
	
	String productName = "ZARA COAT 3";

	/*
	 * We don't require static or main now, We can use TestNG Annotations
	 * 
	 * @Test--Which marks as the test submitOrder is test case
	 */

	@Test(dataProvider="getData",groups="Purchases")
	public void submitOrder(HashMap <String,String> input) throws IOException {
		
		ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"), input.get("password"));

//		ProductCatalog productCatalog = new ProductCatalog(driver);
		List<WebElement> products = productCatalog.getProductList();

//		productCatalog.getProductByName(productName);
		productCatalog.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalog.goToCartPage();
//		CartPage cartPage = new CartPage(driver);
		Boolean match = cartPage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		

	}
	
	//Verify ZARA COAT 3 is displaying in Orders page
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest() {
		//ZARA COAT 3
		ProductCatalog productCatalog = landingPage.loginApplication("shvank@gmail.com", "shvAnk10");
		OrderPage orderPage = productCatalog.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
		

	}
	
	
	
	@DataProvider
	public Object[] [] getData() throws IOException {
		
//		HashMap <String,String> map = new HashMap<String,String>();
//		map.put("email", "shvank@gmail.com");
//		map.put("password", "shvAnk10");
//		map.put("productName","ZARA COAT 3" );
//		
//		HashMap <String,String> map1 = new HashMap<String,String>();
//		map1.put("email", "vanishv@gmail.com");
//		map1.put("password", "shvAnk10");
//		map1.put("productName","IPHONE 13 PRO" );
		List <HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\shivaniankam\\data\\PurchaseOrder.json");
		return new Object[] [] {{data.get(0)},{data.get(1)}};
		
	}
//	@DataProvider
//	public Object[] [] getData() {
//		
//		
//		return new Object[] [] {{"shvank@gmail.com","shvAnk10","ZARA COAT 3"},{"vanishv@gmail.com","shvAnk10","IPHONE 13 PRO"}};
//		
//	}
}
