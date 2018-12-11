package Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import helpers.Helpers;
import helpers.ScreenShooter;
import helpers.WebDriverManager;
import pages.PageLogin;
import pages.PageLogon;
import pages.PageReservation;

public class TestOne {
	private WebDriver driver;
	ArrayList<String> tabs;
//	@SuppressWarnings("deprecation")
	@BeforeMethod
	public void setUp() {
//				Ocupar FireFox
//		DesiredCapabilities caps = new DesiredCapabilities();
//		caps.setCapability("marionette", true);
//		System.setProperty("webdriver.gecko.driver", "Drivers/geckodriver.exe");
//		driver = new FirefoxDriver(caps);		
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.manage().window().fullscreen();
//		driver.manage().window().setSize(new Dimension(200,200));//Pantalla completa en navegador
//		driver.manage().window().setPosition(new Point(300,600)); //Cambiar la posición del navegador
		driver.navigate().to("http://newtours.demoaut.com/");
		Helpers helper = new Helpers(driver);
		JavascriptExecutor javaScriptExecutor = (JavascriptExecutor)driver; //Abrir nuevos Tabs creando un objeto de la class JavascriptExecutor 
		String googleWindow = "window.open('https://www.google.com')";
		javaScriptExecutor.executeScript(googleWindow);
		tabs = new ArrayList<String>(driver.getWindowHandles());
		helper.ImplicitlWait();
	}
	@Test
	public void LoginIncorrecto(){
		WebDriverManager.SetWindowSize(driver, "maximize");
		driver.switchTo().window(tabs.get(1)).navigate().to("https://www.youtube.com");//Cambiar de Tabs
		driver.switchTo().window(tabs.get(0));
		PageLogin pageLogin = new PageLogin(driver);
		PageLogon pageLogon = new PageLogon(driver);
		pageLogin.Login("user", "user");
		pageLogon.AssertLogonPage();
	}
	@Test
	public void PruebaDos(){
		WebDriverManager.SetWindowSize(driver, "fullscreen");
		PageLogin pageLogin = new PageLogin(driver);
		PageReservation pageReservation = new PageReservation(driver);
		pageLogin.Login("mercury", "mercury");
		pageReservation.AssertPage();
	}
	@Test
	public void Login() {
		WebDriverManager.SetWindowSize(driver, 400, 400);
		PageLogin pageLogin = new PageLogin(driver);
		PageReservation pageReservation = new PageReservation(driver);
		pageLogin.Login("mercury", "imercury"); //Falla por el usuario
		pageReservation.AssertPage();
	}
	@Test
	public void PruebaTres() {
		PageLogin pageLogin = new PageLogin(driver);
		PageReservation pageReservation = new PageReservation(driver);
		pageLogin.Login("mercury", "mercury");
		pageReservation.AssertPage();
		pageReservation.SelectPassengers(2);
		pageReservation.SelectFromPort(3);
		pageReservation.SelectToPort("London");
	}
	@Test
	public void PruebaCantidadCampos() {
		PageLogin pageLogin = new PageLogin(driver);
		pageLogin.VerifyFields();
	}
	@AfterMethod
	public void tearDown(ITestResult result) {
		if(!result.isSuccess()) {
			ScreenShooter.TakeScreenShot("Error", driver);
		}
//		driver.close();
//		driver.quit();
		driver.switchTo().window(tabs.get(1)).close();
		driver.switchTo().window(tabs.get(0)).close();
		
	}
}