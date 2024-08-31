package shivaniankam.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import shivaniankam.pageobjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException {

		/*
		 * 1.Maintain global properties file where you store which browser you want to
		 * execute. 2.So for that,In java there is one class for properties--->this
		 * properties class can read the global properties. 3.If you write any file name
		 * with .properties extension then using property class from java able to pass
		 * the file and extract all global parameter values.
		 */

		// Properties class
		Properties prop = new Properties();
		FileInputStream fil = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\shivaniankam\\resources\\GlobalData.properties");
		// to load GlobalData property file. file need to sent as input stream
		prop.load(fil);

		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");

		// Firefox
		if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}

		// Chrome
		else if (browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			if(browserName.contains("headless")) {
			options.addArguments("headless");
			}
			
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));//full screen
		}

		// Edge
		else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
//		

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;

	}
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		
		
		//Read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath),
				StandardCharsets.UTF_8);
		
		//String to HashMap -- need new dependency called Jackson Databind
		
		ObjectMapper mapper = new ObjectMapper();
		List <HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){
	});
		return data;
		
		
		
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//" +testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//" +testCaseName + ".png";
	}

	// Login page is common for all test cases so lets create method for login page
		@BeforeMethod(alwaysRun=true)
		public LandingPage launchApplication() throws IOException {

			driver = initializeDriver();
			landingPage = new LandingPage(driver);
			landingPage.goTo();
			return landingPage;

	}
	
	@AfterMethod(alwaysRun=true)
	public void closeWindow() {
		driver.close();
	}
	

}
